import java.util.ArrayList;

class GenerateQuery {
    private Truc truc;
    private ArrayList<GeneratedComposant> generatedTriples;
    private FilterGenerator filterGenerator;
    private PAGE page;

    GenerateQuery(Truc truc, FilterGenerator filterGenerator, PAGE page) {
        this.filterGenerator = filterGenerator;
        this.truc = truc;
        generatedTriples = new ArrayList<>();
        this.page = page;
        createGeneratedTriples();
    }

    ArrayList<GeneratedComposant> getGeneratedTriples() {
        return generatedTriples;
    }

    private GeneratedComposant generateRessources(Truc truc, PAGE page) {
        String triples = null;
        if (truc.getPosition() == POSITION.SUBJECT) {
            triples = truc.getVariables().get(VARIABLES.VARIABLE)
                    + truc.getCurrentTriple().get(POSITION.PREDICATE) + " " + truc.getCurrentTriple().get(POSITION.OBJECT) + " . ";
        } else if (truc.getPosition() == POSITION.PREDICATE) {
            triples = truc.getCurrentTriple().get(POSITION.SUBJECT) + truc.getVariables().get(VARIABLES.VARIABLE)
                    + truc.getCurrentTriple().get(POSITION.OBJECT) + " . ";
        } else if (truc.getPosition() == POSITION.OBJECT) {
            triples = truc.getCurrentTriple().get(POSITION.SUBJECT) + " " + truc.getCurrentTriple().get(POSITION.PREDICATE)
                    + truc.getVariables().get(VARIABLES.VARIABLE) + " . ";
        }
        if (triples != null) {
            String filter = filterGenerator.createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.VARIABLE));
            return new GeneratedComposant(triples, filter, page);
        }
        return null;
    }

    private GeneratedComposant generatelabels(Truc truc, PAGE page) {
        GeneratedComposant result = generateRessources(truc, page);
        if (result != null) {
            return new GeneratedComposant(result.getTriple() + truc.getVariables().get(VARIABLES.VARIABLE)
                    + Constants.RDF + truc.getVariables().get(VARIABLES.LABEL) + " . ",
                    filterGenerator.createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.LABEL)), page);
        }
        return null;
    }

    private GeneratedComposant generateProprieties(Truc truc, PAGE page) {
        GeneratedComposant result = generateRessources(truc, page);
        if (result != null) {
            return new GeneratedComposant(result.getTriple() + truc.getVariables().get(VARIABLES.VARIABLE)
                    + truc.getVariables().get(VARIABLES.TMP1) + truc.getVariables().get(VARIABLES.TMP2) + " . ",
                    filterGenerator.createSPARQLFilter(truc.getCurrentTriple().get(truc.getPosition()), truc.getVariables().get(VARIABLES.TMP2)), page);

        }
        return null;
    }

    private void createGeneratedTriples() {
        switch (page) {
            case FIRST:
                PageFirst();
                break;
            case SECOND:
                PageFirst();
                PageSecond();
                break;
            case THIRD:
                PageFirst();
                PageSecond();
                PageThird();
                break;
            case OPTIONNAL:
                PageOptionnal();
                break;
        }

    }

    private void PageOptionnal() {
        if (truc.isOptionnal()) {
            generatedTriples.add(generateRessources(truc, PAGE.OPTIONNAL));
            generatedTriples.add(generatelabels(truc, PAGE.OPTIONNAL));
            generatedTriples.add(generateProprieties(truc, PAGE.OPTIONNAL));
        }
    }

    private void PageFirst() {
        generatedTriples.add(generatelabels(truc, PAGE.FIRST));
        if (truc.getPosition() == POSITION.OBJECT) {
            generatedTriples.add(generateRessources(truc, PAGE.FIRST));
        }
    }

    private void PageSecond() {
        if (truc.getPosition() == POSITION.PREDICATE) {
            generatedTriples.add(generateRessources(truc, PAGE.SECOND));
        } else {
            generatedTriples.add(generateProprieties(truc, PAGE.SECOND));
        }
    }

    private void PageThird() {
        if (truc.getPosition() == POSITION.SUBJECT) {
            generatedTriples.add(generateRessources(truc, PAGE.THIRD));
        }
    }

    public String toString() {
        return truc.toString() + "\n"
                + generatedTriples.size() + " queries generated.";
    }
}
