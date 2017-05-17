import javafx.util.Pair;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class D_SimpleARQL {
    private final static Logger logger = Logger.getLogger(D_SimpleARQL.class);
    public static void main(String[] args) throws Exception {
        org.apache.log4j.BasicConfigurator.configure();
        logger.setLevel(Level.DEBUG);
        String file="SimplePARQL.txt";
        ANTLRInputStream code = new ANTLRInputStream(new FileInputStream(file));
        SimplePARQLLexer lexer = new SimplePARQLLexer(code);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimplePARQLParser parser = new SimplePARQLParser(tokens);
        ParseTree query = parser.query();
        D_MyVisitor visitor = new D_MyVisitor();
        visitor.visit(query);

        /*
        // get parts of the query
        D_PrologueClause prologue = visitor.getPrologue();
        logger.debug(prologue.toString());

        D_SelectClause select = visitor.getSelect();
        logger.debug(select.toString());
        */
        D_WhereClause where= visitor.getWhereClause();
        D_WhereClause whereclone=new D_WhereClause(where);
        logger.debug(where.toString());


       // ArrayList<String> queries = getSPARQLQueries("D_SimpleARQL.txt");
       // logger.debug(queries.toString());
    }

    private static ArrayList<String> getSPARQLQueries(String filename) throws IOException, CloneNotSupportedException {
        ArrayList<String> queries = new ArrayList<>();
        SimplePARQLLexer lexer = new SimplePARQLLexer(new ANTLRFileStream(filename));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimplePARQLParser parser = new SimplePARQLParser(tokens);
        ParseTree query = parser.query();
        D_MyVisitor visitor = new D_MyVisitor();
        visitor.visit(query);

        D_PrologueClause prologue = visitor.getPrologue();
        D_SelectClause select = visitor.getSelect();
        String queryString = prologue.toString() + "\n"
                + select.toString() + "\n";

        D_WhereClause where = visitor.getWhereClause();
        ArrayList<D_Triple> triples = where.getTriples();
        /*
        ArrayList<D_Triple> newTriples = new ArrayList<>();
        ArrayList<D_Triple> removedTriples= new ArrayList<>();
        for (D_Triple parcours : triples) {
            for (int i = 0; i < D_Constants.TRUCS.size(); i++) {
                if (parcours.getSubject().getValue().equals(D_Constants.TRUCS.get(i))) {
                    String variable="?SimpleARQL_" + counter;
                    Pair<String, String> subject = new Pair<>(variable, D_Constants.VAR);
                    D_Triple newOne = new D_Triple(subject, parcours.getPredicate(), parcours.getObject());
                    newTriples.add(newOne);
                    removedTriples.add(parcours);
                    where.setFilter(createSimpleARQLFilter(variable,parcours.getSubject().getKey()));
                    counter++;
                }
            }
        }
        triples.removeAll(removedTriples);
        triples.addAll(newTriples);
        */
        int counter = 1;
        for (D_Triple parcours : triples) {
            for (int j = 0; j < D_Constants.TRUCS.size(); j++) {
                if (parcours.getSubject().getValue().equals(D_Constants.TRUCS.get(j))) {
                    String variable = "?SimpleARQL_" + counter;
                    queryString += firstCase(variable, parcours, where);
                    counter++;
                }
            }
        }
        queries.add(queryString);
        return queries;
    }

    private static String createSimpleARQLFilter(String variable, String truc) {
        String result = "FILTER (";
        truc = truc.replace("\"", "");
        String[] trucs = truc.split(" ");
        int counter = 0;
        for (String myTruc : trucs) {
            result += "CONTAINS(UPPER(STR(" + variable + ")),\"" + myTruc.toUpperCase() + "\") ";
            if (counter != trucs.length - 1) {
                result += " && ";
            }
            counter++;
        }
        result += ")";
        return result;
    }

    private static String firstCase(String variable, D_Triple parcours, D_WhereClause where) throws CloneNotSupportedException {
        Pair<String, String> subject = new Pair<>(variable, D_Constants.VAR);
        D_Triple newOne = new D_Triple(subject, parcours.getPredicate(), parcours.getObject());
        where.addTriple(newOne);
        where.removeTriple(parcours);
        where.setFilter(createSimpleARQLFilter(variable, parcours.getSubject().getKey()));
        return where.toString();
    }
}


