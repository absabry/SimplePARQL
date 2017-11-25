package fr.esilv.simpleparql.source.converter;

import com.google.common.collect.Iterables;
import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import fr.esilv.simpleparql.source.converter.filter.FilterDefault;
import fr.esilv.simpleparql.source.converter.filter.FilterGenerator;
import fr.esilv.simpleparql.source.converter.query.SPARQLQueryGenerator;
import fr.esilv.simpleparql.source.model.*;
import fr.esilv.simpleparql.source.configuration.QueryConfig;
import fr.esilv.simpleparql.source.server.Server;
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
 * <strong>optional:</strong> convert the Truc in the optional field<br>
 * <strong>ignoredConfig: List of proprties we want to exlude from our request</strong><br>
 */
public class SparqlQueries {

    private ArrayList<Truc> simpleARQLTrucs;
    private ArrayList<SPARQLQueryGenerated> generatedQueries;
    private SimplePARQLParser parser;
    private int counter = 0;
    private FilterGenerator filterGenerator;
    private boolean optional;
    private ArrayList<String> ignoredConfig;
    private Logger logger;

    public SparqlQueries(SimplePARQLParser parser, FilterGenerator filterGenerator, boolean optional, QueryConfig ignoredConfig) throws IOException {
        this.filterGenerator = filterGenerator;
        this.optional = optional;
        simpleARQLTrucs = new ArrayList<>();
        generatedQueries = new ArrayList<>();
        this.parser = parser;
        this.ignoredConfig = ignoredConfig.getIgnoredProprieties();
        ParserRuleContext query = this.parser.query();
        generatedQueries.add(new SPARQLQueryGenerated(query, PAGE.FIRST));
        logger = Server.logger;
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

    public ArrayList<SPARQLQueryGenerated> getGeneratedQueries() {
        return generatedQueries;
    }

    /**
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
     * 4.Finally we generate cartesian product of the truc, after checking the optional field (we can disable trucs from the optional composant) <br>
     */
    private void generatedSPARQLQueries() throws IOException {
        // clone generated tree
        ArrayList<SPARQLQueryGenerated> oldGeneratedTrees = new ArrayList<>();
        oldGeneratedTrees.addAll(generatedQueries);
        logger.debug("begin generate function");
        for (SPARQLQueryGenerated oldGenereatedTree : oldGeneratedTrees) {
            logger.debug("current tree is: ");
            logger.debug(oldGenereatedTree);
            Collection<ParseTree> trucs = XPath.findAll(oldGenereatedTree.getQuery(), "//trucSujet", parser);
            if (trucs.size() > 0) {
                logger.debug("We're in trucSujet");
                Truc truc = createTruc(Iterables.get(trucs, 0));
                if (truc.isOptionnal() && !optional) {
                    simpleARQLTrucs.remove(truc);
                    removeOptionnalTrucFromTree(oldGenereatedTree, truc);
                } else {
                    generateCartesianProductTrees(oldGenereatedTree, truc);
                }
            } else {
                logger.debug("We're not in trucSujet");
                trucs = XPath.findAll(oldGenereatedTree.getQuery(), "//trucPredicat", parser);
                if (trucs.size() > 0) {
                    logger.debug("We're in trucPredicat");
                    Truc truc = createTruc(Iterables.get(trucs, 0));
                    if (truc.isOptionnal() && !optional) {
                        simpleARQLTrucs.remove(truc);
                        removeOptionnalTrucFromTree(oldGenereatedTree, truc);
                    } else {
                        generateCartesianProductTrees(oldGenereatedTree, truc);
                    }
                } else {
                    logger.debug("We're not in trucPredicat");
                    trucs = XPath.findAll(oldGenereatedTree.getQuery(), "//trucObject", parser);
                    if (trucs.size() > 0) {
                        logger.debug("We're in trucObject");
                        Truc truc = createTruc(Iterables.get(trucs, 0));
                        if (truc.isOptionnal() && !optional) {
                            simpleARQLTrucs.remove(truc);
                            removeOptionnalTrucFromTree(oldGenereatedTree, truc);
                        } else {
                            generateCartesianProductTrees(oldGenereatedTree, truc);
                        }
                    }
                    else{
                        logger.debug("We're not in trucObject");
                    }
                }
            }
        }
        generatedQueries.removeAll(oldGeneratedTrees); // remove old tree from the edited one
        // recursive call if the list still containing at least one truc
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
            if (simpleARQLTruc.getText().equals(trucInTree.getText())) {
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
     * Create a SPARQLQueryGenerator element, that will create all of the filter, triples and PAGE of the truc. <br>
     * we'll just remove the truc and remplace it with the variable corresponding to the truc. <br>
     * foreach Composant item created (containg filter and triples), we clone the tree, create a new one and attach the new filter and the new triple
     * and finally we add the result to the generatedQueries List. <br>
     *
     * @param tree original tree to get it's query and it's page
     * @param truc truc which we add it's triples and filter to the new query
     */
    private void generateCartesianProductTrees(SPARQLQueryGenerated tree, Truc truc) throws IOException {
        // geenrate queries of this truc only
        SPARQLQueryGenerator generateQuery = new SPARQLQueryGenerator(truc, filterGenerator, ignoredConfig);
        // add those just generated composants to all queries in the generatedQueries (not the clone of generatedQueries)
        for (Composant element : generateQuery.getGeneratedComposants()) {
            SimplePARQLParser newOne = Constants.getTreeOfText(Constants.treeToString(parser, tree.getQuery()));
            ParserRuleContext newOneQuery = newOne.query();
            Pair<ParserRuleContext, Integer> groupGraphPattern = findInTree(newOneQuery, truc, SimplePARQLParser.RULE_groupGraphPattern);
            Pair<ParserRuleContext, Integer> triplesBlocks = findInTree(newOneQuery, truc, SimplePARQLParser.RULE_triplesBlock);
            String triples = ""; // all triples to be added to the tree
            for (String triple : element.getTriples()) {
                // check if the triple already exists in the query,
                // would be true when there are more than one truc,
                // and this isn"t the first one
                if (groupGraphPattern != null && !containsTriple(groupGraphPattern.getKey(), triple.replace(".", ""))) {
                    triples += triple + "\n";
                }
            }
            // add the triples if there is at least one
            if (!triples.equals("")) {
                addTripleToTree(triplesBlocks, triples);
            }
            boolean filterAddIt = true;
            for (String filter : element.getFilters()) {
                boolean tmp = addFilterToTree(groupGraphPattern, filter);
                if (!tmp)  // if one filter already exists, we will not check it's page (to not influence the results)
                    filterAddIt = false;
            }
            ParserRuleContext newQuery = Constants.getTreeOfText(Constants.treeToString(parser, newOneQuery)).query();
            PAGE greater = tree.getPage();
            if (filterAddIt)
                greater = getGreaterPage(element.getPage(), tree.getPage()); // check for which page the query will belong to

            addToGeneratedQueries(new SPARQLQueryGenerated(newQuery, greater));
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
        if (page1 == null) {
            return page2;
        }
        if (page2 == null) {
            return page1;
        }
        if (page1.compareTo(page2) > 0) {
            return page1;
        }
        return page2;
    }

    /**
     * Remove the TRIPLES node that contains truc in an <strong>optional context </strong>, when we set optinonal to false. <br>
     *
     * @param tree the tree which we'll delete the optional from it
     * @param truc remove from the tree
     */
    private void removeOptionnalTrucFromTree(SPARQLQueryGenerated tree, Truc truc) {
        ParserRuleContext clonedQuery = Constants.getTreeOfText(Constants.treeToString(parser, tree.getQuery())).query();
        Pair<ParserRuleContext, Integer> triplesBlocks = findInTree(clonedQuery, truc, SimplePARQLParser.RULE_triplesBlock);
        Pair<ParserRuleContext, Integer> graphPatternNotTriples = findInTree(clonedQuery, truc, SimplePARQLParser.RULE_graphPatternNotTriples);
        if (triplesBlocks != null) {
            ParserRuleContext tripleBlocksParent = triplesBlocks.getKey().getParent();
            ParseTree lastChild = triplesBlocks.getKey().children.get(triplesBlocks.getKey().children.size() - 1);
            if (lastChild instanceof SimplePARQLParser.TriplesBlockContext) {
                tripleBlocksParent.children.set(triplesBlocks.getValue(), lastChild);  // replace the old child with the last child
            } else {
                if (tripleBlocksParent instanceof SimplePARQLParser.GroupGraphPatternContext && countTripleBlocks(triplesBlocks.getKey()) == 0) {
                    // we have to delete the optional node because it will be empty after deleting the triple of TRUC
                    if (graphPatternNotTriples != null) {
                        graphPatternNotTriples.getKey().getParent().children.set(graphPatternNotTriples.getValue(), new ParserRuleContext());
                    }
                } else {
                    tripleBlocksParent.children.set(triplesBlocks.getValue(), new ParserRuleContext()); // replace the old child with a new EMPTY one
                }
            }


            ParserRuleContext newQuery = Constants.getTreeOfText(Constants.treeToString(parser, clonedQuery)).query(); // create the new tree
            addToGeneratedQueries(new SPARQLQueryGenerated(newQuery, tree.getPage()));
        }
    }

    /**
     * Add element to the final list, with some inspection..
     * he cannot add it if it's already (exactly) contained in the queries.
     *
     * @param sparqlQueryGenerated element we want to add to the list
     */
    private void addToGeneratedQueries(SPARQLQueryGenerated sparqlQueryGenerated) {
        if (!generatedQueries.contains(sparqlQueryGenerated)) {
            generatedQueries.add(sparqlQueryGenerated);
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
            // usally, this is true when there is two or more triples in the block
            if (lastChild.getClass() == SimplePARQLParser.TriplesBlockContext.class) {
                triplesBlocks.getKey().getParent().children.set(triplesBlocks.getValue(), newTriplesForTrucs); // replace the last child with the new triple
                ParseTree child = newTriplesForTrucs;
                // get the last triple of this block
                while (child.getChild(child.getChildCount() - 1) instanceof ParserRuleContext) {
                    child = child.getChild(child.getChildCount() - 1);
                }
                //add the lastchild to the last triple block, after adding newTriplesForTrucs
                ((ParserRuleContext) child).addChild((ParserRuleContext) lastChild);
            } else {
                triplesBlocks.getKey().getParent().children.set(triplesBlocks.getValue(), newTriplesForTrucs); // add it directly
            }
        }
    }

    /**
     * Add filter e to the end of it's groupGraphPattern, if it dosen't exist already. Filter exists if the truc already exist in the truc's list.
     *
     * @param groupGraphPattern root of the truc
     * @param filterText        text of the filter we want to add to the tree
     * @return boolean, indicating if the filter is added to the query.
     */
    private boolean addFilterToTree(Pair<ParserRuleContext, Integer> groupGraphPattern, String filterText) {
        if (groupGraphPattern != null && !containsFilter(groupGraphPattern.getKey(), filterText)) {
            SimplePARQLParser.GraphPatternNotTriplesContext graphPatternNotTriplesContext
                    = new SimplePARQLParser.GraphPatternNotTriplesContext(groupGraphPattern.getKey(), 1);
            // keep the closed brackets to use it later
            ParseTree closedBrackets = groupGraphPattern.getKey().getChild(groupGraphPattern.getKey().getChildCount() - 1);
            // replace closed bracket with the new graph pattern
            groupGraphPattern.getKey().children.set(groupGraphPattern.getKey().getChildCount() - 1, graphPatternNotTriplesContext);
            groupGraphPattern.getKey().addChild((TerminalNodeImpl) closedBrackets);
            graphPatternNotTriplesContext.addChild(Constants.getTreeOfText(filterText).filter());
            return true;
        }
        return false;
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
        Pair<ParserRuleContext, Integer> tempElement = new Pair<>(tree, -1);
        tempList.add(tempElement);

        // clone parent list of the truc, from end to begining to add the query at first position, and truc at last position
        for (int i = element.getParents().size() - 1; i > 0; i--) {
            Pair<ParserRuleContext, Integer> next = element.getParents().get(i - 1);
            tempElement = new Pair<>((ParserRuleContext) tempElement.getKey().getChild(next.getValue()), next.getValue());
            tempList.add(tempElement);
        }

        // get first leaf having the rule index we're searching for
        // in reverse mode, to begin with the truc to his ancestors
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
            if (XPath.findAll(tree.getQuery(), "//trucSujet", parser).size() > 0
                    || XPath.findAll(tree.getQuery(), "//trucPredicat", parser).size() > 0
                    || XPath.findAll(tree.getQuery(), "//trucObject", parser).size() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the tree contains this filter in order to remove redudant filter (when there are multiple trucs in the same query).
     *
     * @param groupGraphPattern the groupGraphPattern root of the truc
     * @param filterText        filter string
     * @return boolean indicating if the query contains this filter or not
     */
    private boolean containsFilter(ParserRuleContext groupGraphPattern, String filterText) {
        Collection<ParseTree> filters = XPath.findAll(groupGraphPattern, "//filter", parser);
        filterText = filterText.replace(" ", ""); // ignore spaces in the comparaison, filter comming from the query is without any spaces
        for (ParseTree filter : filters) {
            if (filter.getText().replace(" ", "").contains(filterText)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the tree contains this triplet in order to remove redudant triplet (when there are multiple trucs in the same query).
     *
     * @param groupGraphPattern the groupGraphPattern root of the truc
     * @param triplesText       one triplet text (without the point)
     * @return boolean indicating if the query contains this filter or not
     */
    private boolean containsTriple(ParserRuleContext groupGraphPattern, String triplesText) {
        Collection<ParseTree> triples = XPath.findAll(groupGraphPattern, "//triplesSameSubject", parser);
        triplesText = triplesText.replace(" ", ""); // ignore spaces in the comparaison, filter comming from the query is without any spaces
        for (ParseTree triple : triples) {
            if (triple.getText().replace(" ", "").contains(triplesText))
                return true;
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
