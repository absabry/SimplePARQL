package fr.esilv.simpleparql.source.webservice;

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
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Create a thread for each user that connects to the server. This will create a multiclient server that will not be influenced by other clients.
 *
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
            JsonObject result = launch(request);
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
     * @return JsonObject that will be send to the client using the websocket. The first object (and so the even objects) is the base informations, the second
     * one (and also all the odd objects) is the  SimplePARQL query's result of the base of the previous object.
     * @throws IOException
     * @throws JSONException
     */
    private JsonObject launch(Request request) throws IOException, JSONException {
        CharStream codeStream = CharStreams.fromString(request.getQuery());
        SimplePARQLParser queryFromUser = Constants.getTreeOfText(codeStream.toString());
        SimplePARQLQuery simplePARQLQuery = new SimplePARQLQuery(queryFromUser, queryConfig.getPredifinedPrefixes());
        SimplePARQLParser parser = simplePARQLQuery.getParser();
        String treeString = Constants.treeToString(parser, parser.query());

        JsonObject json = new JsonObject();
        for (String base : request.getBases()) {
            BaseConfig config = getFile(base);
            addBaseInformations(json, config);

            JsonArray jsonElementBase = new JsonArray();
            SparqlQueries sparqlQueries = new SparqlQueries(Constants.getTreeOfText(treeString), config.getFilter(), config.isOptionnal(), queryConfig);


            for (SPARQLQueryGenerated generatedElement : sparqlQueries.getGeneratedQueries(request.getPage())) {
                JsonObject jsonElementgeneratedElement = new JsonObject();
                String query = Constants.treeToString(parser, generatedElement.getQuery());
                jsonElementgeneratedElement.addProperty("query", query);
                SPARQLResult SPARQLresult = new Jena().executeSparqlQuery(config.getLink(), query.replace(Constants.CONTAINS_BIF, Constants.JENA_BIF), request.getTimeout());
                if (SPARQLresult.getError() != null) {
                    return sendError(SPARQLresult);
                } else {
                    TemporaryTrucVariables trucVariables = getVariablesOfTrucs(SPARQLresult, sparqlQueries);
                    JsonArray theSelectedVariables = addVariablesTojSON(SPARQLresult, sparqlQueries, simplePARQLQuery);
                    jsonElementgeneratedElement.add("variables", theSelectedVariables);
                    JsonArray results = addResultsToJson(SPARQLresult, trucVariables, simplePARQLQuery);
                    results = sortResults(theSelectedVariables, results);
                    jsonElementgeneratedElement.add("results", results);
                    jsonElementBase.add(jsonElementgeneratedElement);
                }
                json.add("result", jsonElementBase);
            }
        }
        return json;
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
    private JsonObject sendError(SPARQLResult SPARQLQuery) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("error", SPARQLQuery.getError());
        return jsonObject;
    }

    /**
     * Get all the query's variables, even those which are not selected.
     *
     * @param SPARQLresult      SPARQLResult object of the queries
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
     * @param SPARQLQuery      SPARQLResult object of the queries
     * @param sparqlQueries the generated queries we get from the SparqlQueries object
     * @param queryOrdered     the original query reordered, to know if the query is selectAll, and if the variable is a selected one
     * @return JsonArray that will be added to the SPARQLResult transfered in a socket tovthe user
     */
    private JsonArray addVariablesTojSON(SPARQLResult SPARQLQuery, SparqlQueries sparqlQueries, SimplePARQLQuery queryOrdered) {

        ArrayList<String> variables = new ArrayList<>(SPARQLQuery.getVariables());
        for (Truc truc : sparqlQueries.getSimpleARQLTrucs()) {
            for (String value : truc.getVariables().values()) {
                variables.remove(value.substring(2));
            }
        }
        JsonArray theSelectedVariables = new JsonArray();
        for (int i = 0; i < sparqlQueries.getSimpleARQLTrucs().size(); i++) {
            theSelectedVariables.add(new JsonPrimitive(sparqlQueries.getSimpleARQLTrucs().get(i).getCleanedName()));
        }
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
        JsonArray elementOfArray;
        for (int i = 0; i < SPARQLQuery.getResponses().size(); i++) {
            elementOfArray = new JsonArray();
            for (int j = 0; j < SPARQLQuery.getVariables().size(); j++) {
                JsonObject jsonObject = new JsonObject();
                if (trucVariables.contains(j)) { // we know it's a temporary Truc variable.
                    JsonArray jsonArray;
                    TemporaryTrucVariable tmp = trucVariables.getTemporaryTrucVariable(j);
                    if (tmp.isMajor()) { // première variable de ce "truc"
                        jsonArray = new JsonArray();
                        JsonObject jsonElementTruc = new JsonObject();
                        jsonElementTruc.addProperty("Variable", tmp.getTruc().getCleanedName());
                        jsonArray.add(jsonElementTruc);
                        elementOfArray.add(jsonArray);
                    }
                    jsonArray = elementOfArray.get(tmp.getIndexOfFirstInVariablesList()).getAsJsonArray();
                    jsonObject.addProperty("SPARQLResult", SPARQLQuery.getResponses().get(i).get(j));
                    jsonObject.addProperty("Position", tmp.getPosition());
                    jsonArray.add(jsonObject);
                } else {
                    if (queryOrdered.isSelectAll() || queryOrdered.getSelectedVariables().contains(SPARQLQuery.getVariables().get(j))) {
                        jsonObject.addProperty("Variable", SPARQLQuery.getVariables().get(j));
                        jsonObject.addProperty("SPARQLResult", SPARQLQuery.getResponses().get(i).get(j));
                        elementOfArray.add(jsonObject);
                    }
                }
            }
            results.add(elementOfArray);
        }
        return results;
    }

    /**
     * Sort results of the json array, to be in the same order of the selected variables (or trucs) .
     *
     * @param results old results : JsonArray
     * @return sorted JsonArray jenaresult
     */
    private JsonArray sortResults(JsonArray variables, JsonArray results) throws JSONException {
        JsonArray newResults = new JsonArray();
        for (int i = 0; i < results.size(); i++) {
            JsonArray newOne = new JsonArray();
            JsonArray current = (JsonArray) results.get(i);
            for (int j = 0; j < current.size(); j++) {
                newOne.add(getResultElement(current, variables.get(j).getAsString()));
            }
            newResults.add(newOne);
        }
        return newResults;
    }

    /**
     * Get the variable's result from the each tuple of the array.
     *
     * @param array        the results array (the array containing one jenaresult of all variables)
     * @param variableName name of the variable
     * @return the json object of the array, that variable matches with variableName.
     * @throws JSONException
     */
    private static JsonElement getResultElement(JsonArray array, String variableName) throws JSONException {
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
        for (File f : file.listFiles()) {
            if (base.toLowerCase().equals(f.getName().substring(0, f.getName().lastIndexOf(".")))) {
                return new BaseConfig(f.getAbsolutePath());
            }
        }
        return null;
    }
}
