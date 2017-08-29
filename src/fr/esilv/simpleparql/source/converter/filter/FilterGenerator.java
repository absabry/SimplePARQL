package fr.esilv.simpleparql.source.converter.filter;

/**
 * Interface to handle filter's strategies.
 */
public interface FilterGenerator {
    String createSPARQLFilter(String truc, String variable);
}
