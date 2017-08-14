package fr.esilv.simpleparql.source.converter.filter;

import fr.esilv.simpleparql.source.model.Constants;

import java.util.List;

/**
 * No special filter's case here, it's just the default case.
 */
public class FilterDefault implements FilterGenerator {

    /**
     * Filter of this specific query depending on the type of the query (labels, IRI or proprieties)
     *
     * @param truc     the "truc" string we will search for in the variable
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
}
