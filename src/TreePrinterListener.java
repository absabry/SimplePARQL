import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Trees;

import java.util.Arrays;
import java.util.List;


/*
        TreePrinterListener listener = new TreePrinterListener(parser);
        ParseTreeWalker.DEFAULT.walk(listener, tree);
        logger.info(listener.toString());
*/

public class TreePrinterListener implements ParseTreeListener {
    private final List<String> ruleNames;
    private final StringBuilder builder = new StringBuilder();
    private static boolean breakLine=false;

    public TreePrinterListener(Parser parser) {
        this.ruleNames = Arrays.asList(parser.getRuleNames());
    }

    public TreePrinterListener(List<String> ruleNames) {
        this.ruleNames = ruleNames;
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        if (builder.length() > 0 && !breakLine) {
            builder.append(' ');
        }
        String terminalText = Utils.escapeWhitespace(Trees.getNodeText(node, ruleNames), false);
        if(terminalText.equals("{") || terminalText.equals("}")){
            builder.append("\n");
            builder.append(terminalText);
            builder.append("\n");
            breakLine=true;
        }
        else if (terminalText.equals("PREFIX") || terminalText.equals("SELECT") || terminalText.equals("WHERE") || terminalText.equals("FILTER")){
            builder.append("\n");
            builder.append(terminalText);
            breakLine=false;
        }
        else if(terminalText.equals(".")){
            builder.append(terminalText);
            builder.append("\n");
            breakLine=true;
        }
        else if(!terminalText.equals("<EOF>")){
            builder.append(terminalText);
            breakLine=false;
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
