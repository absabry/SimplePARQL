import javafx.util.Pair;

import java.util.ArrayList;

class WhereClause {
    private ArrayList<Triple> triples;
    private String bind;

    WhereClause() {
        triples = new ArrayList<>();
        bind = null;
    }

    void addTriple(Pair<String, String> subject, Pair<String, String> predicate, Pair<String, String> object) {
        triples.add(new Triple(subject, predicate, object));
    }

    public String getBind() {
        return bind;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }

    public void removeTriple(Triple triple) {
        for (Triple parcours : triples) {
            if (parcours.equals(triple)) {
                triples.remove(parcours);
            }
        }
    }

    public void clearTriples() {
        triples.clear();
    }

    public String toString() {
        return triples.toString()
                + (bind==null?"":"and the bind is \n" + bind);
    }

}
