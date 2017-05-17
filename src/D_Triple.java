import javafx.util.Pair;

public class D_Triple {
    private Pair<String,String> subject;
    private Pair<String,String> predicate;
    private Pair<String,String> object;

    public D_Triple(Pair<String,String> subject, Pair<String,String> predicate, Pair<String,String> object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    public Pair<String,String> getSubject() {
        return subject;
    }

    public Pair<String,String> getPredicate() {
        return predicate;
    }

    public Pair<String,String> getObject() {
        return object;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof D_Triple) {
            D_Triple other = (D_Triple) obj;
            return other.getSubject().equals(getSubject()) &&
                    other.getObject().equals(getObject()) &&
                    other.getPredicate().equals(getPredicate());
        } else {
            return false;
        }
    }

    public String toString() {
        return subject.getKey() + "\t" + predicate.getKey() + "\t" + object.getKey();
    }
}
