package fr.esilv.simpleparql.source.webservice;

import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.esilv.simpleparql.source.configuration.BaseConfig;
import fr.esilv.simpleparql.source.configuration.QueryConfig;
import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import fr.esilv.simpleparql.source.converter.SparqlQueries;
import fr.esilv.simpleparql.source.model.*;
import fr.esilv.simpleparql.source.result.Jena;
import fr.esilv.simpleparql.source.result.Result;
import fr.esilv.simpleparql.source.result.TrucVariable;
import fr.esilv.simpleparql.source.result.TrucVariables;
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
 * Create a thread for each user that connects to the server.
 * This will create a multiclient server that will not be influenced
 * by other clients.
 */
public class ServerThread extends Thread {
    private final static Logger logger = Logger.getLogger(ServerThread.class);
    private Socket socket;
    private int client;
    private String address;

    private final PAGE PAGECHOOSEN = PAGE.THIRD;

    private String baseConfig;
    private QueryConfig queryConfig;

    ServerThread(int client, Socket socket, String baseConfig, QueryConfig queryConfig) {
        this.socket = socket;
        this.client = client;
        this.baseConfig = baseConfig;
        this.queryConfig = queryConfig;
        address = socket.getInetAddress().getHostAddress();
    }

    /**
     * function that will be looped till the end of this thread
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
     * main function that call Jena results, the converter and the request from the client
     *
     * @param request request of client
     * @return
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
            SparqlQueries generatedQueries = new SparqlQueries(Constants.getTreeOfText(treeString), config.getFilter(), PAGECHOOSEN, config.isOptionnal(), queryConfig);
            for (ParseElement generatedElement : generatedQueries.getGeneratedQueries()) {
                JsonObject jsonElementgeneratedElement = new JsonObject();
                String query = Constants.treeToString(parser, generatedElement.getQuery());
                jsonElementgeneratedElement.addProperty("query", query);
                Result result = new Jena().executeSparql(config.getLink(), query.replace(Constants.CONTAINS_BIF, Constants.JENA_BIF), request.getTimeout());
                if (result.getError() != null) {
                    return sendError(result);
                } else {
                    TrucVariables trucVariables = getVariablesOfTrucs(result, generatedQueries);
                    JsonArray theSelectedVariables = addVariablesTojSON(result, generatedQueries, simplePARQLQuery);
                    jsonElementgeneratedElement.add("variables", theSelectedVariables);
                    JsonArray results = addResultsToJson(result, trucVariables, simplePARQLQuery);
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
     * add the base infomations to the returned json
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
     * send error to the client when there is one.
     *
     * @param result Result structure of the SPARQL query
     */
    private JsonObject sendError(Result result) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("error", result.getError());
        return jsonObject;
    }

    /**
     * get all the variables of the query, even those which are not selected
     *
     * @param result           result object of the queries
     * @param generatedQueries the generated queries we get from the SparqlQueries object
     * @return TrucVariables Object (ArrayList of TrucVariable) that contains all the temporary variables (the generated variables the used don't need)
     */
    private TrucVariables getVariablesOfTrucs(Result result, SparqlQueries generatedQueries) {
        TrucVariables variables = new TrucVariables();
        for (int i = 0; i < result.getVariables().size(); i++) {
            String variable = result.getVariables().get(i);
            Truc found = generatedQueries.find(variable);
            if (found != null) {
                variables.add(new TrucVariable(found, i, found.getVariablePosition(variable)));
            }
        }
        return variables;
    }

    /**
     * add the selected variables (or all if it's all) to the results.
     *
     * @param result           result object of the queries
     * @param generatedQueries the generated queries we get from the SparqlQueries object
     * @param queryOrdered     the original query reordered, to know if the query is selectAll, and if the variable is a selected one
     * @return JsonArray that will be added to the result transfered in a socket tovthe user
     */
    private JsonArray addVariablesTojSON(Result result, SparqlQueries generatedQueries, SimplePARQLQuery queryOrdered) {

        ArrayList<String> variables = new ArrayList<>(result.getVariables());
        for (Truc truc : generatedQueries.getSimpleARQLTrucs()) {
            for (String value : truc.getVariables().values()) {
                variables.remove(value.substring(2));
            }
        }
        JsonArray theSelectedVariables = new JsonArray();
        for (int i = 0; i < generatedQueries.getSimpleARQLTrucs().size(); i++) {
            theSelectedVariables.add(new JsonPrimitive(generatedQueries.getSimpleARQLTrucs().get(i).getCleanedName()));
        }
        for (String variable : variables) {
            if (queryOrdered.isSelectAll() || queryOrdered.getSelectedVariables().contains(variable)) {
                theSelectedVariables.add(new JsonPrimitive(variable));
            }
        }
        return theSelectedVariables;
    }

    /**
     * get a json array to add it to the result that will be tranfered in a sokcet to the user
     *
     * @param result        result object of the queries
     * @param trucVariables the temporary variables generated to convert SimplePARQL to SPARQL
     * @return JSON Array to be added to the Json result
     */
    private JsonArray addResultsToJson(Result result, TrucVariables trucVariables, SimplePARQLQuery queryOrdered) {
        JsonArray results = new JsonArray();
        JsonArray elementOfArray;
        for (int i = 0; i < result.getResponses().size(); i++) {
            elementOfArray = new JsonArray();
            for (int j = 0; j < result.getVariables().size(); j++) {
                JsonObject jsonObject = new JsonObject();
                if (trucVariables.contains(j)) { // on sait que c'est une variable générée.
                    JsonArray jsonArray;
                    TrucVariable tmp = trucVariables.getTriplet(j);
                    if (tmp.isMajor()) { // première variable de ce "truc"
                        jsonArray = new JsonArray();
                        JsonObject jsonElementTruc = new JsonObject();
                        jsonElementTruc.addProperty("Variable", tmp.getTruc().getCleanedName());
                        jsonArray.add(jsonElementTruc);
                        elementOfArray.add(jsonArray);
                    }
                    jsonArray = elementOfArray.get(tmp.getIndexOfFirstInVariables()).getAsJsonArray();
                    jsonObject.addProperty("Result", result.getResponses().get(i).get(j));
                    jsonObject.addProperty("Position", tmp.getPosition());
                    jsonArray.add(jsonObject);
                } else {
                    if (queryOrdered.isSelectAll() || queryOrdered.getSelectedVariables().contains(result.getVariables().get(j))) {
                        jsonObject.addProperty("Variable", result.getVariables().get(j));
                        jsonObject.addProperty("Result", result.getResponses().get(i).get(j));
                        elementOfArray.add(jsonObject);
                    }
                }
            }
            results.add(elementOfArray);
        }
        return results;
    }

    /**
     * Sort results of the json array, to be in the same order of the variables (in the begining of the json object).
     *
     * @param results old results : JsonArray
     * @return sorted JsonArray result
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
     * get the variable's result from the final results json array
     *
     * @param array        the results array (the array containing one result of all variables)
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
