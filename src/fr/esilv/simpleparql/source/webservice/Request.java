package fr.esilv.simpleparql.source.webservice;

import fr.esilv.simpleparql.source.model.PAGE;

import java.util.ArrayList;

/**
 * Request of the <strong>receving data </strong>from the server.
 * <br>
 * <strong>query:</strong> The query having all the prefixes <br>
 * <strong>query:</strong> The bases the user wants to ask<br>
 * <strong>query:</strong> The timeout of each <strong>SPARQL</strong> query<br>
 */
public class Request {
    private String query;
    private ArrayList<String> bases;
    private String timeout;
    private String page;

    public String getQuery() {
        return query;
    }

    public ArrayList<String> getBases() {
        return bases;
    }

    public String getTimeout() {
        return timeout;
    }

    public PAGE getPage() {
        return PAGE.valueOf(page);
    }

    public String toString() {
        return "page : " + page + "\n" +
                "base : " + bases + "\n" +
                "query : " + query + "\n" +
                "timeout : " + timeout;
    }
}
