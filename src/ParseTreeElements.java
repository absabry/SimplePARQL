import com.google.common.base.Joiner;
import javafx.util.Pair;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


/**
 * DEPERECATED
 * Je n'utilise plus cette classe, les fonctions générés sont utilisé dans d'autres classes
 * et sont plus précises que les méthodes de cette classe. Je la garde ici juste au cas ou.
 * <p>
 * generer les requetes version 30/05/2017, à midi
 * <p>
 * Collection<ParseTree> trucs = XPath.findAll(newQuery, "//truc", newParser);
 * ParseTreeElements parsedTrucs = new ParseTreeElements(getTreeOfText(treeString));
 * trucs.forEach(node -> parsedTrucs.add(new ParseTreeElement(node)));
 * parsedTrucs.getGeneratedTrees().forEach(bla -> logger.debug(treeToString(newParser, bla)));
 * logger.debug(parsedTrucs.getGeneratedTrees().size());
 * printTree(newParser, parsedTrucs.getGeneratedTrees().get(parsedTrucs.getGeneratedTrees().size() - 1));
 */

class ParseTreeElements {
    private final static Logger logger = Logger.getLogger(ParseTreeElements.class);
    private ArrayList<ParseTreeElement> trucs;
    private ArrayList<ParserRuleContext> generatedTrees;
    private SimplePARQLParser parser;
    private ParserRuleContext query;

    ParseTreeElements(SimplePARQLParser parser) {
        trucs = new ArrayList<>();
        generatedTrees = new ArrayList<>();
        this.parser = parser;
        query = this.parser.query();
    }

    ArrayList<ParserRuleContext> getGeneratedTrees() {
        return generatedTrees;
    }

    void add(ParseTreeElement parseTreeElement) {
        trucs.add(parseTreeElement);
        generateCartesianProductTrees(parseTreeElement);
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
        return trucs.toString() + "\n" +
                "Generated " + generatedTrees.size() + " elements." + "\n" +
                "Values: " + Joiner.on("\n").skipNulls().join(generatedTrees);

    }

    private void addTripleToTree(ParserRuleContext tree, String triplesText) {
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

    private void generateCartesianProductTrees(ParseTreeElement parseTreeElement) {
        if (generatedTrees.isEmpty()) {
            for (Pair<String, String> element : parseTreeElement.getGeneratedTriples()) {
                SimplePARQLParser newOne = getComposantOfTree(treeToString(parser, query));
                ParserRuleContext newOneQuery = newOne.query();
                addTripleToTree(newOneQuery, element.getKey());
                addFilterToTree(newOneQuery, element.getValue());
                generatedTrees.add(newOneQuery);
            }
        } else {
            ArrayList<ParserRuleContext> temp = new ArrayList<>();
            for (Pair<String, String> element : parseTreeElement.getGeneratedTriples()) {
                for (ParserRuleContext alreadyIn : generatedTrees) {
                    SimplePARQLParser newOne = getComposantOfTree(treeToString(parser, alreadyIn));
                    ParserRuleContext newOneQuery = newOne.query();
                    addTripleToTree(newOneQuery, element.getKey());
                    addFilterToTree(newOneQuery, element.getValue());
                    temp.add(newOneQuery);
                }
            }
            generatedTrees.clear();
            generatedTrees.addAll(temp);
        }
        detachTrucsFromTree();
    }

    private void detachTrucsFromTree() {
        for (ParserRuleContext tree : generatedTrees) {
            Collection<ParseTree> trucs = XPath.findAll(tree, "//truc", parser);
            for (ParseTree node : trucs) {
                while (node.getClass() != SimplePARQLParser.TriplesBlockContext.class) {
                    node = node.getParent();
                }
                int indexParent = getNodeIndex(node);
                ParserRuleContext parent = (ParserRuleContext) node.getParent();
                ParseTree lastChild = node.getChild(node.getChildCount() - 1);
                if (lastChild instanceof ParserRuleContext) {
                    if (lastChild.getClass() == SimplePARQLParser.TriplesBlockContext.class) {
                        parent.children.set(indexParent, lastChild);
                    }
                } else {
                    parent.children.remove(indexParent);
                }
            }
        }
    }

    private int getNodeIndex(ParseTree node) {
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

    private void reArrangeQueries() {
        // placer tout les filtres a la fin
    }


    // Ici : le code quand je veux recuperer la place du truc, et le mettre a sa place exactement. Beaucoup de galères...
    // A SUPPRIMMER !!!!

    // complicated : quand on ajoute une leaf a la tree (on ajoute le premier choix) , ca modifie les parents des autres enfants trucs et donc ca tue presque tout
    private void DeprecatedAddTripleToTree(ParserRuleContext tree, ParseTreeElement element, String triplesText) {
        ParserRuleContext whereclause = null;
        for (int i = 0; i < tree.children.size(); i++) {
            if (tree.children.get(i).getClass() == SimplePARQLParser.WhereClauseContext.class) {
                whereclause = (ParserRuleContext) tree.children.get(i).getChild(1);
            }
        }
        if (whereclause != null) {
            Pair<ParserRuleContext, Integer> triplesBlocks = DeprecatedFind(tree, element, SimplePARQLParser.RULE_triplesBlock);
            if (triplesBlocks != null) {
                ParseTree lastChild = triplesBlocks.getKey().getChild(triplesBlocks.getKey().getChildCount() - 1);
                if (lastChild instanceof ParserRuleContext) {
                    Pair<ParserRuleContext, Integer> tripleSameSubject = DeprecatedFind(tree, element, SimplePARQLParser.RULE_triplesSameSubject);
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
    private void DeprecatedGenerateCartesianProductTrees(ParseTreeElement parseTreeElement) {
        if (generatedTrees.isEmpty()) {
            int counter = 0;
            for (Pair<String, String> element : parseTreeElement.getGeneratedTriples()) {
                SimplePARQLParser newOne = getComposantOfTree(treeToString(parser, query));
                ParserRuleContext newOneQuery = newOne.query();
                if (counter == 1) {
                    printTree(getComposantOfTree(treeToString(parser, query)), getComposantOfTree(treeToString(parser, query)).query());
                }
                DeprecatedAddTripleToTree(newOneQuery, parseTreeElement, element.getKey());
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
                    logger.debug("----------------" + counter + "---------------------");
                    logger.debug("TREE BEFORE");
                    logger.debug(treeToString(newOne, newOneQuery));
                    if (counter == 1) {
                        printTree(getComposantOfTree(treeToString(parser, alreadyIn)), (getComposantOfTree(treeToString(parser, alreadyIn))).query());
                    }
                    DeprecatedAddTripleToTree(newOneQuery, parseTreeElement, element.getKey());
                    addFilterToTree(newOneQuery, element.getValue());
                    logger.debug("TREE AFTER");
                    logger.debug(treeToString(newOne, newOneQuery));
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

    private Pair<ParserRuleContext, Integer> DeprecatedFind(ParserRuleContext tree, ParseTreeElement element, int ruleIndex) {
        ArrayList<Pair<ParserRuleContext, Integer>> tempList = new ArrayList<>();
        Pair<ParserRuleContext, Integer> truc = new Pair<>(tree, -1);
        tempList.add(truc);

        for (int i = element.getParents().size() - 1; i > 0; i--) {
            Pair<ParserRuleContext, Integer> next = element.getParents().get(i - 1);
            truc = new Pair<>((ParserRuleContext) truc.getKey().getChild(next.getValue()), next.getValue());
            tempList.add(truc);
        }
        for (int i = tempList.size() - 1; i >= 0; i--) {
            Pair<ParserRuleContext, Integer> temp = tempList.get(i);
            if (temp.getKey().getRuleIndex() == ruleIndex) {
                return temp;
            }
        }
        return null;
    }
}
