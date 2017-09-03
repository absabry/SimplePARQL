package fr.esilv.simpleparql.source.model;

import java.util.ArrayList;

/**
 * Item generated for each "truc" found in the SimpleARQL query
 */
public class Composant {
    private ArrayList<String> filters;
    private ArrayList<String>  triples;
    private PAGE page;

    public Composant(ArrayList<String> triple, ArrayList<String> filters, PAGE page) {
        this.triples = triple;
        this.filters = filters;
        this.page = page;
    }

    public ArrayList<String> getFilters() {
        return filters;
    }

    public ArrayList<String> getTriples() {
        return triples;
    }

    public PAGE getPage() {
        return page;
    }

    public String toString() {
        return "Triple: " + triples + "\n" +
                "Filters: " + filters.toString() + "\n" +
                "Page: " + page + "\n";
    }
}
