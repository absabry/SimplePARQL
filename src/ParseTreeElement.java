import javafx.util.Pair;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ParseTreeElement {
    private ArrayList<Pair<ParserRuleContext, Integer>> parents;
    protected POSITION position;
    protected ArrayList<Pair<String, String>> generatedTriples;
    protected int counter;

    ParseTreeElement(ParseTree node, int counter) {
        parents = new ArrayList<>();
        generatedTriples = new ArrayList<>();
        this.counter = counter;
        createParentTree(node);
        computePosition();
    }

    int getCounter() {
        return counter;
    }

    String getName() {
        return parents.get(0).getKey().getText();
    }

    ArrayList<Pair<ParserRuleContext, Integer>> getParents() {
        return parents;
    }

    ArrayList<Pair<String, String>> getGeneratedTriples() {
        return generatedTriples;
    }

    // create parent tree of the "truc" (the path up to the root)
    private void createParentTree(ParseTree node) {
        int ruleTriplesBlock = SimplePARQLParser.RULE_query;
        int ruleIndex = -1;
        while (ruleIndex != ruleTriplesBlock) {
            ParserRuleContext elementNode = (ParserRuleContext) node;
            ruleIndex = elementNode.getRuleIndex();
            parents.add(new Pair<>(elementNode, Functions.getNodeIndex(node)));
            node = node.getParent();
        }
    }

    // compute the position of the "truc" directly after adding it
    private void computePosition() {
        position = POSITION.SUBJECT;
        if (find(SimplePARQLParser.RULE_verb) != null) {
            position = POSITION.PREDICATE;
        } else if (find(SimplePARQLParser.RULE_object) != null) {
            position = POSITION.OBJECT;
        }
    }

    // find in the parents of "truc" the ParseRuleContext when want
    private Pair<ParserRuleContext, Integer> find(int ruleIndex) {
        for (Pair<ParserRuleContext, Integer> pair : parents) {
            if (pair.getKey().getRuleIndex() == ruleIndex) {
                return pair;
            }
        }
        return null;
    }

    // get subject, pred and object
    protected Map<POSITION, String> getTriplesComposantes() {
        if (parents.size() != 0) {
            Map<POSITION, String> triplesComposantes = new HashMap<>();
            Pair<ParserRuleContext, Integer> triplesSameSubject = find(SimplePARQLParser.RULE_triplesSameSubject);
            if (triplesSameSubject != null) {
                triplesComposantes.put(POSITION.SUBJECT, triplesSameSubject.getKey().getChild(0).getText());
                ParserRuleContext propretyList = (ParserRuleContext) triplesSameSubject.getKey().getChild(1);
                if (position == POSITION.SUBJECT) {
                    triplesComposantes.put(POSITION.PREDICATE, propretyList.getChild(0).getText());
                    triplesComposantes.put(POSITION.OBJECT, propretyList.getChild(1).getText()); // element juste après c'est son object
                } else if (position == POSITION.PREDICATE) {
                    Pair<ParserRuleContext, Integer> verb = find(SimplePARQLParser.RULE_verb);
                    if (verb != null) {
                        triplesComposantes.put(POSITION.PREDICATE, verb.getKey().getText());
                        triplesComposantes.put(POSITION.OBJECT, propretyList.getChild(verb.getValue() + 1).getText()); // element juste après c'est son object
                    }
                } else if (position == POSITION.OBJECT) {
                    Pair<ParserRuleContext, Integer> objectList = find(SimplePARQLParser.RULE_objectList);
                    if (objectList != null) {
                        triplesComposantes.put(POSITION.PREDICATE, propretyList.getChild(objectList.getValue() - 1).getText());// element juste avant c'est son predicate
                        triplesComposantes.put(POSITION.OBJECT, objectList.getKey().getText());
                    }
                }
                return triplesComposantes;
            }
        }
        return null;
    }

    String printParentPath(SimplePARQLParser parser) {
        String result = "";
        for (Pair<ParserRuleContext, Integer> rule : this.getParents()) {
            result += getRuleName(rule.getKey(), parser) + " " + rule.getValue().toString();
            result += "\n";
        }
        return result;
    }

    private String getRuleName(ParserRuleContext rule, SimplePARQLParser parser) {
        int ruleIndex = rule.getRuleIndex();
        return parser.getRuleNames()[ruleIndex];
    }

    public String toString() {
        return "Truc: " + parents.get(0).getKey().getText() + " nommé : " + Constants.VARIABLE + counter + " Position: " + position;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof ParseTreeElement)) return false;
        return (parents.get(0).getKey().getText()).equals(((ParseTreeElement) other).getParents().get(0).getKey().getText());
    }

}
