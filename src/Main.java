import javafx.util.Pair;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.antlr.v4.gui.TreeViewer;
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
        ANTLRInputStream code = new ANTLRInputStream(new FileInputStream(file));
        SimplePARQLLexer lexer = new SimplePARQLLexer(code);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimplePARQLParser parser = new SimplePARQLParser(tokens);
        ParseTree query = parser.query();
        Collection<ParseTree> trucs = XPath.findAll(query, "//truc", parser);
        ArrayList<ArrayList<Pair<RuleContext,Integer>>> trucPath = new ArrayList<>();
        for (ParseTree node : trucs) {
            trucPath.add(getParentsPath(node));
        }
        printParentPath(parser,trucPath);
        printTree(parser, query);
    }

    private static  ArrayList<Pair<RuleContext,Integer>>getParentsPath(ParseTree node) {
        ArrayList<Pair<RuleContext,Integer>> rules = new ArrayList<>();
        int end = SimplePARQLParser.RULE_triplesSameSubject;
        int ruleIndex = -1;
        while (ruleIndex != end) {
            RuleContext elementNode = (RuleContext) node;
            ruleIndex = elementNode.getRuleIndex();
            rules.add(new Pair<>(elementNode,getNodeIndex(node)));
            node = node.getParent();
        }
        return rules;
    }
    private static void printParentPath(SimplePARQLParser parser, ArrayList<ArrayList<Pair<RuleContext,Integer>>> trucPath) {
        for (ArrayList<Pair<RuleContext,Integer>> rules : trucPath) {
            for (Pair<RuleContext,Integer> rule : rules) {
                int ruleIndex = rule.getKey().getRuleIndex();
                String name = parser.getRuleNames()[ruleIndex];
                String index=rule.getValue().toString();
                logger.debug(name);
                logger.debug(index);
            }
        }
    }
    private static void printTree(SimplePARQLParser parser, ParseTree query) {
        System.out.println(query.toStringTree(parser));
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
    private static int getNodeIndex(ParseTree node) {
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
    private static void getRuleNames(SimplePARQLParser parser) {
        for (int i = 0; i < parser.getRuleNames().length; i++) {
            logger.debug(parser.getRuleNames()[i]);
        }

        for (int i = 0; i < parser.getTokenStream().size(); i++) {
            logger.debug(parser.getTokenStream().get(i));
        }
    }
}
