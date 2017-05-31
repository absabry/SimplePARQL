import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.swing.*;

public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure();
        logger.setLevel(Level.DEBUG);
        String file = "SimplePARQL.txt";

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

    private static boolean checkInParents(ParseTree node) {
        int ruleOptionalGraphPattern = SimplePARQLParser.RULE_optionalGraphPattern;
        int ruleQuery = SimplePARQLParser.RULE_query;
        int ruleIndex = -1;
        while (ruleIndex != ruleQuery) {
            ParserRuleContext elementNode = (ParserRuleContext) node;
            ruleIndex = elementNode.getRuleIndex();
            if (ruleIndex == ruleOptionalGraphPattern) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }
}
