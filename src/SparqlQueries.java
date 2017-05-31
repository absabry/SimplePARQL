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

class SparqlQueries {

    private final static Logger logger = Logger.getLogger(SparqlQueries.class);
    private ArrayList<ParseTreeElement> simpleARQLTrucs;
    private ArrayList<ParserRuleContext> generatedQueries;
    private SimplePARQLParser parser;
    private int counter = 0;

    SparqlQueries(SimplePARQLParser parser) {
        simpleARQLTrucs = new ArrayList<>();
        generatedQueries = new ArrayList<>();
        this.parser = parser;
        ParserRuleContext query = this.parser.query();
        generatedQueries.add(query);

        if (containsTruc()) {
            mainGenerate();
        }
    }

    private void mainGenerate() {
        ArrayList<ParserRuleContext> oldGeneratedTrees = new ArrayList<>();
        oldGeneratedTrees.addAll(generatedQueries);
        counter++; // obligé pour distinguer les trucs les uns des autres
        for (ParserRuleContext oldGenereatedTree : oldGeneratedTrees) {
            Collection<ParseTree> trucs = XPath.findAll(oldGenereatedTree, "//truc", parser);
            ParseTreeElement trucFound = new ParseTreeElement(Iterables.get(trucs, 0), counter);
            if (!simpleARQLTrucs.contains(trucFound)) {
                simpleARQLTrucs.add(trucFound);
            }
            if (trucs.size() > 0) {
                generateCartesianProductTrees(oldGenereatedTree, trucFound);
            }
        }
        generatedQueries.removeAll(oldGeneratedTrees);

        if (containsTruc()) {
            mainGenerate();
        }
    }

    private boolean containsTruc() {
        ArrayList<ParserRuleContext> checkList = new ArrayList<>();
        checkList.addAll(generatedQueries);
        for (ParserRuleContext tree : checkList) {
            if (XPath.findAll(tree, "//truc", parser).size() > 0) {
                return true;
            }
        }
        return false;
    }

    private void generateCartesianProductTrees(ParserRuleContext tree, ParseTreeElement parseTreeElement) {
        for (Pair<String, String> element : parseTreeElement.getGeneratedTriples()) {
            SimplePARQLParser newOne = Functions.getTreeOfText(Functions.treeToString(parser, tree));
            ParserRuleContext newOneQuery = newOne.query();
            addTripleToTree(newOneQuery, parseTreeElement, element);
            addFilterToTree(newOneQuery, element.getValue());
            generatedQueries.add(Functions.getTreeOfText(Functions.treeToString(parser, newOneQuery)).query());
        }
    }

    private void addTripleToTree(ParserRuleContext tree, ParseTreeElement parseTreeElement, Pair<String, String> element) {
        Pair<ParserRuleContext, Integer> triplesBlocks = findInTree(tree, parseTreeElement, SimplePARQLParser.RULE_triplesBlock);
        ParseTree newTriplesForTrucs = Functions.getTreeOfText(element.getKey()).triplesBlock();
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
    }

    private void addFilterToTree(ParserRuleContext tree, String filterText) {
        ParserRuleContext whereclause = null;
        for (int i = 0; i < tree.children.size(); i++) {
            if (tree.children.get(i).getClass() == SimplePARQLParser.WhereClauseContext.class) {
                whereclause = (ParserRuleContext) tree.children.get(i).getChild(1);
            }
        }
        if (whereclause != null) {
            SimplePARQLParser.GraphPatternNotTriplesContext graphPatternNotTriplesContext
                    = new SimplePARQLParser.GraphPatternNotTriplesContext(whereclause, 1);
            ParseTree closedBrackets = whereclause.getChild(whereclause.getChildCount() - 1);
            whereclause.children.set(whereclause.getChildCount() - 1, graphPatternNotTriplesContext);
            whereclause.addChild((TerminalNodeImpl) closedBrackets);
            graphPatternNotTriplesContext.addChild(Functions.getTreeOfText(filterText).filter());
        }
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

    public String toString() {
        String result = "";
        for (ParserRuleContext generatedTree : generatedQueries) {
            result += Functions.treeToString(parser, generatedTree) + "\n";
        }
        result += generatedQueries.size() + " queries generated." + "\n";
        result += simpleARQLTrucs.toString();
        return result;
    }
}
