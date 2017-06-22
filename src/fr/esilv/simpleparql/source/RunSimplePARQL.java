package fr.esilv.simpleparql.source;

import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import fr.esilv.simpleparql.source.converter.SparqlQueries;
import fr.esilv.simpleparql.source.converter.filter.FilterRegex;
import fr.esilv.simpleparql.source.converter.model.Constants;
import fr.esilv.simpleparql.source.converter.model.ParseElement;
import fr.esilv.simpleparql.source.converter.order.QueryOrdered;
import fr.esilv.simpleparql.source.webservice.Queries;
import fr.esilv.simpleparql.source.webservice.Request;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

/**
 * RunSimplePARQL Class
 */
public class RunSimplePARQL {
    private final static Logger logger = Logger.getLogger(RunSimplePARQL.class);

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure();
        logger.setLevel(Level.DEBUG);
        String file = "Test.txt";

        CharStream codeStream = CharStreams.fromFileName(file);
        SimplePARQLParser parser = new QueryOrdered(Constants.getTreeOfText(codeStream.toString())).getParser();
        ParserRuleContext query = parser.query();
        String treeString = Constants.treeToString(parser, query);

        // générer des triples version 31/05/2017 à 11h30
        SparqlQueries queries = new SparqlQueries(Constants.getTreeOfText(treeString), new FilterRegex());
        for (ParseElement generatedQueries : queries.getGeneratedQueries()) {
            logger.debug(Constants.treeToString(parser, generatedQueries.getQuery()));
        }
    }
}
