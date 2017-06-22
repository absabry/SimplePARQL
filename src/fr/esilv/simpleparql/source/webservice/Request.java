package fr.esilv.simpleparql.source.webservice;

import java.util.ArrayList;

/**
 * Request of the receving data from the server
 */
public class Request {
    private String query;
    private String plateform;
    private ArrayList<String> bases;
    private ArrayList<String> prefixes;

    public String getQuery() {
        return query;
    }

    public String getPlateform() {
        return plateform;
    }

    public ArrayList<String> getPrefixes() {
        ArrayList<String> temp = new ArrayList<>();
        for (String prefixe : prefixes) {
            temp.add("PREFIX " + prefixe);
        }
        return temp;
    }

    public ArrayList<String> getBase() {
        return bases;
    }

    public String toString() {
        return "platform : " + plateform + "\n" +
                "base : " + bases + "\n" +
                "prefixes : " + prefixes + "\n" +
                "query : " + query;
    }
}
