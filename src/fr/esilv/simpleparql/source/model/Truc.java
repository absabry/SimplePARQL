package fr.esilv.simpleparql.source.model;

import fr.esilv.simpleparql.grammar.SimplePARQLParser;
import javafx.util.Pair;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Strcutre of the truc IN SimplaPARQL <br>
 * <strong>name</strong> The name of the truc <br>
 * <strong>position</strong> Position in the triple <br>
 * <strong>counter</strong> Counter (when we will convert it, we'll create variables with this counter) <br>
 * <strong> currentTriple </strong> CurrentTriple is the complete triple where the "truc" is locted. <br>
 * <strong>variables</strong> Variables is the variable we generate to this truc (?SimpleARQL_, label_,etc...) <br>
 */

public class Truc {
    private ArrayList<Pair<ParserRuleContext, Integer>> parents;
    private String name;
    private String language;
    private String internalLanguage;
    private POSITION position;
    private int counter;
    private Triple currentTriple;
    private TRUC_TYPE type;
    private HashMap<VARIABLES, String> variables;

    /**
     * When we create a truc, we generate everything in thr constructor directly <br>
     * We create their parent tree to the query <br>
     * We compute their position in the triple <br>
     * We generate the variables <br>
     * generate their new triples <br>
     */
    public Truc(ParseTree node, int counter) {
        parents = new ArrayList<>();
        this.counter = counter;
        language = null;
        internalLanguage = null;
        intializeLanguageAndName(node);
        createParentTree(node);
        computePosition();
        computeType();
        generateVariables();
        generateCurrentTripleComposantes();
    }

    /**
     * Get the text (the name) of the truc and it's language if there is one.
     *
     * @param node truc's node in text format
     */
    private void intializeLanguageAndName(ParseTree node) {

        String composant[] = node.getText().split("@");
        name = "";
        if (composant.length == 1) {
            name = composant[0]; // no filter language (or internal language) is added
        } else {
            for (int i = 0; i < composant.length - 1; i++) {
                name += composant[i];
            }
            String lastPart = composant[composant.length - 1];
            String lastChar = lastPart.substring(lastPart.length() - 1, lastPart.length());
            language = lastPart;
            if (lastChar.equals("/") || lastChar.equals("\"")) { // it's an intern lanuage, and not a explicit one!
                internalLanguage = language.substring(0, language.length() - 1);
                language = null;
                name += lastChar;
            }
        }
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

    public String getLanguage() {
        return language;
    }

    public String getInternalLanguage() {
        return internalLanguage;
    }


    /**
     * Get truc's name without the quotes and the slash.
     *
     * @return the name without delimter
     */
    public String getCleanedName() {
        return clean(name);
    }

    /**
     * Get truc's name exactly like in the tree (with language and @ tag).
     *
     * @return the name without delimter
     */
    public String getText() {
        String tempName = name;
        if (internalLanguage != null) {
            tempName = name.substring(0, name.length() - 1) + "@" + internalLanguage + name.substring(name.length() - 1, name.length());
        }
        return tempName + (language == null ? "" : "@" + language);
    }

    public TRUC_TYPE getType() {
        return type;
    }

    /**
     * Clean text we get from the triple, it may contains quotes or rafters,this functions will clean it.
     *
     * @param text text to be cleaned from the characteres representing truc
     * @return same text cleaned up
     */
    private String clean(String text) {
        return text.replace("\"", "").replace("/", "");
    }

    public ArrayList<Pair<ParserRuleContext, Integer>> getParents() {
        return parents;
    }

    /**
     * Create parent tree of the "truc" (the path up to the root which is query).
     *
     * @param node the truc in the orignial tree
     */
    private void createParentTree(ParseTree node) {
        int ruleTriplesBlock = SimplePARQLParser.RULE_query;
        int ruleIndex = -1;
        while (ruleIndex != ruleTriplesBlock) { // get parents till the query
            ParserRuleContext elementNode = (ParserRuleContext) node; // convert it to get it's rule index
            ruleIndex = elementNode.getRuleIndex();
            parents.add(new Pair<>(elementNode, Constants.getNodeIndex(node)));
            node = node.getParent(); // the itteration
        }
    }

    /**
     * Compute the position of the "truc" in it's current triple after adding it.
     */
    private void computePosition() {
        position = POSITION.SUBJECT;
        if (find(SimplePARQLParser.RULE_trucPredicat) != null) {
            position = POSITION.PREDICATE;
        } else if (find(SimplePARQLParser.RULE_trucObject) != null) {
            position = POSITION.OBJECT;
        }
    }

    /**
     * Compute the type of the "truc".
     */
    private void computeType() {
        type = TRUC_TYPE.WORD;
        if (name.contains("/")) {
            type = TRUC_TYPE.SEVERALWORDS;
        } else if (name.contains("\"")) {
            type = TRUC_TYPE.STRING;
        }
    }


    /**
     * Find the closest item in the parents of "truc" having the rule context we're looking for <br>
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
     * Get the triple where the truc are, and generate a clone of this triple to be used on demand.
     */
    private void generateCurrentTripleComposantes() {
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
     * Generate variables for this truc like ?SPARQL_1, label_1, etc...
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
        return type == TRUC_TYPE.STRING;
    }

    /**
     * Get the variable type having the constant name of the variable.
     * This will be displayed in the JSON we send to the user.
     *
     * @param variable variable from the SPARQL query in string format
     * @return VARIABLE enum (VARIABLES,LABEL,TMP1,TMP2)
     */
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
        return (getCleanedName()).equals(((Truc) other).getCleanedName());
    }

    public String toString() {
        return "Truc: " + name + " nommé : " + variables.toString() + " Position: " + position;
    }

}
