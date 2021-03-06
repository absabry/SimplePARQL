package fr.esilv.simpleparql.source.model;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Trees;

import java.util.Arrays;
import java.util.List;

/**
 * Override toString method for the semantic tree to avoid the EOF and the joined text when displaying the tree.
 *
 * Example of usage : <br>
 * TreePrinterListener listener = new TreePrinterListener(parser); <br>
 * ParseTreeWalker.DEFAULT.walk(listener, tree); <br>
 * logger.info(listener.toString()); <br>
 */

public class TreePrinterListener implements ParseTreeListener {
    private final List<String> ruleNames;
    private final StringBuilder builder = new StringBuilder();

    public TreePrinterListener(Parser parser) {
        this.ruleNames = Arrays.asList(parser.getRuleNames());
    }

    public TreePrinterListener(List<String> ruleNames) {
        this.ruleNames = ruleNames;
    }

    /**
     *  No manipulation needed, just delete the EOF tag, and add a space between each word.
     * @param node root of the SPARQL query.
     */
    @Override
    public void visitTerminal(TerminalNode node) {
        if (builder.length() > 0) {
            builder.append(' ');
        }
        String terminalText = Utils.escapeWhitespace(Trees.getNodeText(node, ruleNames), false);
        if (!terminalText.equals("<EOF>")) {
            builder.append(terminalText);
        }
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        if (builder.length() > 0) {
            builder.append(' ');
        }
        builder.append(Utils.escapeWhitespace(Trees.getNodeText(node, ruleNames), false));
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
