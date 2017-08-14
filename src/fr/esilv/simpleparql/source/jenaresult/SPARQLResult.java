package fr.esilv.simpleparql.source.jenaresult;

import fr.esilv.simpleparql.source.model.Truc;

import java.util.ArrayList;

/**
 * The result we get from JENA server. <br>
 * We keep variables and all stuff we need to handle the result of the sparql query
 * and give it to the user in the best readble way.
 * <p>
 * <strong>variables:</strong> The variables of the SPARQL query <br>
 * <strong>responses:</strong> The list of the results list <br>
 * <strong>error:</strong> If an error occured, this field won't be null. We know then that this SPARQL have an error.
 */
public class SPARQLResult {
    private ArrayList<String> variables;
    private ArrayList<ArrayList<String>> responses;
    private String error;

    public SPARQLResult() {
        variables = new ArrayList<>();
        responses = new ArrayList<>();
    }

    public ArrayList<String> getVariables() {
        return variables;
    }

    public ArrayList<ArrayList<String>> getResponses() {
        return responses;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setVariables(ArrayList<String> variables) {
        this.variables = variables;
    }

    public void addToResponse(ArrayList<String> response) {
        responses.add(response);
    }

    public String toString() {
        String result = "";
        result += "Variables: " + variables + "\n";
        result += responses.size() + " responses generated";
        for (int i = 0; i < responses.size(); i++) {
            result += responses.get(i) + "\n";
        }
        return result;
    }
}
