package fr.esilv.simpleparql.source.webservice;

import fr.esilv.simpleparql.source.configuration.ServerConfig;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Lauch Java server to create a webservice. <br>
 *
 * <strong> cliendID:</strong> The query having all the prefixes, select é where composants<br>
 * <strong> ServerConfig:</strong> Configuration of the server from the Server.config file beside this file.
 * The user can choose a personalized configuration file which he'll give as a parametere.
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

    /**
     * Run the server without an exit statement. Each client which connect to the server, will have a ClientThread
     * for him that will handle receving informations, converting it and send it back to the client.
     * @throws IOException
     */
    private static void runServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverConfig.getPort());
        logger.debug("Server up & ready for connections...");
        while (true) {
            Socket client = serverSocket.accept();
            new ClientThread(clientID, client, serverConfig.getBasesConfig(), serverConfig.getQueryConfig()).start();
            logger.debug("#" + clientID + " just connected from " + client.getRemoteSocketAddress().toString());
            clientID++;
        }
    }
}
