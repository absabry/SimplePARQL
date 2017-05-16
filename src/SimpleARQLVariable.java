public class SimpleARQLVariable {
    private String variable;
    public SimpleARQLVariable(String variable) {
        this.variable = variable;
    }
    public String getVariable(){
        return variable;
    }
    public String getVarname() {
        return variable.substring(1);
    }
    public String toString(){
        return getVarname();
    }
}
