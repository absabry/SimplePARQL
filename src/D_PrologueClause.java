import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class D_PrologueClause {
    private final static Logger logger = Logger.getLogger(D_PrologueClause.class);
    private ArrayList<D_Prefixe> prefixes;

    public D_PrologueClause() {
        prefixes = new ArrayList<>();
        addPrefixe("rdfs:","<http://www.w3.org/2000/01/rdf-schema#>");
    }

    public void addPrefixe(String namespacePrefix, String namespaceUrl) {
        D_Prefixe prefixe = new D_Prefixe(namespacePrefix, namespaceUrl);
        if (!contains(prefixe)) {
            prefixes.add(new D_Prefixe(namespacePrefix, namespaceUrl));
        }
    }

    public boolean contains(D_Prefixe prefixe) {
        for (D_Prefixe parcours : prefixes) {
            if (prefixe.equals(parcours)) {
                return true;
            }
        }
        return false;
    }

    public void removePrefixe(D_Prefixe prefixe) {
        for (D_Prefixe parcours : prefixes) {
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
        return String.join("\n", prefixes.stream()
                .map(D_Prefixe::toString)
                .collect(Collectors.toList())
        );
    }
}
