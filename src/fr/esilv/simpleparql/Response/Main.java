package fr.esilv.simpleparql.Response;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.sparql.resultset.RDFOutput;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;

import java.io.*;
import java.util.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure(new NullAppender());
        QueryFormatted("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>PREFIX dbo: <http://dbpedia.org/ontology/>PREFIX dbr: <http://dbpedia.org/resource/>select distinct ?x ?age ?cityBirth where { ?x rdf:type dbo:Painter. ?x dbo:birthDate ?birth. ?x dbo:birthPlace ?cityBirth.  ?x dbo:deathDate ?death .?cityBirth dbo:country dbr:Germany. BIND(year(?death)-year(?birth)as ?age)  FILTER(?age < 40)}LIMIT 100"
                , "json", "blabla");
    }

    // main function
    private static void QueryFormatted(String sparqlQueryString, String format, String filename) throws IOException {
        Query query = QueryFactory.create(sparqlQueryString);
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
        ResultSet results = qexec.execSelect();
        ResultSet resultsClone = ResultSetFactory.copyResults(results);
        Model model = new RDFOutput().toModel(ResultSetFactory.copyResults(resultsClone));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        switch (format.toLowerCase()) {
            case "json":
                model.write(outputStream, "JSON-LD");
                break;
            case "xml":
                ResultSetFormatter.outputAsXML(outputStream, resultsClone);
                break;
            case "csv":
                ResultSetFormatter.outputAsCSV(outputStream, resultsClone);
                break;
            case "tsv":
                ResultSetFormatter.outputAsTSV(outputStream, resultsClone);
                break;
            case "rdf/json":
                model.write(outputStream, "RDF/JSON");
                break;
            case "rdf/xml":
                model.write(outputStream, "RDF/XML");
                break;
            case "turtle":
                model.write(outputStream, "TURTLE");
                break;
            case "n-triples":
                model.write(outputStream, "N-TRIPLES");
                break;
            case "html":
                try {
                    outputStream = saveHTML(resultsClone);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Mauvais format d'exportation");
                return;
        }
        String formattedResult = new String(outputStream.toByteArray());
        System.out.println(formattedResult);
        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename + "." + getFormat(format)), "UTF-16"))) {
            out.write(formattedResult);
        }
    }

    // save in html format
    private static ByteArrayOutputStream saveHTML(ResultSet results) throws Exception {
        Document document = DocumentHelper.createDocument();
        Element html = document.addElement("html");
        Element head = html.addElement("head");
        head.addElement("style")
                .addText("table {" +
                        "  width:100%;" +
                        "  table-layout: fixed;" +
                        "  border-collapse: collapse;" +
                        "  color: black; " +
                        "}" +
                        "caption {" +
                        "  font-size:1.4rem;" +
                        "  color:#ffffff;" +
                        "  background-color:#003d99;" +
                        "}" +
                        "th {" +
                        "  font-size:1.1rem;" +
                        "  padding:5px;" +
                        "  background-color:#66a3ff;" +
                        "  color:#fff;" +
                        "}" +
                        "tbody tr:nth-child(odd) {" +
                        "  background-color: #e6f9ff;" +
                        "  border-bottom:1px solid #ccc;" +
                        "  color:#444;" +
                        "}" +
                        "tbody tr:nth-child(even) {" +
                        "  background-color: #ffffff;" +
                        "  border-bottom:1px solid #ccc;" +
                        "  color:#444;" +
                        "}" +
                        "a {" +
                        "  color: black; " +
                        "}" +
                        " tbody tr:hover{" +
                        "  background-color: #80b3ff;" +
                        "  cursor:pointer;" +
                        "  color: white; " +
                        "}" +
                        " tbody tr:hover a{" +
                        "  background-color: #80b3ff;" +
                        "  cursor:pointer;" +
                        "  color: white; " +
                        "}" +
                        " tbody tr,td{" +
                        "  text-align:center;" +
                        "}"
                );
        head.addElement("title").addText("Results SPARQL query");
        Element body = html.addElement("body");
        Element table = body.addElement("table").addAttribute("border", "1");

        ArrayList<String> varNames = new ArrayList<>(results.getResultVars());

        table.addElement("caption").addText("Sparql Query Results");
        Element thead = table.addElement("thead");
        Element tr = thead.addElement("tr");
        for (String varItter : varNames)
            tr.addElement("th").addText(varItter);


        Element tbody = table.addElement("tbody");
        for (; results.hasNext(); ) {
            tr = tbody.addElement("tr");
            QuerySolution sol = results.nextSolution();
            for (String ittVar : varNames) {
                String val;
                RDFNode var = sol.get(ittVar);
                if (var.isLiteral()) {
                    Literal l = (Literal) var;
                    val = l.getValue().toString();
                    tr.addElement("td").addText(val);
                } else {
                    Resource r = (Resource) var;
                    val = r.getURI();
                    tr.addElement("td").addElement("a").addAttribute("href", val).addText(val);
                }
            }
        }

        StringWriter buffer = new StringWriter();
        HTMLWriter writer = new HTMLWriter(buffer, OutputFormat.createPrettyPrint());
        writer.write(document);
        System.out.println(buffer);
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        byteOutput.write(buffer.toString().getBytes());
        return byteOutput;
    }

    // get exact format of files we use in this application
    private static String getFormat(String format) {
        String result = format.toLowerCase();
        if (Objects.equals(result, "turtle")) {
            result = "ttl";
        } else if (Objects.equals(result, "n-triples")) {
            result = "nt";
        } else if (format.contains("/")) {
            result = result.substring(result.indexOf('/') + 1, result.length());
        }
        return result;
    }

    // work with a local database as an endpoint
    private static void TDBTest() {
        String tdbDirectory = "C:\\TDBTEST";
        String dbdump0 = "C:\\Users\\Abdel\\Desktop\\Stage_A4\\StageRechreche\\dataTarantinoFilms.purge.nt";
        Dataset dataset = TDBFactory.createDataset(tdbDirectory);
        Model tdb = dataset.getDefaultModel();
        FileManager.get().readModel(tdb, dbdump0);
        Query query = QueryFactory.create(" SELECT  * WHERE {?subject ?predicate ?object} LIMIT 100");
        QueryExecution qexec = QueryExecutionFactory.create(query, tdb);
        ResultSet results = qexec.execSelect();
        ResultSetFormatter.outputAsRDF(System.out, "RDF/XML", results);
        qexec.close();
        tdb.close();
    }

    // get values of variables
    private static void getValues(String sparqlQueryString) {
        List<ArrayList<String>> res = new ArrayList<>();

        try {
            Query query = QueryFactory.create(sparqlQueryString);
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
            ResultSet results = qexec.execSelect();
            ArrayList<String> varNames = new ArrayList<>(results.getResultVars());
            res.add(varNames);
            for (; results.hasNext(); ) {
                QuerySolution sol = results.nextSolution();
                for (String ittVar : varNames) {
                    ArrayList<String> solution = new ArrayList<>();
                    String val;
                    RDFNode var = sol.get(ittVar);
                    if (var.isLiteral()) {
                        Literal l = (Literal) var;
                        val = l.getValue().toString();
                    } else {
                        Resource r = (Resource) var;
                        val = r.getURI();
                    }
                    solution.add(val);
                    res.add(solution);
                }
            }
            qexec.close();
        } catch (Exception e) {
            System.out.println("Failed to execute query \"" + sparqlQueryString + "\":" + e);
        }
        System.out.println(res);
    }


}