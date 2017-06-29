package fr.esilv.simpleparql.source.converter.filter;

import java.util.List;

public interface FilterGenerator {
    String createSPARQLFilter(String truc, String variable);
    String removeIgnoredPropreties(String variable, List<String> ignored);

}
