package fr.esilv.simpleparql.source.webservice;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Lauch server on port 1234 to create a webservice
 */
public class Server {
    private final static Logger logger = Logger.getLogger(Server.class);
    private static final int PORT = 1234;
    private static int clientID = 0;

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure();
        runServer();
    }

    private static void runServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server up & ready for connections...");
        while (true) {
            Socket client = serverSocket.accept();
            new ServerThread(clientID, client).start();
            logger.debug("#" + clientID + " just connected from " + client.getRemoteSocketAddress().toString());
            clientID++;
        }
    }
}
