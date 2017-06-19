import com.google.common.collect.Iterables;
import javafx.util.Pair;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

/**
 * main class to get the SPARQL queries from SimpleARQL queries
 * <p>
 * we keep:
 * list of truc
 * list of generated queries with their page
 * counter of truc in the tree
 * filter geenrator the user choose
 * if he wants to keeps the trucs in the optional or not
 * the page he belong to
 */
class SparqlQueries {

    private final static Logger logger = Logger.getLogger(SparqlQueries.class);
    private ArrayList<Truc> simpleARQLTrucs;
    private ArrayList<ParseElement> generatedQueries;
    private SimplePARQLParser parser;
    private int counter = 0; // counter of Variables created
    private FilterGenerator filterGenerator; // normal or virtuoso or other
    private boolean optionnal;
    private PAGE page;

    SparqlQueries(SimplePARQLParser parser, FilterGenerator filterGenerator, PAGE page, boolean optionnal) {
        this.page = page;
        this.filterGenerator = filterGenerator;
        this.optionnal = optionnal;
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
        this(parser, new FilterNormal(), PAGE.FIRST, true);
    }

    SparqlQueries(SimplePARQLParser parser, boolean optionnal) {
        this(parser, new FilterNormal(), PAGE.FIRST, optionnal);
    }

    SparqlQueries(SimplePARQLParser parser, FilterGenerator filterGenerator) {
        this(parser, filterGenerator, PAGE.FIRST, true);
    }

    SparqlQueries(SimplePARQLParser parser, PAGE page) {
        this(parser, new FilterNormal(), page, true);
    }

    /**
     * main function to generate the sparql queries from the simpleARQL queries
     * <p>
     * We clone the array og queries
     * Then we browse to find (in the old tree) all truc
     * we create structure Truc
     * Then we generate cartesian prodcut of the truc (that may contains multiple new Items)
     */
    private void mainGenerate() {
        ArrayList<ParseElement> oldGeneratedTrees = new ArrayList<>();
        oldGeneratedTrees.addAll(generatedQueries);
        for (ParseElement oldGenereatedTree : oldGeneratedTrees) {
            Collection<ParseTree> trucs = XPath.findAll(oldGenereatedTree.getQuery(), "//truc", parser);
            if (trucs.size() > 0) {
                Truc trucFound = createTruc(Iterables.get(trucs, 0));
                if (trucFound.isOptionnal() && !optionnal) {
                    simpleARQLTrucs.remove(trucFound);
                    removeOptionnalTrucFromTree(oldGenereatedTree, trucFound);
                } else {
                    generateCartesianProductTrees(oldGenereatedTree, trucFound);
                }
            }
        }
        generatedQueries.removeAll(oldGeneratedTrees);
        if (containsTruc()) {
            mainGenerate();
        }
    }

    /**
     * check if the truc exsits in the oldest trucs
     * if so we get it
     * else we create a new one and add it in the simpleARQLTrucs list
     *
     * @param trucInTree truc found
     * @return new Truc if it dosent exist
     */
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

    /**
     * create a GenerateQuery element, that will create all of the filters, triples and PAGE of the truc
     * foreach Composant item created (containg filter and triples)
     * we clone the tree, create a new one and attach the new filter and the new triple
     * and we add the result to the generatedQueries List
     *
     * @param tree original tree to get it's query and it's page
     * @param truc truc which we add it's triples and filter to the new query
     */
    // Generate tree with cartesian product directly
    private void generateCartesianProductTrees(ParseElement tree, Truc truc) {
        GenerateQuery generateQuery = new GenerateQuery(truc, filterGenerator, page);
        for (Composant element : generateQuery.getGeneratedComposants()) {
            SimplePARQLParser newOne = Constants.getTreeOfText(Constants.treeToString(parser, tree.getQuery()));
            ParserRuleContext newOneQuery = newOne.query();
            Pair<ParserRuleContext, Integer> groupGraphPattern = findInTree(newOneQuery, truc, SimplePARQLParser.RULE_groupGraphPattern);
            Pair<ParserRuleContext, Integer> triplesBlocks = findInTree(newOneQuery, truc, SimplePARQLParser.RULE_triplesBlock);
            addTripleToTree(triplesBlocks, element.getTriple());
            addFilterToTree(groupGraphPattern, element.getFilter());
            ParserRuleContext newQuery = Constants.getTreeOfText(Constants.treeToString(parser, newOneQuery)).query();
            PAGE greater = getGreaterPage(element.getPage(), tree.getPage());
            generatedQueries.add(new ParseElement(newQuery, greater));
        }
    }

    /**
     * third,second => return third
     *
     * @param page1 first page
     * @param page2 second page
     * @return the greater page of page1 and page2
     */
    private PAGE getGreaterPage(PAGE page1, PAGE page2) {
        if (page1.compareTo(page2) > 0) {
            return page1;
        }
        return page2;
    }

    /**
     * Remove the optionnal node that contains truc
     * When we set optinonal to false
     *
     * @param tree the tree which we'll delete the optionnal from it
     * @param truc remove from the tree
     */
    private void removeOptionnalTrucFromTree(ParseElement tree, Truc truc) {
        SimplePARQLParser newOne = Constants.getTreeOfText(Constants.treeToString(parser, tree.getQuery()));
        ParserRuleContext newOneQuery = newOne.query();
        Pair<ParserRuleContext, Integer> triplesBlocks = findInTree(newOneQuery, truc, SimplePARQLParser.RULE_triplesBlock);
        Pair<ParserRuleContext, Integer> graphPatternNotTriples = findInTree(newOneQuery, truc, SimplePARQLParser.RULE_graphPatternNotTriples);
        if (triplesBlocks != null) {
            ParserRuleContext tripleBlocksParent = triplesBlocks.getKey().getParent();
            ParseTree lastChild = triplesBlocks.getKey().children.get(triplesBlocks.getKey().children.size() - 1);
            if (lastChild instanceof SimplePARQLParser.TriplesBlockContext) {
                tripleBlocksParent.children.set(triplesBlocks.getValue(), lastChild);  // replace the old child with the last child
            } else {
                if (tripleBlocksParent instanceof SimplePARQLParser.GroupGraphPatternContext && countTripleBlocks(triplesBlocks.getKey()) == 0) {
                    // we've to delete the optionnal node because it will be empty after deleting the triple of TRUC
                    if (graphPatternNotTriples != null) {
                        graphPatternNotTriples.getKey().getParent().children.set(graphPatternNotTriples.getValue(), new ParserRuleContext());
                    }
                } else {
                    tripleBlocksParent.children.set(triplesBlocks.getValue(), new ParserRuleContext()); // replace the old child with a new EMPTY one
                }
            }


            ParserRuleContext newQuery = Constants.getTreeOfText(Constants.treeToString(parser, newOneQuery)).query(); // create the new tree
            generatedQueries.add(new ParseElement(newQuery, tree.getPage()));
        }
    }

    /**
     * we need to count how much triplesblocks this tripleBlocksRoot contains
     *
     * @param tripleBlocksRoot the root where we began to count
     * @return the number of triplesBlocks we found under the root
     */

    private int countTripleBlocks(ParserRuleContext tripleBlocksRoot) {
        int result = 0;
        ParseTree lastChild = tripleBlocksRoot.getChild(tripleBlocksRoot.children.size() - 1);
        while (tripleBlocksRoot.getChild(tripleBlocksRoot.children.size() - 1) instanceof SimplePARQLParser.TriplesBlockContext) {
            result += 1;
            tripleBlocksRoot = (ParserRuleContext) lastChild;
        }
        return result;
    }

    /**
     * add the new triples generated to the tree
     * <p>
     * if the last child of triplesblocks is another trimples blocks
     * we get this element and add it at the end of our generated triples
     * <p>
     * else
     * we remove the old triples containing the truc, and add the new triples
     *
     * @param triplesBlocks triplesBlocks root from the truc
     * @param triplesText   String of the new triples we generate for the truc
     */

    private void addTripleToTree(Pair<ParserRuleContext, Integer> triplesBlocks, String triplesText) {
        ParseTree newTriplesForTrucs = Constants.getTreeOfText(triplesText).triplesBlock();
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

    /**
     * Add filter to the tree, if it dosent exists already
     * (filter exists if the truc already exist id before)
     * <p>
     * Add the filter to the end of it's groupGraphPattern
     *
     * @param groupGraphPattern root of the truc
     * @param filterText        text of the filter we want to add to the tree
     */
    private void addFilterToTree(Pair<ParserRuleContext, Integer> groupGraphPattern, String filterText) {
        if (!containsFilter(groupGraphPattern, filterText) && groupGraphPattern != null) {
            SimplePARQLParser.GraphPatternNotTriplesContext graphPatternNotTriplesContext
                    = new SimplePARQLParser.GraphPatternNotTriplesContext(groupGraphPattern.getKey(), 1);
            ParseTree closedBrackets = groupGraphPattern.getKey().getChild(groupGraphPattern.getKey().getChildCount() - 1);
            groupGraphPattern.getKey().children.set(groupGraphPattern.getKey().getChildCount() - 1, graphPatternNotTriplesContext);
            groupGraphPattern.getKey().addChild((TerminalNodeImpl) closedBrackets);
            graphPatternNotTriplesContext.addChild(Constants.getTreeOfText(filterText).filter());
        }
    }


    /**
     * Find in the NEW tree the first element using the find() function of the truc structure
     *
     * @param tree      new tree
     * @param element   the truc of the tree
     * @param ruleIndex the rule index we search for
     * @return the pair of parseRuleContext and its node index in the new tree
     */
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

    /**
     * check if the generatedQueries list containing truc
     * when the generatedTrees still contains some "truc"
     *
     * @return boolean containing the truc
     */
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

    /**
     * check if the generatedTree containing this filter
     *
     * @param groupGraphPattern the groupGraphPattern root of the truc
     * @param filterText        filter string
     * @return boolean containing the truc
     */
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
            result += Constants.treeToString(parser, generatedTree.getQuery()) + "\n";
        }
        result += generatedQueries.size() + " queries generated." + "\n";
        result += simpleARQLTrucs.toString();
        return result;
    }
}
