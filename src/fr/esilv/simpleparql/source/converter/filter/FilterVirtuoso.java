package fr.esilv.simpleparql.source.converter.filter;

import fr.esilv.simpleparql.source.converter.model.Constants;

/**
 * Filter when the interface is virtuoso
 * we use FILTER(bif:contains(..,..))
 */

public class FilterVirtuoso implements FilterGenerator {
    @Override
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
