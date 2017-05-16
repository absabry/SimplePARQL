
public class Prefixe {
    private String namespacePrefix;
    private String namespaceUrl;

    public Prefixe(String namespacePrefix, String namespaceUrl) {
        this.namespacePrefix = namespacePrefix.substring(0, namespacePrefix.length()-1);
        this.namespaceUrl = namespaceUrl;
    }

    public String getNamespacePrefix() {
        return namespacePrefix;
    }

    public String getNamespaceUrl() {
        return namespaceUrl;
    }

    public String toString(){
        return namespacePrefix+":"+namespaceUrl;
    }

}
