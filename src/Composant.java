/**
 * Item generated for each "truc" found in the SimpleARQL query
 */
public class Composant {
    private String filter;
    private String triple;
    private PAGE page;

    public Composant(String triple, String filter, PAGE page) {
        this.triple = triple;
        this.filter = filter;
        this.page = page;
    }

    public String getFilter() {
        return filter;
    }

    String getTriple() {
        return triple;
    }

    PAGE getPage() {
        return page;
    }

    public String toString() {
        return "Triple: " + triple + "\n" +
                "Filter: " + filter + "\n" +
                "Page: " + page + "\n";
    }

}
