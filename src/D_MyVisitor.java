import javafx.util.Pair;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.log4j.Logger;

public class D_MyVisitor extends SimplePARQLBaseVisitor<String> {
    private final static Logger logger = Logger.getLogger(D_MyVisitor.class);
    private D_PrologueClause prologues;
    private D_SelectClause selectClause;
    private D_WhereClause whereClause;

    D_MyVisitor() {
        selectClause = new D_SelectClause();
        prologues = new D_PrologueClause();
        whereClause = new D_WhereClause();
    }

    D_SelectClause getSelect() {
        return selectClause;
    }

    D_PrologueClause getPrologue() {
        return prologues;
    }

    D_WhereClause getWhereClause() {
        return whereClause;
    }

    @Override
    public String visitPrologue(SimplePARQLParser.PrologueContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            prologues.addPrefixe(ctx.getChild(i).getChild(1).getText(), ctx.getChild(i).getChild(2).getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitSelectQuery(SimplePARQLParser.SelectQueryContext ctx) {
        selectClause.clearVariables();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i).getText().startsWith("?")) {
                selectClause.addVariable(ctx.getChild(i).getText());
            } else if (ctx.getChild(i).getText().equals("*")) {
                selectClause.setStar(true);
            } else if (ctx.getChild(i).getText().equals("DISTINCT")) {
                selectClause.setDistinct(true);
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitTriplesSameSubject(SimplePARQLParser.TriplesSameSubjectContext ctx) {
        int sub = (getStartNode(ctx).getSymbol().getType());
        int pred = (getStartNode(ctx.getChild(1)).getSymbol().getType());
        int obj = (getStopNode(ctx.getChild(1)).getSymbol().getType());
        Pair<String, String> subject = new Pair<>(ctx.getChild(0).getText(), SimplePARQLParser.VOCABULARY.getSymbolicName(sub));
        Pair<String, String> predicate = new Pair<>(ctx.getChild(1).getChild(0).getText(), SimplePARQLParser.VOCABULARY.getSymbolicName(pred));
        Pair<String, String> object = new Pair<>(ctx.getChild(1).getChild(1).getText(), SimplePARQLParser.VOCABULARY.getSymbolicName(obj));
        whereClause.addTriple(subject, predicate, object);
        return visitChildren(ctx);
    }

    @Override
    public String visitBind(SimplePARQLParser.BindContext ctx) {
        whereClause.setBind(ctx.getText());
        return visitChildren(ctx);
    }

    private static TerminalNode getStartNode(ParseTree context) {
        if (context == null) {
            return null;
        }

        if (context instanceof TerminalNode) {
            return (TerminalNode) context;
        }

        for (int i = 0; i < context.getChildCount(); i++) {
            TerminalNode startNode = getStartNode(context.getChild(i));
            if (startNode != null) {
                return startNode;
            }
        }

        return null;
    }

    private static TerminalNode getStopNode(ParseTree context) {
        if (context == null) {
            return null;
        }
        if (context instanceof TerminalNode) {
            return (TerminalNode) context;
        }

        for (int i = context.getChildCount() - 1; i >= 0; i--) {
            TerminalNode stopNode = getStopNode(context.getChild(i));
            if (stopNode != null) {
                return stopNode;
            }
        }

        return null;
    }

    private static int getNodeIndex(ParseTree node) {
        if (node == null || node.getParent() == null) {
            return -1;
        }
        ParseTree parent = node.getParent();
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (parent.getChild(i) == node) {
                return i;
            }
        }
        return -1;
    }
}
