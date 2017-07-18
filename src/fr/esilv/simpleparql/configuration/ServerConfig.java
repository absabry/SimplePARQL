package fr.esilv.simpleparql.configuration;

import java.io.*;

/**
 * Strcuture of the configuration file of the server between the client and the webservice
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
