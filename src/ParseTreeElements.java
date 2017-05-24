import com.google.common.base.Joiner;
import javafx.util.Pair;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.util.ArrayList;

class ParseTreeElements {
    private ArrayList<ParseTreeElement> parsedTree;
    private ArrayList<Pair<String, String>> generatedStringForTree;
    private SimplePARQLParser parser;
    private ParserRuleContext query;

    ParseTreeElements(SimplePARQLParser parser) {
        parsedTree = new ArrayList<>();
        generatedStringForTree = new ArrayList<>();
        this.parser = parser;
        query = this.parser.query();
    }

    void add(ParseTreeElement parseTreeElement) {
        parsedTree.add(parseTreeElement);
        generateCartesianProduct(parseTreeElement);
        //anotherGenerateCartesianProduct(parseTreeElement);
    }

    private void generateCartesianProduct(ParseTreeElement parseTreeElement) {
        if (generatedStringForTree.isEmpty()) {
            generatedStringForTree.addAll(parseTreeElement.getGeneratedTriples());
        } else {
            ArrayList<Pair<String, String>> tempGenerated = new ArrayList<>();
            for (Pair<String, String> oldGenerated : generatedStringForTree) {
                for (Pair<String, String> tobeAdded : parseTreeElement.getGeneratedTriples()) {
                    Pair<String, String> temp = new Pair<>(oldGenerated.getKey() + " . " + tobeAdded.getKey(),
                            oldGenerated.getValue() + " " + tobeAdded.getValue());
                    tempGenerated.add(temp);
                }
            }
            generatedStringForTree.clear();
            generatedStringForTree.addAll(tempGenerated);
        }
    }

    ArrayList<String> ConvertTree() {
        ArrayList<String> newQueries = new ArrayList<>();
        for (Pair<String, String> element : generatedStringForTree) {
            SimplePARQLParser newOne = getComposantOfTree(treeToString(parser, query));
            ParserRuleContext newOneQuery = newOne.query();
            addTripleToTree(newOneQuery, element.getKey());
            addFilterToTree(newOneQuery, element.getValue());
            newQueries.add(treeToString(parser, newOneQuery));
        }
        return newQueries;
    }

    private void addFilterToTree(ParserRuleContext tree, String filterText) {
        ParserRuleContext whereclause = null;
        for (int i = 0; i < tree.children.size(); i++) {
            if (tree.children.get(i).getClass() == SimplePARQLParser.WhereClauseContext.class) {
                whereclause = (ParserRuleContext) tree.children.get(i).getChild(1);
            }
        }
        if (whereclause != null) {
            // ajouter le graphPatternnotTriples au mileu
            ParseTree closedBrackets = whereclause.getChild(whereclause.getChildCount() - 1);
            whereclause.children.set(whereclause.getChildCount() - 1, getComposantOfTree(filterText).filter());
            whereclause.addChild((TerminalNodeImpl) closedBrackets);
        }
    }

    private void addTripleToTree(ParserRuleContext tree, String triplesText) {
        ParserRuleContext whereclause = null;
        for (int i = 0; i < tree.children.size(); i++) {
            if (tree.children.get(i).getClass() == SimplePARQLParser.WhereClauseContext.class) {
                whereclause = (ParserRuleContext) tree.children.get(i).getChild(1);
            }
        }
        if (whereclause != null) {
            // ajouter le graphPatternnotTriples au mileu
            ParseTree closedBrackets = whereclause.getChild(whereclause.getChildCount() - 1);
            whereclause.children.set(whereclause.getChildCount() - 1, getComposantOfTree(triplesText).triplesBlock());
            whereclause.addChild((TerminalNodeImpl) closedBrackets);
        }
    }

    private SimplePARQLParser getComposantOfTree(String text) {
        CharStream codeStream = CharStreams.fromString(text);
        SimplePARQLLexer lexer = new SimplePARQLLexer(codeStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SimplePARQLParser(tokens);
    }

    private String treeToString(SimplePARQLParser parser, ParserRuleContext tree) {
        TreePrinterListener listener = new TreePrinterListener(parser);
        ParseTreeWalker.DEFAULT.walk(listener, tree);
        return listener.toString();
    }

    public String toString() {
        return parsedTree.toString() + "\n" +
                "Generated " + generatedStringForTree.size() + " elements." + "\n" +
                "Values: " + Joiner.on("\n").skipNulls().join(generatedStringForTree);

    }

    private void generateCartesianProductTrees(ParseTreeElement parseTreeElement) {
        if (generatedStringForTree.isEmpty()) {
            generatedStringForTree.addAll(parseTreeElement.getGeneratedTriples());
        } else {
            ArrayList<Pair<String, String>> tempGenerated = new ArrayList<>();
            for (Pair<String, String> oldGenerated : generatedStringForTree) {
                for (Pair<String, String> tobeAdded : parseTreeElement.getGeneratedTriples()) {
                    Pair<String, String> temp = new Pair<>(oldGenerated.getKey() + " . " + tobeAdded.getKey(),
                            oldGenerated.getValue() + " " + tobeAdded.getValue());
                    tempGenerated.add(temp);
                }
            }
            generatedStringForTree.clear();
            generatedStringForTree.addAll(tempGenerated);
        }
    }
}
