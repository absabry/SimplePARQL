package fr.esilv.simpleparql.source.configuration;

import java.io.*;

import org.apache.log4j.Level;

/**
 * Configuration file of the server between the client and the server. <br>
 * <p>
 * <strong>port:</strong>  Port used to communicate between the client and the server. <br>
 * <strong>basesConfig:</strong> The repository containing the configuration files of each base. <br>
 * <strong>queryConfig:</strong> The configuration file of the query. <br>
 */
public class ServerConfig {
    private int port;
    private String basesConfig;
    private QueryConfig queryConfig;
    private Level level;
    private String stats;

    public ServerConfig(InputStream file) throws IOException {
        readFile(file);
    }

    public int getPort() {
        return port;
    }

    public String getBasesConfig() {
        return basesConfig;
    }

    public QueryConfig getQueryConfig() {
        return queryConfig;
    }

    public Level getLevel() {
        return level;
    }

    public String getStats(){
        return stats;
    }

    private Level convertToLevel(String level) {
        switch (level) {
            case "ERROR":
                return Level.ERROR;
            case "DEBUG":
                return Level.DEBUG;
            case "INFO":
                return Level.INFO;
            case "WARNING":
                return Level.WARN;
            default:
                return null;
        }
    }

    /**
     * Read file and get each composant of server's configuration
     *
     * @param file configuration file
     * @throws IOException
     */
    private void readFile(InputStream file) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            String[] line = strLine.split("\\s+");
            switch (line[0].toLowerCase()) {
                case "#":
                    break;
                case "basesconfig":
                    basesConfig = line[1];
                    break;
                case "port":
                    port = Integer.parseInt(line[1]);
                    break;
                case "queryconfig":
                    queryConfig = new QueryConfig(new FileInputStream(line[1]));
                    break;
                case "level":
                    level = convertToLevel(line[1]);
                    break;
                case "statslog":
                    stats = line[1];
                    break;
                default:
                    break;
            }
        }
        br.close();
    }
}
