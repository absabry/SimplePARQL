import javafx.util.Pair;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

public class Virtuoso extends ParseTreeElement implements QueryService {
    Virtuoso(ParseTree node, int counter) {
        super(node, counter);
        GenerateTriples();
    }

    @Override
    // generate the triples for one "truc" directly after adding it
    public void GenerateTriples() {
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

    @Override
    // Create the filter text of the SPARQL Filter
    public String createSPARQLFilter(String truc, String variable) {
        // ?name bif:contains "Republic"
        // or
        // FILTER(bif:contains(?name, "Republic")) .
        // Exactement le même résultat
        int counter = 1;
        String[] splitted = truc.split(" ");
        String result = Constants.FILTER + "(";
        for (String wordOfTruc : splitted) {
            result += Constants.CONTAINS_BIF;
            result += "(" + Constants.STR + "(" + variable + ")," + Constants.UCASE + "(\"" + wordOfTruc + "\"))";
            if (splitted.length != counter) {
                result += Constants.AND;
            }
            counter++;
        }
        result += " )";
        return result;
    }
}
