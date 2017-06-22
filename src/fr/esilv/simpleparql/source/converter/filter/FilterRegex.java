package fr.esilv.simpleparql.source.converter.filter;

import fr.esilv.simpleparql.source.converter.model.Constants;

/**
 * Filter when we prefer to use regex
 * we use FILTER regex(?name, "text", "i")
 */
public class FilterRegex implements FilterGenerator {

    @Override
    public String createSPARQLFilter(String truc, String variable) {
        int counter = 1;
        String[] splitted = truc.split(" ");
        String result = Constants.FILTER + "(";
        for (String wordOfTruc : splitted) {
            result += Constants.REGEX;
            result += "(" + variable + ",\"" + wordOfTruc + "\",\"i\")";
            if (splitted.length != counter) {
                result += Constants.AND;
            }
            counter++;
        }
        result += " )";
        return result;
    }
}
