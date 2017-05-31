import com.google.common.collect.Iterables;
import javafx.util.Pair;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

class TestWithIndex {

    private final static Logger logger = Logger.getLogger(TestWithIndex.class);
    private ArrayList<ParserRuleContext> generatedTrees;
    private SimplePARQLParser parser;
    private ParserRuleContext query;
    private static int counter = 0;

    TestWithIndex(SimplePARQLParser parser) {
        generatedTrees = new ArrayList<>();
        this.parser = parser;
        query = this.parser.query();
        generatedTrees.add(query);
        //generateCartesianProductTrees(new ParseTreeElement(Iterables.get(XPath.findAll(query, "//truc", parser), 0)));
        if (containsTruc()) {
            mainGenerate();
        }

        for (ParserRuleContext generatedTree : generatedTrees) {
            logger.debug(treeToString(parser, generatedTree));
        }
        logger.debug(generatedTrees.size() + " queries generated.");

    }

    private void mainGenerate() {
        ArrayList<ParserRuleContext> oldGeneratedTrees = new ArrayList<>();
        oldGeneratedTrees.addAll(generatedTrees);
        for (ParserRuleContext oldGenereatedTree : oldGeneratedTrees) {
            Collection<ParseTree> trucs = XPath.findAll(oldGenereatedTree, "//truc", parser);
            if (trucs.size() > 0) {
                generateCartesianProductTrees(oldGenereatedTree, new ParseTreeElement(Iterables.get(trucs, 0)));
            }
        }
        generatedTrees.removeAll(oldGeneratedTrees);

        if (containsTruc()) {
            mainGenerate();
        }
    }

    private boolean containsTruc() {
        ArrayList<ParserRuleContext> checkList = new ArrayList<>();
        checkList.addAll(generatedTrees);
        for (ParserRuleContext tree : checkList) {
            if (XPath.findAll(tree, "//truc", parser).size() > 0) {
                return true;
            }
        }
        return false;
    }

    private void generateCartesianProductTrees(ParserRuleContext tree, ParseTreeElement parseTreeElement) {
        for (Pair<String, String> element : parseTreeElement.getGeneratedTriples()) {
            SimplePARQLParser newOne = getComposantOfTree(treeToString(parser, tree));
            ParserRuleContext newOneQuery = newOne.query();

            Pair<ParserRuleContext, Integer> triplesBlocks = findInTree(newOneQuery, parseTreeElement, SimplePARQLParser.RULE_triplesBlock);
            ParseTree newTriplesForTrucs = getComposantOfTree(element.getKey()).triplesBlock();
            if (triplesBlocks != null) {
                ParseTree lastChild = triplesBlocks.getKey().getChild(triplesBlocks.getKey().getChildCount() - 1);
                if (lastChild instanceof ParserRuleContext) {
                    triplesBlocks.getKey().getParent().children.set(triplesBlocks.getValue(), newTriplesForTrucs);
                    ParseTree child = newTriplesForTrucs;
                    while (child.getChild(child.getChildCount() - 1) instanceof ParserRuleContext) {
                        child = child.getChild(child.getChildCount() - 1);
                    }
                    ((ParserRuleContext) child).addChild((ParserRuleContext) lastChild);
                } else {
                    triplesBlocks.getKey().getParent().children.set(triplesBlocks.getValue(), newTriplesForTrucs);
                }
            }
            addFilterToTree(newOneQuery, element.getValue());
            generatedTrees.add(getComposantOfTree(treeToString(parser, newOneQuery)).query());
        }
    }


    private static void printParentPath(SimplePARQLParser parser, ParseTreeElement tree) {
        for (Pair<ParserRuleContext, Integer> rule : tree.getParents()) {
            logger.debug(getRuleName(rule.getKey(), parser));
            logger.debug(rule.getValue().toString());
        }
    }

    private static String getRuleName(ParserRuleContext rule, SimplePARQLParser parser) {
        int ruleIndex = rule.getRuleIndex();
        return parser.getRuleNames()[ruleIndex];
    }

    private Pair<ParserRuleContext, Integer> findInTree(ParserRuleContext tree, ParseTreeElement element, int ruleIndex) {
        ArrayList<Pair<ParserRuleContext, Integer>> tempList = new ArrayList<>();
        Pair<ParserRuleContext, Integer> truc = new Pair<>(tree, -1);
        tempList.add(truc);

        for (int i = element.getParents().size() - 1; i > 0; i--) {
            Pair<ParserRuleContext, Integer> next = element.getParents().get(i - 1);
            truc = new Pair<>((ParserRuleContext) truc.getKey().getChild(next.getValue()), next.getValue());
            tempList.add(truc);
        }

        for (int i = tempList.size() - 1; i >= 0; i--) {
            Pair<ParserRuleContext, Integer> temp = tempList.get(i);
            if (temp.getKey().getRuleIndex() == ruleIndex) {
                return temp;
            }
        }
        return null;
    }

    private void addFilterToTree(ParserRuleContext tree, String filterText) {
        ParserRuleContext whereclause = null;
        for (int i = 0; i < tree.children.size(); i++) {
            if (tree.children.get(i).getClass() == SimplePARQLParser.WhereClauseContext.class) {
                whereclause = (ParserRuleContext) tree.children.get(i).getChild(1);
            }
        }
        if (whereclause != null) {
            // ajouter le graphPatternnotTriples au mileu
            SimplePARQLParser.GraphPatternNotTriplesContext graphPatternNotTriplesContext
                    = new SimplePARQLParser.GraphPatternNotTriplesContext(whereclause, 1);
            ParseTree closedBrackets = whereclause.getChild(whereclause.getChildCount() - 1);
            whereclause.children.set(whereclause.getChildCount() - 1, graphPatternNotTriplesContext);
            whereclause.addChild((TerminalNodeImpl) closedBrackets);
            graphPatternNotTriplesContext.addChild(getComposantOfTree(filterText).filter());
        }
    }

    private String treeToString(SimplePARQLParser parser, ParserRuleContext tree) {
        TreePrinterListener listener = new TreePrinterListener(parser);
        ParseTreeWalker.DEFAULT.walk(listener, tree);
        return listener.toString();
    }

    private static void printTree(SimplePARQLParser parser, ParserRuleContext query, String title) {
        //  logger.debug(query.toStringTree(parser));
        JFrame frame = new JFrame(title);
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

    private SimplePARQLParser getComposantOfTree(String text) {
        CharStream codeStream = CharStreams.fromString(text);
        SimplePARQLLexer lexer = new SimplePARQLLexer(codeStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SimplePARQLParser(tokens);
    }
}
