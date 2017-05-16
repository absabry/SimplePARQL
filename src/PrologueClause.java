import java.util.ArrayList;

public class PrologueClause {

    private ArrayList<Prefixe> prefixes;

    public PrologueClause() {
        prefixes = new ArrayList<>();
    }

    public void addPrefixe(String namespacePrefix, String namespaceUrl) {
        prefixes.add(new Prefixe(namespacePrefix, namespaceUrl));
    }

    public void removePrefixe(Prefixe prefixe) {
        for (Prefixe parcours : prefixes) {
            if (parcours.getNamespacePrefix().equals(prefixe.getNamespacePrefix())
                    && parcours.getNamespaceUrl().equals(prefixe.getNamespaceUrl())) {
                prefixes.remove(parcours);
            }
        }
    }

    void clearPrefixes() {
        prefixes.clear();
    }

    public String toString() {
        return prefixes.toString();
    }
}
