import org.antlr.v4.runtime.*;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure();
        logger.setLevel(Level.DEBUG);
        String file = "Test.txt";

        CharStream codeStream = CharStreams.fromFileName(file);
        SimplePARQLParser parser = new QueryOrdered(Constants.getTreeOfText(codeStream.toString())).getParser();
        ParserRuleContext query = parser.query();
        String treeString = Constants.treeToString(parser, query);

        // générer des triples version 31/05/2017 à 11h30
        SparqlQueries queries = new SparqlQueries(Constants.getTreeOfText(treeString), new FilterVirtuoso(), PAGE.THIRD, true);
        logger.debug(queries);
    }
}
