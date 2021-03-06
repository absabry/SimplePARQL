package fr.esilv.simpleparql.source.server;

import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import fr.esilv.simpleparql.source.configuration.BaseConfig;
import fr.esilv.simpleparql.source.configuration.QueryConfig;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private Logger logger;
    private Socket socket;
    private int client;
    private String address;

    private String baseConfig;
    private QueryConfig queryConfig;
    private BufferedWriter stats;

    ClientThread(int client, Socket socket, String baseConfig, QueryConfig queryConfig, BufferedWriter stats) {
        this.socket = socket;
        this.client = client;
        this.baseConfig = baseConfig;
        this.queryConfig = queryConfig;
        address = socket.getInetAddress().getHostAddress();
        logger = Server.logger;
        this.stats = stats;
    }

    /**
     * Function that will be looped till the end of this thread
     */
    public void run() {
        try {
            /* OLDONE
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = bufferedReader.readLine();
            Request request = new Gson().fromJson(message, Request.class);
            JsonArray result = launch(request);
            logger.debug("Here's the SimplePARQL query: ");
            logger.debug(request.getQuery());
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            logger.debug(gson.toJson(result));
            output.flush();
            output.write(result.toString() + null); // add null to mark the end of the json
            output.flush();
            output.close();
            socket.close();
            */
            /* NEWONE */
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = bufferedReader.readLine();
            Request request = new Gson().fromJson(message, Request.class);
            logger.debug("Here's the SimplePARQL query: ");
            logger.debug(request.getQuery());
            launch(request, output);
            sendToWebService("null", "END", output);
            output.close();
            socket.close();
        } catch (SocketException socketException) {
            if (socketException.getMessage().equals("Connection reset ")) {
                logger.info("#" + client + "," + address + " has brutally quit the room.");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main function that call Jena results, the converter and the request from the client. In the end the
     * jsonObject that will be sent to the client using the websocket. The object is composed of three objects.
     * the second one (and also all the odd objects) is the  SimplePARQL query's result of the base of the previous object.
     * All of this, will be produced for every base.
     *
     * @param request request of the client
     * @param output  writer message to the client
     *                The first one is a boolean if it was a SimplePARQL query or not the second one (and so the even objects) is the base informations
     * @throws IOException
     * @throws JSONException
     */
    private void launch(Request request, PrintWriter output) throws IOException, JSONException {
        //try {
            CharStream codeStream = CharStreams.fromString(request.getQuery()); // query text
            SimplePARQLParser queryFromUser = Constants.getTreeOfText(codeStream.toString()); // parse query to SimplePARQL tree
            SimplePARQLQuery simplePARQLQuery = new SimplePARQLQuery(queryFromUser, queryConfig.getPredifinedPrefixes()); // clean and pre-proceess tasks
            SimplePARQLParser parser = simplePARQLQuery.getParser();
            String treeString = Constants.treeToString(parser, parser.query());  // prepared tree in text format

            //JsonArray result = new JsonArray(); // contains results of all bases
            boolean error = false;
            for (String base : request.getBases()) {
                BaseConfig config = getBaseConfigurationFile(base);

                if (config == null) {
                    JsonObject temp = new JsonObject();
                    temp.addProperty("error", "Can't reach " + base + " configuration file.");
                    sendToWebService(temp.toString() + "Eend", "Error", output);
                    continue;
                }

                // send informations to the client directly!
                JsonObject temp = new JsonObject();
                temp.addProperty("isSPARQL", simplePARQLQuery.isSPARQL());
                addBaseInformations(temp, config);
                sendToWebService(temp.toString() + "Iend", "Informations", output);

                SparqlQueries sparqlQueries = new SparqlQueries(Constants.getTreeOfText(treeString), config.getFilter(), config.isOptionnal(), queryConfig);
                logger.debug("Here are the generated sparql trucs and queries");
                logger.debug(sparqlQueries.getSimpleARQLTrucs());
                logger.debug(sparqlQueries.getGeneratedQueries());

                // travel the SPARQL query geenrated, filtered by the wanted page
                for (SPARQLQueryGenerated generatedElement : sparqlQueries.getGeneratedQueries(request.getPage())) {
                    JsonObject jsonElementgeneratedElement = new JsonObject();
                    // add the sparql query
                    String query = Constants.treeToString(parser, generatedElement.getQuery());
                    jsonElementgeneratedElement.addProperty("query", query);
                    SPARQLResult SPARQLresult = new Jena().executeSparqlQuery(config.getLink(), query.replace(Constants.CONTAINS_BIF, Constants.JENA_BIF), request.getTimeout());
                    // if any error occured in sparql query's execution,
                    // the json element will be deleted and replaced with this error
                    if (SPARQLresult.getError() != null) {
                        temp = error(SPARQLresult, config.getName());
                        sendToWebService(temp.toString() + "Eend", "Error", output);
                        error = true;
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
                    }
                    sendToWebService(jsonElementgeneratedElement.toString() + "Rend", "Query Result", output);
                }
            }
            if (!error && request.getPage() == PAGE.FIRST) {
                // on essaye de generer la requete une seule fois par utilisatoin (qui est donc quand il demande la premiere page seulement)
                updateLogFile(request);
            }
/*        }
        catch (Exception e){
            logger.debug(e);
            JsonObject temp = new JsonObject();
            temp.addProperty("error", "An error occuered while handilng your query:"+e.getMessage());
            sendToWebService(temp.toString() + "Eend", "Error", output);
        }*/
    }

    /**
     * Send text to the web service
     *
     * @param text   text to be sent to the client
     * @param type   type of this text, can be error, informations or query results.
     * @param output writer to the web service
     */
    private void sendToWebService(String text, String type, PrintWriter output) {
        output.flush();
        output.write(text);
        output.flush();
        logger.debug("Here's the message we sent to the webservice [" + type + "]");
        logger.debug(text);
    }

   /**
     * update the log file, used to stock the simple aprql queries exectued from all users.
     *
     * @param request request  got from the client
     */
    private void updateLogFile(Request request) throws IOException {
        stats.newLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        stats.append(dateFormat.format(new Date())); // now in the pattern format
        stats.newLine();
        stats.append(request.getQuery());
        stats.newLine();
        stats.flush();
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
        baseInformations.addProperty("name", config.getName());
        baseInformations.addProperty("plateforme", config.getPlateforme());
        baseInformations.addProperty("link", config.getLink());
        baseInformations.addProperty("api", config.getApi());
        json.add("base", baseInformations);
    }

    /**
     * Send error to the client as json object when there is one.
     *
     * @param SPARQLQuery SPARQLResult structure of the SPARQL query
     */
    private JsonObject error(SPARQLResult SPARQLQuery, String base) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("error", "[Endpoint" + base + "] " + SPARQLQuery.getError());
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
                        JsonObject variableObject = new JsonObject();
                        variableObject.addProperty("Variable", temporaryTrucVariable.getTruc().getCleanedName());
                        trucResult.add(variableObject);
                        tuple.add(trucResult);
                    }
                    trucResult = getTrucArrayFromTuple(tuple, temporaryTrucVariable.getTruc().getCleanedName()); //  get the array of this truc from the tuple
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
     * Get the JSON array of this truc, from the tuple JSON array.
     *
     * @param tuple    tuples in jsona array format
     * @param variable truc's name in the tuple
     * @return truc array belonging to the truc named "variable"
     */
    private JsonArray getTrucArrayFromTuple(JsonArray tuple, String variable) {
        for (int i = 0; i < tuple.size(); i++) {
            if (tuple.get(i) instanceof JsonArray) { // truc in the tuple will always be an array
                JsonArray currentTrucArray = tuple.get(i).getAsJsonArray();
                String thisTrucName = currentTrucArray.get(0).getAsJsonObject().get("Variable").getAsString(); // get the truc name in the first object of the array
                if (thisTrucName.equals(variable))
                    return currentTrucArray;
            }
        }
        return null;
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
    private BaseConfig getBaseConfigurationFile(String base) throws IOException {
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
