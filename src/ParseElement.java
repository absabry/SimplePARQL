import org.antlr.v4.runtime.ParserRuleContext;

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
