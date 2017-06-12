public class GeneratedComposant {
    private String filter;
    private String triple;
    private PAGE page;

    public GeneratedComposant(String triple, String filter, PAGE page) {
        this.triple = triple;
        this.filter = filter;
        this.page = page;
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

    public String toString() {
        return "Triple: "+triple+"\n"+
                "Filter: "+filter+"\n"+
                "Page: " + page + "\n";
    }

}
