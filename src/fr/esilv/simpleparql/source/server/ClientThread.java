package fr.esilv.simpleparql.source.server;

import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.esilv.simpleparql.source.configuration.BaseConfig;
import fr.esilv.simpleparql.source.configuration.QueryConfig;
import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import fr.esilv.simpleparql.source.converter.SparqlQueries;
import fr.esilv.simpleparql.source.jenaresult.TemporaryTrucVariable;
import fr.esilv.simpleparql.source.jenaresult.TemporaryTrucVariables;
import fr.esilv.simpleparql.source.model.*;
import fr.esilv.simpleparql.source.jenaresult.Jena;
import fr.esilv.simpleparql.source.jenaresult.SPARQLResult;
import org.antlr.v4.runtime.*;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Create a thread for each user that connects to the server. This will create a multiclient server that will not be influenced by other clients.
 * <p>
 * <strong> socket</strong> socket of the connected user. <br>
 * <strong> client</strong> ID of the connected user. <br>
 * <strong> address</strong>adress of the connected user. <br>
 * <strong> baseConfig</strong> repos containing the configuration file of each base. <br>
 * <strong> queryConfig</strong>configuration query's file. <br>
 */
public class ClientThread extends Thread {
    private final static Logger logger = Logger.getLogger(ClientThread.class);
    private Socket socket;
    private int client;
    private String address;

    private String baseConfig;
    private QueryConfig queryConfig;

    ClientThread(int client, Socket socket, String baseConfig, QueryConfig queryConfig) {
        this.socket = socket;
        this.client = client;
        this.baseConfig = baseConfig;
        this.queryConfig = queryConfig;
        address = socket.getInetAddress().getHostAddress();
    }

    /**
     * Function that will be looped till the end of this thread
     */
    public void run() {
        try {
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = bufferedReader.readLine();
            Request request = new Gson().fromJson(message, Request.class);
            JsonArray result = launch(request);
            // begin debug
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            logger.debug(gson.toJson(result));
            // end debug
            output.flush();
            output.write(result.toString() + null); // add null to mark the end of the json
            output.flush();
            output.close();
            socket.close();
        } catch (SocketException socketException) {
            if (socketException.getMessage().equals("Connection reset ")) {
                logger.debug("#" + client + "," + address + " has brutally quit the room.");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main function that call Jena results, the converter and the request from the client
     *
     * @param request request of client
     * @return JsonObject that will be send to the client using the websocket. The object is composed of three objects.
     * The first one is a boolean if it was a SimplePARQL query or not the second one (and so the even objects) is the base informations,
     * the second one (and also all the odd objects) is the  SimplePARQL query's result of the base of the previous object.
     * @throws IOException
     * @throws JSONException
     */
    private JsonArray launch(Request request) throws IOException, JSONException {
        CharStream codeStream = CharStreams.fromString(request.getQuery()); // query text
        SimplePARQLParser queryFromUser = Constants.getTreeOfText(codeStream.toString()); // parse query to SimplePARQL tree
        SimplePARQLQuery simplePARQLQuery = new SimplePARQLQuery(queryFromUser, queryConfig.getPredifinedPrefixes()); // clean and pre-proceess tasks
        SimplePARQLParser parser = simplePARQLQuery.getParser();
        String treeString = Constants.treeToString(parser, parser.query());  // prepared tree in text format

        JsonArray result = new JsonArray(); // contains results of all bases
        for (String base : request.getBases()) {
            JsonObject json = new JsonObject(); // contains results of this base

            //is SPARQL field
            json.addProperty("isSPARQL", simplePARQLQuery.isSPARQL());

            //base field
            BaseConfig config = getFile(base);
            addBaseInformations(json, config);

            // results field
            JsonArray jsonElementBase = new JsonArray();
            SparqlQueries sparqlQueries = new SparqlQueries(Constants.getTreeOfText(treeString), config.getFilter(), config.isOptionnal(), queryConfig);

            // travel the SPARQL query geenrated, filtered by the wanted page
            for (SPARQLQueryGenerated generatedElement : sparqlQueries.getGeneratedQueries(request.getPage())) {

                JsonObject jsonElementgeneratedElement = new JsonObject();
                // add the sparql query
                String query = Constants.treeToString(parser, generatedElement.getQuery());
                jsonElementgeneratedElement.addProperty("query", query);
                SPARQLResult SPARQLresult = new Jena().executeSparqlQuery(config.getLink(), query.replace(Constants.CONTAINS_BIF, Constants.JENA_BIF), request.getTimeout());
                // if any error occured in sparqm query's execution,
                // the json element will be deleted and replaced with this error
                if (SPARQLresult.getError() != null) {
                    json = error(SPARQLresult);
                    break;
                } else {
                    TemporaryTrucVariables trucVariables = getVariablesOfTrucs(SPARQLresult, sparqlQueries); //  get temp variables generated in the query
                    // add variables field
                    JsonArray variables = addVariablesTojSON(SPARQLresult, sparqlQueries, simplePARQLQuery);
                    jsonElementgeneratedElement.add("variables", variables);
                    // add all tuples (results)
                    JsonArray results = addResultsToJson(SPARQLresult, trucVariables, simplePARQLQuery);
                    results = sortResults(variables, results); // without sorting result, tuples wont be organized!! ?b could have ?a results for example.
                    jsonElementgeneratedElement.add("results", results);
                    jsonElementBase.add(jsonElementgeneratedElement);
                }
                json.add("result", jsonElementBase);
            }
            result.add(json);
        }
        return result;
    }

    /**
     * Add the base's infomations to the returned json.
     *
     * @param json   object that will be returned to the client
     * @param config configuration structure of the current base
     */
    private void addBaseInformations(JsonObject json, BaseConfig config) {
        JsonObject baseInformations = new JsonObject();
        baseInformations.addProperty("name", config.getName());
        baseInformations.addProperty("link", config.getLink());
        baseInformations.addProperty("api", config.getApi());
        json.add("base", baseInformations);
    }

    /**
     * Send error to the client as json object when there is one.
     *
     * @param SPARQLQuery SPARQLResult structure of the SPARQL query
     */
    private JsonObject error(SPARQLResult SPARQLQuery) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("error", SPARQLQuery.getError());
        return jsonObject;
    }

    /**
     * Get all the query's variables, even those which are not selected.
     *
     * @param SPARQLresult  SPARQLResult object of the queries
     * @param sparqlQueries the generated queries we get from the SparqlQueries object
     * @return TemporaryTrucVariables Object (ArrayList of TemporaryTrucVariable) that contains all the temporary variables (the generated variables the used don't need)
     */
    private TemporaryTrucVariables getVariablesOfTrucs(SPARQLResult SPARQLresult, SparqlQueries sparqlQueries) {
        TemporaryTrucVariables variables = new TemporaryTrucVariables();
        for (int i = 0; i < SPARQLresult.getVariables().size(); i++) {
            String variable = SPARQLresult.getVariables().get(i);
            Truc found = sparqlQueries.find(variable); // get truc from this variable
            if (found != null) {
                variables.add(new TemporaryTrucVariable(found, i, found.getVariablePosition(variable)));
            }
        }
        return variables;
    }

    /**
     * Add the selected variables (or all if it's all) to the results.
     *
     * @param SPARQLQuery   SPARQLResult object of the queries
     * @param sparqlQueries the generated queries we get from the SparqlQueries object
     * @param queryOrdered  the original query reordered, to know if the query is selectAll, and if the variable is a selected one
     * @return JsonArray that will be added to the SPARQLResult transfered in a socket tovthe user
     */
    private JsonArray addVariablesTojSON(SPARQLResult SPARQLQuery, SparqlQueries sparqlQueries, SimplePARQLQuery queryOrdered) {

        ArrayList<String> variables = new ArrayList<>(SPARQLQuery.getVariables());

        // remove temp variables generated for the truc, from the variables list.
        for (Truc truc : sparqlQueries.getSimpleARQLTrucs()) {
            for (String value : truc.getVariables().values()) {
                variables.remove(value.substring(2));
            }
        }

        JsonArray theSelectedVariables = new JsonArray();
        // add trucs to variables, with other informations about them
        for (Truc truc : sparqlQueries.getSimpleARQLTrucs()) {
            JsonObject trucTitle = new JsonObject();
            trucTitle.addProperty("name", truc.getCleanedName());
            trucTitle.addProperty("lang", truc.getLanguage());
            trucTitle.addProperty("type", truc.getType().toString());
            theSelectedVariables.add(trucTitle);
        }
        // add selected variables
        for (String variable : variables) {
            if (queryOrdered.isSelectAll() || queryOrdered.getSelectedVariables().contains(variable)) {
                theSelectedVariables.add(new JsonPrimitive(variable));
            }
        }
        return theSelectedVariables;
    }

    /**
     * Json array containing the detailed result of one SPARQL query.
     *
     * @param SPARQLQuery   SPARQLResult object of the queries
     * @param trucVariables the temporary variables generated to convert SimplePARQL to SPARQL
     * @return JSON Array to be added to the Json SPARQLResult
     */
    private JsonArray addResultsToJson(SPARQLResult SPARQLQuery, TemporaryTrucVariables trucVariables, SimplePARQLQuery queryOrdered) {
        JsonArray results = new JsonArray();
        JsonArray tuple;
        for (int i = 0; i < SPARQLQuery.getResponses().size(); i++) {
            tuple = new JsonArray();
            for (int j = 0; j < SPARQLQuery.getVariables().size(); j++) {
                JsonObject jsonObject = new JsonObject();
                if (trucVariables.contains(j)) { // we know it's a temporary Truc variable.
                    JsonArray trucResult;
                    TemporaryTrucVariable temporaryTrucVariable = trucVariables.getTemporaryTrucVariable(j);
                    if (temporaryTrucVariable.isMajor()) { // première variable de ce "truc"
                        trucResult = new JsonArray();
                        JsonObject jsonElementTruc = new JsonObject();
                        jsonElementTruc.addProperty("Variable", temporaryTrucVariable.getTruc().getCleanedName());
                        trucResult.add(jsonElementTruc);
                        tuple.add(trucResult);
                    }
                    trucResult = tuple.get(temporaryTrucVariable.getIndexOfFirstInVariablesList()).getAsJsonArray();
                    jsonObject.addProperty("SPARQLResult", SPARQLQuery.getResponses().get(i).get(j));
                    jsonObject.addProperty("Position", temporaryTrucVariable.getPosition());
                    trucResult.add(jsonObject);
                } else {
                    if (queryOrdered.isSelectAll() || queryOrdered.getSelectedVariables().contains(SPARQLQuery.getVariables().get(j))) {
                        jsonObject.addProperty("Variable", SPARQLQuery.getVariables().get(j));
                        jsonObject.addProperty("SPARQLResult", SPARQLQuery.getResponses().get(i).get(j));
                        tuple.add(jsonObject);
                    }
                }
            }
            results.add(tuple);
        }
        return results;
    }

    /**
     * Sort results of the json array, to be in the same order of the selected variables (or trucs) .
     *
     * @param variables query selected variables, including all trucs.
     * @param results   old results : JsonArray
     * @return sorted JsonArray jenaresult
     */
    private JsonArray sortResults(JsonArray variables, JsonArray results) throws JSONException {
        JsonArray newResults = new JsonArray();
        for (int i = 0; i < results.size(); i++) {
            JsonArray newOne = new JsonArray();
            JsonArray current = (JsonArray) results.get(i);
            for (int j = 0; j < current.size(); j++) {
                if (variables.get(j) instanceof JsonObject) {
                    JsonObject tmp = ((JsonObject) variables.get(j));
                    newOne.add(getResultElement(current, tmp.get("name").getAsString()));
                } else {
                    newOne.add(getResultElement(current, variables.get(j).getAsString()));
                }
            }
            newResults.add(newOne);
        }
        return newResults;
    }

    /**
     * Get the variable's result from each tuple of the array.
     *
     * @param array        the tuples array (the array containing one jenaresult of all variables)
     * @param variableName name of the variable
     * @return the json object of the array, that variable matches with variableName.
     * @throws JSONException
     */
    private JsonElement getResultElement(JsonArray array, String variableName) throws JSONException {
        for (JsonElement element : array) {
            if (element instanceof JsonArray) {
                JSONObject json = new JSONObject(((JsonArray) element).get(0).toString());
                if (json.getString("Variable").equals(variableName)) {
                    return element;
                }
            } else {
                JSONObject json = new JSONObject(element.toString());
                if (json.getString("Variable").equals(variableName)) {
                    return element;
                }
            }
        }
        return null;
    }

    /**
     * Create a BaseConfig object from a configuration file
     *
     * @param base the base in it's string format
     * @return BaseConfig object that contains all informations about this base
     * @throws IOException
     */
    private BaseConfig getFile(String base) throws IOException {
        File file = new File(baseConfig);
        if (file.listFiles() != null) {
            for (File f : file.listFiles()) {
                if (base.toLowerCase().equals(f.getName().substring(0, f.getName().lastIndexOf(".")))) {
                    return new BaseConfig(f.getAbsolutePath());
                }
            }
        }
        return null;
    }
}
