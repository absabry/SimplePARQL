package fr.esilv.simpleparql.crashTest;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import org.apache.log4j.varia.NullAppender;

import java.io.IOException;
import java.util.ArrayList;

/**
 * juste des tests, on s'en fou
 */
public class crashtest {

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure(new NullAppender());
        String query = "PREFIX dbo: <http://dbpedia.org/ontology/> " +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                "SELECT * WHERE { " +
                "?SimplePARQL_1 dbo:birthPlace ?SimplePARQL_2 . " +
                "?SimplePARQL_2 ?tmp_var1_2 ?tmp_var2_2 . " +
                "?SimplePARQL_1 ?tmp_var1_1 ?tmp_var2_1 . " +
                "FILTER (  <bif:contains> ( ?tmp_var2_1 , \"John AND Smith \" ) ) " +
                "FILTER (  !CONTAINS ( STR(?tmp_var1_1) , \"http://dbpedia.org/ontology/abstract\" ) )" +
                "FILTER (  <bif:contains> ( ?tmp_var2_2 , \"London \" ) ) " +
                "FILTER (  !CONTAINS ( STR(?tmp_var1_2) , \"http://dbpedia.org/ontology/abstract\" ) ) " +
                "}";
        new crashtest().executeSparql("http://dbpedia.org/sparql", query);
    }


    private void executeSparql(String base, String sparqlQueryString) {

        try {
            Query query = QueryFactory.create(sparqlQueryString);
            QueryExecution qexec = QueryExecutionFactory.sparqlService(base, query);
            ResultSet results = qexec.execSelect();
            ArrayList<String> variables = new ArrayList<>(results.getResultVars()); // get variables of the query
            System.out.println(variables);
            for (; results.hasNext(); ) {
                QuerySolution sol = results.nextSolution();
                ArrayList<String> solution = new ArrayList<>();
                for (String ittVar : variables) {
                    String val;
                    RDFNode var = sol.get(ittVar);
                    if (var.isLiteral()) {
                        Literal l = (Literal) var;
                        val = l.getValue().toString();
                    } else {
                        Resource r = (Resource) var;
                        val = r.getURI();
                    }
                    solution.add(val);
                }
                System.out.println(solution);
            }
            qexec.close();
        } catch (Exception e) {
            System.out.println("Failed to execute query \"" + sparqlQueryString + "\":" + e);
        }
    }
}
