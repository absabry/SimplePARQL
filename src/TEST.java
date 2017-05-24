import org.antlr.v4.gui.TreePostScriptGenerator;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.apache.log4j.Logger;

import javax.print.PrintException;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/*
SELECT ?s ?r
WHERE
{
?s ?r ?o
FILTER(CONTAINS(STR(?o), "Germany"))
}
*/
public class TEST {
    private final static Logger logger = Logger.getLogger(TEST.class);

    public static void main(String args[]) throws Exception {
        org.apache.log4j.BasicConfigurator.configure();
        SimplePARQLParser parser = getTree("WithIt.txt");
        ParserRuleContext tree = parser.query();
        addFilter(tree,"FILTER ( CONTAINS(UCASE(STR(?Ressource)),UCASE(\"Feodor\")))");
        printTree(parser,tree);
    }

    private static void printTree(SimplePARQLParser parser, ParserRuleContext query) {
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
    }

    private static void addLimit(ParserRuleContext tree) {
        for (int i = 0; i < tree.children.size(); i++) {
            ParseTree child = tree.children.get(i);
            if (child.getClass() == SimplePARQLParser.SolutionModifierContext.class) {

                SimplePARQLParser.LimitOffsetClausesContext limitOffsetClausesContext = new SimplePARQLParser.LimitOffsetClausesContext((ParserRuleContext) child, 1);
                SimplePARQLParser.LimitClauseContext limitClauseContext = new SimplePARQLParser.LimitClauseContext(limitOffsetClausesContext, 1);

                limitClauseContext.addChild(new TerminalNodeImpl(new Token() {
                    @Override
                    public String getText() {
                        return "LIMIT";
                    }

                    @Override
                    public int getType() {
                        return SimplePARQLLexer.T__60;
                    }

                    @Override
                    public int getLine() {
                        return 0;
                    }

                    @Override
                    public int getCharPositionInLine() {
                        return 0;
                    }

                    @Override
                    public int getChannel() {
                        return 0;
                    }

                    @Override
                    public int getTokenIndex() {
                        return 0;
                    }

                    @Override
                    public int getStartIndex() {
                        return 0;
                    }

                    @Override
                    public int getStopIndex() {
                        return 0;
                    }

                    @Override
                    public TokenSource getTokenSource() {
                        return null;
                    }

                    @Override
                    public CharStream getInputStream() {
                        return null;
                    }
                }));
                limitClauseContext.addChild(new TerminalNodeImpl(new Token() {
                    @Override
                    public String getText() {
                        return "10";
                    }

                    @Override
                    public int getType() {
                        return SimplePARQLLexer.INTEGER;
                    }

                    @Override
                    public int getLine() {
                        return 0;
                    }

                    @Override
                    public int getCharPositionInLine() {
                        return 0;
                    }

                    @Override
                    public int getChannel() {
                        return 0;
                    }

                    @Override
                    public int getTokenIndex() {
                        return 0;
                    }

                    @Override
                    public int getStartIndex() {
                        return 0;
                    }

                    @Override
                    public int getStopIndex() {
                        return 0;
                    }

                    @Override
                    public TokenSource getTokenSource() {
                        return null;
                    }

                    @Override
                    public CharStream getInputStream() {
                        return null;
                    }
                }));
                limitOffsetClausesContext.addChild(limitClauseContext);
                ((ParserRuleContext) child).addChild(limitOffsetClausesContext);
            }
        }
    }

    private static void addFilterManually(ParserRuleContext tree) {
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
            ParseTree temp = whereclause.getChild(whereclause.getChildCount() - 1);
            whereclause.children.set(whereclause.getChildCount() - 1, graphPatternNotTriplesContext);
            whereclause.addChild((TerminalNodeImpl) temp);


            SimplePARQLParser.FilterContext filterContext =
                    new SimplePARQLParser.FilterContext(graphPatternNotTriplesContext, 1);
            filterContext.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return "FILTER";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.T__8;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));
            SimplePARQLParser.ConstraintContext constraintContext =
                    new SimplePARQLParser.ConstraintContext(filterContext, 1);
            constraintContext.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return "(";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.T__9;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));
            SimplePARQLParser.ExpressionContext expressionContext =
                    new SimplePARQLParser.ExpressionContext(constraintContext, 1);
            constraintContext.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return ")";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.T__10;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));
            SimplePARQLParser.ConditionalOrExpressionContext conditionalOrExpressionContext =
                    new SimplePARQLParser.ConditionalOrExpressionContext(expressionContext, 1);
            SimplePARQLParser.ConditionalAndExpressionContext conditionalAndExpressionContext =
                    new SimplePARQLParser.ConditionalAndExpressionContext(conditionalOrExpressionContext, 1);
            SimplePARQLParser.ValueLogicalContext valueLogicalContext =
                    new SimplePARQLParser.ValueLogicalContext(conditionalAndExpressionContext, 1);
            SimplePARQLParser.NumericExpressionContext numericExpressionContext =
                    new SimplePARQLParser.NumericExpressionContext(valueLogicalContext, 1);
            SimplePARQLParser.AdditiveExpressionContext additiveExpressionContext =
                    new SimplePARQLParser.AdditiveExpressionContext(numericExpressionContext, 1);
            SimplePARQLParser.MultiplicativeExpressionContext multiplicativeExpressionContext =
                    new SimplePARQLParser.MultiplicativeExpressionContext(additiveExpressionContext, 1);
            SimplePARQLParser.UnaryExpressionContext unaryExpressionContext =
                    new SimplePARQLParser.UnaryExpressionContext(multiplicativeExpressionContext, 1);
            SimplePARQLParser.PrimaryExpressionContext primaryExpressionContext =
                    new SimplePARQLParser.PrimaryExpressionContext(unaryExpressionContext, 1);
            SimplePARQLParser.BuiltInCallContext builtInCallContext =
                    new SimplePARQLParser.BuiltInCallContext(primaryExpressionContext, 1);
            builtInCallContext.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return "CONTAINS";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.STRING_LITERAL2;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));
            builtInCallContext.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return "(";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.T__9;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));
            SimplePARQLParser.ExpressionContext expressionContext1 =
                    new SimplePARQLParser.ExpressionContext(builtInCallContext, 1);
            builtInCallContext.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return ".";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.T__6;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));
            SimplePARQLParser.ExpressionContext expressionContext2 =
                    new SimplePARQLParser.ExpressionContext(builtInCallContext, 1);
            builtInCallContext.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return ")";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.T__10;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));
            SimplePARQLParser.ConditionalOrExpressionContext conditionalOrExpressionContext1 =
                    new SimplePARQLParser.ConditionalOrExpressionContext(expressionContext1, 1);
            SimplePARQLParser.ConditionalAndExpressionContext conditionalandExpressionContext1 =
                    new SimplePARQLParser.ConditionalAndExpressionContext(conditionalOrExpressionContext1, 1);
            SimplePARQLParser.ValueLogicalContext valueLogicalContext1 =
                    new SimplePARQLParser.ValueLogicalContext(conditionalandExpressionContext1, 1);
            SimplePARQLParser.NumericExpressionContext numericExpressionContext1 =
                    new SimplePARQLParser.NumericExpressionContext(valueLogicalContext1, 1);
            SimplePARQLParser.AdditiveExpressionContext additiveExpressionContext1 =
                    new SimplePARQLParser.AdditiveExpressionContext(numericExpressionContext1, 1);
            SimplePARQLParser.MultiplicativeExpressionContext multiplicativeExpressionContext1 =
                    new SimplePARQLParser.MultiplicativeExpressionContext(additiveExpressionContext1, 1);
            SimplePARQLParser.UnaryExpressionContext unaryExpressionContext1 =
                    new SimplePARQLParser.UnaryExpressionContext(multiplicativeExpressionContext1, 1);
            SimplePARQLParser.PrimaryExpressionContext primaryExpressionContext1 =
                    new SimplePARQLParser.PrimaryExpressionContext(unaryExpressionContext1, 1);
            SimplePARQLParser.BuiltInCallContext builtInCallContext1 =
                    new SimplePARQLParser.BuiltInCallContext(primaryExpressionContext1, 1);
            builtInCallContext1.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return "STR";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.T__23;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));
            builtInCallContext1.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return "(";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.T__9;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));
            SimplePARQLParser.ExpressionContext expressionContext3 =
                    new SimplePARQLParser.ExpressionContext(builtInCallContext1, 1);
            builtInCallContext1.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return ")";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.T__10;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));
            SimplePARQLParser.ConditionalOrExpressionContext conditionalOrExpressionContext2 =
                    new SimplePARQLParser.ConditionalOrExpressionContext(expressionContext3, 1);
            SimplePARQLParser.ConditionalAndExpressionContext conditionalAndExpressionContext1 =
                    new SimplePARQLParser.ConditionalAndExpressionContext(conditionalOrExpressionContext2, 1);
            SimplePARQLParser.ValueLogicalContext valueLogicalContext2
                    = new SimplePARQLParser.ValueLogicalContext(conditionalAndExpressionContext1, 1);
            SimplePARQLParser.NumericExpressionContext numericExpressionContext2
                    = new SimplePARQLParser.NumericExpressionContext(valueLogicalContext2, 1);
            SimplePARQLParser.AdditiveExpressionContext additiveExpressionContext2
                    = new SimplePARQLParser.AdditiveExpressionContext(numericExpressionContext2, 1);
            SimplePARQLParser.MultiplicativeExpressionContext multiplicativeExpressionContext2
                    = new SimplePARQLParser.MultiplicativeExpressionContext(additiveExpressionContext2, 1);
            SimplePARQLParser.UnaryExpressionContext unaryExpressionContext2
                    = new SimplePARQLParser.UnaryExpressionContext(multiplicativeExpressionContext2, 1);
            SimplePARQLParser.PrimaryExpressionContext primaryExpressionContext2
                    = new SimplePARQLParser.PrimaryExpressionContext(unaryExpressionContext2, 1);
            primaryExpressionContext2.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return "?o";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.VAR;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));


            SimplePARQLParser.ConditionalOrExpressionContext conditionalOrExpressionContext3
                    = new SimplePARQLParser.ConditionalOrExpressionContext(expressionContext2, 1);
            SimplePARQLParser.ConditionalAndExpressionContext conditionalAndExpressionContext2
                    = new SimplePARQLParser.ConditionalAndExpressionContext(conditionalOrExpressionContext3, 1);
            SimplePARQLParser.ValueLogicalContext valueLogicalContext3
                    = new SimplePARQLParser.ValueLogicalContext(conditionalAndExpressionContext2, 1);
            SimplePARQLParser.NumericExpressionContext numericExpressionContext3
                    = new SimplePARQLParser.NumericExpressionContext(valueLogicalContext3, 1);
            SimplePARQLParser.AdditiveExpressionContext additiveExpressionContext3
                    = new SimplePARQLParser.AdditiveExpressionContext(numericExpressionContext3, 1);
            SimplePARQLParser.MultiplicativeExpressionContext multiplicativeExpressionContext3
                    = new SimplePARQLParser.MultiplicativeExpressionContext(additiveExpressionContext3, 1);
            SimplePARQLParser.UnaryExpressionContext unaryExpressionContext3
                    = new SimplePARQLParser.UnaryExpressionContext(multiplicativeExpressionContext3, 1);
            SimplePARQLParser.PrimaryExpressionContext primaryExpressionContext3
                    = new SimplePARQLParser.PrimaryExpressionContext(unaryExpressionContext3, 1);
            SimplePARQLParser.RdfLiteralContext rdfLiteralContext
                    = new SimplePARQLParser.RdfLiteralContext(primaryExpressionContext3, 1);
            SimplePARQLParser.StringContext stringContext
                    = new SimplePARQLParser.StringContext(rdfLiteralContext, 1);
            stringContext.addChild(new TerminalNodeImpl(new Token() {
                @Override
                public String getText() {
                    return "Germany";
                }

                @Override
                public int getType() {
                    return SimplePARQLLexer.STRING_LITERAL2;
                }

                @Override
                public int getLine() {
                    return 0;
                }

                @Override
                public int getCharPositionInLine() {
                    return 0;
                }

                @Override
                public int getChannel() {
                    return 0;
                }

                @Override
                public int getTokenIndex() {
                    return 0;
                }

                @Override
                public int getStartIndex() {
                    return 0;
                }

                @Override
                public int getStopIndex() {
                    return 0;
                }

                @Override
                public TokenSource getTokenSource() {
                    return null;
                }

                @Override
                public CharStream getInputStream() {
                    return null;
                }
            }));

        }

    }

    private static void addFilter(ParserRuleContext tree,String filterText) {
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
            ParseTree temp = whereclause.getChild(whereclause.getChildCount() - 1);
            whereclause.children.set(whereclause.getChildCount() - 1, graphPatternNotTriplesContext);
            whereclause.addChild((TerminalNodeImpl) temp);
            ParserRuleContext filterTree = getFilter(filterText).filter();
            graphPatternNotTriplesContext.addChild(filterTree);
        }
    }

    private static SimplePARQLParser getTree(String file) throws IOException {
        CharStream codeStream = CharStreams.fromFileName(file);
        SimplePARQLLexer lexer = new SimplePARQLLexer(codeStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SimplePARQLParser(tokens);
    }

    private static SimplePARQLParser getFilter(String text) {
        CharStream codeStream = CharStreams.fromString(text);
        SimplePARQLLexer lexer = new SimplePARQLLexer(codeStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new SimplePARQLParser(tokens);
    }

     /*
        Token LIMIT = ((TerminalNodeImpl) child.getChild(0).getChild(0).getChild(0)).getSymbol();
        Token number = ((TerminalNodeImpl) child.getChild(0).getChild(0).getChild(1)).getSymbol();
        logger.debug(LIMIT.getText());
        logger.debug(number.getText()); // YES
        logger.debug(LIMIT.getType());
        logger.debug(number.getType()); // SimplePARQL.tokens (les types d'entrées)
        logger.debug(LIMIT.getLine());
        logger.debug(number.getLine()); // trouver la ligne du child d'avant, et l'incrémenter ici
        logger.debug(LIMIT.getCharPositionInLine());
        logger.debug(number.getCharPositionInLine()); // commence a 0, puis on ajoute le text.length plus l'espace de ce qui est avant
        logger.debug(LIMIT.getChannel());
        logger.debug(number.getChannel());//???
        logger.debug(LIMIT.getTokenIndex());
        logger.debug(number.getTokenIndex()); // 11 et 12 ????
        logger.debug(LIMIT.getStartIndex());
        logger.debug(number.getStartIndex()); // ca à l'air d'avoir une liaison avec getLine(à et tout
        logger.debug(LIMIT.getStopIndex());
        logger.debug(number.getStopIndex()); // fin du text
        logger.debug(LIMIT.getTokenSource());
        logger.debug(number.getTokenSource().getSourceName());
    */

}
