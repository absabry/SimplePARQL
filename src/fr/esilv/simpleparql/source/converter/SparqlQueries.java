package fr.esilv.simpleparql.source.converter;

import com.google.common.collect.Iterables;
import fr.esilv.simpleparql.source.converter.filter.FilterDefault;
import fr.esilv.simpleparql.source.converter.filter.FilterGenerator;
import fr.esilv.simpleparql.source.converter.query.SimplePARQLQueryGenerator;
import fr.esilv.simpleparql.source.model.*;
import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import fr.esilv.simpleparql.source.configuration.QueryConfig;
import javafx.util.Pair;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Main class to get the SPARQL queries generated from SimpleARQL queries. <br>
 * <strong>simpleARQLTrucs:</strong> List of Truc in the SimplePARQL query.<br>
 * <strong>generatedQueries:</strong> List of SPARQL query generated. <br>
 * <strong>parser:</strong> SimplePARQL parser from the ANTLR tree <br>
 * <strong>counter:</strong>Counter of truc in the tree. The first one will be 0, then 1 etc..<br>
 * <strong>filterGenerator:</strong> filter geenrator for this base <br>
 * <strong>optionnal:</strong> convert the Truc in the optional field<br>
 * <strong>ignoredConfig: List of proprties we want to exlude from our request</strong><br>
 */
public class SparqlQueries {

    private final static Logger logger = Logger.getLogger(SparqlQueries.class);
    private ArrayList<Truc> simpleARQLTrucs;
    private ArrayList<SPARQLQueryGenerated> generatedQueries;
    private SimplePARQLParser parser;
    private int counter = 0;
    private FilterGenerator filterGenerator;
    private boolean optionnal;
    private ArrayList<String> ignoredConfig;

    public SparqlQueries(SimplePARQLParser parser, FilterGenerator filterGenerator, boolean optionnal, QueryConfig ignoredConfig) throws IOException {
        this.filterGenerator = filterGenerator;
        this.optionnal = optionnal;
        simpleARQLTrucs = new ArrayList<>();
        generatedQueries = new ArrayList<>();
        this.parser = parser;
        this.ignoredConfig = ignoredConfig.getIgnoredProprieties();
        ParserRuleContext query = this.parser.query();
        generatedQueries.add(new SPARQLQueryGenerated(query, PAGE.FIRST));
        if (containsTruc()) {
            generatedSPARQLQueries();
        }

    }

    public SparqlQueries(SimplePARQLParser parser, QueryConfig ignoredConfig) throws IOException {
        this(parser, new FilterDefault(), true, ignoredConfig);
    }

    public SparqlQueries(SimplePARQLParser parser, boolean optionnal, QueryConfig ignoredConfig) throws IOException {
        this(parser, new FilterDefault(), optionnal, ignoredConfig);
    }

    public SparqlQueries(SimplePARQLParser parser, FilterGenerator filterGenerator, QueryConfig ignoredConfig) throws IOException {
        this(parser, filterGenerator, true, ignoredConfig);
    }

    public ArrayList<Truc> getSimpleARQLTrucs() {
        return simpleARQLTrucs;
    }

    /**
     *
     * Filter the generated query list to get the one assosicated with the page we're looking for.
     *
     * @param page The page we're looking for.
     * @return The element(s) of the generatedqueries in a new list, corresponding to the page we asked for.
     */
    public ArrayList<SPARQLQueryGenerated> getGeneratedQueries(PAGE page) {
        ArrayList<SPARQLQueryGenerated> result = new ArrayList<>();
        for (SPARQLQueryGenerated element : generatedQueries) {
            if (element.getPage().compareTo(page) == 0) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Main function to generate the sparql queries from the simpleARQL queries. <br>
     * 1.We clone the array of queries <br>
     * 2.Then we browse to find (in the old tree) all truc <br>
     * 3.We create structure Truc <br>
     * 4.Finally we generate cartesian product of the truc, after checking the optionnal field (we can disable trucs from the optionnal composant) <br>
     */
    private void generatedSPARQLQueries() throws IOException {
        ArrayList<SPARQLQueryGenerated> oldGeneratedTrees = new ArrayList<>();
        oldGeneratedTrees.addAll(generatedQueries);
        for (SPARQLQueryGenerated oldGenereatedTree : oldGeneratedTrees) {
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
            generatedSPARQLQueries();
        }
    }

    /**
     * Check if the truc exists in the oldest list of truc.<br>
     * If so we get it, otherwise we create a new one and add it in the simpleARQLTrucs list. <br>
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
     * Get the parents' name
     *
     * @param parser containing all the parser
     * @return parent's name of the truc
     * @deprecated
     */
    String printParentPath(SimplePARQLParser parser, Truc truc) {
        String result = "";
        for (Pair<ParserRuleContext, Integer> rule : truc.getParents()) {
            result += getRuleName(rule.getKey(), parser) + " " + rule.getValue().toString();
            result += "\n";
        }
        return result;
    }

    /**
     * gets the rule name from the rule tree
     *
     * @param rule   the rule itself
     * @param parser to get all the rules
     * @return the rule name
     * @deprecated
     */
    private String getRuleName(ParserRuleContext rule, SimplePARQLParser parser) {
        int ruleIndex = rule.getRuleIndex();
        return parser.getRuleNames()[ruleIndex];
    }

    /**
     * Create a SimplePARQLQueryGenerator element, that will create all of the filter, triples and PAGE of the truc. <br>
     * foreach Composant item created (containg filter and triples),we clone the tree, create a new one and attach the new filter and the new triple
     * and finally we add the result to the generatedQueries List. <br>
     * @param tree original tree to get it's query and it's page
     * @param truc truc which we add it's triples and filter to the new query
     */
    private void generateCartesianProductTrees(SPARQLQueryGenerated tree, Truc truc) throws IOException {
        // we create the QueryConfig here to handle the closed stream after each using, and so,
        // we create a QueryConfig object foreach SimplePARQLQueryGenerator
        SimplePARQLQueryGenerator generateQuery = new SimplePARQLQueryGenerator(truc, filterGenerator, ignoredConfig);
        for (Composant element : generateQuery.getGeneratedComposants()) {
            SimplePARQLParser newOne = Constants.getTreeOfText(Constants.treeToString(parser, tree.getQuery()));
            ParserRuleContext newOneQuery = newOne.query();
            Pair<ParserRuleContext, Integer> groupGraphPattern = findInTree(newOneQuery, truc, SimplePARQLParser.RULE_groupGraphPattern);
            Pair<ParserRuleContext, Integer> triplesBlocks = findInTree(newOneQuery, truc, SimplePARQLParser.RULE_triplesBlock);
            addTripleToTree(triplesBlocks, element.getTriple());
            addFilterToTree(groupGraphPattern, element.getFilter());
            if (element.getIgnoredFilter() != null) {
                addFilterToTree(groupGraphPattern, element.getIgnoredFilter());
            }

            ParserRuleContext newQuery = Constants.getTreeOfText(Constants.treeToString(parser, newOneQuery)).query();
            PAGE greater = getGreaterPage(element.getPage(), tree.getPage());
            generatedQueries.add(new SPARQLQueryGenerated(newQuery, greater));
        }
    }

    /**
     * If we have the a query containing a composant from the third PAGE and another one from the second PAGE, it should return the third page.
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
     * Remove the TRIPLES node that contains truc in an <strong>optionnal context </strong>, when we set optinonal to false. <br>
     *
     * @param tree the tree which we'll delete the optionnal from it
     * @param truc remove from the tree
     */
    private void removeOptionnalTrucFromTree(SPARQLQueryGenerated tree, Truc truc) {
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
            generatedQueries.add(new SPARQLQueryGenerated(newQuery, tree.getPage()));
        }
    }

    /**
     * Count how much triplesblocks this tripleBlocksRoot contains
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
     * Add the new triples generated to the tree.
     * <br>
     * If the last child of triplesblocks is another triples blocks,, we get this element and add it at the end of our generated triples.
     * <br>
     * Otherwise, we remove the old triples containing the truc, and add the new triples. <br>
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
     * Add filter e to the end of it's groupGraphPattern, if it dosen't exist already. Filter exists if the truc already exist in the truc's list.
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
     * Find in the NEW tree (that's why we can't use the already defined find function) the first parent using the find() function of the truc structure.
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
     * Check if there are any truc left in the generatedQueries list.
     *
     * @return boolean containing the truc
     */
    private boolean containsTruc() {
        ArrayList<SPARQLQueryGenerated> checkList = new ArrayList<>();
        checkList.addAll(generatedQueries);
        for (SPARQLQueryGenerated tree : checkList) {
            if (XPath.findAll(tree.getQuery(), "//truc", parser).size() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the tree contains this filter in order to remove redudant filter (when there are multiple trucs in the same triple).
     *
     * @param groupGraphPattern the groupGraphPattern root of the truc
     * @param filterText        filter string
     * @return boolean indicating if the query contains this filter or not
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

    /**
     * Find the Truc from the truc's list, this SPARQL variable belongs to.
     *
     * @param variable SPARQL variable, belongs to a truc  (like SimplePARQL_,label_,etc..)
     * @return boolean indicating if the query's truc contains this variable or not
     */
    public Truc find(String variable) {
        for (Truc truc : simpleARQLTrucs) {
            for (Map.Entry mapentry : truc.getVariables().entrySet()) {
                if (mapentry.getValue().toString().trim().equals("?" + variable.trim())) {
                    return truc;
                }
            }
        }
        return null;
    }

    public String toString() {
        String result = "";
        for (SPARQLQueryGenerated generatedTree : generatedQueries) {
            result += "Page: " + generatedTree.getPage() + "\n";
            result += Constants.treeToStringFormatted(parser, generatedTree.getQuery()) + "\n";
        }
        result += generatedQueries.size() + " queries generated." + "\n";
        result += simpleARQLTrucs.toString() + "\n";
        result += simpleARQLTrucs.size() + " truc in the query.";
        return result;
    }
}
