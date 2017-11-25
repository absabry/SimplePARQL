package fr.esilv.simpleparql.source.jenaresult;


import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.sparql.core.TriplePath;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;
import com.hp.hpl.jena.sparql.resultset.RDFOutput;
import com.hp.hpl.jena.sparql.syntax.ElementPathBlock;
import com.hp.hpl.jena.sparql.syntax.ElementVisitorBase;
import com.hp.hpl.jena.sparql.syntax.ElementWalker;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
import fr.esilv.simpleparql.source.server.Server;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;

import java.io.*;
import java.util.*;

/**
 * Working with Jena API.
 */
public class Jena {

    Logger logger = Server.logger;
    /**
     * Execute SPARQL query using Jena API.
     * @param base Base the user chose for exectuing this query
     * @param sparqlQueryString The query in string format
     * @param timeout The timeout decided for the query
     * @return SPARQLResult containing all the tuples of this SPARQL query
     */
    public SPARQLResult executeSparqlQuery(String base, String sparqlQueryString, String timeout) {
        SPARQLResult SPARQLQuery = new SPARQLResult();
        try {
            Query query = QueryFactory.create(sparqlQueryString);
            QueryEngineHTTP qexec = (QueryEngineHTTP) QueryExecutionFactory.sparqlService(base, query);
            qexec.addParam("timeout", timeout);
            ResultSet results = qexec.execSelect();
            SPARQLQuery.setVariables(new ArrayList<>(results.getResultVars())); // get variables of the query
            for (; results.hasNext(); ) {
                QuerySolution sol = results.nextSolution();
                ArrayList<String> solution = new ArrayList<>();
                for (String ittVar : SPARQLQuery.getVariables()) {
                    String val;
                    RDFNode var = sol.get(ittVar);
                    if (var.isLiteral()) {
                        Literal l = (Literal) var;
                        val = l.toString();
                        // delete the types from the string (but keep the @lang)!
                        if (val.contains("^^")) {
                            val = val.substring(0, val.indexOf("^^"));
                        }
                    } else {
                        Resource r = (Resource) var;
                        val = r.getURI();
                    }
                    solution.add(val);
                }
                SPARQLQuery.addToResponse(solution);
            }
            qexec.close();
        } catch (QueryParseException e) {
            logger.debug(e);
            SPARQLQuery.setError("error while parsing your SimplePARQL query\n"+e.getMessage().substring(e.getMessage().indexOf(" ") + 1));
        } catch (Exception e) {
            logger.debug(e);
            logger.debug(sparqlQueryString+ " occured an error");
            SPARQLQuery.setError(e.getMessage().substring(e.getMessage().indexOf(":") + 1).replace("Server", "remote server"));
        }
        return SPARQLQuery;
    }

    /*---------------------------------------OLD STUFF, CAN BE USEFUL LATER---------------------------------------------------*/

    /**
     * old main function
     */
    private static void saveSparql(String base, String sparqlQueryString, String format, String filename) throws IOException {
        Query query = QueryFactory.create(sparqlQueryString);
        QueryEngineHTTP qexec = (QueryEngineHTTP) QueryExecutionFactory.sparqlService(base, query);
        qexec.addParam("timeout", "5000");
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

    /**
     * save in html format
     */
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

    /**
     * get exact format of files we use in this application
     */
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

    /**
     * work with a local database as an endpoint
     */
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

    /**
     * get all triples of the query
     */
    private static void getTriples(String sparqlQueryString) {

        Query query = QueryFactory.create(sparqlQueryString);
        final Set<Node> subjects = new HashSet<>();
        final Set<Node> predictions = new HashSet<>();
        final Set<Node> objects = new HashSet<>();
        ElementWalker.walk(query.getQueryPattern(),
                new ElementVisitorBase() {
                    public void visit(ElementPathBlock el) {
                        Iterator<TriplePath> triples = el.patternElts();
                        while (triples.hasNext()) {
                            TriplePath element = triples.next();
                            System.out.println(
                                    "Subject: " + element.getSubject()
                                            + "  Predictions : " + element.getPredicate()
                                            + "  Object : " + element.getObject()
                            );
                            subjects.add(element.getSubject());
                            predictions.add(element.getPredicate());
                            objects.add(element.getObject());
                        }
                    }
                }
        );
        System.out.println("FIN");
        System.out.println("Subjects");
        System.out.println(subjects);
        System.out.println("Predicates");
        System.out.println(predictions);
        System.out.println("Objects");
        System.out.println(objects);
    }


}