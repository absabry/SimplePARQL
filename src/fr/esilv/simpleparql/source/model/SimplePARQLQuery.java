package fr.esilv.simpleparql.source.model;

import com.google.common.collect.Iterables;
import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class will handle the SimplePARQL query, get some informations about it and transform some parts of it before the conversion.
 * It will order the query to have triples only. Here's a simple example of what this class do: <br>
 * ?a ?b ?c;?d ?e,?f  <br>
 * the jenaresult will be: <br>
 * ?a ?b ?c . <br>
 * ?a ?d ?e . <br>
 * ?a ?d ?e. <br>
 * <p>
 * It will add the predefined prefixes indicated in the configuration of the query.
 * It will also replace the select clause with the select all clause.
 * The results will be filtred after treatement to return what the user wish.<br>
 */
public class SimplePARQLQuery {
    private SimplePARQLParser parser;
    private ArrayList<String> selectedVariables;
    private ArrayList<String> predefinedPrefixes;
    private boolean selectAll;
    private boolean isSPARQL;

    public SimplePARQLQuery(SimplePARQLParser parser, ArrayList<String> predefinedPrefixes) {
        selectAll = false;
        this.predefinedPrefixes = predefinedPrefixes;
        this.parser = RearrangeQuery(computeAndReplaceSelectedVariables(parser));
    }

    public SimplePARQLParser getParser() {
        return parser;
    }

    /**
     * Set the boolean value of isSPARQL.
     *
     * @param query  the query's root
     * @param parser SimplePARQL parser
     */
    private void setIsSPARQL(SimplePARQLParser parser, ParserRuleContext query) {
        Collection<ParseTree> trucSujet = XPath.findAll(query, "//trucSujet", parser); // search for all trucs in the query
        Collection<ParseTree> trucPredicat = XPath.findAll(query, "//trucPredicat", parser); // search for all trucs in the query
        Collection<ParseTree> trucObject = XPath.findAll(query, "//trucObject", parser); // search for all trucs in the query
        isSPARQL = trucSujet.size() == 0 && trucPredicat.size() == 0 && trucObject.size() == 0;
    }

    /**
     * Check if the query is SimplePARQL query, or a SPARQL query.
     *
     * @return boolean determining if the query is a SPARQL query
     */
    public boolean isSPARQL() {
        return isSPARQL;
    }

    /**
     * @return selected variables from the original query
     */
    public ArrayList<String> getSelectedVariables() {
        return selectedVariables;
    }

    public boolean isSelectAll() {
        return selectAll;
    }

    /**
     * Main function to re-order the query.
     *
     * @param parser parser of the SimpleARQL query we get from the user's query
     * @return new SimplePARQLParser with the new triples instead of the triples "," and ";"
     */
    private SimplePARQLParser RearrangeQuery(SimplePARQLParser parser) {
        ParserRuleContext query = parser.query();
        setIsSPARQL(parser, query); // check if the query is a SPARQL one.
        addPredefinedPrefixes(parser, query);
        ArrayList<Triple> triples;
        Collection<ParseTree> triplesSameSubjects = XPath.findAll(query, "//triplesSameSubject", parser);
        for (ParseTree triplesSameSubject : triplesSameSubjects) {
            triples = getComposantesOfTriples(triplesSameSubject);
            if (triples.size() > 1) {
                createNewChildren(triplesSameSubject, triples);
            }
        }
        return Constants.getTreeOfText(Constants.treeToString(parser, query));
    }

    /**
     * Remove all SPARQL's shortcuts and rearrange to be triples ONLY (wthout any ";" or "," in the triple)
     *
     * @param triplesSameSubject the root of the triples composants
     * @return List of triples
     */
    private ArrayList<Triple> getComposantesOfTriples(ParseTree triplesSameSubject) {
        ArrayList<Triple> triples = new ArrayList<>();
        String subject = triplesSameSubject.getChild(0).getText(); // subject is always the first child of triplesSameSubject
        ParseTree propretyListNotEmpty = triplesSameSubject.getChild(1); // the root of predicate and objects is always the second child of triplesSameSubject
        for (int i = 0; i < propretyListNotEmpty.getChildCount(); i += 3) { // predicate,object and skip the ";" ==> +=3
            ParseTree predicate = propretyListNotEmpty.getChild(i); // first child of proprety list is the predicate
            ParseTree object = propretyListNotEmpty.getChild(i + 1); // second child is the object (or list of objects) belongs to this predicate
            for (int j = 0; j < object.getChildCount(); j += 2) { // skip the "," between objects ==> +=2
                triples.add(new Triple(subject, predicate.getText(), object.getChild(j).getText()));
            }
        }
        return triples;
    }

    /**
     * Add the new childen to the triple and remove the old one.
     * This will be used when shortcuts are found and should be removed to create normal triples.
     *
     * @param triplesSameSubject the root of the composant
     * @param triples            the new triples list, we convert it to tree then use it
     */
    private void createNewChildren(ParseTree triplesSameSubject, ArrayList<Triple> triples) {
        ParserRuleContext triplesBlocks = (ParserRuleContext) triplesSameSubject.getParent();
        int triplesBlocksIndex = Constants.getNodeIndex(triplesBlocks);
        ParseTree triplesBlocksLastChild = triplesBlocks.getChild(triplesBlocks.getChildCount() - 1);
        ParserRuleContext newTriplesBlock = Constants.getTreeOfText(join(triples, "\n")).triplesBlock();
        if (triplesBlocksLastChild.getClass() == SimplePARQLParser.TriplesBlockContext.class) {
            triplesBlocks.getParent().children.set(triplesBlocksIndex, newTriplesBlock);
            ParseTree child = newTriplesBlock;
            // get the last triple of this recusrive rules
            while (child.getChild(child.getChildCount() - 1) instanceof ParserRuleContext) {
                child = child.getChild(child.getChildCount() - 1);
            }
            // add the last child of the newtriplebloc when it's free
            ((ParserRuleContext) child).addChild((ParserRuleContext) triplesBlocksLastChild);
        } else {
            triplesBlocks.getParent().children.set(triplesBlocksIndex, newTriplesBlock); // add it directly
        }
    }

    /**
     * Join list of items with some delimter
     *
     * @param list     items of list
     * @param delimter between the items of list
     * @return Joined string of the list of items
     */
    private String join(ArrayList<Triple> list, String delimter) {
        String result = "";
        for (Object object : list) {
            result += object.toString() + delimter;
        }
        return result;
    }

    /**
     * Compute the selected variables from the original SimplePARQL query
     * and replace the select clause of the query with the "Select * ". <br>
     *
     * @param parser parser of the SimpleARQL query we get from the user's query
     */
    private SimplePARQLParser computeAndReplaceSelectedVariables(SimplePARQLParser parser) {
        selectedVariables = new ArrayList<>();
        ParserRuleContext newSelectQuery = Constants.getTreeOfText("SELECT * ").selectQuery(); // new select query
        ParserRuleContext query = parser.query();
        ParserRuleContext selectQuery = (ParserRuleContext) Iterables.get(XPath.findAll(query, "//selectQuery", parser), 0); // there is only one leaf of selectQuery in the query

        for (ParseTree variable : selectQuery.children) {

            if (!variable.getText().toUpperCase().equals("SELECT") && !variable.getText().toUpperCase().equals("DISTINCT")) {
                if (variable.getText().trim().equals("*")) {
                    selectAll = true;
                    break;
                }
                selectedVariables.add(variable.getText().replace("?", "")); // name of variable without "?"
            }
        }
        int indexOfSelectQuery = Constants.getNodeIndex(selectQuery);// index of the select query in his parent
        selectQuery.getParent().children.set(indexOfSelectQuery, newSelectQuery);
        return Constants.getTreeOfText(Constants.treeToString(parser, query));
    }

    /**
     * Adding the prefixes rdf and rdfs in the begining of the query. Some of those prefixes are mandatory for the Jena API,
     * that's why we add it if the user forgot about them. We're adding also some other prefixes we think the user could use more than others,
     * he can though add all other prefixes he wants to.
     *
     * @param query root of the query, we kept the query and not the prologue for handling the case where the user not mention any prefixe
     */
    private void addPredefinedPrefixes(SimplePARQLParser parser, ParserRuleContext query) {
        Collection<ParseTree> prologues = XPath.findAll(query, "//prologue", parser);
        if (prologues.size() == 0) {
            query.addChild(Constants.getTreeOfText(String.join("\n", predefinedPrefixes)).prologue());
        } else {
            ParserRuleContext prologue = (ParserRuleContext) Iterables.get(prologues, 0);
            for (String prefixe : predefinedPrefixes) {
                if (!containsPrefix(prologue, prefixe, parser)) {
                    prologue.addChild(Constants.getTreeOfText(prefixe).prefixDecl());
                }
            }
        }
    }

    /**
     * Check if the generatedTree containing this prefix, in order to add  pre-defined prefixes to the query if it's not already added
     *
     * @param prologue the prefixes root of the query
     * @param prefix   prefix string
     * @return boolean indicating if the query contains this prefix or not
     */
    private boolean containsPrefix(ParserRuleContext prologue, String prefix, SimplePARQLParser parser) {
        Collection<ParseTree> prefixDecls = XPath.findAll(prologue, "//prefixDecl", parser);
        for (ParseTree prefixDecl : prefixDecls) {
            prefix = prefix.replace(" ", ""); // ignore spaces in the comparaison, filter comming from the query is without any spaces
            if (prefixDecl.getText().contains(prefix)) {
                return true;
            }
        }
        return false;
    }

}
