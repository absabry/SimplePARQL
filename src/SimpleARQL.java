import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class SimpleARQL {
    private final static Logger logger = Logger.getLogger(SimpleARQL.class);

    public static void main(String[] args) throws Exception {
        org.apache.log4j.BasicConfigurator.configure();
        logger.setLevel(Level.DEBUG);

        SimplePARQLLexer lexer = new SimplePARQLLexer(new ANTLRFileStream("example.txt"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimplePARQLParser parser = new SimplePARQLParser(tokens);
        ParseTree query = parser.query();
        MyVisitor visitor = new MyVisitor();
        visitor.visit(query);

        // get parts of the query
        PrologueClause prologue = visitor.getPrologue();
        logger.debug(prologue.toString());

        SelectClause select = visitor.getSelect();
        logger.debug(select.toString());

        WhereClause where= visitor.getWhereClause();
        logger.debug(where.toString());
    }
}


