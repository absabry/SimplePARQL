import javafx.util.Pair;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ParseTreeElement {
    private ArrayList<Pair<ParserRuleContext, Integer>> parents;
    private POSITION position;
    private ArrayList<Pair<String, String>> generatedTriples;
    private int counter;
    private boolean virtuoso;

    ParseTreeElement(ParseTree node, int counter) {
        parents = new ArrayList<>();
        generatedTriples = new ArrayList<>();
        this.counter = counter;
        virtuoso = false; // aribtrairement
        createParentTree(node);
        computePosition();
        GenerateTriples();
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
    private Map<POSITION, String> getTriplesComposantes() {
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

    // generate the triples for one "truc" directly after adding it
    private void GenerateTriples() {
        String variable = Constants.VARIABLE + counter + " ";
        String label = Constants.VARIABLE_LABEL + counter + " ";
        String temp_var_1 = Constants.VARIABLE_TMP_1 + counter + " ";
        String temp_var_2 = Constants.VARIABLE_TMP_2 + counter + " ";
        Map<POSITION, String> levels = getTriplesComposantes();
        String subject = levels.get(POSITION.SUBJECT);
        String predicate = levels.get(POSITION.PREDICATE);
        String object = levels.get(POSITION.OBJECT);
        if (position == POSITION.SUBJECT) {
            subject = subject.replace("\"", "").replace("#", "");
            generatedTriples.add(new Pair<>(variable + predicate + " " + object + " . ", createSPARQLFilter(subject, variable)));
            generatedTriples.add(new Pair<>(variable + predicate + " " + object + " . " + variable + Constants.RDF + label + " . ", createSPARQLFilter(subject, label)));
            generatedTriples.add(new Pair<>(variable + predicate + " " + object + " . " + variable + temp_var_1 + temp_var_2 + " . ", createSPARQLFilter(subject, temp_var_2)));
        } else if (position == POSITION.PREDICATE) {
            predicate = predicate.replace("\"", "").replace("#", "");
            generatedTriples.add(new Pair<>(subject + variable + object + " . ", createSPARQLFilter(predicate, variable)));
            generatedTriples.add(new Pair<>(subject + variable + object + " . " + variable + Constants.RDF + label + " . ", createSPARQLFilter(predicate, label)));
        } else if (position == POSITION.OBJECT) {
            object = object.replace("\"", "").replace("#", "");
            generatedTriples.add(new Pair<>(subject + " " + predicate + variable + " . ", createSPARQLFilter(object, variable)));
            generatedTriples.add(new Pair<>(subject + " " + predicate + variable + " . " + variable + Constants.RDF + label + " . ", createSPARQLFilter(object, label)));
            generatedTriples.add(new Pair<>(subject + " " + predicate + variable + " . " + variable + temp_var_1 + temp_var_2 + " . ", createSPARQLFilter(object, temp_var_2)));
        }
    }

    // Create the filter text of the SPARQL Filter
    private String createSPARQLFilter(String truc, String variable) {
        int counter = 1;
        String[] splitted = truc.split(" ");
        String result = Constants.FILTER + "(";
        for (String wordOfTruc : splitted) {
            if (virtuoso) {
                result += Constants.CONTAINS_BIF;
            } else {
                result += Constants.CONTAINS;
            }
            result += "(" + Constants.STR + "(" + variable + ")," + Constants.UCASE + "(\"" + wordOfTruc + "\"))";
            if (splitted.length != counter) {
                result += Constants.AND;
            }
            counter++;
        }
        result += " )";
        return result;
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
