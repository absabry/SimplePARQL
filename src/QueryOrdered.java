import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.util.ArrayList;
import java.util.Collection;

class QueryOrdered {
    private SimplePARQLParser parser;

    QueryOrdered(SimplePARQLParser parser) {
        this.parser = RearrangeQuery(parser);
    }

    public SimplePARQLParser getParser() {
        return parser;
    }

    private static SimplePARQLParser RearrangeQuery(SimplePARQLParser parser) {
        ParserRuleContext query = parser.query();
        ArrayList<String> triples = new ArrayList<>();
        Collection<ParseTree> triplesSameSubjects = XPath.findAll(query, "//triplesSameSubject", parser);
        for (ParseTree triplesSameSubject : triplesSameSubjects) {
            triples.clear();
            triples = getComposantesOfTriples(triplesSameSubject, triples);
            if (triples.size() > 1) {
                createNewChildren(triplesSameSubject, triples);
            }
        }
        return Functions.getTreeOfText(Functions.treeToString(parser, query));
    }

    private static ArrayList<String> getComposantesOfTriples(ParseTree triplesSameSubject, ArrayList<String> triples) {
        triples.clear();
        String subject = triplesSameSubject.getChild(0).getText();
        ParseTree propretyListNotEmpty = triplesSameSubject.getChild(1);
        for (int i = 0; i < propretyListNotEmpty.getChildCount(); i += 3) { // predicate,object utilisé, puis le ";" qui nous sert a rien
            ParseTree predicate = propretyListNotEmpty.getChild(i);
            ParseTree object = propretyListNotEmpty.getChild(i + 1);
            for (int j = 0; j < object.getChildCount(); j += 2) { //puis le "," qui nous sert a rien
                triples.add(subject + " " + predicate.getText() + " " + object.getChild(j).getText() + " .");
            }
        }
        return triples;
    }

    private static void createNewChildren(ParseTree triplesSameSubject, ArrayList<String> triples) {
        ParserRuleContext triplesBlocks = (ParserRuleContext) triplesSameSubject.getParent();
        int triplesBlocksIndex = Functions.getNodeIndex(triplesBlocks);
        ParseTree lastChild = triplesBlocks.getChild(triplesBlocks.getChildCount() - 1);
        SimplePARQLParser triplesTree = Functions.getTreeOfText(String.join("\n", triples));
        ParserRuleContext newTriplesBlock = triplesTree.triplesBlock();
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

}
