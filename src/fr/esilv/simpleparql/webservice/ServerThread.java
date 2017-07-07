package fr.esilv.simpleparql.webservice;

import com.google.gson.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.esilv.simpleparql.configuration.BaseConfig;
import fr.esilv.simpleparql.configuration.IgnoredConfig;
import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import fr.esilv.simpleparql.source.converter.SparqlQueries;
import fr.esilv.simpleparql.source.model.*;
import fr.esilv.simpleparql.source.response.Jena;
import fr.esilv.simpleparql.source.response.Result;
import fr.esilv.simpleparql.source.response.TrucVariable;
import fr.esilv.simpleparql.source.response.TrucVariables;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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

    private String baseConfig;
    private IgnoredConfig ignoredConfig;

    ServerThread(int client, Socket socket, String baseConfig, IgnoredConfig ignoredConfig) {
        this.socket = socket;
        this.client = client;
        this.baseConfig = baseConfig;
        this.ignoredConfig = ignoredConfig;
        address = socket.getInetAddress().getHostAddress();
    }

    public void run() {

        try {
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = bufferedReader.readLine()) != null) {;
                Request request = new Gson().fromJson(message, Request.class);
                output.flush();
                launch(request, output);
                output.flush();

            }
            output.close();
            // detect when the client disconnect from the server
            if (bufferedReader.read() == -1) {
                logger.debug("#" + client + "," + address + " has propely quit the room.");
            }
            socket.close();
        } catch (SocketException socketException) {
            if (socketException.getMessage().equals("Connection reset ")) {
                logger.debug("#" + client + "," + address + " has brutally quit the room.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void launch(Request request, PrintWriter output) throws IOException {
        CharStream codeStream = CharStreams.fromString(request.getResultedQuery());
        QueryOrdered queryOrdered = new QueryOrdered(Constants.getTreeOfText(codeStream.toString()));
        SimplePARQLParser parser = queryOrdered.getParser();
        String treeString = Constants.treeToString(parser, parser.query());

        JsonObject json = new JsonObject();
        for (String base : request.getBases()) {
            // TODO créer un thread pour chaque base
            BaseConfig config = getFile(base);
            //json.add("Base", new JsonPrimitive(config.getName()));
            JsonArray jsonElementBase = new JsonArray();
            SparqlQueries generatedQueries = new SparqlQueries(Constants.getTreeOfText(treeString), config.getFilter(), PAGE.THIRD, config.getOptionnal(), ignoredConfig);
            for (ParseElement generatedElement : generatedQueries.getGeneratedQueries()) {
                JsonObject jsonElementgeneratedElement = new JsonObject();
                String query = Constants.treeToString(parser, generatedElement.getQuery());
                jsonElementgeneratedElement.addProperty("query", query);
                Result result = new Jena().executeSparql(config.getLink(), query.replace(Constants.CONTAINS_BIF, Constants.JENA_BIF));
                if (result.getError() != null) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("error", result.getError());
                    output.write(jsonObject.toString() + null);
                    return;
                } else {
                    TrucVariables trucVariables = getVariablesOfTrucs(result, generatedQueries);
                    JsonArray theSelectedVariables = addVariablesTojSON(result, generatedQueries, queryOrdered);
                    jsonElementgeneratedElement.add("variables", theSelectedVariables);
                    JsonArray results = addResultsToJson(result, trucVariables);
                    jsonElementgeneratedElement.add("results", results);
                    jsonElementBase.add(jsonElementgeneratedElement);
                }
                json.add("base", new JsonPrimitive(config.getName()));
                json.add("response", jsonElementBase);
            }
        }
        output.write(json.toString() + null);
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        logger.debug(gson.toJson(json));
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
    private JsonArray addVariablesTojSON(Result result, SparqlQueries generatedQueries, QueryOrdered queryOrdered) {
        JsonArray theSelectedVariables = new JsonArray();
        for (int i = 0; i < result.getVariables().size(); i++) {
            String variable = result.getVariables().get(i);
            if (queryOrdered.isSelectAll() || queryOrdered.getSelectedVariables().contains(variable)) {
                theSelectedVariables.add(new JsonPrimitive(variable));
            }
        }

        for (int i = 0; i < generatedQueries.getSimpleARQLTrucs().size(); i++) {
            theSelectedVariables.add(new JsonPrimitive(generatedQueries.getSimpleARQLTrucs().get(i).getCleanedName()));
        }

        /*
            logger.debug(variable);
            Truc found = generatedQueries.find(variable);
            if (found != null) {
                logger.debug(1);
                if (!trucVariables.contains(found)) {
                    logger.debug(2);
                    theSelectedVariables.add(new JsonPrimitive(found.getCleanedName()));
                }
            }
         */
        return theSelectedVariables;
    }

    /**
     * get a json array to add it to the result that will be tranfered in a sokcet to the user
     *
     * @param result        result object of the queries
     * @param trucVariables the temporary variables generated to convert SimplePARQL to SPARQL
     * @return JSON Array to be added to the Json result
     */
    private JsonArray addResultsToJson(Result result, TrucVariables trucVariables) {
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
                    jsonObject.addProperty("Variable", result.getVariables().get(j));
                    jsonObject.addProperty("Result", result.getResponses().get(i).get(j));
                    elementOfArray.add(jsonObject);
                }
            }
            results.add(elementOfArray);
        }
        return results;
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
