package fr.esilv.simpleparql.source.converter.filter;

import fr.esilv.simpleparql.source.converter.model.Constants;

/**
 * Normal filter, no special case here
 */
public class FilterNormal implements FilterGenerator {

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
