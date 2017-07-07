package fr.esilv.simpleparql.source.converter.filter;

import fr.esilv.simpleparql.source.model.Constants;

import java.util.List;

/**
 * Normal filter, no special case here
 */
public class FilterNormal implements FilterGenerator {

    /**
     * Filter of this specific query depending on the type of the query (labels, IRI or proprieties)
     * @param truc the "truc" string we will search for in the variable
     * @param variable the variable we're looking for, it will change depending on the query
     * @return the filter in the text form that we will add to the query after converting it to ANTLR tree
     */
    @Override
    public String createSPARQLFilter(String truc, String variable) {
        int counter = 1;
        String[] splitted = truc.split(" ");
        String result = Constants.FILTER + "(";
        for (String wordOfTruc : splitted) {
            result += Constants.CONTAINS;
            result += "(" + Constants.UCASE + " ( " + Constants.STR + "(" + variable + "))," + Constants.UCASE + "(\"" + wordOfTruc + "\"))";
            if (splitted.length != counter) {
                result += Constants.AND;
            }
            counter++;
        }
        result += " )";
        return result;
    }
    /**
     * Filter of this query depending on the type of the query (labels, IRI or proprieties)
     * if it's proprieties, we will add it, otherwise it will return null, we don't need this function.
     * This filter will ignore some proprietes when looking for all proprieties (like labels, abstract or comment)
     * @param variable the variable we're looking for, it will always be the tmp_var1 of the query
     * @return the filter in the text form that we will add to the query after converting it to ANTLR tree
     */
    @Override
    public String removeIgnoredPropreties(String variable, List<String> ignored) {
        //FILTER ( ! CONTAINS ( STR ( ?tmp_var1_2 ) , \"http://dbpedia.org/ontology/abstract\" ) && ! CONTAINS ( STR ( ?tmp_var1_2 ) , \"http://www.w3.org/2000/01/rdf-schema#comment\" ) && ! CONTAINS ( STR ( ?tmp_var1_2 ) , \"http://www.w3.org/2000/01/rdf-schema#label\" ) )
        //FILTER ( ?tmp_var1_2  != "http://dbpedia.org/ontology/abstract" ) && ?tmp_var1_2 !=  "http://www.w3.org/2000/01/rdf-schema#comment"  && ?tmp_var1_2  !=  "http://www.w3.org/2000/01/rdf-schema#label" )
        String result = Constants.FILTER + "(";
        for (int i = 0; i < ignored.size(); i++) {
            result += variable + " != \"" + ignored.get(i) + "\")";
            if (i != ignored.size() - 1) {
                result += Constants.AND;
            }
        }
        result += " )";
        return result;
    }
}
