package fr.esilv.simpleparql.webservice;

import fr.esilv.simpleparql.configuration.ServerConfig;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Lauch server on port 1234 to create a webservice
 */
public class Server {
    private final static Logger logger = Logger.getLogger(Server.class);
    private static int clientID = 0;
    private static ServerConfig serverConfig;

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure();

        if (args.length != 0) {
            serverConfig = new ServerConfig(new FileInputStream(args[0]));
        } else {
            serverConfig = new ServerConfig(Server.class.getResourceAsStream("Server.config"));
        }
        runServer();
    }

    private static void runServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverConfig.getPort());
        logger.debug("Server up & ready for connections...");
        while (true) {
            Socket client = serverSocket.accept();
            new ServerThread(clientID, client, serverConfig.getBasesConfig(), serverConfig.getIgnored()).start();
            logger.debug("#" + clientID + " just connected from " + client.getRemoteSocketAddress().toString());
            clientID++;
        }
    }
}
