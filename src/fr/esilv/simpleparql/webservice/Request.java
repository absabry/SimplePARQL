package fr.esilv.simpleparql.webservice;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Request of the receving data from the server
 */
public class Request {
    private String query;
    private ArrayList<String> bases;

    public String getQuery() {
        return query;
    }

    public ArrayList<String> getBases() {
        return bases;
    }

    public String toString() {
        return "base : " + bases + "\n" +
                "query : " + query;
    }
}
