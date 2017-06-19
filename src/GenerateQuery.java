import java.util.ArrayList;

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

class GenerateQuery {
    private Truc truc;
    private ArrayList<Composant> generatedComposants;
    private FilterGenerator filterGenerator;
    private PAGE page;

    GenerateQuery(Truc truc, FilterGenerator filterGenerator, PAGE page) {
        this.filterGenerator = filterGenerator;
        this.truc = truc;
        generatedComposants = new ArrayList<>();
        this.page = page;
        createGeneratedTriples();
    }

    ArrayList<Composant> getGeneratedComposants() {
        return generatedComposants;
    }

    /**
     * This function never change, it generates the ressource
     * SELECT *
     * WHERE {
     * ?SimplePARQL_1 ?b ?c .
     * FILTER ( CONTAINS ( STR ( ?SimplePARQL_1 ) , UCASE ( "Sh" ) ) )
     * }
     *
     * @param truc
     * @param page
     * @return
     */
    private Composant generateRessources(Truc truc, PAGE page) {
        String triples = null;
        if (truc.getPosition() == POSITION.SUBJECT) {
            triples = truc.getVariables().get(VARIABLES.VARIABLE)
                    + truc.getCurrentTriple().getPredicate() + " " + truc.getCurrentTriple().getObject() + " . ";
        } else if (truc.getPosition() == POSITION.PREDICATE) {
            triples = truc.getCurrentTriple().getSubject() + truc.getVariables().get(VARIABLES.VARIABLE)
                    + truc.getCurrentTriple().getObject() + " . ";
        } else if (truc.getPosition() == POSITION.OBJECT) {
            triples = truc.getCurrentTriple().getSubject() + " " + truc.getCurrentTriple().getPredicate()
                    + truc.getVariables().get(VARIABLES.VARIABLE) + " . ";
        }
        if (triples != null) {
            String filter = filterGenerator.createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.VARIABLE));
            return new Composant(triples, filter, page);
        }
        return null;
    }

    /**
     * This function never change, it generates the labels
     * SELECT *
     * WHERE {
     * ?SimplePARQL_1 ?b ?c .
     * ?SimplePARQL_1 rdfs:label ?label_1 .
     * FILTER ( CONTAINS ( STR ( ?label_1 ) , UCASE ( "Sh" ) ) )
     * }
     *
     * @param truc
     * @param page
     * @return generated item (triple, filter and page it belongs to)
     */
    private Composant generatelabels(Truc truc, PAGE page) {
        Composant result = generateRessources(truc, page);
        if (result != null) {
            return new Composant(result.getTriple() + truc.getVariables().get(VARIABLES.VARIABLE)
                    + Constants.RDF + truc.getVariables().get(VARIABLES.LABEL) + " . ",
                    filterGenerator.createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.LABEL)), page);
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
     * @param truc
     * @param page
     * @return
     */
    private Composant generateProprieties(Truc truc, PAGE page) {
        Composant result = generateRessources(truc, page);
        if (result != null) {
            return new Composant(result.getTriple() + truc.getVariables().get(VARIABLES.VARIABLE)
                    + truc.getVariables().get(VARIABLES.TMP1) + truc.getVariables().get(VARIABLES.TMP2) + " . ",
                    filterGenerator.createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.TMP2)), page);

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
     * if subjet => generateLabels
     * if predicate => generateLabels
     * if object => generatePropreties and generateLabels
     */
    private void PageFirst() {
        generatedComposants.add(generatelabels(truc, PAGE.FIRST));
        if (truc.getPosition() == POSITION.OBJECT) {
            generatedComposants.add(generateRessources(truc, PAGE.FIRST));
        }
    }

    /**
     * Second page generate :
     * if subjet => generatePropreties
     * if predicate => generateRessource
     * if object => generatePropreties
     */
    private void PageSecond() {
        if (truc.getPosition() == POSITION.PREDICATE) {
            generatedComposants.add(generateRessources(truc, PAGE.SECOND));
        } else {
            generatedComposants.add(generateProprieties(truc, PAGE.SECOND));
        }
    }

    /**
     * Third page generate :
     * if subjet => ressource
     * if predicate => do nothing
     * if object => do nothing
     */
    private void PageThird() {
        if (truc.getPosition() == POSITION.SUBJECT) {
            generatedComposants.add(generateRessources(truc, PAGE.THIRD));
        }
    }

    public String toString() {
        return truc.toString() + "\n"
                + generatedComposants.size() + " queries generated.";
    }
}
