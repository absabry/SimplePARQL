import com.google.common.collect.Iterables;
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

        // user
        SimplePARQLParser parser = getTree(file);
        ParserRuleContext query = parser.query();
        String oringianltreeString = treeToString(parser, query);

        // que des triples
        SimplePARQLParser newParser = RearrangeQuery(getTree(file));
        ParserRuleContext newQuery = newParser.query();
        String treeString = treeToString(newParser, newQuery);

        // generer les requetes version 30/05/2017, à midi
        /*
        Collection<ParseTree> trucs = XPath.findAll(newQuery, "//truc", newParser);
        ParseTreeElements parsedTrucs = new ParseTreeElements(getComposantOfTree(treeString));
        trucs.forEach(node -> parsedTrucs.add(new ParseTreeElement(node)));
        parsedTrucs.getGeneratedTrees().forEach(bla -> logger.debug(treeToString(newParser, bla)));
        logger.debug(parsedTrucs.getGeneratedTrees().size());
        printTree(newParser, parsedTrucs.getGeneratedTrees().get(parsedTrucs.getGeneratedTrees().size() - 1));
        */

        TestWithIndex test = new TestWithIndex(getComposantOfTree(treeString));

    }

    private static SimplePARQLParser getComposantOfTree(String text) {
        CharStream codeStream = CharStreams.fromString(text);
        SimplePARQLLexer lexer = new SimplePARQLLexer(codeStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SimplePARQLParser(tokens);
    }

    private static SimplePARQLParser RearrangeQuery(SimplePARQLParser parser) {
        ParserRuleContext query = parser.query();
        ParserRuleContext groupGraphPattern = (ParserRuleContext) Iterables.get(XPath.findAll(query, "//whereClause//groupGraphPattern", parser), 0); // il n'existe qu'un seul
        ArrayList<String> triples = new ArrayList<>();
        Collection<ParseTree> triplesSameSubjects = XPath.findAll(query, "//triplesSameSubject", parser);
        Collection<ParseTree> notGraphPattern = XPath.findAll(query, "//graphPatternNotTriples", parser);
        triplesSameSubjects.removeAll(notGraphPattern);
        triplesSameSubjects.forEach(triplesSameSubject -> {
            if (!checkInParents(triplesSameSubject)) {
                String subject = triplesSameSubject.getChild(0).getText();
                ParseTree propretyListNotEmpty = triplesSameSubject.getChild(1);
                for (int i = 0; i < propretyListNotEmpty.getChildCount(); i += 3) { // predicate,object utilisé, puis le ";" qui nous sert a rien
                    ParseTree predicate = propretyListNotEmpty.getChild(i);
                    ParseTree object = propretyListNotEmpty.getChild(i + 1);
                    for (int j = 0; j < object.getChildCount(); j += 2) { //puis le "," qui nous sert a rien
                        triples.add(subject + " " + predicate.getText() + " " + object.getChild(j).getText() + " .");
                    }
                }
            }
        });

        SimplePARQLParser triplesTree = getComposantOfTree(String.join("\n", triples));
        ParserRuleContext triplesBlockTree = triplesTree.triplesBlock();
        Collection<ParseTree> otherTriplesBlocks = XPath.findAll(query, "//whereClause//groupGraphPattern//triplesBlock", parser);
        groupGraphPattern.children.set(1, triplesBlockTree);
        groupGraphPattern.children.removeAll(otherTriplesBlocks);
        return getComposantOfTree(treeToString(parser, query));
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
