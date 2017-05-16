import java.util.ArrayList;

class SelectClause {
    private boolean distinct;
    private boolean star;
    private ArrayList<SimpleARQLVariable> variables;

    public SelectClause(){
        this.distinct=false;
        this.star=false;
        this.variables= new ArrayList<>();
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
    void addVariable(String variable){
        if(variable.startsWith("?")){
            variables.add(new SimpleARQLVariable(variable));
        }
    }
    void clearVariables(){
        variables.clear();
    }
    public void removeVariable(SimpleARQLVariable variable ){
        for(SimpleARQLVariable parcours : variables) {
            if(parcours.getVariable().equals(variable.getVariable())) {
                variables.remove(parcours);
            }
        }
    }
    public String toString(){
        return "The query is " + (isStar()? "a star ":" some variables ")
                + (isDistinct()? "and it's distinct ": "")
                + (!isStar()? "\nThe variables are " + variables.toString():"")
                ;
    }


}
