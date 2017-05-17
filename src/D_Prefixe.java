
public class D_Prefixe {
    private String namespacePrefix;
    private String namespaceUrl;

    public D_Prefixe(String namespacePrefix, String namespaceUrl) {
        this.namespacePrefix = namespacePrefix.substring(0, namespacePrefix.length() - 1);
        this.namespaceUrl = namespaceUrl;
    }

    public String getNamespacePrefix() {
        return namespacePrefix;
    }

    public String getNamespaceUrl() {
        return namespaceUrl;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof D_Prefixe) {
            D_Prefixe other = (D_Prefixe) obj;
            return other.getNamespacePrefix().equals(getNamespacePrefix()) &&
                    other.getNamespaceUrl().equals(getNamespaceUrl());
        } else {
            return false;
        }
    }

    public String toString() {
        return "PREFIX " + namespacePrefix + ": " + namespaceUrl;
    }

}
