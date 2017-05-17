import java.util.ArrayList;
import java.util.stream.Collectors;

class D_SelectClause {
    private boolean distinct;
    private boolean star;
    private ArrayList<D_SimpleARQLVariable> variables;

    public D_SelectClause() {
        this.distinct = false;
        this.star = false;
        this.variables = new ArrayList<>();
    }

    boolean isDistinct() {
        return distinct;
    }

    void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    boolean isStar() {
        return star;
    }

    void setStar(boolean star) {
        this.star = star;
    }

    void addVariable(String variable) {
        if (variable.startsWith("?")) {
            variables.add(new D_SimpleARQLVariable(variable));
        }
    }

    void clearVariables() {
        variables.clear();
    }

    public void removeVariable(D_SimpleARQLVariable variable) {
        for (D_SimpleARQLVariable parcours : variables) {
            if (parcours.getVariable().equals(variable.getVariable())) {
                variables.remove(parcours);
            }
        }
    }

    public String toString() {
        return "SELECT " + (isStar() ? " * " : "")
                + (isDistinct() ? " DISTINCT " : "")
                + (!isStar() ? String.join(" ", variables.stream()
                .map(D_SimpleARQLVariable::toString)
                .collect(Collectors.toList())
        ) : "");
    }


}
