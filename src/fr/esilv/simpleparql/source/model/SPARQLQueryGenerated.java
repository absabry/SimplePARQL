package fr.esilv.simpleparql.source.model;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Element of the SPARQL generated queries. <br>
 * <strong>page</strong>to handle the split between those queries <br>
 * <strong>query </strong>the query generted,in it's tree form <br>
 */
public class SPARQLQueryGenerated {
    private ParserRuleContext query;
    private PAGE page;

    public SPARQLQueryGenerated(ParserRuleContext query, PAGE page) {
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
