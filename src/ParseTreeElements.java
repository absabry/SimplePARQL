import javafx.util.Pair;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.util.ArrayList;

public class ParseTreeElements {
    private ArrayList<ParseTreeElement> parsedTree;
    private ArrayList<Pair<String, String>> generatedForTree;

    ParseTreeElements() {
        parsedTree = new ArrayList<>();
        generatedForTree = new ArrayList<>();
    }

    void add(ParseTreeElement parseTreeElement) {
        parsedTree.add(parseTreeElement);
        generateCartesianProduct(parseTreeElement);
    }

    void add(Pair<String, String> triplesAndFilter) {
        generatedForTree.add(triplesAndFilter);
    }

    private void generateCartesianProduct(ParseTreeElement parseTreeElement) {
        if (generatedForTree.isEmpty()) {
            generatedForTree.addAll(parseTreeElement.getGeneratedTriples());
        } else {
            ArrayList<Pair<String, String>> tempGenerated= new ArrayList<>();
            for (Pair<String, String> aGeneratedForTree : generatedForTree) {
                for (int j = 0; j < parseTreeElement.getGeneratedTriples().size(); j++) {
                    Pair<String, String> temp = new Pair<>(parseTreeElement.getGeneratedTriples().get(j).getKey() + aGeneratedForTree.getKey()
                            , parseTreeElement.getGeneratedTriples().get(j).getValue() + aGeneratedForTree.getValue());
                    tempGenerated.add(temp);
                }
            }
            generatedForTree.clear();
            generatedForTree.addAll(tempGenerated);
        }
    }




    private void ConvertTree(ArrayList<ParseTreeElement> trucPath, ParserRuleContext tree) {
        for (ParseTreeElement element : trucPath) {
            Pair<String, String> generated = element.getGeneratedTriples().get(2);
            Pair<ParserRuleContext, Integer> triplesBlock = element.find(SimplePARQLParser.RULE_triplesBlock);
            org.antlr.v4.runtime.tree.ParseTree lastChild = triplesBlock.getKey().getChild(triplesBlock.getKey().children.size() - 1);
            if (lastChild instanceof ParserRuleContext) {
                Pair<ParserRuleContext, Integer> tripleSameSubject = element.find(SimplePARQLParser.RULE_triplesSameSubject);
                triplesBlock.getKey().children.set(tripleSameSubject.getValue(), getComposantOfTree(generated.getKey()).triplesBlock());
                addFilterToTree(tree, generated.getValue());
            } else {
                triplesBlock.getKey().getParent().children.set(triplesBlock.getValue(), getComposantOfTree(generated.getKey()).triplesBlock());
                addFilterToTree(tree, generated.getValue());
            }
        }
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
            SimplePARQLParser.GraphPatternNotTriplesContext graphPatternNotTriplesContext =
                    new SimplePARQLParser.GraphPatternNotTriplesContext(whereclause, 1);
            org.antlr.v4.runtime.tree.ParseTree temp = whereclause.getChild(whereclause.getChildCount() - 1);
            whereclause.children.set(whereclause.getChildCount() - 1, graphPatternNotTriplesContext);
            whereclause.addChild((TerminalNodeImpl) temp);
            ParserRuleContext filterTree = getComposantOfTree(filterText).filter();
            graphPatternNotTriplesContext.addChild(filterTree);
        }
    }

    private SimplePARQLParser getComposantOfTree(String text) {
        CharStream codeStream = CharStreams.fromString(text);
        SimplePARQLLexer lexer = new SimplePARQLLexer(codeStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SimplePARQLParser(tokens);
    }
}
