package fr.esilv.simpleparql.source.model;

import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import javafx.util.Pair;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Strcutre of the truc IN SimplaPARQL <br>
 * <strong>name</strong> The name of the truc <br>
 * <strong>position</strong>position in the triple <br>
 * <strong>counter</strong>counter (when we will convert it, we'll create variables with this counter) <br>
 * <strong> currentTriple </strong>currentTriple is the complete triple where the "truc" is locted. <br>
 * <strong>variables</strong>variables is the variable we generate to this truc (?SimpleARQL_, label_,etc...) <br>
 */

public class Truc {
    private ArrayList<Pair<ParserRuleContext, Integer>> parents;
    private String name;
    private POSITION position;
    private int counter;
    private Triple currentTriple;
    private HashMap<VARIABLES, String> variables;

    /**
     * When we create a truc, we generate everything in thr constructor directly
     * We create their parent tree to the query
     * We compute their position in the triple
     * We generate the variables
     * generate their new triples
     */
    public Truc(ParseTree node, int counter) {
        parents = new ArrayList<>();
        name = node.getText();
        this.counter = counter;
        createParentTree(node);
        computePosition();
        generateVariables();
        generateTripleComposantes();
    }

    public int getCounter() {
        return counter;
    }

    public POSITION getPosition() {
        return position;
    }

    public Triple getCurrentTriple() {
        return currentTriple;
    }

    public HashMap<VARIABLES, String> getVariables() {
        return variables;
    }

    public String getName() {
        return name;
    }

    public String getCleanedName() {
        return clean(name);
    }

    public ArrayList<Pair<ParserRuleContext, Integer>> getParents() {
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
     * compute the position of the "truc"  in it"s current triple after adding it
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
     * Find in the parents of "truc" the ParseRuleContext when want <br>
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

    /**
     * Clean text we get from the triple, it may contains quotes or rafters.
     * This functions will clean it.
     *
     * @param text text to be cleaned from the characteres representing truc
     * @return same text cleaned up
     */
    private String clean(String text) {
        return text.replace("\"", "").replace("/", "").replace("/", "");
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

    /**
     * Get if this truc is in optional group pattern or not.
     *
     * @return true if it's it, false if it's not.
     */
    public boolean isOptionnal() {
        return find(SimplePARQLParser.RULE_optionalGraphPattern) != null;
    }

    /**
     * Get if this truc is an exact truc or not, the exact truc is the one delimited by double quotes " ".
     *
     * @return true if it's it, false if it's not
     */
    public boolean isExact() {
        ParserRuleContext truc = parents.get(0).getKey();
        if (truc.getChild(0) instanceof ParserRuleContext) {
            if (((ParserRuleContext) truc.getChild(0)).getRuleIndex() == SimplePARQLParser.RULE_string) {
                return true;
            }
        }
        return false;
    }

    public VARIABLES getVariablePosition(String variable) {
        for (Map.Entry mapentry : variables.entrySet()) {
            if (mapentry.getValue().toString().trim().equals("?" + variable.trim())) {
                return (VARIABLES) mapentry.getKey();
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Truc)) return false;
        return (name).equals(((Truc) other).getName());
    }

    public String toString() {
        return "Truc: " + name + " nommé : " + variables.toString() + " Position: " + position;
    }

}
