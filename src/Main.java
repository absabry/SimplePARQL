import javafx.util.Pair;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.*;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.swing.*;

public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure();
        logger.setLevel(Level.DEBUG);
        String file = "SimplePARQL.txt";

        SimplePARQLParser parser = getTree(file);
        ParserRuleContext query = parser.query();
        Collection<ParseTree> trucs = XPath.findAll(query, "//truc", parser);
        ArrayList<ParseTreeElement> parsedTrucs = new ArrayList<>();
        int counter = 1;
        for (ParseTree node : trucs) {
            ParseTreeElement tree = new ParseTreeElement(node);
            tree.computePosition();
            tree.GenerateTriples(counter);
            parsedTrucs.add(tree);
            counter++;
        }
        logger.debug(treeToString(parser, query));
        /*
        ConvertTree(parsedTrucs, query);
        SimplePARQLParser validate = getComposant(treeToString(parser, query));
        ParserRuleContext queryValidate = validate.query();
        printTree(validate, queryValidate);
        logger.debug(treeToString(validate, queryValidate));
        */
    }


    private static String getRuleName(ParserRuleContext rule, SimplePARQLParser parser) {
        int ruleIndex = rule.getRuleIndex();
        return parser.getRuleNames()[ruleIndex];
    }

    private static void printParentPath(SimplePARQLParser parser, ArrayList<ParseTreeElement> trucPath) {
        for (ParseTreeElement tree : trucPath) {
            for (Pair<ParserRuleContext, Integer> rule : tree.getParents()) {
                logger.debug(getRuleName(rule.getKey(), parser));
                logger.debug(rule.getValue().toString());
            }
        }
    }

    // get tree or subtrees of the graph
    private static SimplePARQLParser getTree(String file) throws IOException {
        CharStream codeStream = CharStreams.fromFileName(file);
        SimplePARQLLexer lexer = new SimplePARQLLexer(codeStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SimplePARQLParser(tokens);
    }

    // print the tree in Jframe
    private static void printTree(SimplePARQLParser parser, ParserRuleContext query) {
        //  logger.debug(query.toStringTree(parser));
        JFrame frame = new JFrame("Antlr Tree");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()), query);
        viewr.setScale(2);
        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static String treeToString(SimplePARQLParser parser, ParserRuleContext query) {
        TreePrinterListener listener = new TreePrinterListener(parser);
        ParseTreeWalker.DEFAULT.walk(listener, query);
        return listener.toString();
    }


}
