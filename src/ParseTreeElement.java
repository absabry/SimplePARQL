import javafx.util.Pair;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class ParseTreeElement {
    private ArrayList<Pair<ParserRuleContext, Integer>> parents;
    private POSITIONS position;
    private ArrayList<Pair<String, String>> generatedTriples;
    private static int counter = 1;
    private boolean virtuoso;

    ParseTreeElement(ParseTree node) {
        parents = new ArrayList<>();
        generatedTriples = new ArrayList<>();
        createParentTree(node);
        computePosition();
        GenerateTriples();
        virtuoso = false; // aribtrairement maintenant
        counter++;
    }


    ArrayList<Pair<ParserRuleContext, Integer>> getParents() {
        return parents;
    } // useful?

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
            parents.add(new Pair<>(elementNode, getNodeIndex(node)));
            node = node.getParent();
        }
    }

    private int getNodeIndex(ParseTree node) {
        if (node == null || node.getParent() == null) {
            return -1;
        }
        ParseTree parent = node.getParent();
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (parent.getChild(i) == node) {
                return i;
            }
        }
        return -1;
    }

    // compute the position of the "truc" directly after adding it
    private void computePosition() {
        position = POSITIONS.SUBJECT;
        if (find(SimplePARQLParser.RULE_verb) != null) {
            position = POSITIONS.PREDICATE;
        } else if (find(SimplePARQLParser.RULE_object) != null) {
            position = POSITIONS.OBJECT;
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
    private Map<POSITIONS, String> getTriplesComposantes() {
        if (parents.size() != 0) {
            Map<POSITIONS, String> triplesComposantes = new HashMap<>();
            Pair<ParserRuleContext, Integer> triplesSameSubject = find(SimplePARQLParser.RULE_triplesSameSubject);
            if (triplesSameSubject != null) {
                triplesComposantes.put(POSITIONS.SUBJECT, triplesSameSubject.getKey().getChild(0).getText());
                ParserRuleContext propretyList = (ParserRuleContext) triplesSameSubject.getKey().getChild(1);
                if (position == POSITIONS.SUBJECT) {
                    triplesComposantes.put(POSITIONS.PREDICATE, propretyList.getChild(0).getText());
                    triplesComposantes.put(POSITIONS.OBJECT, propretyList.getChild(1).getText()); // element juste après c'est son object
                } else if (position == POSITIONS.PREDICATE) {
                    Pair<ParserRuleContext, Integer> verb = find(SimplePARQLParser.RULE_verb);
                    if (verb != null) {
                        triplesComposantes.put(POSITIONS.PREDICATE, verb.getKey().getText());
                        triplesComposantes.put(POSITIONS.OBJECT, propretyList.getChild(verb.getValue() + 1).getText()); // element juste après c'est son object
                    }
                } else if (position == POSITIONS.OBJECT) {
                    Pair<ParserRuleContext, Integer> objectList = find(SimplePARQLParser.RULE_objectList);
                    if (objectList != null) {
                        triplesComposantes.put(POSITIONS.PREDICATE, propretyList.getChild(objectList.getValue() - 1).getText());// element juste avant c'est son predicate
                        triplesComposantes.put(POSITIONS.OBJECT, objectList.getKey().getText());
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
        Map<POSITIONS, String> levels = getTriplesComposantes();
        String subject = levels.get(POSITIONS.SUBJECT);
        String predicate = levels.get(POSITIONS.PREDICATE);
        String object = levels.get(POSITIONS.OBJECT);
        if (position == POSITIONS.SUBJECT) {
            subject = subject.replace("\"", "").replace("#", "");
            generatedTriples.add(new Pair<>(variable + predicate + " " + object + " . ", createSPARQLFilter(subject, variable)));
            generatedTriples.add(new Pair<>(variable + predicate + " " + object + " . " + variable + Constants.RDF + label + " . ", createSPARQLFilter(subject, label)));
            generatedTriples.add(new Pair<>(variable + predicate + " " + object + " . " + variable + temp_var_1 + temp_var_2 + " . ", createSPARQLFilter(subject, temp_var_2)));
        } else if (position == POSITIONS.PREDICATE) {
            predicate = predicate.replace("\"", "").replace("#", "");
            generatedTriples.add(new Pair<>(subject + variable + object + " . ", createSPARQLFilter(predicate, variable)));
            generatedTriples.add(new Pair<>(subject + variable + object + " . " + variable + Constants.RDF + label + " . ", createSPARQLFilter(predicate, label)));
        } else if (position == POSITIONS.OBJECT) {
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
        String result = "FILTER (";
        for (String wordOfTruc : splitted) {
            if (virtuoso) {
                result += "bif:contains(STR(" + variable + "), UCASE(\"" + wordOfTruc + "\"))";
            } else {
                result += "CONTAINS(STR(" + variable + "),UCASE(\"" + wordOfTruc + "\"))";
            }
            if (splitted.length != counter) {
                result += " && ";
            }
            counter++;
        }
        result += " )";
        return result;
    }

    public String toString() {
        return "Truc: " + parents.get(0).getKey().getText() + " Position: " + position;
    }


}
