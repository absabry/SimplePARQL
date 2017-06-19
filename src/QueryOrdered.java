import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.util.ArrayList;
import java.util.Collection;

/**
 * BUG: TODO  Quand on a deux d'affilé, il ne prend pas le deuxième, il faut faire un appel récursive parce que le "truc" est déjà supprimé.
 * order the query to have triples only
 * ?a ?b ?c;?d ?e,?f
 * ==>
 * ?a ?b ?c .
 * ?a ?d ?e .
 * ?a ?d ?e
 */
class QueryOrdered {
    private SimplePARQLParser parser;

    QueryOrdered(SimplePARQLParser parser) {
        this.parser = RearrangeQuery(parser);
    }

    public SimplePARQLParser getParser() {
        return parser;
    }

    /**
     * Main function to re order the query
     *
     * @param parser parser of the SimpleARQL query we get from the user
     * @return new SimplePARQLParser with the new triples instead of the triples "," and ";"
     */
    private static SimplePARQLParser RearrangeQuery(SimplePARQLParser parser) {
        ParserRuleContext query = parser.query();
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
     * Remove all of shortcuts Sparql offer and
     * rearrange to be triples ONLY (wthout any ";" or "," in the triple)
     *
     * @param triplesSameSubject the root of the triples composants
     * @return List of triples
     */
    private static ArrayList<Triple> getComposantesOfTriples(ParseTree triplesSameSubject) {
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
     * add the new childen to the triple and remove the old one
     *
     * @param triplesSameSubject the root of the composant
     * @param triples            the new triples list, we convert it to tree then use it
     */
    private static void createNewChildren(ParseTree triplesSameSubject, ArrayList<Triple> triples) {
        ParserRuleContext triplesBlocks = (ParserRuleContext) triplesSameSubject.getParent();
        int triplesBlocksIndex = Constants.getNodeIndex(triplesBlocks);
        ParseTree lastChild = triplesBlocks.getChild(triplesBlocks.getChildCount() - 1);
        ParserRuleContext newTriplesBlock = Constants.getTreeOfText(join(triples, "\n")).triplesBlock();
        if (lastChild.getClass() == SimplePARQLParser.TriplesBlockContext.class) {
            triplesBlocks.getParent().children.set(triplesBlocksIndex, newTriplesBlock);
            ParseTree child = newTriplesBlock;
            while (child.getChild(child.getChildCount() - 1) instanceof ParserRuleContext) {
                child = child.getChild(child.getChildCount() - 1);
            }
            ((ParserRuleContext) child).addChild((ParserRuleContext) lastChild);
        } else {
            triplesBlocks.getParent().children.set(triplesBlocksIndex, newTriplesBlock);
        }
    }

    /**
     * join list of items with some delimter
     *
     * @param list     items of list
     * @param delimter between the items of list
     * @return Joined string of the list of items
     */
    private static String join(ArrayList<Triple> list, String delimter) {
        String result = "";
        for (Object object : list) {
            result += object.toString() + delimter;
        }
        return result;
    }

}
