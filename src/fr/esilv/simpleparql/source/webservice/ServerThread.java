package fr.esilv.simpleparql.source.webservice;

import com.google.gson.Gson;
import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import fr.esilv.simpleparql.source.converter.SparqlQueries;
import fr.esilv.simpleparql.source.converter.filter.FilterRegex;
import fr.esilv.simpleparql.source.converter.model.Constants;
import fr.esilv.simpleparql.source.converter.model.ParseElement;
import fr.esilv.simpleparql.source.converter.order.QueryOrdered;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


    ServerThread(int client, Socket socket) {
        this.socket = socket;
        this.client = client;
        address = socket.getInetAddress().getHostAddress();
    }

    public static boolean isJSONValid(String jsonInString) {
        try {
            new Gson().fromJson(jsonInString, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = bufferedReader.readLine()) != null) {
                Request request = new Gson().fromJson(message, Request.class);
                launch(request);
            }
            socket.close();
        } catch (SocketException socketException) {
            if (socketException.getMessage().equals("Connection reset")) {
                logger.debug("#" + client + "," + address + " quit the room.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> splitBases(Request request) {
        ArrayList<String> queries = new ArrayList<>();
        for (String base : request.getBase()) {
            queries.add(String.join("\n", request.getPrefixes()) + " " + request.getQuery());
        }
        return queries;
    }

    private void launch(Request request) {
        ArrayList<String> queries = splitBases(request);
        for (String query : queries) {
            logger.debug(query);
            CharStream codeStream = CharStreams.fromString(query);
            SimplePARQLParser parser = new QueryOrdered(Constants.getTreeOfText(codeStream.toString())).getParser();
            String treeString = Constants.treeToString(parser, parser.query());
            logger.debug(treeString);
            /*
            // générer des triples version 31/05/2017 à 11h30
            SparqlQueries generatedQueries = new SparqlQueries(Constants.getTreeOfText(treeString), new FilterRegex());
            for (ParseElement generatedElement : generatedQueries.getGeneratedQueries()) {
                logger.debug(Constants.treeToString(parser, generatedElement.getQuery()));
            }*/
        }
    }
}
