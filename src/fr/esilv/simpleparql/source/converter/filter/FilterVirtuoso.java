package fr.esilv.simpleparql.source.converter.filter;

import fr.esilv.simpleparql.source.model.Constants;

import java.util.List;

/**
 * It's working sometimes and sometimes it dosent,  therefore it's better to add it as triples
 * Filter when the interface is virtuoso
 * we use FILTER(bif:contains(..,..))
 */

public class FilterVirtuoso implements FilterGenerator {


    /**
     * Filter of this specific query depending on the type of the query (labels, IRI or proprieties)
     *
     * @param truc     the "truc" string we will search for in the variable
     * @param variable the variable we're looking for, it will change depending on the query
     * @return the filter in the text form that we will add to the query after converting it to ANTLR tree
     */
    @Override
    public String createSPARQLFilter(String truc, String variable) {
        // FILTER ( <bif:contains> ( ?label_1 , "John AND Smith "))
        int counter = 1;
        String[] splitted = truc.split(" ");
        String result = Constants.FILTER + "( " + Constants.CONTAINS_BIF + " (" + variable + "," + "\"";
        for (String wordOfTruc : splitted) {
            result += wordOfTruc;
            if (splitted.length != counter) {
                result += Constants.AND_VIRTUOSO;
            }
            counter++;
        }
        result += " \"))";
        return result;
    }


    /**
     * Filter of this query depending on the type of the query (labels, IRI or proprieties)
     * if it's proprieties, we will add it, otherwise it will return null, we don't need this function.
     * This filter will ignore some proprietes when looking for all proprieties (like labels, abstract or comment)
     *
     * @param variable the variable we're looking for, it will always be the tmp_var1 of the query
     * @return the filter in the text form that we will add to the query after converting it to ANTLR tree
     */
    @Override
    public String removeIgnoredPropreties(String variable, List<String> ignored) {
        return new FilterNormal().removeIgnoredPropreties(variable, ignored);
    }
}
