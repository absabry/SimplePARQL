package fr.esilv.simpleparql.webservice;

import com.google.gson.*;
import fr.esilv.simpleparql.configuration.BaseConfig;
import fr.esilv.simpleparql.configuration.IgnoredConfig;
import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import fr.esilv.simpleparql.source.converter.SparqlQueries;
import fr.esilv.simpleparql.source.model.*;
import fr.esilv.simpleparql.source.response.MainResponse;
import fr.esilv.simpleparql.source.response.Result;
import fr.esilv.simpleparql.source.response.Triplet;
import fr.esilv.simpleparql.source.response.Triplets;
import javafx.util.Pair;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            while ((message = bufferedReader.readLine()) != null) {
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


    // TODO dans une autre classe, en utilsiant des sockets à la place de websockets

    private void launch(Request request, PrintWriter output) throws IOException {
        CharStream codeStream = CharStreams.fromString(request.getResultedQuery());
        QueryOrdered queryOrdered = new QueryOrdered(Constants.getTreeOfText(codeStream.toString()));
        SimplePARQLParser parser = queryOrdered.getParser();
        String treeString = Constants.treeToString(parser, parser.query());
        ArrayList<String> selectedVariables = queryOrdered.getSelectedVariables();

        JsonObject json = new JsonObject();
        for (String base : request.getBases()) {
            // TODO créer un thread pour chaque base
            BaseConfig config = getFile(base);

            JsonArray current = new JsonArray();

            //générer des triples version 31/05/2017 à 11h30
            SparqlQueries generatedQueries = new SparqlQueries(Constants.getTreeOfText(treeString), config.getFilter(), PAGE.FIRST, config.getOptionnal(), ignoredConfig);
            for (ParseElement generatedElement : generatedQueries.getGeneratedQueries()) {
                JsonObject item = new JsonObject();
                String query = Constants.treeToString(parser, generatedElement.getQuery());
                item.addProperty("Query", query);


                Result result = new MainResponse().executeSparql(config.getLink(), query);
                JsonArray theSelectedVariables = new JsonArray();
                Triplets variables = new Triplets();

                for (int i = 0; i < result.getVariables().size(); i++) {
                    String variable = result.getVariables().get(i);
                    Truc found = generatedQueries.find(variable);
                    if (found != null) {
                        if (!variables.contains(found)) {
                            theSelectedVariables.add(new JsonPrimitive(found.getCleanedName()));
                        }
                        variables.add(new Triplet(found, i, found.getVariablePosition(variable)));
                    } else {
                        if (queryOrdered.isSelectAll() || selectedVariables.contains(variable)) {
                            theSelectedVariables.add(new JsonPrimitive(variable));
                        }
                    }
                }

                item.add("variables", theSelectedVariables);
                JsonArray results = new JsonArray();
                JsonArray elementOfArray;
                for (int i = 0; i < result.getResponses().size(); i++) {
                    elementOfArray = new JsonArray();
                    for (int j = 0; j < result.getVariables().size(); j++) {
                        JsonObject jsonObject = new JsonObject();
                        if (variables.contains(j)) {
                            JsonArray jsonArray;
                            Triplet tmp = variables.getTriplet(j);
                            if (tmp.isMajor()) {
                                jsonArray = new JsonArray();
                                JsonObject truc = new JsonObject();
                                truc.addProperty("Variable", tmp.getTruc().getCleanedName());
                                jsonArray.add(truc);
                            } else {
                                jsonArray = elementOfArray.get(tmp.getIndexOfFirstInVariables()).getAsJsonArray();
                            }
                            jsonObject.addProperty("Result", result.getResponses().get(i).get(j));
                            jsonObject.addProperty("Position", tmp.getPosition());
                            jsonArray.add(jsonObject);
                            elementOfArray.add(jsonArray);
                        } else {
                            jsonObject.addProperty("Variable", result.getVariables().get(j));
                            jsonObject.addProperty("Result", result.getResponses().get(i).get(j));
                            elementOfArray.add(jsonObject);
                        }
                    }
                    results.add(elementOfArray);
                }
                item.add("Results", results);

                current.add(item);
            }
            json.add(config.getName(), current);

        }
        logger.debug(json.toString());
        output.write(json.toString());
    }

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
