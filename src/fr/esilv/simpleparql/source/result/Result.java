package fr.esilv.simpleparql.source.result;

import fr.esilv.simpleparql.source.model.Truc;

import java.util.ArrayList;

/**
 * Strcuture of the result we get from JENA server.
 * We keep variables and all stuff we need to handle the result of the sparql query
 * and give it to the user in the best readble way
 */
public class Result {
    private ArrayList<String> variables;
    private ArrayList<ArrayList<String>> responses;
    private ArrayList<Truc> responsesTruc;
    private String error;

    public Result() {
        variables = new ArrayList<>();
        responsesTruc = new ArrayList<>();
        responses = new ArrayList<>();
    }

    public ArrayList<String> getVariables() {
        return variables;
    }

    public ArrayList<ArrayList<String>> getResponses() {
        return responses;
    }

    public ArrayList<Truc> getResponsesTruc() {
        return responsesTruc;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    /**
     * All the variables of the query
     * @param variables the arrayList variables
     */
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
