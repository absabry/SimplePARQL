import org.antlr.v4.runtime.*;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure();
        logger.setLevel(Level.DEBUG);
        String file = "worstCase.txt";

        // user
        CharStream codeStream = CharStreams.fromFileName(file);
        SimplePARQLParser parser = Functions.getTreeOfText(codeStream.toString());
        ParserRuleContext query = parser.query();
        String oringianltreeString = Functions.treeToString(parser, query);

        // que des triples
        SimplePARQLParser newParser = new QueryOrdered(Functions.getTreeOfText(codeStream.toString())).getParser();
        ParserRuleContext newQuery = newParser.query();
        String treeString = Functions.treeToString(newParser, newQuery);

        // générer des triples version 31/05/2017 à 11h30
        SparqlQueries queries = new SparqlQueries(Functions.getTreeOfText(treeString));
        logger.debug(queries);
    }
}
