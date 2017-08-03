package fr.esilv.simpleparql.source.converter.filter;

public interface FilterGenerator {
    String createSPARQLFilter(String truc, String variable);

}
