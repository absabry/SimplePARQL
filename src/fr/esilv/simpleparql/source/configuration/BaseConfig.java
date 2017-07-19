package fr.esilv.simpleparql.source.configuration;


import fr.esilv.simpleparql.source.converter.filter.FilterGenerator;
import fr.esilv.simpleparql.source.converter.filter.FilterNormal;
import fr.esilv.simpleparql.source.converter.filter.FilterRegex;
import fr.esilv.simpleparql.source.converter.filter.FilterVirtuoso;

import java.io.*;

/**
 * Strcuture of the configuration file of the bases
 */
public class BaseConfig {

    private String name;
    private String link;
    private String filter;
    private boolean optionnal;
    private String api;


    public BaseConfig(String base) throws IOException {
        readBase(new FileInputStream(base));
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public FilterGenerator getFilter() {
        switch (filter) {
            case "REGEX":
                return new FilterRegex();
            case "VIRTUOSO":
                return new FilterVirtuoso();
            case "OTHER":
                return new FilterNormal();
            default:
                return null;
        }
    }

    public boolean isOptionnal() {
        return optionnal;
    }

    public String getApi() {
        return api;
    }

    private void readBase(InputStream file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            String[] line = strLine.split("\\s+");
            switch (line[0].toLowerCase()) {
                case "#":
                    break;
                case "name":
                    name = line[1];
                    break;
                case "link":
                    link = line[1];
                    break;
                case "filter":
                    filter = line[1];
                    break;
                case "optionnal":
                    optionnal = Boolean.valueOf(line[1]);
                    break;
                case "api":
                    api = line[1];
                    break;
                default:
                    break;
            }
        }
        br.close();
    }
}
