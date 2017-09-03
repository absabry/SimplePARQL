package fr.esilv.simpleparql.source.configuration;

import java.io.*;

/**
 * Configuration file of the server between the client and the server. <br>
 *
 *  <strong>port:</strong>  Port used to communicate between the client and the server. <br>
 *  <strong>basesConfig:</strong> The repository containing the configuration files of each base. <br>
 *  <strong>queryConfig:</strong> The configuration file of the query. <br>
 */
public class ServerConfig {
    private int port;
    private String basesConfig;
    private QueryConfig queryConfig;

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

    /**
     * Read file and get each composant of server's configuration
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
                default:
                    break;
            }
        }
        br.close();
    }
}
