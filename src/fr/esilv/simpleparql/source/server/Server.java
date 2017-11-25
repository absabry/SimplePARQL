package fr.esilv.simpleparql.source.server;

import fr.esilv.simpleparql.source.configuration.ServerConfig;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Lauch Java server to create a server. <br>
 * <p>
 * <strong> cliendID:</strong> The query having all the prefixes, select é where composants<br>
 * <strong> ServerConfig:</strong> Configuration of the server from the Server.config file beside this file.
 * The user can choose a personalized configuration file which he'll give as a parametere.
 */
public class Server {
    public final static Logger logger = Logger.getLogger(Server.class);
    private static int clientID = 0;
    private static ServerConfig serverConfig;

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure();

        if (args.length != 0) {
            serverConfig = new ServerConfig(new FileInputStream(args[0])); // get the server configuration file from parameter
        } else {
            serverConfig = new ServerConfig(Server.class.getResourceAsStream("Server.config")); //configuration file by default
        }
        Logger.getRootLogger().setLevel(serverConfig.getLevel());
        runServer();
    }

    /**
     * Run the server without an exit statement. Each client which connect to the server, will have a ClientThread
     * for him that will handle receving informations, converting it and send it back to the client.
     *
     * @throws IOException
     */
    private static void runServer() throws IOException {
        BufferedWriter stats = null;
        if (fileExists(serverConfig.getStats())) {
            stats = new BufferedWriter(new FileWriter(serverConfig.getStats(), true));
            logger.info(serverConfig.getStats() + " is used as stats logger file.");
        }
        else{
            logger.warn("output logger file: "+serverConfig.getStats()+" don't exists. The SimplePARQL queries won't be saved anywhere...");
        }
        ServerSocket serverSocket = new ServerSocket(serverConfig.getPort());
        logger.debug(serverConfig.getLevel());
        logger.info("Server up & ready for connections...");
        // serveur loop will not stop until admin stop it himself
        while (true) {
            Socket client = serverSocket.accept();
            new ClientThread(clientID, client, serverConfig.getBasesConfig(), serverConfig.getQueryConfig(), stats).start();
            logger.info("#" + clientID + " just connected from " + client.getRemoteSocketAddress().toString());
            clientID++;
        }
    }

    private static boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists() && !file.isDirectory();
    }
}
