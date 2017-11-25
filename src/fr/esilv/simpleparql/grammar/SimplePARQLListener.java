// Generated from C:/Users/HP/IdeaProjects/SimplePARQL/src/fr/esilv/simpleparql/grammar\SimplePARQL.g4 by ANTLR 4.7
package fr.esilv.simpleparql.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimplePARQLParser}.
 */
public interface SimplePARQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(SimplePARQLParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(SimplePARQLParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#prologue}.
	 * @param ctx the parse tree
	 */
	void enterPrologue(SimplePARQLParser.PrologueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#prologue}.
	 * @param ctx the parse tree
	 */
	void exitPrologue(SimplePARQLParser.PrologueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#prefixDecl}.
	 * @param ctx the parse tree
	 */
	void enterPrefixDecl(SimplePARQLParser.PrefixDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#prefixDecl}.
	 * @param ctx the parse tree
	 */
	void exitPrefixDecl(SimplePARQLParser.PrefixDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#selectQuery}.
	 * @param ctx the parse tree
	 */
	void enterSelectQuery(SimplePARQLParser.SelectQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#selectQuery}.
	 * @param ctx the parse tree
	 */
	void exitSelectQuery(SimplePARQLParser.SelectQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(SimplePARQLParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(SimplePARQLParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#groupGraphPattern}.
	 * @param ctx the parse tree
	 */
	void enterGroupGraphPattern(SimplePARQLParser.GroupGraphPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#groupGraphPattern}.
	 * @param ctx the parse tree
	 */
	void exitGroupGraphPattern(SimplePARQLParser.GroupGraphPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(SimplePARQLParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(SimplePARQLParser.FilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(SimplePARQLParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(SimplePARQLParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(SimplePARQLParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(SimplePARQLParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#brackettedExpression}.
	 * @param ctx the parse tree
	 */
	void enterBrackettedExpression(SimplePARQLParser.BrackettedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#brackettedExpression}.
	 * @param ctx the parse tree
	 */
	void exitBrackettedExpression(SimplePARQLParser.BrackettedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SimplePARQLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SimplePARQLParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalOrExpression(SimplePARQLParser.ConditionalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalOrExpression(SimplePARQLParser.ConditionalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalAndExpression(SimplePARQLParser.ConditionalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalAndExpression(SimplePARQLParser.ConditionalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#valueLogical}.
	 * @param ctx the parse tree
	 */
	void enterValueLogical(SimplePARQLParser.ValueLogicalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#valueLogical}.
	 * @param ctx the parse tree
	 */
	void exitValueLogical(SimplePARQLParser.ValueLogicalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#numericExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericExpression(SimplePARQLParser.NumericExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#numericExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericExpression(SimplePARQLParser.NumericExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(SimplePARQLParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(SimplePARQLParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(SimplePARQLParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(SimplePARQLParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(SimplePARQLParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(SimplePARQLParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(SimplePARQLParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(SimplePARQLParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#builtInCall}.
	 * @param ctx the parse tree
	 */
	void enterBuiltInCall(SimplePARQLParser.BuiltInCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#builtInCall}.
	 * @param ctx the parse tree
	 */
	void exitBuiltInCall(SimplePARQLParser.BuiltInCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#regexExpression}.
	 * @param ctx the parse tree
	 */
	void enterRegexExpression(SimplePARQLParser.RegexExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#regexExpression}.
	 * @param ctx the parse tree
	 */
	void exitRegexExpression(SimplePARQLParser.RegexExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#iriRefOrFunction}.
	 * @param ctx the parse tree
	 */
	void enterIriRefOrFunction(SimplePARQLParser.IriRefOrFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#iriRefOrFunction}.
	 * @param ctx the parse tree
	 */
	void exitIriRefOrFunction(SimplePARQLParser.IriRefOrFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(SimplePARQLParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(SimplePARQLParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#iriRef}.
	 * @param ctx the parse tree
	 */
	void enterIriRef(SimplePARQLParser.IriRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#iriRef}.
	 * @param ctx the parse tree
	 */
	void exitIriRef(SimplePARQLParser.IriRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#prefixedName}.
	 * @param ctx the parse tree
	 */
	void enterPrefixedName(SimplePARQLParser.PrefixedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#prefixedName}.
	 * @param ctx the parse tree
	 */
	void exitPrefixedName(SimplePARQLParser.PrefixedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#triplesBlock}.
	 * @param ctx the parse tree
	 */
	void enterTriplesBlock(SimplePARQLParser.TriplesBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#triplesBlock}.
	 * @param ctx the parse tree
	 */
	void exitTriplesBlock(SimplePARQLParser.TriplesBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#triplesSameSubject}.
	 * @param ctx the parse tree
	 */
	void enterTriplesSameSubject(SimplePARQLParser.TriplesSameSubjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#triplesSameSubject}.
	 * @param ctx the parse tree
	 */
	void exitTriplesSameSubject(SimplePARQLParser.TriplesSameSubjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#graphPatternNotTriples}.
	 * @param ctx the parse tree
	 */
	void enterGraphPatternNotTriples(SimplePARQLParser.GraphPatternNotTriplesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#graphPatternNotTriples}.
	 * @param ctx the parse tree
	 */
	void exitGraphPatternNotTriples(SimplePARQLParser.GraphPatternNotTriplesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#bind}.
	 * @param ctx the parse tree
	 */
	void enterBind(SimplePARQLParser.BindContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#bind}.
	 * @param ctx the parse tree
	 */
	void exitBind(SimplePARQLParser.BindContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#optionalGraphPattern}.
	 * @param ctx the parse tree
	 */
	void enterOptionalGraphPattern(SimplePARQLParser.OptionalGraphPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#optionalGraphPattern}.
	 * @param ctx the parse tree
	 */
	void exitOptionalGraphPattern(SimplePARQLParser.OptionalGraphPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#triplesNode}.
	 * @param ctx the parse tree
	 */
	void enterTriplesNode(SimplePARQLParser.TriplesNodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#triplesNode}.
	 * @param ctx the parse tree
	 */
	void exitTriplesNode(SimplePARQLParser.TriplesNodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#collection}.
	 * @param ctx the parse tree
	 */
	void enterCollection(SimplePARQLParser.CollectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#collection}.
	 * @param ctx the parse tree
	 */
	void exitCollection(SimplePARQLParser.CollectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#blankNodePropertyList}.
	 * @param ctx the parse tree
	 */
	void enterBlankNodePropertyList(SimplePARQLParser.BlankNodePropertyListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#blankNodePropertyList}.
	 * @param ctx the parse tree
	 */
	void exitBlankNodePropertyList(SimplePARQLParser.BlankNodePropertyListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#propertyListNotEmpty}.
	 * @param ctx the parse tree
	 */
	void enterPropertyListNotEmpty(SimplePARQLParser.PropertyListNotEmptyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#propertyListNotEmpty}.
	 * @param ctx the parse tree
	 */
	void exitPropertyListNotEmpty(SimplePARQLParser.PropertyListNotEmptyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#objectList}.
	 * @param ctx the parse tree
	 */
	void enterObjectList(SimplePARQLParser.ObjectListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#objectList}.
	 * @param ctx the parse tree
	 */
	void exitObjectList(SimplePARQLParser.ObjectListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(SimplePARQLParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(SimplePARQLParser.ObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#verb}.
	 * @param ctx the parse tree
	 */
	void enterVerb(SimplePARQLParser.VerbContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#verb}.
	 * @param ctx the parse tree
	 */
	void exitVerb(SimplePARQLParser.VerbContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#varOrIRIref}.
	 * @param ctx the parse tree
	 */
	void enterVarOrIRIref(SimplePARQLParser.VarOrIRIrefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#varOrIRIref}.
	 * @param ctx the parse tree
	 */
	void exitVarOrIRIref(SimplePARQLParser.VarOrIRIrefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#graphNode}.
	 * @param ctx the parse tree
	 */
	void enterGraphNode(SimplePARQLParser.GraphNodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#graphNode}.
	 * @param ctx the parse tree
	 */
	void exitGraphNode(SimplePARQLParser.GraphNodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#varOrTermObject}.
	 * @param ctx the parse tree
	 */
	void enterVarOrTermObject(SimplePARQLParser.VarOrTermObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#varOrTermObject}.
	 * @param ctx the parse tree
	 */
	void exitVarOrTermObject(SimplePARQLParser.VarOrTermObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#varOrTermSujet}.
	 * @param ctx the parse tree
	 */
	void enterVarOrTermSujet(SimplePARQLParser.VarOrTermSujetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#varOrTermSujet}.
	 * @param ctx the parse tree
	 */
	void exitVarOrTermSujet(SimplePARQLParser.VarOrTermSujetContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#graphTerm}.
	 * @param ctx the parse tree
	 */
	void enterGraphTerm(SimplePARQLParser.GraphTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#graphTerm}.
	 * @param ctx the parse tree
	 */
	void exitGraphTerm(SimplePARQLParser.GraphTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#rdfLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRdfLiteral(SimplePARQLParser.RdfLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#rdfLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRdfLiteral(SimplePARQLParser.RdfLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(SimplePARQLParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(SimplePARQLParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#blankNode}.
	 * @param ctx the parse tree
	 */
	void enterBlankNode(SimplePARQLParser.BlankNodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#blankNode}.
	 * @param ctx the parse tree
	 */
	void exitBlankNode(SimplePARQLParser.BlankNodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(SimplePARQLParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(SimplePARQLParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#groupOrUnionGraphPattern}.
	 * @param ctx the parse tree
	 */
	void enterGroupOrUnionGraphPattern(SimplePARQLParser.GroupOrUnionGraphPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#groupOrUnionGraphPattern}.
	 * @param ctx the parse tree
	 */
	void exitGroupOrUnionGraphPattern(SimplePARQLParser.GroupOrUnionGraphPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#trucSujet}.
	 * @param ctx the parse tree
	 */
	void enterTrucSujet(SimplePARQLParser.TrucSujetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#trucSujet}.
	 * @param ctx the parse tree
	 */
	void exitTrucSujet(SimplePARQLParser.TrucSujetContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#trucPredicat}.
	 * @param ctx the parse tree
	 */
	void enterTrucPredicat(SimplePARQLParser.TrucPredicatContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#trucPredicat}.
	 * @param ctx the parse tree
	 */
	void exitTrucPredicat(SimplePARQLParser.TrucPredicatContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#trucObject}.
	 * @param ctx the parse tree
	 */
	void enterTrucObject(SimplePARQLParser.TrucObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#trucObject}.
	 * @param ctx the parse tree
	 */
	void exitTrucObject(SimplePARQLParser.TrucObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#solutionModifier}.
	 * @param ctx the parse tree
	 */
	void enterSolutionModifier(SimplePARQLParser.SolutionModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#solutionModifier}.
	 * @param ctx the parse tree
	 */
	void exitSolutionModifier(SimplePARQLParser.SolutionModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#limitOffsetClauses}.
	 * @param ctx the parse tree
	 */
	void enterLimitOffsetClauses(SimplePARQLParser.LimitOffsetClausesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#limitOffsetClauses}.
	 * @param ctx the parse tree
	 */
	void exitLimitOffsetClauses(SimplePARQLParser.LimitOffsetClausesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#orderClause}.
	 * @param ctx the parse tree
	 */
	void enterOrderClause(SimplePARQLParser.OrderClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#orderClause}.
	 * @param ctx the parse tree
	 */
	void exitOrderClause(SimplePARQLParser.OrderClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#orderCondition}.
	 * @param ctx the parse tree
	 */
	void enterOrderCondition(SimplePARQLParser.OrderConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#orderCondition}.
	 * @param ctx the parse tree
	 */
	void exitOrderCondition(SimplePARQLParser.OrderConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void enterLimitClause(SimplePARQLParser.LimitClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void exitLimitClause(SimplePARQLParser.LimitClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#offsetClause}.
	 * @param ctx the parse tree
	 */
	void enterOffsetClause(SimplePARQLParser.OffsetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#offsetClause}.
	 * @param ctx the parse tree
	 */
	void exitOffsetClause(SimplePARQLParser.OffsetClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(SimplePARQLParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(SimplePARQLParser.NumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#numericLiteralUnsigned}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteralUnsigned(SimplePARQLParser.NumericLiteralUnsignedContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#numericLiteralUnsigned}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteralUnsigned(SimplePARQLParser.NumericLiteralUnsignedContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#numericLiteralPositive}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteralPositive(SimplePARQLParser.NumericLiteralPositiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#numericLiteralPositive}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteralPositive(SimplePARQLParser.NumericLiteralPositiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimplePARQLParser#numericLiteralNegative}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteralNegative(SimplePARQLParser.NumericLiteralNegativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimplePARQLParser#numericLiteralNegative}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteralNegative(SimplePARQLParser.NumericLiteralNegativeContext ctx);
}