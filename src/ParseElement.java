import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Element of the generatedQueries
 * We keep :
 * page (to handle th esplit between those queries if needed)
 * query (the main tree geenrated-
 */
class ParseElement {
    private ParserRuleContext query;
    private PAGE page;

    ParseElement(ParserRuleContext query, PAGE page) {
        this.query = query;
        this.page = page;
    }

    ParserRuleContext getQuery() {
        return query;
    }

    PAGE getPage() {
        return page;
    }

    public String toString() {
        return "Page: " + page + "\n" + query.getText();
    }
}
