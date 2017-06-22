package fr.esilv.simpleparql.source.converter.model;

;
import fr.esilv.simpleparql.grammar.SimplePARQLLexer;
import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * .Constants used in the code
 */
public class Constants {
    public static final String VARIABLE = " ?SimplePARQL_";
    public static final String VARIABLE_LABEL = " ?label_";
    public static final String VARIABLE_TMP_1 = " ?tmp_var1_";
    public static final String VARIABLE_TMP_2 = " ?tmp_var2_";
    public static final String RDF = " rdfs:label ";
    public static final String FILTER = "FILTER";
    public static final String CONTAINS_BIF = "bif:contains";
    public static final String CONTAINS = "CONTAINS";
    public static final String REGEX = "REGEX";
    public static final String UCASE = "UCASE";
    public static final String STR = "STR";
    public static final String AND = " && ";

    /**
     * Create parser from text
     *
     * @param text the query in text form
     * @return the query in SimplePARQLParser form
     */
    public static SimplePARQLParser getTreeOfText(String text) {
        CharStream codeStream = CharStreams.fromString(text);
        SimplePARQLLexer lexer = new SimplePARQLLexer(codeStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SimplePARQLParser(tokens);
    }

    /**
     * Draw the syntaxic tree in a Jframe
     *
     * @param parser teh current parser in the text form
     * @param title  title of the Jframe to be displayed
     */
    public static void printTree(String tree, String title) {
        SimplePARQLParser cloned = Constants.getTreeOfText(tree);
        JFrame frame = new JFrame(title);
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                cloned.getRuleNames()), cloned.query());
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

    /**
     * /**
     * print the tree in logger (personalized design)
     *
     * @param parser the current parser in a SimplePARQLParser form
     * @param query  tree's root
     * @return the query in it's text form
     */

    public static String treeToString(SimplePARQLParser parser, ParserRuleContext query) {
        TreePrinterListener listener = new TreePrinterListener(parser);
        ParseTreeWalker.DEFAULT.walk(listener, query);
        return listener.toString();
    }

    public static String treeToStringFormatted(SimplePARQLParser parser, ParserRuleContext query) {
        TreePrinterListenerFormatted listener = new TreePrinterListenerFormatted(parser);
        ParseTreeWalker.DEFAULT.walk(listener, query);
        return listener.toString();
    }


    /**
     * get node index in parent children
     *
     * @param node current node
     * @return the index of the node in the parent children
     */
    public static int getNodeIndex(ParseTree node) {
        if (node == null || node.getParent() == null) {
            return -1;
        }
        ParseTree parent = node.getParent();
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (parent.getChild(i) == node) {
                return i;
            }
        }
        return -1;
    }
}
