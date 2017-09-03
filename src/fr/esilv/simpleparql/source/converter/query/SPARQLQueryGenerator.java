package fr.esilv.simpleparql.source.converter.query;


import fr.esilv.simpleparql.source.converter.filter.FilterCommon;
import fr.esilv.simpleparql.source.converter.filter.FilterDefault;
import fr.esilv.simpleparql.source.converter.filter.FilterGenerator;
import fr.esilv.simpleparql.source.model.Truc;
import fr.esilv.simpleparql.source.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Methodes to generate the SimplePARQL queries <strong>for just one truc</strong>. It use the filter interface to add the filter composant. <br>
 * We can override the methodes (PageFirst(), PageSecond() and PageThird()) to change the pages' strategy, or create a new personliazed ones.
 * <p>
 * <strong>truc</strong> The truc for which we create the queries. <br>
 * <strong>genertedComposants</strong> Generated composants for this truc. (Filter,triples and pages) <br>
 * <strong>filterGenertor</strong> The filter generator interface. <br>
 * <strong>ignoredProprieties</strong> ignored proprieties when we search for all proprieties. <br>
 */

public class SPARQLQueryGenerator {
    private Truc truc;
    private ArrayList<Composant> generatedComposants;
    private FilterGenerator filterGenerator;
    private List<String> ignoredProprieties;


    public SPARQLQueryGenerator(Truc truc, FilterGenerator filterGenerator, List<String> ignoredProprieties) {
        this.filterGenerator = filterGenerator;
        this.truc = truc;
        generatedComposants = new ArrayList<>();
        this.ignoredProprieties = ignoredProprieties;
        createGeneratedTriples();
    }

    public ArrayList<Composant> getGeneratedComposants() {
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
     * @param page page which the query belongs to
     * @return generated item (triple, filter and page it belongs to)
     */
    private Composant generateURI(PAGE page) {
        ArrayList<String> triples = new ArrayList<>();
        if (truc.getPosition() == POSITION.SUBJECT) {
            triples.add(truc.getVariables().get(VARIABLES.VARIABLE) + " "
                    + truc.getCurrentTriple().getPredicate() + " " + truc.getCurrentTriple().getObject() + " . ");
        } else if (truc.getPosition() == POSITION.PREDICATE) {
            triples.add(truc.getCurrentTriple().getSubject() + " " + truc.getVariables().get(VARIABLES.VARIABLE) + " "
                    + truc.getCurrentTriple().getObject() + " . ");
        } else if (truc.getPosition() == POSITION.OBJECT) {
            triples.add(truc.getCurrentTriple().getSubject() + " " + truc.getCurrentTriple().getPredicate() + " "
                    + truc.getVariables().get(VARIABLES.VARIABLE) + " . ");
        }
        if (triples.size() != 0) {
            ArrayList<String> filters = new ArrayList<>();
            // main filter
            if (truc.isExact()) {
                filters.add(new FilterCommon().createSPARQLFilter(truc.getCleanedName(), truc.getVariables().get(VARIABLES.VARIABLE)));
            } else {
                filters.add(new FilterDefault().createSPARQLFilter(truc.getCleanedName(), truc.getVariables().get(VARIABLES.VARIABLE)));
            }
            if (truc.getLanguage() != null) {
                filters.add(new FilterCommon().createSPARQLLanguageFilter(truc.getVariables().get(VARIABLES.VARIABLE), truc.getLanguage()));
            }
            return new Composant(triples, filters, page);
        }
        return null;
    }

    /**
     * This function never change, it generates the query that check the rdfs:label of the ressource.<br>
     * SELECT *
     * WHERE {
     * ?SimplePARQL_1 ?b ?c .
     * ?SimplePARQL_1 rdfs:label ?label_1 .
     * FILTER ( CONTAINS ( STR ( ?label_1 ) , UCASE ( "Sh" ) ) )
     * }
     *
     * @param page page which the query belongs to
     * @return generated item (triple, filter and page it belongs to)
     */
    private Composant generatelabels(PAGE page) {
        Composant result = generateURI(page);
        if (result != null) {
            ArrayList<String> triples = new ArrayList<>();
            triples.addAll(result.getTriples());
            triples.add(truc.getVariables().get(VARIABLES.VARIABLE) + " "
                    + Constants.RDF + truc.getVariables().get(VARIABLES.LABEL) + " . ");
            ArrayList<String> filters = new ArrayList<>();
            // main filter
            if (truc.isExact()) {
                filters.add(new FilterCommon().createSPARQLFilter(truc.getCleanedName(), truc.getVariables().get(VARIABLES.LABEL)));
            } else {
                filters.add(filterGenerator.createSPARQLFilter(truc.getCleanedName(), truc.getVariables().get(VARIABLES.LABEL)));
            }
            if (truc.getLanguage() != null) {
                filters.add(new FilterCommon().createSPARQLLanguageFilter(truc.getVariables().get(VARIABLES.LABEL), truc.getLanguage()));
            }
            return new Composant(triples, filters, page);
        }
        return null;
    }

    /**
     * This function never change, it generates the query that check all proprietes. <br>
     * SELECT *
     * WHERE {
     * ?SimplePARQL_1 ?b ?c .
     * ?SimplePARQL_1 ?tmp_var1_1 ?tmp_var2_1 .
     * FILTER ( CONTAINS ( STR ( ?tmp_var2_1 ) , UCASE ( "Sh" ) ) )
     * }
     *
     * @param page page which the query belongs to
     * @return generated item (triple, filter and page it belongs to)
     */
    private Composant generateProprieties(PAGE page) {
        Composant result = generateURI(page);
        if (result != null) {
            ArrayList<String> triples = new ArrayList<>();
            triples.addAll(result.getTriples());
            triples.add(truc.getVariables().get(VARIABLES.VARIABLE)
                    + truc.getVariables().get(VARIABLES.TMP1) + truc.getVariables().get(VARIABLES.TMP2) + " . ");
            ArrayList<String> filters = new ArrayList<>();
            if (truc.isExact()) {
                filters.add(new FilterCommon().createSPARQLFilter(truc.getCleanedName(), truc.getVariables().get(VARIABLES.TMP2)));
            } else {
                filters.add(filterGenerator.createSPARQLFilter(truc.getCleanedName(), truc.getVariables().get(VARIABLES.TMP2)));
            }
            if (truc.getLanguage() != null) {
                filters.add(new FilterCommon().createSPARQLLanguageFilter(truc.getVariables().get(VARIABLES.TMP2), truc.getLanguage()));
            }
            filters.add(new FilterCommon().removeIgnoredPropreties(truc.getVariables().get(VARIABLES.TMP1), ignoredProprieties));
            return new Composant(triples, filters, page);


        }
        return null;
    }

    /**
     * Main function to launch the first, second and third page.<br>
     * Should be overrided if we want to change the strategies
     */
    private void createGeneratedTriples() {
        PageThird();
        PageSecond();
        PageFirst();
    }

    /**
     * First page generate :
     * if subjet then it will generateLabels. <br>
     * if predicate then it will generateLabels.<br>
     * if object then it will generatePropreties and generateLabels
     * <strong>BUT</strong> if it's an exact query (delimited by double quotes ""),
     * if it's in object position, it will not generate literal search directly in the first page. <br>
     * <strong>Otherwise,</strong>
     * it will generate labels exactly like when it's not an exact truc.<br>
     */
    private void PageFirst() {
        if (truc.isExact()) {
            if (truc.getPosition() == POSITION.OBJECT) {
                generatedComposants.add(generateURI(PAGE.FIRST));
            } else {
                generatedComposants.add(generatelabels(PAGE.FIRST));
            }
        } else {
            generatedComposants.add(generatelabels(PAGE.FIRST));
            if (truc.getPosition() == POSITION.OBJECT) {
                generatedComposants.add(generateURI(PAGE.FIRST));
            }
        }
    }

    /**
     * Second page generate :
     * If subjet then it will generatePropreties.<br>
     * If predicate then it will generateURI.<br>
     * If object then it will generatePropreties.<br>
     * <p>
     * <strong>BUT</strong> if it's an exact query (delimited by double quotes "")
     * if it's in object position, it will not generate labels (that we haven't geenrate in the PageFirst() function search
     * <strong>otherwise</strong>
     * it will generate proprieties. <br>
     */
    private void PageSecond() {
        if (truc.isExact()) {
            if (truc.getPosition() == POSITION.OBJECT) {
                generatedComposants.add(generatelabels(PAGE.SECOND));
            } else {
                generatedComposants.add(generateProprieties(PAGE.SECOND));
            }
        } else {
            if (truc.getPosition() == POSITION.PREDICATE) {
                generatedComposants.add(generateURI(PAGE.SECOND));
            } else {
                generatedComposants.add(generateProprieties(PAGE.SECOND));
            }
        }
    }

    /**
     * Third page generate :
     * If subjet then it will generateURI . <br>
     * If predicate then it will do nothing. <br>
     * If object then it will do nothing.
     * <strong>BUT</strong> if it's an exact query (delimited by double quotes ""). <br>
     * if it's in object position we'll not generate propreties which e haven't generate in PageSecond() function . <br>
     */
    private void PageThird() {
        if (truc.isExact()) {
            if (truc.getPosition() == POSITION.OBJECT) {
                generatedComposants.add(generateProprieties(PAGE.THIRD));
            }
        } else {
            if (truc.getPosition() == POSITION.SUBJECT) {
                generatedComposants.add(generateURI(PAGE.THIRD));
            }
        }
    }

    public String toString() {
        return truc.toString() + "\n"
                + generatedComposants.size() + " queries generated.";
    }
}
