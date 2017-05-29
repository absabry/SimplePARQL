import com.google.common.base.Joiner;
import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;
import javafx.util.Pair;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.xpath.XPath;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class ParseTreeElements {
    private ArrayList<ParseTreeElement> parsedTree;
    private ArrayList<ParserRuleContext> generatedTrees;
    private SimplePARQLParser parser;
    private ParserRuleContext query;

    ParseTreeElements(SimplePARQLParser parser) {
        parsedTree = new ArrayList<>();
        generatedTrees = new ArrayList<>();
        this.parser = parser;
        query = this.parser.query();
    }

    ArrayList<ParserRuleContext> getGeneratedTrees() {
        return generatedTrees;
    }

    void add(ParseTreeElement parseTreeElement) {
        parsedTree.add(parseTreeElement);
        newGenerateCartesianProductTrees(parseTreeElement);
    }

    private void detachtrucs() {
        for (ParseTreeElement element : parsedTree) {
            Pair<ParserRuleContext, Integer> triplesBlock = element.find(SimplePARQLParser.RULE_triplesBlock);
            ParseTree lastChild = triplesBlock.getKey().getChild(triplesBlock.getKey().getChildCount());
            if (lastChild instanceof ParserRuleContext) {
                ParserRuleContext parent = triplesBlock.getKey().getParent();
                triplesBlock.getKey().getParent().children.remove(triplesBlock.getKey());
            } else if (lastChild instanceof TerminalNodeImpl) {
                triplesBlock.getKey().getParent().children.remove(triplesBlock.getKey());
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
            SimplePARQLParser.GraphPatternNotTriplesContext graphPatternNotTriplesContext
                    = new SimplePARQLParser.GraphPatternNotTriplesContext(whereclause, 1);
            ParseTree closedBrackets = whereclause.getChild(whereclause.getChildCount() - 1);
            whereclause.children.set(whereclause.getChildCount() - 1, graphPatternNotTriplesContext);
            whereclause.addChild((TerminalNodeImpl) closedBrackets);
            graphPatternNotTriplesContext.addChild(getComposantOfTree(filterText).filter());
        }
    }

    private Pair<ParserRuleContext, Integer> find(ParserRuleContext tree, ParseTreeElement element, int ruleIndex) {
        ArrayList<Pair<ParserRuleContext, Integer>> tempList = new ArrayList<>();
        Pair<ParserRuleContext, Integer> truc = new Pair<>(tree, -1);
        tempList.add(truc);

        for (int i = element.getParents().size() - 1; i > 0; i--) {
            Pair<ParserRuleContext, Integer> next = element.getParents().get(i - 1);
            if (!(truc.getKey().getChild(next.getValue()) instanceof ParserRuleContext)) {
                System.out.println("HERE!!!!");
                System.out.println(next.getKey().getClass() + " ///" + next.getValue());
                System.out.println(next.getKey().getText());
            } else {
                truc = new Pair<>((ParserRuleContext) truc.getKey().getChild(next.getValue()), next.getValue());
                tempList.add(truc);
            }
        }
        for (int i = tempList.size() - 1; i >= 0; i--) {
            Pair<ParserRuleContext, Integer> temp = tempList.get(i);
            if (temp.getKey().getRuleIndex() == ruleIndex) {
                return temp;
            }
        }
        return null;
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
                "Generated " + generatedTrees.size() + " elements." + "\n" +
                "Values: " + Joiner.on("\n").skipNulls().join(generatedTrees);

    }

    private void newAddTripleToTree(ParserRuleContext tree, ParseTreeElement element, String triplesText) {
        ParserRuleContext whereclause = null;
        for (int i = 0; i < tree.children.size(); i++) {
            if (tree.children.get(i).getClass() == SimplePARQLParser.WhereClauseContext.class) {
                whereclause = (ParserRuleContext) tree.children.get(i).getChild(1);
            }
        }
        if (whereclause != null) {
            ParseTree closedBrackets = whereclause.getChild(whereclause.getChildCount() - 1);
            whereclause.children.set(whereclause.getChildCount() - 1, getComposantOfTree(triplesText).triplesBlock());
            whereclause.addChild((TerminalNodeImpl) closedBrackets);
        }
    }

    // tested : il manque juste a supprimer des leafs qui sont des "trucs"
    private void newGenerateCartesianProductTrees(ParseTreeElement parseTreeElement) {
        if (generatedTrees.isEmpty()) {
            for (Pair<String, String> element : parseTreeElement.getGeneratedTriples()) {
                SimplePARQLParser newOne = getComposantOfTree(treeToString(parser, query));
                ParserRuleContext newOneQuery = newOne.query();
                newAddTripleToTree(newOneQuery, parseTreeElement, element.getKey());
                addFilterToTree(newOneQuery, element.getValue());
                generatedTrees.add(newOneQuery);
            }
        } else {
            ArrayList<ParserRuleContext> temp = new ArrayList<>();
            for (Pair<String, String> element : parseTreeElement.getGeneratedTriples()) {
                for (ParserRuleContext alreadyIn : generatedTrees) {
                    SimplePARQLParser newOne = getComposantOfTree(treeToString(parser, alreadyIn));
                    ParserRuleContext newOneQuery = newOne.query();
                    newAddTripleToTree(newOneQuery, parseTreeElement, element.getKey());
                    addFilterToTree(newOneQuery, element.getValue());
                    temp.add(newOneQuery);
                }
            }
            generatedTrees.clear();
            generatedTrees.addAll(temp);
        }
    }

    private void detachTrucs(){

    }
    private void reArrangeQueries(){
        // changer tout les filtres a la fin
    }




    // Ici : le code quand je veux recuperer la place du truc, et le mettre a sa place exactement. Beaucoup de galères...
    // A SUPPRIMMER !!!!

    // complicated : quand on ajoute une leaf a la tree (on ajoute le premier choix) , ca modifie les parents des autres enfants trucs et donc ca tue presque tout
    private void addTripleToTree(ParserRuleContext tree, ParseTreeElement element, String triplesText) {
        ParserRuleContext whereclause = null;
        for (int i = 0; i < tree.children.size(); i++) {
            if (tree.children.get(i).getClass() == SimplePARQLParser.WhereClauseContext.class) {
                whereclause = (ParserRuleContext) tree.children.get(i).getChild(1);
            }
        }
        if (whereclause != null) {
            /*
            old one
            ParseTree closedBrackets = whereclause.getChild(whereclause.getChildCount() - 1);
            whereclause.children.set(whereclause.getChildCount() - 1, getComposantOfTree(triplesText).triplesBlock());
            whereclause.addChild((TerminalNodeImpl) closedBrackets);
            */
            Pair<ParserRuleContext, Integer> triplesBlocks = find(tree, element, SimplePARQLParser.RULE_triplesBlock);
            if (triplesBlocks != null) {
                ParseTree lastChild = triplesBlocks.getKey().getChild(triplesBlocks.getKey().getChildCount() - 1);
                if (lastChild instanceof ParserRuleContext) {
                    Pair<ParserRuleContext, Integer> tripleSameSubject = find(tree, element, SimplePARQLParser.RULE_triplesSameSubject);
                    if (tripleSameSubject != null) {
                        triplesBlocks.getKey().children.set(tripleSameSubject.getValue(), getComposantOfTree(triplesText).triplesBlock());
                    }
                } else {
                    triplesBlocks.getKey().getParent().children.set(triplesBlocks.getValue(), getComposantOfTree(triplesText).triplesBlock());
                }
            }
        }
    }

    // complicated : quand on ajoute une leaf a la tree (on ajoute le premier choix) , ca modifie les parents des autres enfants trucs et donc ca tue presque tout
    private void generateCartesianProductTrees(ParseTreeElement parseTreeElement) {
        if (generatedTrees.isEmpty()) {
            int counter = 0;
            for (Pair<String, String> element : parseTreeElement.getGeneratedTriples()) {
                SimplePARQLParser newOne = getComposantOfTree(treeToString(parser, query));
                ParserRuleContext newOneQuery = newOne.query();
                if (counter == 1) {
                    printTree(getComposantOfTree(treeToString(parser, query)), getComposantOfTree(treeToString(parser, query)).query());
                }
                addTripleToTree(newOneQuery, parseTreeElement, element.getKey());
                addFilterToTree(newOneQuery, element.getValue());
                generatedTrees.add(newOneQuery);
                counter++;
            }
        } else {
            int counter = 0;
            ArrayList<ParserRuleContext> temp = new ArrayList<>();
            for (Pair<String, String> element : parseTreeElement.getGeneratedTriples()) {
                for (ParserRuleContext alreadyIn : generatedTrees) {
                    SimplePARQLParser newOne = getComposantOfTree(treeToString(parser, alreadyIn));
                    ParserRuleContext newOneQuery = newOne.query();
                    System.out.println("----------------" + counter + "---------------------");
                    System.out.println("TREE BEFORE");
                    System.out.println(treeToString(newOne, newOneQuery));
                    if (counter == 1) {
                        printTree(getComposantOfTree(treeToString(parser, alreadyIn)), (getComposantOfTree(treeToString(parser, alreadyIn))).query());
                    }
                    addTripleToTree(newOneQuery, parseTreeElement, element.getKey());
                    addFilterToTree(newOneQuery, element.getValue());
                    System.out.println("TREE AFTER");
                    System.out.println(treeToString(newOne, newOneQuery));
                    if (counter == 1) {
                        printTree(newOne, newOneQuery);
                    }
                    temp.add(newOneQuery);
                    counter++;
                }
            }
            generatedTrees.clear();
            generatedTrees.addAll(temp);
        }
    }

    // print the tree in a Jframe
    private static void printTree(SimplePARQLParser parser, ParserRuleContext query) {
        //  logger.debug(query.toStringTree(parser));
        JFrame frame = new JFrame("Antlr Tree");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()), query);
        viewr.setScale(2);
        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 30));
        frame.getContentPane().add(jScrollPane);
    }
}
