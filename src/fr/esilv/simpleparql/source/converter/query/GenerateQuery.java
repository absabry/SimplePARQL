package fr.esilv.simpleparql.source.converter.query;


import fr.esilv.simpleparql.source.converter.filter.FilterGenerator;
import fr.esilv.simpleparql.source.converter.filter.FilterNormal;
import fr.esilv.simpleparql.source.model.Truc;
import fr.esilv.simpleparql.source.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Methodes to generate the query
 * We can override those methodes if we need to change it or create new methodes
 * <p>
 * We keep:
 * the truc for whom we create the queries
 * generated composants for this truc
 * the filter generator interface
 * the page we keep it in
 */

public class GenerateQuery {
    private Truc truc;
    private ArrayList<Composant> generatedComposants;
    private FilterGenerator filterGenerator;
    private PAGE page;
    private List<String> ignoredProprieties;


    public GenerateQuery(Truc truc, FilterGenerator filterGenerator, PAGE page, List<String> ignoredConfig) {
        this.filterGenerator = filterGenerator;
        this.truc = truc;
        generatedComposants = new ArrayList<>();
        this.page = page;
        ignoredProprieties = ignoredConfig;

        createGeneratedTriples();
    }

    public ArrayList<Composant> getGeneratedComposants() {
        return generatedComposants;
    }

    /**
     * This function never change, it generates the query that check the IRI of the ressource
     * SELECT *
     * WHERE {
     * ?SimplePARQL_1 ?b ?c .
     * FILTER ( CONTAINS ( STR ( ?SimplePARQL_1 ) , UCASE ( "Sh" ) ) )
     * }
     *
     * @param truc current fr.esilv.simpleparql.source.model.Truc
     * @param page page which the query belongs to
     * @return generated item (triple, filter and page it belongs to)
     */
    private Composant generateIRI(Truc truc, PAGE page) {
        String triples = null;
        if (truc.getPosition() == POSITION.SUBJECT) {
            triples = truc.getVariables().get(VARIABLES.VARIABLE) + " "
                    + truc.getCurrentTriple().getPredicate() + " " + truc.getCurrentTriple().getObject() + " . ";
        } else if (truc.getPosition() == POSITION.PREDICATE) {
            triples = truc.getCurrentTriple().getSubject() + " " + truc.getVariables().get(VARIABLES.VARIABLE) + " "
                    + truc.getCurrentTriple().getObject() + " . ";
        } else if (truc.getPosition() == POSITION.OBJECT) {
            triples = truc.getCurrentTriple().getSubject() + " " + truc.getCurrentTriple().getPredicate() + " "
                    + truc.getVariables().get(VARIABLES.VARIABLE) + " . ";
        }
        if (triples != null) {
            // TODO filtre normal parce que les bif:contains ne fonctionnent pas sur les URI?
            String filter = new FilterNormal().createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.VARIABLE));
            return new Composant(triples, filter, null, page);
        }
        return null;
    }

    /**
     * This function never change, it generates the query that check the rdfs:label of the ressource
     * SELECT *
     * WHERE {
     * ?SimplePARQL_1 ?b ?c .
     * ?SimplePARQL_1 rdfs:label ?label_1 .
     * FILTER ( CONTAINS ( STR ( ?label_1 ) , UCASE ( "Sh" ) ) )
     * }
     *
     * @param truc current fr.esilv.simpleparql.source.model.Truc
     * @param page page which the query belongs to
     * @return generated item (triple, filter and page it belongs to)
     */
    private Composant generatelabels(Truc truc, PAGE page) {
        Composant result = generateIRI(truc, page);
        if (result != null) {
            return new Composant(result.getTriple() + " " + truc.getVariables().get(VARIABLES.VARIABLE) + " "
                    + Constants.RDF + truc.getVariables().get(VARIABLES.LABEL) + " . ",
                    filterGenerator.createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.LABEL)), null, page);
        }
        return null;
    }

    /**
     * This function never change, it generates the query that check all proprietes
     * SELECT *
     * WHERE {
     * ?SimplePARQL_1 ?b ?c .
     * ?SimplePARQL_1 ?tmp_var1_1 ?tmp_var2_1 .
     * FILTER ( CONTAINS ( STR ( ?tmp_var2_1 ) , UCASE ( "Sh" ) ) )
     * }
     *
     * @param truc current fr.esilv.simpleparql.source.model.Truc
     * @param page page which the query belongs to
     * @return generated item (triple, filter and page it belongs to)
     */
    private Composant generateProprieties(Truc truc, PAGE page) {
        Composant result = generateIRI(truc, page);
        if (result != null) {
            return new Composant(result.getTriple() + truc.getVariables().get(VARIABLES.VARIABLE)
                    + truc.getVariables().get(VARIABLES.TMP1) + truc.getVariables().get(VARIABLES.TMP2) + " . ",
                    filterGenerator.createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.TMP2)),
                    filterGenerator.removeIgnoredPropreties(truc.getVariables().get(VARIABLES.TMP1), ignoredProprieties),
                    page);
        }
        return null;
    }

    private void createGeneratedTriples() {
        switch (page) {
            case THIRD:
                PageThird();
            case SECOND:
                PageSecond();
            case FIRST:
                PageFirst();
                break;
        }
    }

    /**
     * First page generate :
     * if subjet then it willgenerateLabels
     * if predicate then it will generateLabels
     * if object then it will generatePropreties and generateLabels
     */
    private void PageFirst() {
        generatedComposants.add(generatelabels(truc, PAGE.FIRST));
        if (truc.getPosition() == POSITION.OBJECT) {
            generatedComposants.add(generateIRI(truc, PAGE.FIRST));
        }
    }

    /**
     * Second page generate :
     * if subjet then it will generatePropreties
     * if predicate then it will generateIRI
     * if object then it will generatePropreties
     */
    private void PageSecond() {
        if (truc.getPosition() == POSITION.PREDICATE) {
            generatedComposants.add(generateIRI(truc, PAGE.SECOND));
        } else {
            generatedComposants.add(generateProprieties(truc, PAGE.SECOND));
        }
    }

    /**
     * Third page generate :
     * if subjet then it will generateIRI
     * if predicate then it will do nothing
     * if object then it willdo nothing
     */
    private void PageThird() {
        if (truc.getPosition() == POSITION.SUBJECT) {
            generatedComposants.add(generateIRI(truc, PAGE.THIRD));
        }
    }

    public String toString() {
        return truc.toString() + "\n"
                + generatedComposants.size() + " queries generated.";
    }
}
