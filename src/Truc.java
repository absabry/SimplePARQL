import javafx.util.Pair;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Strcutre truc, contains:
 * name
 * position in the triple
 * counter (when we will convert it, we create variables)
 * currentTriple is the complete triple where the "truc" are
 * variables is the variable we generate to this truc (?SimpleARQL_, label_,etc...)
 * <p>
 * When we create a truc, we generate everything in thr constructor directly
 * We create their parent tree to the query
 * We compute their position in the triple
 * We generate the variables
 * generate their new triples
 */

class Truc {
    private ArrayList<Pair<ParserRuleContext, Integer>> parents;
    private String name;
    private POSITION position;
    private int counter;
    private Triple currentTriple;
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

    Triple getCurrentTriple() {
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

    /**
     * create parent tree of the "truc" (the path up to the root (query))
     *
     * @param node the truc in the orignial tree
     */
    private void createParentTree(ParseTree node) {
        int ruleTriplesBlock = SimplePARQLParser.RULE_query;
        int ruleIndex = -1;
        while (ruleIndex != ruleTriplesBlock) {
            ParserRuleContext elementNode = (ParserRuleContext) node;
            ruleIndex = elementNode.getRuleIndex();
            parents.add(new Pair<>(elementNode, Constants.getNodeIndex(node)));
            node = node.getParent();
        }
    }

    /**
     * compute the position of the "truc" after adding it
     */
    private void computePosition() {
        position = POSITION.SUBJECT;
        if (find(SimplePARQLParser.RULE_verb) != null) {
            position = POSITION.PREDICATE;
        } else if (find(SimplePARQLParser.RULE_object) != null) {
            position = POSITION.OBJECT;
        }
    }

    /**
     * find in the parents of "truc" the ParseRuleContext when want
     *
     * @param ruleIndex rule index you're searching for
     * @return FIRST node having the ruleIndex found in the tree
     */
    private Pair<ParserRuleContext, Integer> find(int ruleIndex) {
        for (Pair<ParserRuleContext, Integer> pair : parents) {
            if (pair.getKey().getRuleIndex() == ruleIndex) {
                return pair;
            }
        }
        return null;
    }

    /**
     * Generate the triple where are the truc
     */
    private void generateTripleComposantes() {
        if (parents.size() != 0) {
            Pair<ParserRuleContext, Integer> triplesSameSubject = find(SimplePARQLParser.RULE_triplesSameSubject);
            if (triplesSameSubject != null) {
                ParserRuleContext propretyList = (ParserRuleContext) triplesSameSubject.getKey().getChild(1);
                String subject = position == POSITION.SUBJECT ?
                        clean(triplesSameSubject.getKey().getChild(0).getText()) : (triplesSameSubject.getKey().getChild(0).getText());
                String predicate = position == POSITION.PREDICATE ?
                        clean(propretyList.getChild(0).getText()) : propretyList.getChild(0).getText();
                String object = position == POSITION.OBJECT ?
                        clean(propretyList.getChild(1).getText()) : propretyList.getChild(1).getText();
                currentTriple = new Triple(subject, predicate, object);
            }
        }
    }

    private String clean(String text) {
        return text.replace("\"", "").replace("#", "");
    }

    /**
     * Generate variables like ?SPARQL_1, label_1, etc...
     */
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

    /**
     * Get the parents' name
     *
     * @param parser containing all the parser
     * @return parent's name of the truc
     * @deprecated
     */
    String printParentPath(SimplePARQLParser parser) {
        String result = "";
        for (Pair<ParserRuleContext, Integer> rule : this.getParents()) {
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
