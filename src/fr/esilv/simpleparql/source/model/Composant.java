package fr.esilv.simpleparql.source.model;

/**
 * Item generated for each "truc" found in the SimpleARQL query
 */
public class Composant implements Comparable<Composant> {
    private String filter;
    private String ignoredFilter;
    private String triple;
    private PAGE page;

    public Composant(String triple, String filter, String ignoredFilter, PAGE page) {
        this.triple = triple;
        this.filter = filter;
        this.page = page;
        this.ignoredFilter = ignoredFilter;
    }

    public String getFilter() {
        return filter;
    }

    public String getTriple() {
        return triple;
    }

    public PAGE getPage() {
        return page;
    }

    public String getIgnoredFilter() {
        return ignoredFilter;
    }

    public String toString() {
        return "Triple: " + triple + "\n" +
                "Filter: " + filter + "\n" +
                "Page: " + page + "\n";
    }

    @Override
    public int compareTo(Composant composant) {
        return this.page.compareTo(composant.getPage());
    }
}
