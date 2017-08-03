package fr.esilv.simpleparql.source.converter.filter;

import fr.esilv.simpleparql.source.model.Constants;

import java.util.List;

public class FilterCommon {
    /**
     * Filter when it comes to the exact truc, and not the CONTAINS one.
     */
    public String createSPARQLFilter(String truc, String variable) {
        // FILTER ( ?label_1 = "John Smith")
        return Constants.FILTER + "( " + variable + "=" + "\"" + truc + "\")";
    }

    /**
     * Filter of this query depending on the type of the query (labels, IRI or proprieties)
     * if it's proprieties, we will add it, otherwise it will return null, we don't need this function.
     * This filter will ignore some proprietes when looking for all proprieties (like labels, abstract or comment)
     *
     * @param variable the variable we're looking for, it will always be the tmp_var1 of the query
     * @return the filter in the text form that we will add to the query after converting it to ANTLR tree
     */
    public String removeIgnoredPropreties(String variable, List<String> ignored) {
        String result = Constants.FILTER + "(";
        for (int i = 0; i < ignored.size(); i++) {
            result += Constants.STR + "(" + variable + ") != \"" + ignored.get(i) + "\"";
            if (i != ignored.size() - 1) {
                result += Constants.AND;
            }
        }
        result += " )";
        return result;
    }
}
