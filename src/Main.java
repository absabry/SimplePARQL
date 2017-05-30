import javafx.util.Pair;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.awt.*;
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
        printTree(parser, query);

        /*
        Collection<ParseTree> trucs = XPath.findAll(query, "//truc", parser);
        ParseTreeElements parsedTrucs = new ParseTreeElements(getTree(file));
        trucs.forEach(node -> parsedTrucs.add(new ParseTreeElement(node)));
        parsedTrucs.getGeneratedTrees().forEach(bla -> logger.debug(treeToString(parser, bla)));
        logger.debug(parsedTrucs.getGeneratedTrees().size());
        */
    }

    // get tree or subtree of text
    private static SimplePARQLParser getComposant(String text) throws IOException {
        CharStream codeStream = CharStreams.fromString(text);
        SimplePARQLLexer lexer = new SimplePARQLLexer(codeStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SimplePARQLParser(tokens);
    }

    // get tree of file
    private static SimplePARQLParser getTree(String file) throws IOException {
        CharStream codeStream = CharStreams.fromFileName(file);
        SimplePARQLLexer lexer = new SimplePARQLLexer(codeStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SimplePARQLParser(tokens);
    }

    // print the tree in a Jframe
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
        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 30));
        frame.getContentPane().add(jScrollPane);
    }

    // print the tree in logger ( not the default design)
    private static String treeToString(SimplePARQLParser parser, ParserRuleContext query) {
        TreePrinterListener listener = new TreePrinterListener(parser);
        ParseTreeWalker.DEFAULT.walk(listener, query);
        return listener.toString();
    }

    // DEBUG ONLY
    private static String getRuleName(ParserRuleContext rule, SimplePARQLParser parser) {
        int ruleIndex = rule.getRuleIndex();
        return parser.getRuleNames()[ruleIndex];
    }

    // Debug ONLY
    private static void printParentPath(SimplePARQLParser parser, ArrayList<ParseTreeElement> trucPath) {
        for (ParseTreeElement tree : trucPath) {
            for (Pair<ParserRuleContext, Integer> rule : tree.getParents()) {
                logger.debug(getRuleName(rule.getKey(), parser));
                logger.debug(rule.getValue().toString());
            }
        }
    }


}
