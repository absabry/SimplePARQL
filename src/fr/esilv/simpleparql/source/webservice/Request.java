package fr.esilv.simpleparql.source.webservice;

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

    public String getQuery() {
        return query;
    }

    public ArrayList<String> getBases() {
        return bases;
    }

    public String getTimeout() {
        return timeout;
    }

    public String toString() {
        return "base : " + bases + "\n" +
                "query : " + query + "\n" +
                "timeout : " + timeout;
    }
}
