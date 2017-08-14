package fr.esilv.simpleparql.source.converter.query;


import fr.esilv.simpleparql.source.converter.filter.FilterCommon;
import fr.esilv.simpleparql.source.converter.filter.FilterDefault;
import fr.esilv.simpleparql.source.converter.filter.FilterGenerator;
import fr.esilv.simpleparql.source.model.Truc;
import fr.esilv.simpleparql.source.model.*;

import java.util.ArrayList;
import java.util.Collections;
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

public class SimplePARQLQueryGenerator {
    private Truc truc;
    private ArrayList<Composant> generatedComposants;
    private FilterGenerator filterGenerator;
    private PAGE page;
    private List<String> ignoredProprieties;


    public SimplePARQLQueryGenerator(Truc truc, FilterGenerator filterGenerator, PAGE page, List<String> ignoredProprieties) {
        this.filterGenerator = filterGenerator;
        this.truc = truc;
        generatedComposants = new ArrayList<>();
        this.page = page;
        this.ignoredProprieties = ignoredProprieties;

        createGeneratedTriples();
    }

    public ArrayList<Composant> getGeneratedComposants() {
        Collections.sort(generatedComposants);
        return generatedComposants;
    }

    /**
     * This function never change, it generates the query that check the IRI of the ressource. <br>
     * SELECT *
     * WHERE {
     * ?SimplePARQL_1 ?b ?c .
     * FILTER ( CONTAINS ( STR ( ?SimplePARQL_1 ) , UCASE ( "Sh" ) ) )
     * }
     *
     * @param truc Truc which we'll generate composant for it
     * @param page page which the query belongs to
     * @return generated item (triple, filter and page it belongs to)
     */
    private Composant generateURI(Truc truc, PAGE page) {
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
            String filter;
            if (truc.isExact()) {
                filter = new FilterCommon().createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.VARIABLE));
            } else {
                filter = new FilterDefault().createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.VARIABLE));
            }
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
        Composant result = generateURI(truc, page);
        if (result != null) {
            String filter;
            if (truc.isExact()) {
                filter = new FilterCommon().createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.LABEL));
            } else {
                filter = filterGenerator.createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.LABEL));
            }
            return new Composant(result.getTriple() + " " + truc.getVariables().get(VARIABLES.VARIABLE) + " "
                    + Constants.RDF + truc.getVariables().get(VARIABLES.LABEL) + " . ", filter, null, page);
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
     * @param truc current Truc
     * @param page page which the query belongs to
     * @return generated item (triple, filter and page it belongs to)
     */
    private Composant generateProprieties(Truc truc, PAGE page) {
        Composant result = generateURI(truc, page);
        if (result != null) {
            String filter;
            if (truc.isExact()) {
                filter = new FilterCommon().createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.TMP2));
            } else {
                filter = filterGenerator.createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.TMP2));
            }
            return new Composant(result.getTriple() + truc.getVariables().get(VARIABLES.VARIABLE)
                    + truc.getVariables().get(VARIABLES.TMP1) + truc.getVariables().get(VARIABLES.TMP2) + " . ",
                    filter, new FilterCommon().removeIgnoredPropreties(truc.getVariables().get(VARIABLES.TMP1), ignoredProprieties), page);
        }
        return null;
    }

    /**
     * main function to launch the first, second or third page
     */
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
     * if subjet then it will generateLabels
     * if predicate then it will generateLabels
     * if object then it will generatePropreties and generateLabels
     * BUT if it's an exact query (delimited by double quotes "")
     * <p>
     * if it's in object position
     * it will not generate literal search directly in the first page
     * <p>
     * otherwise
     * it will generate labels exactly like when it's not an exact truc
     */
    private void PageFirst() {
        if (truc.isExact()) {
            if (truc.getPosition() == POSITION.OBJECT) {
                generatedComposants.add(generateURI(truc, PAGE.FIRST));
            } else {
                generatedComposants.add(generatelabels(truc, PAGE.FIRST));
            }

        } else {
            generatedComposants.add(generatelabels(truc, PAGE.FIRST));
            if (truc.getPosition() == POSITION.OBJECT) {
                generatedComposants.add(generateURI(truc, PAGE.FIRST));
            }
        }
    }

    /**
     * Second page generate :
     * if subjet then it will generatePropreties
     * if predicate then it will generateURI
     * if object then it will generatePropreties
     * BUT if it's an exact query (delimited by double quotes "")
     * <p>
     * if it's in object position
     * it will not generate labels (that we haven't geenrate in the PageFirst() function search
     * <p>
     * otherwise
     * it will generate proprieties
     */
    private void PageSecond() {
        if (truc.isExact()) {
            if (truc.getPosition() == POSITION.OBJECT) {
                generatedComposants.add(generatelabels(truc, PAGE.SECOND));
            } else {
                generatedComposants.add(generateProprieties(truc, PAGE.SECOND));
            }
        } else {
            if (truc.getPosition() == POSITION.PREDICATE) {
                generatedComposants.add(generateURI(truc, PAGE.SECOND));
            } else {
                generatedComposants.add(generateProprieties(truc, PAGE.SECOND));
            }
        }
    }

    /**
     * Third page generate :
     * if subjet then it will generateURI
     * if predicate then it will do nothing
     * if object then it will do nothing
     * BUT if it's an exact query (delimited by double quotes "")
     * <p>
     * if it's in object position
     * we'll not generate propreties which e haven't generate in PageSecond() function
     */
    private void PageThird() {
        if (truc.isExact()) {
            if (truc.getPosition() == POSITION.OBJECT) {
                generatedComposants.add(generateProprieties(truc, PAGE.THIRD));
            }
        } else {
            if (truc.getPosition() == POSITION.SUBJECT) {
                generatedComposants.add(generateURI(truc, PAGE.THIRD));
            }
        }
    }

    public String toString() {
        return truc.toString() + "\n"
                + generatedComposants.size() + " queries generated.";
    }
}
