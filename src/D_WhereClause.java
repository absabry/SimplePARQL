import javafx.util.Pair;

import java.util.ArrayList;
import java.util.stream.Collectors;

class D_WhereClause {
    private ArrayList<D_Triple> triples;
    private String bind;
    private String filter;
    private String optionnal;

    D_WhereClause() {
        triples = new ArrayList<>();
        bind = null;
        filter = null;
        optionnal = null;
    }
    D_WhereClause(D_WhereClause another){
        System.out.println(this.getTriples());

       /* for(int i=0;i<triples.size();i++){
            another.addTriple(getTriples().get(i));
        }
        bind = this.getBind();
        filter = this.getFilter();
        optionnal = this.getOptionnal();*/
    }

    void addTriple(Pair<String, String> subject, Pair<String, String> predicate, Pair<String, String> object) {
        triples.add(new D_Triple(subject, predicate, object));
    }
    void addTriple(D_Triple triple) {
        triples.add(triple);
    }

    public String getBind() {
        return bind;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        if (this.filter == null) {
            this.filter = filter;
        } else {
            this.filter += filter;
        }
    }

    public String getOptionnal() {
        return optionnal;
    }

    public void setOptionnal(String optionnal) {
        this.optionnal = optionnal;
    }

    public ArrayList<D_Triple> getTriples() {
        return triples;
    }

    public void setTriples(ArrayList<D_Triple> triples) {
        this.triples = triples;
    }

    public void removeTriple(D_Triple triple) {
        for (D_Triple parcours : triples) {
            if (parcours.equals(triple)) {
                triples.remove(parcours);
            }
        }
    }

    public void clearTriples() {
        triples.clear();
    }


    public String toString() {
        return "{\n"
                + "\t" + String.join("\n\t", triples.stream()
                .map(D_Triple::toString)
                .collect(Collectors.toList())) +
                (bind == null ? "" : "\n\t" + bind) +
                (filter == null ? "" : "\n\t" + filter) +
                (optionnal == null ? "" : "\n\t" + optionnal) +
                "\n}";
    }

}
