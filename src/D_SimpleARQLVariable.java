public class D_SimpleARQLVariable {
    private String variable;
    public D_SimpleARQLVariable(String variable) {
        this.variable = variable;
    }
    public String getVariable(){
        return variable;
    }
    public String getVarname() {
        return variable.substring(1);
    }
    public String toString(){
        return variable;
    }
}
