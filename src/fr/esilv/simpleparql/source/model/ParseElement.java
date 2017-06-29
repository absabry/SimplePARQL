package fr.esilv.simpleparql.source.model;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Element of the generatedQueries
 * We keep :
 * page (to handle the split between those queries if needed)
 * query (the main tree geenrated)
 */
public class ParseElement {
    private ParserRuleContext query;
    private PAGE page;

    public ParseElement(ParserRuleContext query, PAGE page) {
        this.query = query;
        this.page = page;
    }

    public ParserRuleContext getQuery() {
        return query;
    }

    public PAGE getPage() {
        return page;
    }

    public String toString() {
        return "Page: " + page + "\n" + query.getText();
    }
}
