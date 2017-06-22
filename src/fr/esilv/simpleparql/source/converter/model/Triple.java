package fr.esilv.simpleparql.source.converter.model;

/**
 * repesent the triple structure (subject, predicate object)
 */
public class Triple {
    private String subject;
    private String predicate;
    private String object;

    public Triple(String subject, String predicate, String object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    public String getSubject() {
        return subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getObject() {
        return object;
    }

    public String get(POSITION position) {
        switch (position) {
            case SUBJECT:
                return getSubject();
            case PREDICATE:
                return getPredicate();
            case OBJECT:
                return getObject();
        }
        return null;
    }

    public String toString() {
        return subject + " " + predicate + " " + object + ".";
    }
}
