package fr.esilv.simpleparql.webservice;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Request of the receving data from the server
 */
public class Request {
    private String query;
    private ArrayList<String> bases;
    private ArrayList<String> prefixes;
    private String resultedQuery;

    public String getQuery() {
        return query;
    }

    /**
     * Add PREFIX to all the prefixes
     *
     * @return edited list, having PREFIX in the begin of al prefixes
     */
    public ArrayList<String> getPrefixes() {
        ArrayList<String> temp = new ArrayList<>();
        for (String prefixe : prefixes) {
            temp.add("PREFIX " + prefixe);
        }
        return temp;
    }

    public ArrayList<String> getBases() {
        return bases;
    }

    public String getResultedQuery() {
        if (getPrefixes() != null) {
            return String.join("\n", getPrefixes()) + query;   // after adding the PREFIX before prefixes
        } else {
            return query;
        }
    }

    public String toString() {
        return "base : " + bases + "\n" +
                "prefixes : " + prefixes + "\n" +
                "query : " + query;
    }
}
