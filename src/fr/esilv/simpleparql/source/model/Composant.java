package fr.esilv.simpleparql.source.model;

import java.util.ArrayList;

/**
 * Item generated for each "truc" found in the SimpleARQL query
 */
public class Composant {
    private ArrayList<String> filters;
    private String triple;
    private PAGE page;

    public Composant(String triple, ArrayList<String> filters, PAGE page) {
        this.triple = triple;
        this.filters = filters;
        this.page = page;
    }

    public ArrayList<String> getFilters() {
        return filters;
    }

    public String getTriple() {
        return triple;
    }

    public PAGE getPage() {
        return page;
    }

    public String toString() {
        return "Triple: " + triple + "\n" +
                "Filters: " + filters.toString() + "\n" +
                "Page: " + page + "\n";
    }
}
