import com.google.common.collect.Iterables;
import javafx.util.Pair;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

// equals to Context
class SparqlQueries {

    private final static Logger logger = Logger.getLogger(SparqlQueries.class);
    private ArrayList<Truc> simpleARQLTrucs;
    private ArrayList<ParseElement> generatedQueries;
    private SimplePARQLParser parser;
    private int counter = 0;
    private FilterGenerator filterGenerator;
    private PAGE page;

    SparqlQueries(SimplePARQLParser parser, FilterGenerator filterGenerator, PAGE page) {
        this.page = page;
        this.filterGenerator = filterGenerator;
        simpleARQLTrucs = new ArrayList<>();
        generatedQueries = new ArrayList<>();
        this.parser = parser;
        ParserRuleContext query = this.parser.query();
        generatedQueries.add(new ParseElement(query, PAGE.FIRST));
        if (containsTruc()) {
            mainGenerate();
        }
    }

    SparqlQueries(SimplePARQLParser parser) {
        this(parser, new FilterNormal(), PAGE.FIRST);
    }

    SparqlQueries(SimplePARQLParser parser, FilterGenerator filterGenerator) {
        this(parser, filterGenerator, PAGE.FIRST);
    }

    SparqlQueries(SimplePARQLParser parser, PAGE page) {
        this(parser, new FilterNormal(), page);
    }

    private void mainGenerate() {
        ArrayList<ParseElement> oldGeneratedTrees = new ArrayList<>();
        oldGeneratedTrees.addAll(generatedQueries);
        for (ParseElement oldGenereatedTree : oldGeneratedTrees) {
            Collection<ParseTree> trucs = XPath.findAll(oldGenereatedTree.getQuery(), "//truc", parser);
            if (trucs.size() > 0) {
                Truc trucFound = createTruc(Iterables.get(trucs, 0));
                generateCartesianProductTrees(oldGenereatedTree, trucFound);
            }
        }
        generatedQueries.removeAll(oldGeneratedTrees);
        if (containsTruc()) {
            mainGenerate();
        }
    }

    // create new truc if it dosen't exists.
    private Truc createTruc(ParseTree trucInTree) {
        Truc trucFound = null;
        // if the truc already exists in the generated queries
        for (Truc simpleARQLTruc : simpleARQLTrucs) {
            if (simpleARQLTruc.getName().equals(trucInTree.getText())) {
                trucFound = new Truc(trucInTree, simpleARQLTruc.getCounter());
                break;
            }
        }
        // if the truc never exists, we can create a new one
        if (trucFound == null) {
            counter++;
            trucFound = new Truc(trucInTree, counter);
            simpleARQLTrucs.add(trucFound);
        }
        return trucFound;
    }

    // Generate tree with cartesian product directly
    private void generateCartesianProductTrees(ParseElement oldTree, Truc truc) {
        GenerateQuery generateQuery = new GenerateQuery(truc, filterGenerator, page);
        for (GeneratedComposant element : generateQuery.getGeneratedTriples()) {
            SimplePARQLParser newOne = Functions.getTreeOfText(Functions.treeToString(parser, oldTree.getQuery()));
            ParserRuleContext newOneQuery = newOne.query();
            Pair<ParserRuleContext, Integer> groupGraphPattern = findInTree(newOneQuery, truc, SimplePARQLParser.RULE_groupGraphPattern);
            Pair<ParserRuleContext, Integer> triplesBlocks = findInTree(newOneQuery, truc, SimplePARQLParser.RULE_triplesBlock);
            addTripleToTree(triplesBlocks, element.getTriple());
            addFilterToTree(groupGraphPattern, element.getFilter());
            ParserRuleContext newQuery = Functions.getTreeOfText(Functions.treeToString(parser, newOneQuery)).query();
            PAGE greater = getGreaterPage(element.getPage(), oldTree.getPage());
            generatedQueries.add(new ParseElement(newQuery, greater));
        }
    }

    // third,second => return third
    private PAGE getGreaterPage(PAGE page1, PAGE page2) {
        if (page1.compareTo(page2) > 0) {
            return page1;
        }
        return page2;
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

    private void addFilterToTree(Pair<ParserRuleContext, Integer> groupGraphPattern, String filterText) {
        if (!containsFilter(groupGraphPattern, filterText) && groupGraphPattern != null) {
            SimplePARQLParser.GraphPatternNotTriplesContext graphPatternNotTriplesContext
                    = new SimplePARQLParser.GraphPatternNotTriplesContext(groupGraphPattern.getKey(), 1);
            ParseTree closedBrackets = groupGraphPattern.getKey().getChild(groupGraphPattern.getKey().getChildCount() - 1);
            groupGraphPattern.getKey().children.set(groupGraphPattern.getKey().getChildCount() - 1, graphPatternNotTriplesContext);
            groupGraphPattern.getKey().addChild((TerminalNodeImpl) closedBrackets);
            graphPatternNotTriplesContext.addChild(Functions.getTreeOfText(filterText).filter());
        }
    }

    private Pair<ParserRuleContext, Integer> findInTree(ParserRuleContext tree, Truc element, int ruleIndex) {
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

    // when the generatedTrees still contains some "truc"
    private boolean containsTruc() {
        ArrayList<ParseElement> checkList = new ArrayList<>();
        checkList.addAll(generatedQueries);
        for (ParseElement tree : checkList) {
            if (XPath.findAll(tree.getQuery(), "//truc", parser).size() > 0) {
                return true;
            }
        }
        return false;
    }

    // filter is already contained in the generatedTree
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
        for (ParseElement generatedTree : generatedQueries) {
            result += "Page: " + generatedTree.getPage() + "\n";
            result += Functions.treeToString(parser, generatedTree.getQuery()) + "\n";
        }
        result += generatedQueries.size() + " queries generated." + "\n";
        result += simpleARQLTrucs.toString();
        return result;
    }
}
