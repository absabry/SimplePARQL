package fr.esilv.simpleparql.configuration;


import java.io.*;
import java.util.ArrayList;

/**
 * Strcuture of the configuration file of the ignored proprieties
 */
public class IgnoredConfig {
    private ArrayList<String> ignoredProprieties;

    public IgnoredConfig(FileInputStream file) throws IOException {
        ignoredProprieties = new ArrayList<>();
        readProprieties(file);
    }

    public ArrayList<String> getIgnoredProprieties() {
        return ignoredProprieties;
    }

    private void readProprieties(InputStream file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            String[] line = strLine.split("\\s+");
            switch (line[0].toLowerCase().trim()) {
                case "#":
                    break;
                case "":
                    break;
                default:
                    ignoredProprieties.add(line[0]);
                    break;
            }
        }
         br.close();
    }
}

