// Generated from C:/Users/Abdel/Desktop/Stage_A4/Codes/Parser/SimplePARQL\SimplePARQL.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimplePARQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimplePARQLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(SimplePARQLParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#prologue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrologue(SimplePARQLParser.PrologueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#prefixDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixDecl(SimplePARQLParser.PrefixDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#selectQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectQuery(SimplePARQLParser.SelectQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(SimplePARQLParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#groupGraphPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupGraphPattern(SimplePARQLParser.GroupGraphPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(SimplePARQLParser.FilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraint(SimplePARQLParser.ConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(SimplePARQLParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#brackettedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrackettedExpression(SimplePARQLParser.BrackettedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SimplePARQLParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalOrExpression(SimplePARQLParser.ConditionalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalAndExpression(SimplePARQLParser.ConditionalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#valueLogical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueLogical(SimplePARQLParser.ValueLogicalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#numericExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericExpression(SimplePARQLParser.NumericExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(SimplePARQLParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(SimplePARQLParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(SimplePARQLParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(SimplePARQLParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#builtInCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuiltInCall(SimplePARQLParser.BuiltInCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#regexExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexExpression(SimplePARQLParser.RegexExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#iriRefOrFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIriRefOrFunction(SimplePARQLParser.IriRefOrFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(SimplePARQLParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#iriRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIriRef(SimplePARQLParser.IriRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#prefixedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixedName(SimplePARQLParser.PrefixedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#triplesBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTriplesBlock(SimplePARQLParser.TriplesBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#triplesSameSubject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTriplesSameSubject(SimplePARQLParser.TriplesSameSubjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#graphPatternNotTriples}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGraphPatternNotTriples(SimplePARQLParser.GraphPatternNotTriplesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#bind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBind(SimplePARQLParser.BindContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#optionalGraphPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionalGraphPattern(SimplePARQLParser.OptionalGraphPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#triplesNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTriplesNode(SimplePARQLParser.TriplesNodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#collection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollection(SimplePARQLParser.CollectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#blankNodePropertyList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlankNodePropertyList(SimplePARQLParser.BlankNodePropertyListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#propertyListNotEmpty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyListNotEmpty(SimplePARQLParser.PropertyListNotEmptyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#objectList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectList(SimplePARQLParser.ObjectListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(SimplePARQLParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#verb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVerb(SimplePARQLParser.VerbContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#varOrIRIreforTruc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarOrIRIreforTruc(SimplePARQLParser.VarOrIRIreforTrucContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#graphNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGraphNode(SimplePARQLParser.GraphNodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#varOrTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarOrTerm(SimplePARQLParser.VarOrTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#graphTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGraphTerm(SimplePARQLParser.GraphTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#rdfLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRdfLiteral(SimplePARQLParser.RdfLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(SimplePARQLParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#blankNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlankNode(SimplePARQLParser.BlankNodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(SimplePARQLParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#groupOrUnionGraphPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupOrUnionGraphPattern(SimplePARQLParser.GroupOrUnionGraphPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#truc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruc(SimplePARQLParser.TrucContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#solutionModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSolutionModifier(SimplePARQLParser.SolutionModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#limitOffsetClauses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitOffsetClauses(SimplePARQLParser.LimitOffsetClausesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#orderClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderClause(SimplePARQLParser.OrderClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#orderCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderCondition(SimplePARQLParser.OrderConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#limitClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitClause(SimplePARQLParser.LimitClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#offsetClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOffsetClause(SimplePARQLParser.OffsetClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#numericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericLiteral(SimplePARQLParser.NumericLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#numericLiteralUnsigned}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericLiteralUnsigned(SimplePARQLParser.NumericLiteralUnsignedContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#numericLiteralPositive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericLiteralPositive(SimplePARQLParser.NumericLiteralPositiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimplePARQLParser#numericLiteralNegative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericLiteralNegative(SimplePARQLParser.NumericLiteralNegativeContext ctx);
}