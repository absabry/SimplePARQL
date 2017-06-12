import javafx.util.Pair;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;

class Truc {
    private ArrayList<Pair<ParserRuleContext, Integer>> parents;
    private String name;
    private POSITION position;
    private int counter;
    private HashMap<POSITION, String> currentTriple;
    private HashMap<VARIABLES, String> variables;

    Truc(ParseTree node, int counter) {
        parents = new ArrayList<>();
        name = node.getText();
        this.counter = counter;
        createParentTree(node);
        computePosition();
        generateVariables();
        generateTripleComposantes();
    }

    int getCounter() {
        return counter;
    }

    POSITION getPosition() {
        return position;
    }

    HashMap<POSITION, String> getCurrentTriple() {
        return currentTriple;
    }

    HashMap<VARIABLES, String> getVariables() {
        return variables;
    }

    String getName() {
        return name;
    }

    ArrayList<Pair<ParserRuleContext, Integer>> getParents() {
        return parents;
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

    //subject, pred and object
    private void generateTripleComposantes() {
        if (parents.size() != 0) {
            Pair<ParserRuleContext, Integer> triplesSameSubject = find(SimplePARQLParser.RULE_triplesSameSubject);
            if (triplesSameSubject != null) {
                ParserRuleContext propretyList = (ParserRuleContext) triplesSameSubject.getKey().getChild(1);
                currentTriple = new HashMap<>();
                currentTriple.put(POSITION.SUBJECT, position == POSITION.SUBJECT ?
                        clean(triplesSameSubject.getKey().getChild(0).getText()) : (triplesSameSubject.getKey().getChild(0).getText()));
                currentTriple.put(POSITION.PREDICATE, position == POSITION.PREDICATE ?
                        clean(propretyList.getChild(0).getText()) : propretyList.getChild(0).getText());
                currentTriple.put(POSITION.OBJECT, position == POSITION.OBJECT ?
                        clean(propretyList.getChild(1).getText()) : propretyList.getChild(1).getText());
            }
        }
    }

    private String clean(String text) {
        return text.replace("\"", "").replace("#", "");
    }

    // ?SPARQL_1, label_1, etc...
    private void generateVariables() {
        variables = new HashMap<>();
        variables.put(VARIABLES.VARIABLE, Constants.VARIABLE + counter);
        variables.put(VARIABLES.LABEL, Constants.VARIABLE_LABEL + counter);
        variables.put(VARIABLES.TMP1, Constants.VARIABLE_TMP_1 + counter);
        variables.put(VARIABLES.TMP2, Constants.VARIABLE_TMP_2 + counter);
    }

    boolean isOptionnal() {
        return find(SimplePARQLParser.RULE_optionalGraphPattern) != null;
    }

    // debug only
    String printParentPath(SimplePARQLParser parser) {
        String result = "";
        for (Pair<ParserRuleContext, Integer> rule : this.getParents()) {
            result += getRuleName(rule.getKey(), parser) + " " + rule.getValue().toString();
            result += "\n";
        }
        return result;
    }

    // debug only
    private String getRuleName(ParserRuleContext rule, SimplePARQLParser parser) {
        int ruleIndex = rule.getRuleIndex();
        return parser.getRuleNames()[ruleIndex];
    }

    public String toString() {
        return "Truc: " + name + " nommé : " + variables.toString() + " Position: " + position;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Truc)) return false;
        return (name).equals(((Truc) other).getName());
    }

}
