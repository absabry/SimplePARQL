package fr.esilv.simpleparql.source.webservice;

import java.util.ArrayList;

/**
 * Request of the receving data from the server
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
