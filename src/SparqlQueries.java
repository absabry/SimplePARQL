import com.google.common.collect.Iterables;
import javafx.util.Pair;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.log4j.Logger;

import java.util.ArrayList;
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

    private void mainGenerate() {
        ArrayList<ParserRuleContext> oldGeneratedTrees = new ArrayList<>();
        oldGeneratedTrees.addAll(generatedQueries);
        for (ParserRuleContext oldGenereatedTree : oldGeneratedTrees) {
            Collection<ParseTree> trucs = XPath.findAll(oldGenereatedTree, "//truc", parser);
            if (trucs.size() > 0) {
                ParseTree firstTruc = Iterables.get(trucs, 0);
                ParseTreeElement trucFound = null;
                // if the truc already exists in the generated queries
                for (ParseTreeElement simpleARQLTruc : simpleARQLTrucs) {
                    if (simpleARQLTruc.getName().equals(firstTruc.getText())) {
                        trucFound = new ParseTreeElement(firstTruc, simpleARQLTruc.getCounter());
                    }
                }
                // if the truc never exists, we can create a new one
                if (trucFound == null) {
                    counter++;
                    trucFound = new ParseTreeElement(firstTruc, counter);
                    simpleARQLTrucs.add(trucFound);
                }

                generateCartesianProductTrees(oldGenereatedTree, trucFound);
            }
        }
        generatedQueries.removeAll(oldGeneratedTrees);

        if (containsTruc()) {
            mainGenerate();
        }
    }

    private void generateCartesianProductTrees(ParserRuleContext tree, ParseTreeElement parseTreeElement) {
        for (Pair<String, String> element : parseTreeElement.getGeneratedTriples()) {
            SimplePARQLParser newOne = Functions.getTreeOfText(Functions.treeToString(parser, tree));
            ParserRuleContext newOneQuery = newOne.query();
            Pair<ParserRuleContext, Integer> groupGraphPattern = findInTree(newOneQuery, parseTreeElement, SimplePARQLParser.RULE_groupGraphPattern);
            Pair<ParserRuleContext, Integer> triplesBlocks = findInTree(newOneQuery, parseTreeElement, SimplePARQLParser.RULE_triplesBlock);
            addTripleToTree(triplesBlocks, element.getKey());
            if (!containsFilter(groupGraphPattern, element.getValue())) {
                addFilter(groupGraphPattern, element.getValue());
            }
            generatedQueries.add(Functions.getTreeOfText(Functions.treeToString(parser, newOneQuery)).query());
        }
    }

    private void addTripleToTree(Pair<ParserRuleContext, Integer> triplesBlocks, String triplesText) {
        ParseTree newTriplesForTrucs = Functions.getTreeOfText(triplesText).triplesBlock();
        if (triplesBlocks != null) {
            ParseTree lastChild = triplesBlocks.getKey().getChild(triplesBlocks.getKey().getChildCount() - 1);
            if (lastChild.getClass() == SimplePARQLParser.TriplesBlockContext.class) {
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

    private void addFilter(Pair<ParserRuleContext, Integer> groupGraphPattern, String filterText) {
        if (groupGraphPattern != null) {
            SimplePARQLParser.GraphPatternNotTriplesContext graphPatternNotTriplesContext
                    = new SimplePARQLParser.GraphPatternNotTriplesContext(groupGraphPattern.getKey(), 1);
            ParseTree closedBrackets = groupGraphPattern.getKey().getChild(groupGraphPattern.getKey().getChildCount() - 1);
            groupGraphPattern.getKey().children.set(groupGraphPattern.getKey().getChildCount() - 1, graphPatternNotTriplesContext);
            groupGraphPattern.getKey().addChild((TerminalNodeImpl) closedBrackets);
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

    private boolean containsFilter(Pair<ParserRuleContext, Integer> groupGraphPattern, String filterText) {
        Collection<ParseTree> filters = XPath.findAll(groupGraphPattern.getKey(), "//filter", parser);
        for (ParseTree filter : filters) {
            filterText = filterText.replace(" ", ""); // ignore spaces in the comparaison, filter comming from the query is without any spaces
            if (filter.getText().contains(filterText)) {
                return true;
            }
        }
        return false;
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
