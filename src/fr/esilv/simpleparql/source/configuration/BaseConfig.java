package fr.esilv.simpleparql.source.configuration;


import fr.esilv.simpleparql.source.converter.filter.FilterGenerator;
import fr.esilv.simpleparql.source.converter.filter.FilterDefault;
import fr.esilv.simpleparql.source.converter.filter.FilterRegex;
import fr.esilv.simpleparql.source.converter.filter.FilterVirtuoso;

import java.io.*;

/**
 * Configuration file of each base. <br>
 *
 *  <strong>name:</strong>  name of the base. <br>
 *  <strong>link:</strong> The link of the base.<br>
 *  <strong>filter:</strong> The filter he want to use, if it's virtuoso it should be filter virtuoso for example. <br>
 *  <strong>optionnal:</strong> Include the optionnal conversion or not, when the trucs is in this composant.  <br>
 *  <strong>api:</strong> The link to redirect the user to the base's api in the web interface (usally begins with link field).<br>
 */
public class BaseConfig {

    private String name;
    private String status;
    private String link;
    private String plateforme;
    private String filter;
    private boolean optionnal;
    private String api;


    public BaseConfig(String base) throws IOException {
        readBase(new FileInputStream(base));
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getLink() {
        return link;
    }

    public String getPlateforme() {
        return plateforme;
    }

    public FilterGenerator getFilter() {
        switch (filter) {
            case "REGEX":
                return new FilterRegex();
            case "VIRTUOSO":
                return new FilterVirtuoso();
            case "DEFAULT":
                return new FilterDefault();
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

    /**
     * Read file and get each composant of base's configuration
     * @param file configuration file
     * @throws IOException
     */
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
                    name = "";
                    for(int i=1;i<line.length;i++){
                        name += " " + line[i];
                    }
                    break;
                case "status":
                    status = line[1];
                    break;
                case "link":
                    link = line[1];
                    break;
                case "plateforme":
                    plateforme = line[1];
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
