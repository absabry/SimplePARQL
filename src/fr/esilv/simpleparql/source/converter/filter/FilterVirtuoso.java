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
}
