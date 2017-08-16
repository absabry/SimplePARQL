// Generated from C:/Users/Abdel/Desktop/Stage_A4/SimplePARQL/SimplePARQL/src/fr/esilv/simpleparql/grammar\SimplePARQL.g4 by ANTLR 4.7
package fr.esilv.simpleparql.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimplePARQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, Select=28, Prefix=29, Where=30, Distinct=31, 
		Filter=32, Str=33, Lang=34, Langmatches=35, Datatype=36, Contains=37, 
		Ucase=38, Lcase=39, Bound=40, Sameterm=41, Isiri=42, Isuri=43, Isblank=44, 
		Isliteral=45, Year=46, Month=47, Day=48, Hours=49, Minutes=50, Seconds=51, 
		Timezone=52, Tz=53, Regex=54, Bind=55, Optional=56, Union=57, Asc=58, 
		Desc=59, Limit=60, Offset=61, Order=62, By=63, INTEGER=64, DECIMAL=65, 
		DOUBLE=66, INTEGER_POSITIVE=67, DECIMAL_POSITIVE=68, DOUBLE_POSITIVE=69, 
		INTEGER_NEGATIVE=70, DECIMAL_NEGATIVE=71, DOUBLE_NEGATIVE=72, EXPONENT=73, 
		DIGIT=74, URI=75, VAR=76, NIL=77, TRUC_WORD=78, VARNAME=79, PNAME_LN=80, 
		PN_LOCAL=81, STRING_LITERAL1=82, STRING_LITERAL2=83, TRUC_DIESE=84, ECHAR=85, 
		ANON=86, LANGTAG=87, BLANK_NODE_LABEL=88, PNAME_NS=89, PN_PREFIX=90, PN_CHARS_UNDERSCORE=91, 
		HTTP=92, WS=93;
	public static final int
		RULE_query = 0, RULE_prologue = 1, RULE_prefixDecl = 2, RULE_selectQuery = 3, 
		RULE_whereClause = 4, RULE_groupGraphPattern = 5, RULE_filter = 6, RULE_constraint = 7, 
		RULE_functionCall = 8, RULE_brackettedExpression = 9, RULE_expression = 10, 
		RULE_conditionalOrExpression = 11, RULE_conditionalAndExpression = 12, 
		RULE_valueLogical = 13, RULE_numericExpression = 14, RULE_additiveExpression = 15, 
		RULE_multiplicativeExpression = 16, RULE_unaryExpression = 17, RULE_primaryExpression = 18, 
		RULE_builtInCall = 19, RULE_regexExpression = 20, RULE_iriRefOrFunction = 21, 
		RULE_argList = 22, RULE_iriRef = 23, RULE_prefixedName = 24, RULE_triplesBlock = 25, 
		RULE_triplesSameSubject = 26, RULE_graphPatternNotTriples = 27, RULE_bind = 28, 
		RULE_optionalGraphPattern = 29, RULE_triplesNode = 30, RULE_collection = 31, 
		RULE_blankNodePropertyList = 32, RULE_propertyListNotEmpty = 33, RULE_objectList = 34, 
		RULE_object = 35, RULE_verb = 36, RULE_varOrIRIreforTruc = 37, RULE_graphNode = 38, 
		RULE_varOrTerm = 39, RULE_graphTerm = 40, RULE_rdfLiteral = 41, RULE_booleanLiteral = 42, 
		RULE_blankNode = 43, RULE_string = 44, RULE_groupOrUnionGraphPattern = 45, 
		RULE_truc = 46, RULE_solutionModifier = 47, RULE_limitOffsetClauses = 48, 
		RULE_orderClause = 49, RULE_orderCondition = 50, RULE_limitClause = 51, 
		RULE_offsetClause = 52, RULE_numericLiteral = 53, RULE_numericLiteralUnsigned = 54, 
		RULE_numericLiteralPositive = 55, RULE_numericLiteralNegative = 56;
	public static final String[] ruleNames = {
		"query", "prologue", "prefixDecl", "selectQuery", "whereClause", "groupGraphPattern", 
		"filter", "constraint", "functionCall", "brackettedExpression", "expression", 
		"conditionalOrExpression", "conditionalAndExpression", "valueLogical", 
		"numericExpression", "additiveExpression", "multiplicativeExpression", 
		"unaryExpression", "primaryExpression", "builtInCall", "regexExpression", 
		"iriRefOrFunction", "argList", "iriRef", "prefixedName", "triplesBlock", 
		"triplesSameSubject", "graphPatternNotTriples", "bind", "optionalGraphPattern", 
		"triplesNode", "collection", "blankNodePropertyList", "propertyListNotEmpty", 
		"objectList", "object", "verb", "varOrIRIreforTruc", "graphNode", "varOrTerm", 
		"graphTerm", "rdfLiteral", "booleanLiteral", "blankNode", "string", "groupOrUnionGraphPattern", 
		"truc", "solutionModifier", "limitOffsetClauses", "orderClause", "orderCondition", 
		"limitClause", "offsetClause", "numericLiteral", "numericLiteralUnsigned", 
		"numericLiteralPositive", "numericLiteralNegative"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'*'", "'{'", "'.'", "'}'", "'('", "')'", "'||'", "'&&'", "'='", 
		"'!='", "'<'", "'>'", "'<='", "'>='", "'+'", "'-'", "'/'", "'!'", "','", 
		"'AS'", "'['", "']'", "';'", "'a'", "'^^'", "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "Select", "Prefix", "Where", "Distinct", "Filter", 
		"Str", "Lang", "Langmatches", "Datatype", "Contains", "Ucase", "Lcase", 
		"Bound", "Sameterm", "Isiri", "Isuri", "Isblank", "Isliteral", "Year", 
		"Month", "Day", "Hours", "Minutes", "Seconds", "Timezone", "Tz", "Regex", 
		"Bind", "Optional", "Union", "Asc", "Desc", "Limit", "Offset", "Order", 
		"By", "INTEGER", "DECIMAL", "DOUBLE", "INTEGER_POSITIVE", "DECIMAL_POSITIVE", 
		"DOUBLE_POSITIVE", "INTEGER_NEGATIVE", "DECIMAL_NEGATIVE", "DOUBLE_NEGATIVE", 
		"EXPONENT", "DIGIT", "URI", "VAR", "NIL", "TRUC_WORD", "VARNAME", "PNAME_LN", 
		"PN_LOCAL", "STRING_LITERAL1", "STRING_LITERAL2", "TRUC_DIESE", "ECHAR", 
		"ANON", "LANGTAG", "BLANK_NODE_LABEL", "PNAME_NS", "PN_PREFIX", "PN_CHARS_UNDERSCORE", 
		"HTTP", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SimplePARQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimplePARQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public PrologueContext prologue() {
			return getRuleContext(PrologueContext.class,0);
		}
		public SelectQueryContext selectQuery() {
			return getRuleContext(SelectQueryContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public SolutionModifierContext solutionModifier() {
			return getRuleContext(SolutionModifierContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SimplePARQLParser.EOF, 0); }
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			prologue();
			setState(115);
			selectQuery();
			setState(116);
			whereClause();
			setState(117);
			solutionModifier();
			setState(118);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrologueContext extends ParserRuleContext {
		public List<PrefixDeclContext> prefixDecl() {
			return getRuleContexts(PrefixDeclContext.class);
		}
		public PrefixDeclContext prefixDecl(int i) {
			return getRuleContext(PrefixDeclContext.class,i);
		}
		public PrologueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prologue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterPrologue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitPrologue(this);
		}
	}

	public final PrologueContext prologue() throws RecognitionException {
		PrologueContext _localctx = new PrologueContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_prologue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Prefix) {
				{
				{
				setState(120);
				prefixDecl();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrefixDeclContext extends ParserRuleContext {
		public TerminalNode Prefix() { return getToken(SimplePARQLParser.Prefix, 0); }
		public TerminalNode PNAME_NS() { return getToken(SimplePARQLParser.PNAME_NS, 0); }
		public TerminalNode URI() { return getToken(SimplePARQLParser.URI, 0); }
		public PrefixDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterPrefixDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitPrefixDecl(this);
		}
	}

	public final PrefixDeclContext prefixDecl() throws RecognitionException {
		PrefixDeclContext _localctx = new PrefixDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_prefixDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(Prefix);
			setState(127);
			match(PNAME_NS);
			setState(128);
			match(URI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectQueryContext extends ParserRuleContext {
		public TerminalNode Select() { return getToken(SimplePARQLParser.Select, 0); }
		public TrucContext truc() {
			return getRuleContext(TrucContext.class,0);
		}
		public TerminalNode Distinct() { return getToken(SimplePARQLParser.Distinct, 0); }
		public List<TerminalNode> VAR() { return getTokens(SimplePARQLParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(SimplePARQLParser.VAR, i);
		}
		public SelectQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterSelectQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitSelectQuery(this);
		}
	}

	public final SelectQueryContext selectQuery() throws RecognitionException {
		SelectQueryContext _localctx = new SelectQueryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_selectQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(Select);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Distinct) {
				{
				setState(131);
				match(Distinct);
				}
			}

			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				{
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(134);
					match(VAR);
					}
					}
					setState(137); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case T__0:
				{
				setState(139);
				match(T__0);
				}
				break;
			case TRUC_WORD:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case TRUC_DIESE:
				{
				setState(140);
				truc();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereClauseContext extends ParserRuleContext {
		public TerminalNode Where() { return getToken(SimplePARQLParser.Where, 0); }
		public GroupGraphPatternContext groupGraphPattern() {
			return getRuleContext(GroupGraphPatternContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitWhereClause(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(Where);
			setState(144);
			groupGraphPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupGraphPatternContext extends ParserRuleContext {
		public List<TriplesBlockContext> triplesBlock() {
			return getRuleContexts(TriplesBlockContext.class);
		}
		public TriplesBlockContext triplesBlock(int i) {
			return getRuleContext(TriplesBlockContext.class,i);
		}
		public List<GraphPatternNotTriplesContext> graphPatternNotTriples() {
			return getRuleContexts(GraphPatternNotTriplesContext.class);
		}
		public GraphPatternNotTriplesContext graphPatternNotTriples(int i) {
			return getRuleContext(GraphPatternNotTriplesContext.class,i);
		}
		public GroupGraphPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupGraphPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterGroupGraphPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitGroupGraphPattern(this);
		}
	}

	public final GroupGraphPatternContext groupGraphPattern() throws RecognitionException {
		GroupGraphPatternContext _localctx = new GroupGraphPatternContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_groupGraphPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__1);
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__20) | (1L << T__25) | (1L << T__26))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INTEGER - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (URI - 64)) | (1L << (VAR - 64)) | (1L << (NIL - 64)) | (1L << (TRUC_WORD - 64)) | (1L << (PNAME_LN - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (TRUC_DIESE - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (PNAME_NS - 64)))) != 0)) {
				{
				setState(147);
				triplesBlock();
				}
			}

			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << Filter) | (1L << Bind) | (1L << Optional))) != 0)) {
				{
				{
				{
				setState(150);
				graphPatternNotTriples();
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(151);
					match(T__2);
					}
				}

				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__20) | (1L << T__25) | (1L << T__26))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INTEGER - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (URI - 64)) | (1L << (VAR - 64)) | (1L << (NIL - 64)) | (1L << (TRUC_WORD - 64)) | (1L << (PNAME_LN - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (TRUC_DIESE - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (PNAME_NS - 64)))) != 0)) {
					{
					setState(154);
					triplesBlock();
					}
				}

				}
				}
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(162);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterContext extends ParserRuleContext {
		public TerminalNode Filter() { return getToken(SimplePARQLParser.Filter, 0); }
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitFilter(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(Filter);
			setState(165);
			constraint();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BuiltInCallContext builtInCall() {
			return getRuleContext(BuiltInCallContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitConstraint(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constraint);
		try {
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				match(T__4);
				setState(168);
				expression();
				setState(169);
				match(T__5);
				}
				break;
			case Str:
			case Lang:
			case Langmatches:
			case Datatype:
			case Contains:
			case Ucase:
			case Lcase:
			case Bound:
			case Sameterm:
			case Isiri:
			case Isuri:
			case Isblank:
			case Isliteral:
			case Year:
			case Month:
			case Day:
			case Hours:
			case Minutes:
			case Seconds:
			case Timezone:
			case Tz:
			case Regex:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				builtInCall();
				}
				break;
			case URI:
			case PNAME_LN:
			case PNAME_NS:
				enterOuterAlt(_localctx, 3);
				{
				setState(172);
				functionCall();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			iriRef();
			setState(176);
			argList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BrackettedExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BrackettedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_brackettedExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterBrackettedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitBrackettedExpression(this);
		}
	}

	public final BrackettedExpressionContext brackettedExpression() throws RecognitionException {
		BrackettedExpressionContext _localctx = new BrackettedExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_brackettedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(T__4);
			setState(179);
			expression();
			setState(180);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ConditionalOrExpressionContext conditionalOrExpression() {
			return getRuleContext(ConditionalOrExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			conditionalOrExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalOrExpressionContext extends ParserRuleContext {
		public List<ConditionalAndExpressionContext> conditionalAndExpression() {
			return getRuleContexts(ConditionalAndExpressionContext.class);
		}
		public ConditionalAndExpressionContext conditionalAndExpression(int i) {
			return getRuleContext(ConditionalAndExpressionContext.class,i);
		}
		public ConditionalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterConditionalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitConditionalOrExpression(this);
		}
	}

	public final ConditionalOrExpressionContext conditionalOrExpression() throws RecognitionException {
		ConditionalOrExpressionContext _localctx = new ConditionalOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_conditionalOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			conditionalAndExpression();
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(185);
				match(T__6);
				setState(186);
				conditionalAndExpression();
				}
				}
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalAndExpressionContext extends ParserRuleContext {
		public List<ValueLogicalContext> valueLogical() {
			return getRuleContexts(ValueLogicalContext.class);
		}
		public ValueLogicalContext valueLogical(int i) {
			return getRuleContext(ValueLogicalContext.class,i);
		}
		public ConditionalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterConditionalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitConditionalAndExpression(this);
		}
	}

	public final ConditionalAndExpressionContext conditionalAndExpression() throws RecognitionException {
		ConditionalAndExpressionContext _localctx = new ConditionalAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_conditionalAndExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			valueLogical();
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(193);
				match(T__7);
				setState(194);
				valueLogical();
				}
				}
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueLogicalContext extends ParserRuleContext {
		public List<NumericExpressionContext> numericExpression() {
			return getRuleContexts(NumericExpressionContext.class);
		}
		public NumericExpressionContext numericExpression(int i) {
			return getRuleContext(NumericExpressionContext.class,i);
		}
		public ValueLogicalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueLogical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterValueLogical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitValueLogical(this);
		}
	}

	public final ValueLogicalContext valueLogical() throws RecognitionException {
		ValueLogicalContext _localctx = new ValueLogicalContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_valueLogical);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			numericExpression();
			setState(213);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				{
				setState(201);
				match(T__8);
				setState(202);
				numericExpression();
				}
				break;
			case T__9:
				{
				setState(203);
				match(T__9);
				setState(204);
				numericExpression();
				}
				break;
			case T__10:
				{
				setState(205);
				match(T__10);
				setState(206);
				numericExpression();
				}
				break;
			case T__11:
				{
				setState(207);
				match(T__11);
				setState(208);
				numericExpression();
				}
				break;
			case T__12:
				{
				setState(209);
				match(T__12);
				setState(210);
				numericExpression();
				}
				break;
			case T__13:
				{
				setState(211);
				match(T__13);
				setState(212);
				numericExpression();
				}
				break;
			case T__5:
			case T__6:
			case T__7:
			case T__18:
			case T__19:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericExpressionContext extends ParserRuleContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public NumericExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterNumericExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitNumericExpression(this);
		}
	}

	public final NumericExpressionContext numericExpression() throws RecognitionException {
		NumericExpressionContext _localctx = new NumericExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_numericExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			additiveExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<NumericLiteralPositiveContext> numericLiteralPositive() {
			return getRuleContexts(NumericLiteralPositiveContext.class);
		}
		public NumericLiteralPositiveContext numericLiteralPositive(int i) {
			return getRuleContext(NumericLiteralPositiveContext.class,i);
		}
		public List<NumericLiteralNegativeContext> numericLiteralNegative() {
			return getRuleContexts(NumericLiteralNegativeContext.class);
		}
		public NumericLiteralNegativeContext numericLiteralNegative(int i) {
			return getRuleContext(NumericLiteralNegativeContext.class,i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitAdditiveExpression(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			multiplicativeExpression();
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 15)) & ~0x3f) == 0 && ((1L << (_la - 15)) & ((1L << (T__14 - 15)) | (1L << (T__15 - 15)) | (1L << (INTEGER_POSITIVE - 15)) | (1L << (DECIMAL_POSITIVE - 15)) | (1L << (DOUBLE_POSITIVE - 15)) | (1L << (INTEGER_NEGATIVE - 15)) | (1L << (DECIMAL_NEGATIVE - 15)) | (1L << (DOUBLE_NEGATIVE - 15)))) != 0)) {
				{
				setState(224);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__14:
					{
					setState(218);
					match(T__14);
					setState(219);
					multiplicativeExpression();
					}
					break;
				case T__15:
					{
					setState(220);
					match(T__15);
					setState(221);
					multiplicativeExpression();
					}
					break;
				case INTEGER_POSITIVE:
				case DECIMAL_POSITIVE:
				case DOUBLE_POSITIVE:
					{
					setState(222);
					numericLiteralPositive();
					}
					break;
				case INTEGER_NEGATIVE:
				case DECIMAL_NEGATIVE:
				case DOUBLE_NEGATIVE:
					{
					setState(223);
					numericLiteralNegative();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public List<UnaryExpressionContext> unaryExpression() {
			return getRuleContexts(UnaryExpressionContext.class);
		}
		public UnaryExpressionContext unaryExpression(int i) {
			return getRuleContext(UnaryExpressionContext.class,i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitMultiplicativeExpression(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			unaryExpression();
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__16) {
				{
				setState(234);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(230);
					match(T__0);
					setState(231);
					unaryExpression();
					}
					break;
				case T__16:
					{
					setState(232);
					match(T__16);
					setState(233);
					unaryExpression();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitUnaryExpression(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_unaryExpression);
		try {
			setState(246);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__25:
			case T__26:
			case Str:
			case Lang:
			case Langmatches:
			case Datatype:
			case Contains:
			case Ucase:
			case Lcase:
			case Bound:
			case Sameterm:
			case Isiri:
			case Isuri:
			case Isblank:
			case Isliteral:
			case Year:
			case Month:
			case Day:
			case Hours:
			case Minutes:
			case Seconds:
			case Timezone:
			case Tz:
			case Regex:
			case INTEGER:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case URI:
			case VAR:
			case PNAME_LN:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case PNAME_NS:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				primaryExpression();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(240);
				match(T__17);
				setState(241);
				primaryExpression();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(242);
				match(T__14);
				setState(243);
				primaryExpression();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 4);
				{
				setState(244);
				match(T__15);
				setState(245);
				primaryExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public BrackettedExpressionContext brackettedExpression() {
			return getRuleContext(BrackettedExpressionContext.class,0);
		}
		public BuiltInCallContext builtInCall() {
			return getRuleContext(BuiltInCallContext.class,0);
		}
		public IriRefOrFunctionContext iriRefOrFunction() {
			return getRuleContext(IriRefOrFunctionContext.class,0);
		}
		public RdfLiteralContext rdfLiteral() {
			return getRuleContext(RdfLiteralContext.class,0);
		}
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public TerminalNode VAR() { return getToken(SimplePARQLParser.VAR, 0); }
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitPrimaryExpression(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_primaryExpression);
		try {
			setState(255);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				brackettedExpression();
				}
				break;
			case Str:
			case Lang:
			case Langmatches:
			case Datatype:
			case Contains:
			case Ucase:
			case Lcase:
			case Bound:
			case Sameterm:
			case Isiri:
			case Isuri:
			case Isblank:
			case Isliteral:
			case Year:
			case Month:
			case Day:
			case Hours:
			case Minutes:
			case Seconds:
			case Timezone:
			case Tz:
			case Regex:
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				builtInCall();
				}
				break;
			case URI:
			case PNAME_LN:
			case PNAME_NS:
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				iriRefOrFunction();
				}
				break;
			case STRING_LITERAL1:
			case STRING_LITERAL2:
				enterOuterAlt(_localctx, 4);
				{
				setState(251);
				rdfLiteral();
				}
				break;
			case INTEGER:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
				enterOuterAlt(_localctx, 5);
				{
				setState(252);
				numericLiteral();
				}
				break;
			case T__25:
			case T__26:
				enterOuterAlt(_localctx, 6);
				{
				setState(253);
				booleanLiteral();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 7);
				{
				setState(254);
				match(VAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BuiltInCallContext extends ParserRuleContext {
		public TerminalNode Str() { return getToken(SimplePARQLParser.Str, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Lang() { return getToken(SimplePARQLParser.Lang, 0); }
		public TerminalNode Langmatches() { return getToken(SimplePARQLParser.Langmatches, 0); }
		public TerminalNode Datatype() { return getToken(SimplePARQLParser.Datatype, 0); }
		public TerminalNode Contains() { return getToken(SimplePARQLParser.Contains, 0); }
		public TerminalNode Ucase() { return getToken(SimplePARQLParser.Ucase, 0); }
		public TerminalNode Lcase() { return getToken(SimplePARQLParser.Lcase, 0); }
		public TerminalNode Bound() { return getToken(SimplePARQLParser.Bound, 0); }
		public TerminalNode VAR() { return getToken(SimplePARQLParser.VAR, 0); }
		public TerminalNode Sameterm() { return getToken(SimplePARQLParser.Sameterm, 0); }
		public TerminalNode Isiri() { return getToken(SimplePARQLParser.Isiri, 0); }
		public TerminalNode Isuri() { return getToken(SimplePARQLParser.Isuri, 0); }
		public TerminalNode Isblank() { return getToken(SimplePARQLParser.Isblank, 0); }
		public TerminalNode Isliteral() { return getToken(SimplePARQLParser.Isliteral, 0); }
		public TerminalNode Year() { return getToken(SimplePARQLParser.Year, 0); }
		public TerminalNode Month() { return getToken(SimplePARQLParser.Month, 0); }
		public TerminalNode Day() { return getToken(SimplePARQLParser.Day, 0); }
		public TerminalNode Hours() { return getToken(SimplePARQLParser.Hours, 0); }
		public TerminalNode Minutes() { return getToken(SimplePARQLParser.Minutes, 0); }
		public TerminalNode Seconds() { return getToken(SimplePARQLParser.Seconds, 0); }
		public TerminalNode Timezone() { return getToken(SimplePARQLParser.Timezone, 0); }
		public TerminalNode Tz() { return getToken(SimplePARQLParser.Tz, 0); }
		public RegexExpressionContext regexExpression() {
			return getRuleContext(RegexExpressionContext.class,0);
		}
		public BuiltInCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_builtInCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterBuiltInCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitBuiltInCall(this);
		}
	}

	public final BuiltInCallContext builtInCall() throws RecognitionException {
		BuiltInCallContext _localctx = new BuiltInCallContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_builtInCall);
		try {
			setState(368);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Str:
				enterOuterAlt(_localctx, 1);
				{
				setState(257);
				match(Str);
				setState(258);
				match(T__4);
				setState(259);
				expression();
				setState(260);
				match(T__5);
				}
				break;
			case Lang:
				enterOuterAlt(_localctx, 2);
				{
				setState(262);
				match(Lang);
				setState(263);
				match(T__4);
				setState(264);
				expression();
				setState(265);
				match(T__5);
				}
				break;
			case Langmatches:
				enterOuterAlt(_localctx, 3);
				{
				setState(267);
				match(Langmatches);
				setState(268);
				match(T__4);
				setState(269);
				expression();
				setState(270);
				match(T__18);
				setState(271);
				expression();
				setState(272);
				match(T__5);
				}
				break;
			case Datatype:
				enterOuterAlt(_localctx, 4);
				{
				setState(274);
				match(Datatype);
				setState(275);
				match(T__4);
				setState(276);
				expression();
				setState(277);
				match(T__5);
				}
				break;
			case Contains:
				enterOuterAlt(_localctx, 5);
				{
				setState(279);
				match(Contains);
				setState(280);
				match(T__4);
				setState(281);
				expression();
				setState(282);
				match(T__18);
				setState(283);
				expression();
				setState(284);
				match(T__5);
				}
				break;
			case Ucase:
				enterOuterAlt(_localctx, 6);
				{
				setState(286);
				match(Ucase);
				setState(287);
				match(T__4);
				setState(288);
				expression();
				setState(289);
				match(T__5);
				}
				break;
			case Lcase:
				enterOuterAlt(_localctx, 7);
				{
				setState(291);
				match(Lcase);
				setState(292);
				match(T__4);
				setState(293);
				expression();
				setState(294);
				match(T__5);
				}
				break;
			case Bound:
				enterOuterAlt(_localctx, 8);
				{
				setState(296);
				match(Bound);
				setState(297);
				match(T__4);
				setState(298);
				match(VAR);
				setState(299);
				match(T__5);
				}
				break;
			case Sameterm:
				enterOuterAlt(_localctx, 9);
				{
				setState(300);
				match(Sameterm);
				setState(301);
				match(T__4);
				setState(302);
				expression();
				setState(303);
				match(T__18);
				setState(304);
				expression();
				setState(305);
				match(T__5);
				}
				break;
			case Isiri:
				enterOuterAlt(_localctx, 10);
				{
				setState(307);
				match(Isiri);
				setState(308);
				match(T__4);
				setState(309);
				expression();
				setState(310);
				match(T__5);
				}
				break;
			case Isuri:
				enterOuterAlt(_localctx, 11);
				{
				setState(312);
				match(Isuri);
				setState(313);
				match(T__4);
				setState(314);
				expression();
				setState(315);
				match(T__5);
				}
				break;
			case Isblank:
				enterOuterAlt(_localctx, 12);
				{
				setState(317);
				match(Isblank);
				setState(318);
				match(T__4);
				setState(319);
				expression();
				setState(320);
				match(T__5);
				}
				break;
			case Isliteral:
				enterOuterAlt(_localctx, 13);
				{
				setState(322);
				match(Isliteral);
				setState(323);
				match(T__4);
				setState(324);
				expression();
				setState(325);
				match(T__5);
				}
				break;
			case Year:
				enterOuterAlt(_localctx, 14);
				{
				setState(327);
				match(Year);
				setState(328);
				match(T__4);
				setState(329);
				expression();
				setState(330);
				match(T__5);
				}
				break;
			case Month:
				enterOuterAlt(_localctx, 15);
				{
				setState(332);
				match(Month);
				setState(333);
				match(T__4);
				setState(334);
				expression();
				setState(335);
				match(T__5);
				}
				break;
			case Day:
				enterOuterAlt(_localctx, 16);
				{
				setState(337);
				match(Day);
				setState(338);
				match(T__4);
				setState(339);
				expression();
				setState(340);
				match(T__5);
				}
				break;
			case Hours:
				enterOuterAlt(_localctx, 17);
				{
				setState(342);
				match(Hours);
				setState(343);
				match(T__4);
				setState(344);
				expression();
				setState(345);
				match(T__5);
				}
				break;
			case Minutes:
				enterOuterAlt(_localctx, 18);
				{
				setState(347);
				match(Minutes);
				setState(348);
				match(T__4);
				setState(349);
				expression();
				setState(350);
				match(T__5);
				}
				break;
			case Seconds:
				enterOuterAlt(_localctx, 19);
				{
				setState(352);
				match(Seconds);
				setState(353);
				match(T__4);
				setState(354);
				expression();
				setState(355);
				match(T__5);
				}
				break;
			case Timezone:
				enterOuterAlt(_localctx, 20);
				{
				setState(357);
				match(Timezone);
				setState(358);
				match(T__4);
				setState(359);
				expression();
				setState(360);
				match(T__5);
				}
				break;
			case Tz:
				enterOuterAlt(_localctx, 21);
				{
				setState(362);
				match(Tz);
				setState(363);
				match(T__4);
				setState(364);
				expression();
				setState(365);
				match(T__5);
				}
				break;
			case Regex:
				enterOuterAlt(_localctx, 22);
				{
				setState(367);
				regexExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegexExpressionContext extends ParserRuleContext {
		public TerminalNode Regex() { return getToken(SimplePARQLParser.Regex, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RegexExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regexExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterRegexExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitRegexExpression(this);
		}
	}

	public final RegexExpressionContext regexExpression() throws RecognitionException {
		RegexExpressionContext _localctx = new RegexExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_regexExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			match(Regex);
			setState(371);
			match(T__4);
			setState(372);
			expression();
			setState(373);
			match(T__18);
			setState(374);
			expression();
			setState(377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(375);
				match(T__18);
				setState(376);
				expression();
				}
			}

			setState(379);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IriRefOrFunctionContext extends ParserRuleContext {
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public IriRefOrFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iriRefOrFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterIriRefOrFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitIriRefOrFunction(this);
		}
	}

	public final IriRefOrFunctionContext iriRefOrFunction() throws RecognitionException {
		IriRefOrFunctionContext _localctx = new IriRefOrFunctionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_iriRefOrFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			iriRef();
			setState(383);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4 || _la==NIL) {
				{
				setState(382);
				argList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public TerminalNode NIL() { return getToken(SimplePARQLParser.NIL, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Distinct() { return getToken(SimplePARQLParser.Distinct, 0); }
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NIL:
				{
				setState(385);
				match(NIL);
				}
				break;
			case T__4:
				{
				setState(386);
				match(T__4);
				setState(388);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Distinct) {
					{
					setState(387);
					match(Distinct);
					}
				}

				setState(390);
				expression();
				setState(395);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__18) {
					{
					{
					setState(391);
					match(T__18);
					setState(392);
					expression();
					}
					}
					setState(397);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(398);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IriRefContext extends ParserRuleContext {
		public TerminalNode URI() { return getToken(SimplePARQLParser.URI, 0); }
		public PrefixedNameContext prefixedName() {
			return getRuleContext(PrefixedNameContext.class,0);
		}
		public IriRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iriRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterIriRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitIriRef(this);
		}
	}

	public final IriRefContext iriRef() throws RecognitionException {
		IriRefContext _localctx = new IriRefContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_iriRef);
		try {
			setState(404);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case URI:
				enterOuterAlt(_localctx, 1);
				{
				setState(402);
				match(URI);
				}
				break;
			case PNAME_LN:
			case PNAME_NS:
				enterOuterAlt(_localctx, 2);
				{
				setState(403);
				prefixedName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrefixedNameContext extends ParserRuleContext {
		public TerminalNode PNAME_LN() { return getToken(SimplePARQLParser.PNAME_LN, 0); }
		public TerminalNode PNAME_NS() { return getToken(SimplePARQLParser.PNAME_NS, 0); }
		public PrefixedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterPrefixedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitPrefixedName(this);
		}
	}

	public final PrefixedNameContext prefixedName() throws RecognitionException {
		PrefixedNameContext _localctx = new PrefixedNameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_prefixedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			_la = _input.LA(1);
			if ( !(_la==PNAME_LN || _la==PNAME_NS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriplesBlockContext extends ParserRuleContext {
		public TriplesSameSubjectContext triplesSameSubject() {
			return getRuleContext(TriplesSameSubjectContext.class,0);
		}
		public TriplesBlockContext triplesBlock() {
			return getRuleContext(TriplesBlockContext.class,0);
		}
		public TriplesBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triplesBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterTriplesBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitTriplesBlock(this);
		}
	}

	public final TriplesBlockContext triplesBlock() throws RecognitionException {
		TriplesBlockContext _localctx = new TriplesBlockContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_triplesBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			triplesSameSubject();
			setState(413);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(409);
				match(T__2);
				setState(411);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__20) | (1L << T__25) | (1L << T__26))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INTEGER - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (URI - 64)) | (1L << (VAR - 64)) | (1L << (NIL - 64)) | (1L << (TRUC_WORD - 64)) | (1L << (PNAME_LN - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (TRUC_DIESE - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (PNAME_NS - 64)))) != 0)) {
					{
					setState(410);
					triplesBlock();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriplesSameSubjectContext extends ParserRuleContext {
		public VarOrTermContext varOrTerm() {
			return getRuleContext(VarOrTermContext.class,0);
		}
		public PropertyListNotEmptyContext propertyListNotEmpty() {
			return getRuleContext(PropertyListNotEmptyContext.class,0);
		}
		public TriplesNodeContext triplesNode() {
			return getRuleContext(TriplesNodeContext.class,0);
		}
		public TriplesSameSubjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triplesSameSubject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterTriplesSameSubject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitTriplesSameSubject(this);
		}
	}

	public final TriplesSameSubjectContext triplesSameSubject() throws RecognitionException {
		TriplesSameSubjectContext _localctx = new TriplesSameSubjectContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_triplesSameSubject);
		int _la;
		try {
			setState(422);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__25:
			case T__26:
			case INTEGER:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case URI:
			case VAR:
			case NIL:
			case TRUC_WORD:
			case PNAME_LN:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case TRUC_DIESE:
			case ANON:
			case BLANK_NODE_LABEL:
			case PNAME_NS:
				enterOuterAlt(_localctx, 1);
				{
				setState(415);
				varOrTerm();
				setState(416);
				propertyListNotEmpty();
				}
				break;
			case T__4:
			case T__20:
				enterOuterAlt(_localctx, 2);
				{
				setState(418);
				triplesNode();
				setState(420);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23 || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (URI - 75)) | (1L << (VAR - 75)) | (1L << (TRUC_WORD - 75)) | (1L << (PNAME_LN - 75)) | (1L << (STRING_LITERAL1 - 75)) | (1L << (STRING_LITERAL2 - 75)) | (1L << (TRUC_DIESE - 75)) | (1L << (PNAME_NS - 75)))) != 0)) {
					{
					setState(419);
					propertyListNotEmpty();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphPatternNotTriplesContext extends ParserRuleContext {
		public OptionalGraphPatternContext optionalGraphPattern() {
			return getRuleContext(OptionalGraphPatternContext.class,0);
		}
		public GroupOrUnionGraphPatternContext groupOrUnionGraphPattern() {
			return getRuleContext(GroupOrUnionGraphPatternContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public BindContext bind() {
			return getRuleContext(BindContext.class,0);
		}
		public GraphPatternNotTriplesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphPatternNotTriples; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterGraphPatternNotTriples(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitGraphPatternNotTriples(this);
		}
	}

	public final GraphPatternNotTriplesContext graphPatternNotTriples() throws RecognitionException {
		GraphPatternNotTriplesContext _localctx = new GraphPatternNotTriplesContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_graphPatternNotTriples);
		try {
			setState(428);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Optional:
				enterOuterAlt(_localctx, 1);
				{
				setState(424);
				optionalGraphPattern();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(425);
				groupOrUnionGraphPattern();
				}
				break;
			case Filter:
				enterOuterAlt(_localctx, 3);
				{
				setState(426);
				filter();
				}
				break;
			case Bind:
				enterOuterAlt(_localctx, 4);
				{
				setState(427);
				bind();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BindContext extends ParserRuleContext {
		public TerminalNode Bind() { return getToken(SimplePARQLParser.Bind, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode VAR() { return getToken(SimplePARQLParser.VAR, 0); }
		public BindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterBind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitBind(this);
		}
	}

	public final BindContext bind() throws RecognitionException {
		BindContext _localctx = new BindContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_bind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			match(Bind);
			setState(431);
			match(T__4);
			setState(432);
			expression();
			setState(433);
			match(T__19);
			setState(434);
			match(VAR);
			setState(435);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionalGraphPatternContext extends ParserRuleContext {
		public TerminalNode Optional() { return getToken(SimplePARQLParser.Optional, 0); }
		public GroupGraphPatternContext groupGraphPattern() {
			return getRuleContext(GroupGraphPatternContext.class,0);
		}
		public OptionalGraphPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionalGraphPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterOptionalGraphPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitOptionalGraphPattern(this);
		}
	}

	public final OptionalGraphPatternContext optionalGraphPattern() throws RecognitionException {
		OptionalGraphPatternContext _localctx = new OptionalGraphPatternContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_optionalGraphPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			match(Optional);
			setState(438);
			groupGraphPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriplesNodeContext extends ParserRuleContext {
		public CollectionContext collection() {
			return getRuleContext(CollectionContext.class,0);
		}
		public BlankNodePropertyListContext blankNodePropertyList() {
			return getRuleContext(BlankNodePropertyListContext.class,0);
		}
		public TriplesNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triplesNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterTriplesNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitTriplesNode(this);
		}
	}

	public final TriplesNodeContext triplesNode() throws RecognitionException {
		TriplesNodeContext _localctx = new TriplesNodeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_triplesNode);
		try {
			setState(442);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(440);
				collection();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 2);
				{
				setState(441);
				blankNodePropertyList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CollectionContext extends ParserRuleContext {
		public List<GraphNodeContext> graphNode() {
			return getRuleContexts(GraphNodeContext.class);
		}
		public GraphNodeContext graphNode(int i) {
			return getRuleContext(GraphNodeContext.class,i);
		}
		public CollectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterCollection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitCollection(this);
		}
	}

	public final CollectionContext collection() throws RecognitionException {
		CollectionContext _localctx = new CollectionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_collection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			match(T__4);
			setState(446); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(445);
				graphNode();
				}
				}
				setState(448); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__20) | (1L << T__25) | (1L << T__26))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INTEGER - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (URI - 64)) | (1L << (VAR - 64)) | (1L << (NIL - 64)) | (1L << (TRUC_WORD - 64)) | (1L << (PNAME_LN - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (TRUC_DIESE - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (PNAME_NS - 64)))) != 0) );
			setState(450);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlankNodePropertyListContext extends ParserRuleContext {
		public PropertyListNotEmptyContext propertyListNotEmpty() {
			return getRuleContext(PropertyListNotEmptyContext.class,0);
		}
		public BlankNodePropertyListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blankNodePropertyList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterBlankNodePropertyList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitBlankNodePropertyList(this);
		}
	}

	public final BlankNodePropertyListContext blankNodePropertyList() throws RecognitionException {
		BlankNodePropertyListContext _localctx = new BlankNodePropertyListContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_blankNodePropertyList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452);
			match(T__20);
			setState(453);
			propertyListNotEmpty();
			setState(454);
			match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyListNotEmptyContext extends ParserRuleContext {
		public List<VerbContext> verb() {
			return getRuleContexts(VerbContext.class);
		}
		public VerbContext verb(int i) {
			return getRuleContext(VerbContext.class,i);
		}
		public List<ObjectListContext> objectList() {
			return getRuleContexts(ObjectListContext.class);
		}
		public ObjectListContext objectList(int i) {
			return getRuleContext(ObjectListContext.class,i);
		}
		public PropertyListNotEmptyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyListNotEmpty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterPropertyListNotEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitPropertyListNotEmpty(this);
		}
	}

	public final PropertyListNotEmptyContext propertyListNotEmpty() throws RecognitionException {
		PropertyListNotEmptyContext _localctx = new PropertyListNotEmptyContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_propertyListNotEmpty);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(456);
			verb();
			setState(457);
			objectList();
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(458);
				match(T__22);
				setState(462);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23 || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (URI - 75)) | (1L << (VAR - 75)) | (1L << (TRUC_WORD - 75)) | (1L << (PNAME_LN - 75)) | (1L << (STRING_LITERAL1 - 75)) | (1L << (STRING_LITERAL2 - 75)) | (1L << (TRUC_DIESE - 75)) | (1L << (PNAME_NS - 75)))) != 0)) {
					{
					setState(459);
					verb();
					setState(460);
					objectList();
					}
				}

				}
				}
				setState(468);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectListContext extends ParserRuleContext {
		public List<ObjectContext> object() {
			return getRuleContexts(ObjectContext.class);
		}
		public ObjectContext object(int i) {
			return getRuleContext(ObjectContext.class,i);
		}
		public ObjectListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterObjectList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitObjectList(this);
		}
	}

	public final ObjectListContext objectList() throws RecognitionException {
		ObjectListContext _localctx = new ObjectListContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_objectList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			object();
			setState(474);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__18) {
				{
				{
				setState(470);
				match(T__18);
				setState(471);
				object();
				}
				}
				setState(476);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectContext extends ParserRuleContext {
		public GraphNodeContext graphNode() {
			return getRuleContext(GraphNodeContext.class,0);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitObject(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(477);
			graphNode();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VerbContext extends ParserRuleContext {
		public VarOrIRIreforTrucContext varOrIRIreforTruc() {
			return getRuleContext(VarOrIRIreforTrucContext.class,0);
		}
		public VerbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_verb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterVerb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitVerb(this);
		}
	}

	public final VerbContext verb() throws RecognitionException {
		VerbContext _localctx = new VerbContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_verb);
		try {
			setState(481);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case URI:
			case VAR:
			case TRUC_WORD:
			case PNAME_LN:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case TRUC_DIESE:
			case PNAME_NS:
				enterOuterAlt(_localctx, 1);
				{
				setState(479);
				varOrIRIreforTruc();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 2);
				{
				setState(480);
				match(T__23);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarOrIRIreforTrucContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(SimplePARQLParser.VAR, 0); }
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public TrucContext truc() {
			return getRuleContext(TrucContext.class,0);
		}
		public VarOrIRIreforTrucContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varOrIRIreforTruc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterVarOrIRIreforTruc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitVarOrIRIreforTruc(this);
		}
	}

	public final VarOrIRIreforTrucContext varOrIRIreforTruc() throws RecognitionException {
		VarOrIRIreforTrucContext _localctx = new VarOrIRIreforTrucContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_varOrIRIreforTruc);
		try {
			setState(486);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(483);
				match(VAR);
				}
				break;
			case URI:
			case PNAME_LN:
			case PNAME_NS:
				enterOuterAlt(_localctx, 2);
				{
				setState(484);
				iriRef();
				}
				break;
			case TRUC_WORD:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case TRUC_DIESE:
				enterOuterAlt(_localctx, 3);
				{
				setState(485);
				truc();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphNodeContext extends ParserRuleContext {
		public VarOrTermContext varOrTerm() {
			return getRuleContext(VarOrTermContext.class,0);
		}
		public TriplesNodeContext triplesNode() {
			return getRuleContext(TriplesNodeContext.class,0);
		}
		public GraphNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterGraphNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitGraphNode(this);
		}
	}

	public final GraphNodeContext graphNode() throws RecognitionException {
		GraphNodeContext _localctx = new GraphNodeContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_graphNode);
		try {
			setState(490);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__25:
			case T__26:
			case INTEGER:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case URI:
			case VAR:
			case NIL:
			case TRUC_WORD:
			case PNAME_LN:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case TRUC_DIESE:
			case ANON:
			case BLANK_NODE_LABEL:
			case PNAME_NS:
				enterOuterAlt(_localctx, 1);
				{
				setState(488);
				varOrTerm();
				}
				break;
			case T__4:
			case T__20:
				enterOuterAlt(_localctx, 2);
				{
				setState(489);
				triplesNode();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarOrTermContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(SimplePARQLParser.VAR, 0); }
		public GraphTermContext graphTerm() {
			return getRuleContext(GraphTermContext.class,0);
		}
		public VarOrTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varOrTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterVarOrTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitVarOrTerm(this);
		}
	}

	public final VarOrTermContext varOrTerm() throws RecognitionException {
		VarOrTermContext _localctx = new VarOrTermContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_varOrTerm);
		try {
			setState(494);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(492);
				match(VAR);
				}
				break;
			case T__25:
			case T__26:
			case INTEGER:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case URI:
			case NIL:
			case TRUC_WORD:
			case PNAME_LN:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case TRUC_DIESE:
			case ANON:
			case BLANK_NODE_LABEL:
			case PNAME_NS:
				enterOuterAlt(_localctx, 2);
				{
				setState(493);
				graphTerm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphTermContext extends ParserRuleContext {
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public TrucContext truc() {
			return getRuleContext(TrucContext.class,0);
		}
		public RdfLiteralContext rdfLiteral() {
			return getRuleContext(RdfLiteralContext.class,0);
		}
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public BlankNodeContext blankNode() {
			return getRuleContext(BlankNodeContext.class,0);
		}
		public TerminalNode NIL() { return getToken(SimplePARQLParser.NIL, 0); }
		public GraphTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterGraphTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitGraphTerm(this);
		}
	}

	public final GraphTermContext graphTerm() throws RecognitionException {
		GraphTermContext _localctx = new GraphTermContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_graphTerm);
		try {
			setState(503);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(496);
				iriRef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(497);
				truc();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(498);
				rdfLiteral();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(499);
				numericLiteral();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(500);
				booleanLiteral();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(501);
				blankNode();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(502);
				match(NIL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RdfLiteralContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode LANGTAG() { return getToken(SimplePARQLParser.LANGTAG, 0); }
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public RdfLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rdfLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterRdfLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitRdfLiteral(this);
		}
	}

	public final RdfLiteralContext rdfLiteral() throws RecognitionException {
		RdfLiteralContext _localctx = new RdfLiteralContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_rdfLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505);
			string();
			setState(509);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LANGTAG:
				{
				setState(506);
				match(LANGTAG);
				}
				break;
			case T__24:
				{
				{
				setState(507);
				match(T__24);
				setState(508);
				iriRef();
				}
				}
				break;
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
			case T__16:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__25:
			case T__26:
			case Filter:
			case Bind:
			case Optional:
			case INTEGER:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case URI:
			case VAR:
			case NIL:
			case TRUC_WORD:
			case PNAME_LN:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case TRUC_DIESE:
			case ANON:
			case BLANK_NODE_LABEL:
			case PNAME_NS:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanLiteralContext extends ParserRuleContext {
		public BooleanLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitBooleanLiteral(this);
		}
	}

	public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
		BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511);
			_la = _input.LA(1);
			if ( !(_la==T__25 || _la==T__26) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlankNodeContext extends ParserRuleContext {
		public TerminalNode BLANK_NODE_LABEL() { return getToken(SimplePARQLParser.BLANK_NODE_LABEL, 0); }
		public TerminalNode ANON() { return getToken(SimplePARQLParser.ANON, 0); }
		public BlankNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blankNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterBlankNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitBlankNode(this);
		}
	}

	public final BlankNodeContext blankNode() throws RecognitionException {
		BlankNodeContext _localctx = new BlankNodeContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_blankNode);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			_la = _input.LA(1);
			if ( !(_la==ANON || _la==BLANK_NODE_LABEL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL1() { return getToken(SimplePARQLParser.STRING_LITERAL1, 0); }
		public TerminalNode STRING_LITERAL2() { return getToken(SimplePARQLParser.STRING_LITERAL2, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(515);
			_la = _input.LA(1);
			if ( !(_la==STRING_LITERAL1 || _la==STRING_LITERAL2) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupOrUnionGraphPatternContext extends ParserRuleContext {
		public List<GroupGraphPatternContext> groupGraphPattern() {
			return getRuleContexts(GroupGraphPatternContext.class);
		}
		public GroupGraphPatternContext groupGraphPattern(int i) {
			return getRuleContext(GroupGraphPatternContext.class,i);
		}
		public List<TerminalNode> Union() { return getTokens(SimplePARQLParser.Union); }
		public TerminalNode Union(int i) {
			return getToken(SimplePARQLParser.Union, i);
		}
		public GroupOrUnionGraphPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupOrUnionGraphPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterGroupOrUnionGraphPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitGroupOrUnionGraphPattern(this);
		}
	}

	public final GroupOrUnionGraphPatternContext groupOrUnionGraphPattern() throws RecognitionException {
		GroupOrUnionGraphPatternContext _localctx = new GroupOrUnionGraphPatternContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_groupOrUnionGraphPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			groupGraphPattern();
			setState(522);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Union) {
				{
				{
				setState(518);
				match(Union);
				setState(519);
				groupGraphPattern();
				}
				}
				setState(524);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TrucContext extends ParserRuleContext {
		public TerminalNode TRUC_WORD() { return getToken(SimplePARQLParser.TRUC_WORD, 0); }
		public TerminalNode TRUC_DIESE() { return getToken(SimplePARQLParser.TRUC_DIESE, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TrucContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_truc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterTruc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitTruc(this);
		}
	}

	public final TrucContext truc() throws RecognitionException {
		TrucContext _localctx = new TrucContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_truc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(528);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUC_WORD:
				{
				setState(525);
				match(TRUC_WORD);
				}
				break;
			case TRUC_DIESE:
				{
				setState(526);
				match(TRUC_DIESE);
				}
				break;
			case STRING_LITERAL1:
			case STRING_LITERAL2:
				{
				setState(527);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SolutionModifierContext extends ParserRuleContext {
		public OrderClauseContext orderClause() {
			return getRuleContext(OrderClauseContext.class,0);
		}
		public LimitOffsetClausesContext limitOffsetClauses() {
			return getRuleContext(LimitOffsetClausesContext.class,0);
		}
		public SolutionModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_solutionModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterSolutionModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitSolutionModifier(this);
		}
	}

	public final SolutionModifierContext solutionModifier() throws RecognitionException {
		SolutionModifierContext _localctx = new SolutionModifierContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_solutionModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(531);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Order) {
				{
				setState(530);
				orderClause();
				}
			}

			setState(534);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Limit || _la==Offset) {
				{
				setState(533);
				limitOffsetClauses();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LimitOffsetClausesContext extends ParserRuleContext {
		public LimitClauseContext limitClause() {
			return getRuleContext(LimitClauseContext.class,0);
		}
		public OffsetClauseContext offsetClause() {
			return getRuleContext(OffsetClauseContext.class,0);
		}
		public LimitOffsetClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitOffsetClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterLimitOffsetClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitLimitOffsetClauses(this);
		}
	}

	public final LimitOffsetClausesContext limitOffsetClauses() throws RecognitionException {
		LimitOffsetClausesContext _localctx = new LimitOffsetClausesContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_limitOffsetClauses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(544);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Limit:
				{
				setState(536);
				limitClause();
				setState(538);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Offset) {
					{
					setState(537);
					offsetClause();
					}
				}

				}
				break;
			case Offset:
				{
				setState(540);
				offsetClause();
				setState(542);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Limit) {
					{
					setState(541);
					limitClause();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrderClauseContext extends ParserRuleContext {
		public TerminalNode Order() { return getToken(SimplePARQLParser.Order, 0); }
		public TerminalNode By() { return getToken(SimplePARQLParser.By, 0); }
		public List<OrderConditionContext> orderCondition() {
			return getRuleContexts(OrderConditionContext.class);
		}
		public OrderConditionContext orderCondition(int i) {
			return getRuleContext(OrderConditionContext.class,i);
		}
		public OrderClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterOrderClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitOrderClause(this);
		}
	}

	public final OrderClauseContext orderClause() throws RecognitionException {
		OrderClauseContext _localctx = new OrderClauseContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_orderClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			match(Order);
			setState(547);
			match(By);
			setState(549); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(548);
				orderCondition();
				}
				}
				setState(551); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << Str) | (1L << Lang) | (1L << Langmatches) | (1L << Datatype) | (1L << Contains) | (1L << Ucase) | (1L << Lcase) | (1L << Bound) | (1L << Sameterm) | (1L << Isiri) | (1L << Isuri) | (1L << Isblank) | (1L << Isliteral) | (1L << Year) | (1L << Month) | (1L << Day) | (1L << Hours) | (1L << Minutes) | (1L << Seconds) | (1L << Timezone) | (1L << Tz) | (1L << Regex) | (1L << Asc) | (1L << Desc))) != 0) || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (URI - 75)) | (1L << (VAR - 75)) | (1L << (PNAME_LN - 75)) | (1L << (PNAME_NS - 75)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrderConditionContext extends ParserRuleContext {
		public BrackettedExpressionContext brackettedExpression() {
			return getRuleContext(BrackettedExpressionContext.class,0);
		}
		public TerminalNode Asc() { return getToken(SimplePARQLParser.Asc, 0); }
		public TerminalNode Desc() { return getToken(SimplePARQLParser.Desc, 0); }
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public TerminalNode VAR() { return getToken(SimplePARQLParser.VAR, 0); }
		public OrderConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterOrderCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitOrderCondition(this);
		}
	}

	public final OrderConditionContext orderCondition() throws RecognitionException {
		OrderConditionContext _localctx = new OrderConditionContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_orderCondition);
		int _la;
		try {
			setState(559);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Asc:
			case Desc:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(553);
				_la = _input.LA(1);
				if ( !(_la==Asc || _la==Desc) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(554);
				brackettedExpression();
				}
				}
				break;
			case T__4:
			case Str:
			case Lang:
			case Langmatches:
			case Datatype:
			case Contains:
			case Ucase:
			case Lcase:
			case Bound:
			case Sameterm:
			case Isiri:
			case Isuri:
			case Isblank:
			case Isliteral:
			case Year:
			case Month:
			case Day:
			case Hours:
			case Minutes:
			case Seconds:
			case Timezone:
			case Tz:
			case Regex:
			case URI:
			case VAR:
			case PNAME_LN:
			case PNAME_NS:
				enterOuterAlt(_localctx, 2);
				{
				setState(557);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
				case Str:
				case Lang:
				case Langmatches:
				case Datatype:
				case Contains:
				case Ucase:
				case Lcase:
				case Bound:
				case Sameterm:
				case Isiri:
				case Isuri:
				case Isblank:
				case Isliteral:
				case Year:
				case Month:
				case Day:
				case Hours:
				case Minutes:
				case Seconds:
				case Timezone:
				case Tz:
				case Regex:
				case URI:
				case PNAME_LN:
				case PNAME_NS:
					{
					setState(555);
					constraint();
					}
					break;
				case VAR:
					{
					setState(556);
					match(VAR);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LimitClauseContext extends ParserRuleContext {
		public TerminalNode Limit() { return getToken(SimplePARQLParser.Limit, 0); }
		public TerminalNode INTEGER() { return getToken(SimplePARQLParser.INTEGER, 0); }
		public LimitClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterLimitClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitLimitClause(this);
		}
	}

	public final LimitClauseContext limitClause() throws RecognitionException {
		LimitClauseContext _localctx = new LimitClauseContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_limitClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(561);
			match(Limit);
			setState(562);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OffsetClauseContext extends ParserRuleContext {
		public TerminalNode Offset() { return getToken(SimplePARQLParser.Offset, 0); }
		public TerminalNode INTEGER() { return getToken(SimplePARQLParser.INTEGER, 0); }
		public OffsetClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_offsetClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterOffsetClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitOffsetClause(this);
		}
	}

	public final OffsetClauseContext offsetClause() throws RecognitionException {
		OffsetClauseContext _localctx = new OffsetClauseContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_offsetClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(564);
			match(Offset);
			setState(565);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralContext extends ParserRuleContext {
		public NumericLiteralUnsignedContext numericLiteralUnsigned() {
			return getRuleContext(NumericLiteralUnsignedContext.class,0);
		}
		public NumericLiteralPositiveContext numericLiteralPositive() {
			return getRuleContext(NumericLiteralPositiveContext.class,0);
		}
		public NumericLiteralNegativeContext numericLiteralNegative() {
			return getRuleContext(NumericLiteralNegativeContext.class,0);
		}
		public NumericLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterNumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitNumericLiteral(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_numericLiteral);
		try {
			setState(570);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
			case DECIMAL:
			case DOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(567);
				numericLiteralUnsigned();
				}
				break;
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(568);
				numericLiteralPositive();
				}
				break;
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(569);
				numericLiteralNegative();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralUnsignedContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(SimplePARQLParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(SimplePARQLParser.DECIMAL, 0); }
		public TerminalNode DOUBLE() { return getToken(SimplePARQLParser.DOUBLE, 0); }
		public NumericLiteralUnsignedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteralUnsigned; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterNumericLiteralUnsigned(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitNumericLiteralUnsigned(this);
		}
	}

	public final NumericLiteralUnsignedContext numericLiteralUnsigned() throws RecognitionException {
		NumericLiteralUnsignedContext _localctx = new NumericLiteralUnsignedContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_numericLiteralUnsigned);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(572);
			_la = _input.LA(1);
			if ( !(((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (INTEGER - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralPositiveContext extends ParserRuleContext {
		public TerminalNode INTEGER_POSITIVE() { return getToken(SimplePARQLParser.INTEGER_POSITIVE, 0); }
		public TerminalNode DECIMAL_POSITIVE() { return getToken(SimplePARQLParser.DECIMAL_POSITIVE, 0); }
		public TerminalNode DOUBLE_POSITIVE() { return getToken(SimplePARQLParser.DOUBLE_POSITIVE, 0); }
		public NumericLiteralPositiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteralPositive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterNumericLiteralPositive(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitNumericLiteralPositive(this);
		}
	}

	public final NumericLiteralPositiveContext numericLiteralPositive() throws RecognitionException {
		NumericLiteralPositiveContext _localctx = new NumericLiteralPositiveContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_numericLiteralPositive);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(574);
			_la = _input.LA(1);
			if ( !(((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (INTEGER_POSITIVE - 67)) | (1L << (DECIMAL_POSITIVE - 67)) | (1L << (DOUBLE_POSITIVE - 67)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralNegativeContext extends ParserRuleContext {
		public TerminalNode INTEGER_NEGATIVE() { return getToken(SimplePARQLParser.INTEGER_NEGATIVE, 0); }
		public TerminalNode DECIMAL_NEGATIVE() { return getToken(SimplePARQLParser.DECIMAL_NEGATIVE, 0); }
		public TerminalNode DOUBLE_NEGATIVE() { return getToken(SimplePARQLParser.DOUBLE_NEGATIVE, 0); }
		public NumericLiteralNegativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteralNegative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).enterNumericLiteralNegative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimplePARQLListener ) ((SimplePARQLListener)listener).exitNumericLiteralNegative(this);
		}
	}

	public final NumericLiteralNegativeContext numericLiteralNegative() throws RecognitionException {
		NumericLiteralNegativeContext _localctx = new NumericLiteralNegativeContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_numericLiteralNegative);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(576);
			_la = _input.LA(1);
			if ( !(((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (INTEGER_NEGATIVE - 70)) | (1L << (DECIMAL_NEGATIVE - 70)) | (1L << (DOUBLE_NEGATIVE - 70)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3_\u0245\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\7\3|\n\3\f\3\16\3\177\13\3\3\4\3\4\3\4\3\4\3\5\3\5\5\5\u0087"+
		"\n\5\3\5\6\5\u008a\n\5\r\5\16\5\u008b\3\5\3\5\5\5\u0090\n\5\3\6\3\6\3"+
		"\6\3\7\3\7\5\7\u0097\n\7\3\7\3\7\5\7\u009b\n\7\3\7\5\7\u009e\n\7\7\7\u00a0"+
		"\n\7\f\7\16\7\u00a3\13\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5"+
		"\t\u00b0\n\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\7\r\u00be"+
		"\n\r\f\r\16\r\u00c1\13\r\3\16\3\16\3\16\7\16\u00c6\n\16\f\16\16\16\u00c9"+
		"\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\5\17\u00d8\n\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00e3"+
		"\n\21\f\21\16\21\u00e6\13\21\3\22\3\22\3\22\3\22\3\22\7\22\u00ed\n\22"+
		"\f\22\16\22\u00f0\13\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00f9"+
		"\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0102\n\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u0173\n\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u017c\n\26\3\26\3\26\3\27\3\27\5\27"+
		"\u0182\n\27\3\30\3\30\3\30\5\30\u0187\n\30\3\30\3\30\3\30\7\30\u018c\n"+
		"\30\f\30\16\30\u018f\13\30\3\30\3\30\5\30\u0193\n\30\3\31\3\31\5\31\u0197"+
		"\n\31\3\32\3\32\3\33\3\33\3\33\5\33\u019e\n\33\5\33\u01a0\n\33\3\34\3"+
		"\34\3\34\3\34\3\34\5\34\u01a7\n\34\5\34\u01a9\n\34\3\35\3\35\3\35\3\35"+
		"\5\35\u01af\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 "+
		"\3 \5 \u01bd\n \3!\3!\6!\u01c1\n!\r!\16!\u01c2\3!\3!\3\"\3\"\3\"\3\"\3"+
		"#\3#\3#\3#\3#\3#\5#\u01d1\n#\7#\u01d3\n#\f#\16#\u01d6\13#\3$\3$\3$\7$"+
		"\u01db\n$\f$\16$\u01de\13$\3%\3%\3&\3&\5&\u01e4\n&\3\'\3\'\3\'\5\'\u01e9"+
		"\n\'\3(\3(\5(\u01ed\n(\3)\3)\5)\u01f1\n)\3*\3*\3*\3*\3*\3*\3*\5*\u01fa"+
		"\n*\3+\3+\3+\3+\5+\u0200\n+\3,\3,\3-\3-\3.\3.\3/\3/\3/\7/\u020b\n/\f/"+
		"\16/\u020e\13/\3\60\3\60\3\60\5\60\u0213\n\60\3\61\5\61\u0216\n\61\3\61"+
		"\5\61\u0219\n\61\3\62\3\62\5\62\u021d\n\62\3\62\3\62\5\62\u0221\n\62\5"+
		"\62\u0223\n\62\3\63\3\63\3\63\6\63\u0228\n\63\r\63\16\63\u0229\3\64\3"+
		"\64\3\64\3\64\5\64\u0230\n\64\5\64\u0232\n\64\3\65\3\65\3\65\3\66\3\66"+
		"\3\66\3\67\3\67\3\67\5\67\u023d\n\67\38\38\39\39\3:\3:\3:\2\2;\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVX"+
		"Z\\^`bdfhjlnpr\2\n\4\2RR[[\3\2\34\35\4\2XXZZ\3\2TU\3\2<=\3\2BD\3\2EG\3"+
		"\2HJ\2\u026e\2t\3\2\2\2\4}\3\2\2\2\6\u0080\3\2\2\2\b\u0084\3\2\2\2\n\u0091"+
		"\3\2\2\2\f\u0094\3\2\2\2\16\u00a6\3\2\2\2\20\u00af\3\2\2\2\22\u00b1\3"+
		"\2\2\2\24\u00b4\3\2\2\2\26\u00b8\3\2\2\2\30\u00ba\3\2\2\2\32\u00c2\3\2"+
		"\2\2\34\u00ca\3\2\2\2\36\u00d9\3\2\2\2 \u00db\3\2\2\2\"\u00e7\3\2\2\2"+
		"$\u00f8\3\2\2\2&\u0101\3\2\2\2(\u0172\3\2\2\2*\u0174\3\2\2\2,\u017f\3"+
		"\2\2\2.\u0192\3\2\2\2\60\u0196\3\2\2\2\62\u0198\3\2\2\2\64\u019a\3\2\2"+
		"\2\66\u01a8\3\2\2\28\u01ae\3\2\2\2:\u01b0\3\2\2\2<\u01b7\3\2\2\2>\u01bc"+
		"\3\2\2\2@\u01be\3\2\2\2B\u01c6\3\2\2\2D\u01ca\3\2\2\2F\u01d7\3\2\2\2H"+
		"\u01df\3\2\2\2J\u01e3\3\2\2\2L\u01e8\3\2\2\2N\u01ec\3\2\2\2P\u01f0\3\2"+
		"\2\2R\u01f9\3\2\2\2T\u01fb\3\2\2\2V\u0201\3\2\2\2X\u0203\3\2\2\2Z\u0205"+
		"\3\2\2\2\\\u0207\3\2\2\2^\u0212\3\2\2\2`\u0215\3\2\2\2b\u0222\3\2\2\2"+
		"d\u0224\3\2\2\2f\u0231\3\2\2\2h\u0233\3\2\2\2j\u0236\3\2\2\2l\u023c\3"+
		"\2\2\2n\u023e\3\2\2\2p\u0240\3\2\2\2r\u0242\3\2\2\2tu\5\4\3\2uv\5\b\5"+
		"\2vw\5\n\6\2wx\5`\61\2xy\7\2\2\3y\3\3\2\2\2z|\5\6\4\2{z\3\2\2\2|\177\3"+
		"\2\2\2}{\3\2\2\2}~\3\2\2\2~\5\3\2\2\2\177}\3\2\2\2\u0080\u0081\7\37\2"+
		"\2\u0081\u0082\7[\2\2\u0082\u0083\7M\2\2\u0083\7\3\2\2\2\u0084\u0086\7"+
		"\36\2\2\u0085\u0087\7!\2\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u008f\3\2\2\2\u0088\u008a\7N\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2"+
		"\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u0090\3\2\2\2\u008d"+
		"\u0090\7\3\2\2\u008e\u0090\5^\60\2\u008f\u0089\3\2\2\2\u008f\u008d\3\2"+
		"\2\2\u008f\u008e\3\2\2\2\u0090\t\3\2\2\2\u0091\u0092\7 \2\2\u0092\u0093"+
		"\5\f\7\2\u0093\13\3\2\2\2\u0094\u0096\7\4\2\2\u0095\u0097\5\64\33\2\u0096"+
		"\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u00a1\3\2\2\2\u0098\u009a\58"+
		"\35\2\u0099\u009b\7\5\2\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b"+
		"\u009d\3\2\2\2\u009c\u009e\5\64\33\2\u009d\u009c\3\2\2\2\u009d\u009e\3"+
		"\2\2\2\u009e\u00a0\3\2\2\2\u009f\u0098\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1"+
		"\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a1\3\2"+
		"\2\2\u00a4\u00a5\7\6\2\2\u00a5\r\3\2\2\2\u00a6\u00a7\7\"\2\2\u00a7\u00a8"+
		"\5\20\t\2\u00a8\17\3\2\2\2\u00a9\u00aa\7\7\2\2\u00aa\u00ab\5\26\f\2\u00ab"+
		"\u00ac\7\b\2\2\u00ac\u00b0\3\2\2\2\u00ad\u00b0\5(\25\2\u00ae\u00b0\5\22"+
		"\n\2\u00af\u00a9\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0"+
		"\21\3\2\2\2\u00b1\u00b2\5\60\31\2\u00b2\u00b3\5.\30\2\u00b3\23\3\2\2\2"+
		"\u00b4\u00b5\7\7\2\2\u00b5\u00b6\5\26\f\2\u00b6\u00b7\7\b\2\2\u00b7\25"+
		"\3\2\2\2\u00b8\u00b9\5\30\r\2\u00b9\27\3\2\2\2\u00ba\u00bf\5\32\16\2\u00bb"+
		"\u00bc\7\t\2\2\u00bc\u00be\5\32\16\2\u00bd\u00bb\3\2\2\2\u00be\u00c1\3"+
		"\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\31\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c2\u00c7\5\34\17\2\u00c3\u00c4\7\n\2\2\u00c4\u00c6\5"+
		"\34\17\2\u00c5\u00c3\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7"+
		"\u00c8\3\2\2\2\u00c8\33\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00d7\5\36\20"+
		"\2\u00cb\u00cc\7\13\2\2\u00cc\u00d8\5\36\20\2\u00cd\u00ce\7\f\2\2\u00ce"+
		"\u00d8\5\36\20\2\u00cf\u00d0\7\r\2\2\u00d0\u00d8\5\36\20\2\u00d1\u00d2"+
		"\7\16\2\2\u00d2\u00d8\5\36\20\2\u00d3\u00d4\7\17\2\2\u00d4\u00d8\5\36"+
		"\20\2\u00d5\u00d6\7\20\2\2\u00d6\u00d8\5\36\20\2\u00d7\u00cb\3\2\2\2\u00d7"+
		"\u00cd\3\2\2\2\u00d7\u00cf\3\2\2\2\u00d7\u00d1\3\2\2\2\u00d7\u00d3\3\2"+
		"\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\35\3\2\2\2\u00d9\u00da"+
		"\5 \21\2\u00da\37\3\2\2\2\u00db\u00e4\5\"\22\2\u00dc\u00dd\7\21\2\2\u00dd"+
		"\u00e3\5\"\22\2\u00de\u00df\7\22\2\2\u00df\u00e3\5\"\22\2\u00e0\u00e3"+
		"\5p9\2\u00e1\u00e3\5r:\2\u00e2\u00dc\3\2\2\2\u00e2\u00de\3\2\2\2\u00e2"+
		"\u00e0\3\2\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2"+
		"\2\2\u00e4\u00e5\3\2\2\2\u00e5!\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00ee"+
		"\5$\23\2\u00e8\u00e9\7\3\2\2\u00e9\u00ed\5$\23\2\u00ea\u00eb\7\23\2\2"+
		"\u00eb\u00ed\5$\23\2\u00ec\u00e8\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00f0"+
		"\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef#\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f1\u00f9\5&\24\2\u00f2\u00f3\7\24\2\2\u00f3\u00f9\5"+
		"&\24\2\u00f4\u00f5\7\21\2\2\u00f5\u00f9\5&\24\2\u00f6\u00f7\7\22\2\2\u00f7"+
		"\u00f9\5&\24\2\u00f8\u00f1\3\2\2\2\u00f8\u00f2\3\2\2\2\u00f8\u00f4\3\2"+
		"\2\2\u00f8\u00f6\3\2\2\2\u00f9%\3\2\2\2\u00fa\u0102\5\24\13\2\u00fb\u0102"+
		"\5(\25\2\u00fc\u0102\5,\27\2\u00fd\u0102\5T+\2\u00fe\u0102\5l\67\2\u00ff"+
		"\u0102\5V,\2\u0100\u0102\7N\2\2\u0101\u00fa\3\2\2\2\u0101\u00fb\3\2\2"+
		"\2\u0101\u00fc\3\2\2\2\u0101\u00fd\3\2\2\2\u0101\u00fe\3\2\2\2\u0101\u00ff"+
		"\3\2\2\2\u0101\u0100\3\2\2\2\u0102\'\3\2\2\2\u0103\u0104\7#\2\2\u0104"+
		"\u0105\7\7\2\2\u0105\u0106\5\26\f\2\u0106\u0107\7\b\2\2\u0107\u0173\3"+
		"\2\2\2\u0108\u0109\7$\2\2\u0109\u010a\7\7\2\2\u010a\u010b\5\26\f\2\u010b"+
		"\u010c\7\b\2\2\u010c\u0173\3\2\2\2\u010d\u010e\7%\2\2\u010e\u010f\7\7"+
		"\2\2\u010f\u0110\5\26\f\2\u0110\u0111\7\25\2\2\u0111\u0112\5\26\f\2\u0112"+
		"\u0113\7\b\2\2\u0113\u0173\3\2\2\2\u0114\u0115\7&\2\2\u0115\u0116\7\7"+
		"\2\2\u0116\u0117\5\26\f\2\u0117\u0118\7\b\2\2\u0118\u0173\3\2\2\2\u0119"+
		"\u011a\7\'\2\2\u011a\u011b\7\7\2\2\u011b\u011c\5\26\f\2\u011c\u011d\7"+
		"\25\2\2\u011d\u011e\5\26\f\2\u011e\u011f\7\b\2\2\u011f\u0173\3\2\2\2\u0120"+
		"\u0121\7(\2\2\u0121\u0122\7\7\2\2\u0122\u0123\5\26\f\2\u0123\u0124\7\b"+
		"\2\2\u0124\u0173\3\2\2\2\u0125\u0126\7)\2\2\u0126\u0127\7\7\2\2\u0127"+
		"\u0128\5\26\f\2\u0128\u0129\7\b\2\2\u0129\u0173\3\2\2\2\u012a\u012b\7"+
		"*\2\2\u012b\u012c\7\7\2\2\u012c\u012d\7N\2\2\u012d\u0173\7\b\2\2\u012e"+
		"\u012f\7+\2\2\u012f\u0130\7\7\2\2\u0130\u0131\5\26\f\2\u0131\u0132\7\25"+
		"\2\2\u0132\u0133\5\26\f\2\u0133\u0134\7\b\2\2\u0134\u0173\3\2\2\2\u0135"+
		"\u0136\7,\2\2\u0136\u0137\7\7\2\2\u0137\u0138\5\26\f\2\u0138\u0139\7\b"+
		"\2\2\u0139\u0173\3\2\2\2\u013a\u013b\7-\2\2\u013b\u013c\7\7\2\2\u013c"+
		"\u013d\5\26\f\2\u013d\u013e\7\b\2\2\u013e\u0173\3\2\2\2\u013f\u0140\7"+
		".\2\2\u0140\u0141\7\7\2\2\u0141\u0142\5\26\f\2\u0142\u0143\7\b\2\2\u0143"+
		"\u0173\3\2\2\2\u0144\u0145\7/\2\2\u0145\u0146\7\7\2\2\u0146\u0147\5\26"+
		"\f\2\u0147\u0148\7\b\2\2\u0148\u0173\3\2\2\2\u0149\u014a\7\60\2\2\u014a"+
		"\u014b\7\7\2\2\u014b\u014c\5\26\f\2\u014c\u014d\7\b\2\2\u014d\u0173\3"+
		"\2\2\2\u014e\u014f\7\61\2\2\u014f\u0150\7\7\2\2\u0150\u0151\5\26\f\2\u0151"+
		"\u0152\7\b\2\2\u0152\u0173\3\2\2\2\u0153\u0154\7\62\2\2\u0154\u0155\7"+
		"\7\2\2\u0155\u0156\5\26\f\2\u0156\u0157\7\b\2\2\u0157\u0173\3\2\2\2\u0158"+
		"\u0159\7\63\2\2\u0159\u015a\7\7\2\2\u015a\u015b\5\26\f\2\u015b\u015c\7"+
		"\b\2\2\u015c\u0173\3\2\2\2\u015d\u015e\7\64\2\2\u015e\u015f\7\7\2\2\u015f"+
		"\u0160\5\26\f\2\u0160\u0161\7\b\2\2\u0161\u0173\3\2\2\2\u0162\u0163\7"+
		"\65\2\2\u0163\u0164\7\7\2\2\u0164\u0165\5\26\f\2\u0165\u0166\7\b\2\2\u0166"+
		"\u0173\3\2\2\2\u0167\u0168\7\66\2\2\u0168\u0169\7\7\2\2\u0169\u016a\5"+
		"\26\f\2\u016a\u016b\7\b\2\2\u016b\u0173\3\2\2\2\u016c\u016d\7\67\2\2\u016d"+
		"\u016e\7\7\2\2\u016e\u016f\5\26\f\2\u016f\u0170\7\b\2\2\u0170\u0173\3"+
		"\2\2\2\u0171\u0173\5*\26\2\u0172\u0103\3\2\2\2\u0172\u0108\3\2\2\2\u0172"+
		"\u010d\3\2\2\2\u0172\u0114\3\2\2\2\u0172\u0119\3\2\2\2\u0172\u0120\3\2"+
		"\2\2\u0172\u0125\3\2\2\2\u0172\u012a\3\2\2\2\u0172\u012e\3\2\2\2\u0172"+
		"\u0135\3\2\2\2\u0172\u013a\3\2\2\2\u0172\u013f\3\2\2\2\u0172\u0144\3\2"+
		"\2\2\u0172\u0149\3\2\2\2\u0172\u014e\3\2\2\2\u0172\u0153\3\2\2\2\u0172"+
		"\u0158\3\2\2\2\u0172\u015d\3\2\2\2\u0172\u0162\3\2\2\2\u0172\u0167\3\2"+
		"\2\2\u0172\u016c\3\2\2\2\u0172\u0171\3\2\2\2\u0173)\3\2\2\2\u0174\u0175"+
		"\78\2\2\u0175\u0176\7\7\2\2\u0176\u0177\5\26\f\2\u0177\u0178\7\25\2\2"+
		"\u0178\u017b\5\26\f\2\u0179\u017a\7\25\2\2\u017a\u017c\5\26\f\2\u017b"+
		"\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017e\7\b"+
		"\2\2\u017e+\3\2\2\2\u017f\u0181\5\60\31\2\u0180\u0182\5.\30\2\u0181\u0180"+
		"\3\2\2\2\u0181\u0182\3\2\2\2\u0182-\3\2\2\2\u0183\u0193\7O\2\2\u0184\u0186"+
		"\7\7\2\2\u0185\u0187\7!\2\2\u0186\u0185\3\2\2\2\u0186\u0187\3\2\2\2\u0187"+
		"\u0188\3\2\2\2\u0188\u018d\5\26\f\2\u0189\u018a\7\25\2\2\u018a\u018c\5"+
		"\26\f\2\u018b\u0189\3\2\2\2\u018c\u018f\3\2\2\2\u018d\u018b\3\2\2\2\u018d"+
		"\u018e\3\2\2\2\u018e\u0190\3\2\2\2\u018f\u018d\3\2\2\2\u0190\u0191\7\b"+
		"\2\2\u0191\u0193\3\2\2\2\u0192\u0183\3\2\2\2\u0192\u0184\3\2\2\2\u0193"+
		"/\3\2\2\2\u0194\u0197\7M\2\2\u0195\u0197\5\62\32\2\u0196\u0194\3\2\2\2"+
		"\u0196\u0195\3\2\2\2\u0197\61\3\2\2\2\u0198\u0199\t\2\2\2\u0199\63\3\2"+
		"\2\2\u019a\u019f\5\66\34\2\u019b\u019d\7\5\2\2\u019c\u019e\5\64\33\2\u019d"+
		"\u019c\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01a0\3\2\2\2\u019f\u019b\3\2"+
		"\2\2\u019f\u01a0\3\2\2\2\u01a0\65\3\2\2\2\u01a1\u01a2\5P)\2\u01a2\u01a3"+
		"\5D#\2\u01a3\u01a9\3\2\2\2\u01a4\u01a6\5> \2\u01a5\u01a7\5D#\2\u01a6\u01a5"+
		"\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01a1\3\2\2\2\u01a8"+
		"\u01a4\3\2\2\2\u01a9\67\3\2\2\2\u01aa\u01af\5<\37\2\u01ab\u01af\5\\/\2"+
		"\u01ac\u01af\5\16\b\2\u01ad\u01af\5:\36\2\u01ae\u01aa\3\2\2\2\u01ae\u01ab"+
		"\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01ad\3\2\2\2\u01af9\3\2\2\2\u01b0"+
		"\u01b1\79\2\2\u01b1\u01b2\7\7\2\2\u01b2\u01b3\5\26\f\2\u01b3\u01b4\7\26"+
		"\2\2\u01b4\u01b5\7N\2\2\u01b5\u01b6\7\b\2\2\u01b6;\3\2\2\2\u01b7\u01b8"+
		"\7:\2\2\u01b8\u01b9\5\f\7\2\u01b9=\3\2\2\2\u01ba\u01bd\5@!\2\u01bb\u01bd"+
		"\5B\"\2\u01bc\u01ba\3\2\2\2\u01bc\u01bb\3\2\2\2\u01bd?\3\2\2\2\u01be\u01c0"+
		"\7\7\2\2\u01bf\u01c1\5N(\2\u01c0\u01bf\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2"+
		"\u01c0\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c5\7\b"+
		"\2\2\u01c5A\3\2\2\2\u01c6\u01c7\7\27\2\2\u01c7\u01c8\5D#\2\u01c8\u01c9"+
		"\7\30\2\2\u01c9C\3\2\2\2\u01ca\u01cb\5J&\2\u01cb\u01d4\5F$\2\u01cc\u01d0"+
		"\7\31\2\2\u01cd\u01ce\5J&\2\u01ce\u01cf\5F$\2\u01cf\u01d1\3\2\2\2\u01d0"+
		"\u01cd\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01d3\3\2\2\2\u01d2\u01cc\3\2"+
		"\2\2\u01d3\u01d6\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5"+
		"E\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d7\u01dc\5H%\2\u01d8\u01d9\7\25\2\2\u01d9"+
		"\u01db\5H%\2\u01da\u01d8\3\2\2\2\u01db\u01de\3\2\2\2\u01dc\u01da\3\2\2"+
		"\2\u01dc\u01dd\3\2\2\2\u01ddG\3\2\2\2\u01de\u01dc\3\2\2\2\u01df\u01e0"+
		"\5N(\2\u01e0I\3\2\2\2\u01e1\u01e4\5L\'\2\u01e2\u01e4\7\32\2\2\u01e3\u01e1"+
		"\3\2\2\2\u01e3\u01e2\3\2\2\2\u01e4K\3\2\2\2\u01e5\u01e9\7N\2\2\u01e6\u01e9"+
		"\5\60\31\2\u01e7\u01e9\5^\60\2\u01e8\u01e5\3\2\2\2\u01e8\u01e6\3\2\2\2"+
		"\u01e8\u01e7\3\2\2\2\u01e9M\3\2\2\2\u01ea\u01ed\5P)\2\u01eb\u01ed\5> "+
		"\2\u01ec\u01ea\3\2\2\2\u01ec\u01eb\3\2\2\2\u01edO\3\2\2\2\u01ee\u01f1"+
		"\7N\2\2\u01ef\u01f1\5R*\2\u01f0\u01ee\3\2\2\2\u01f0\u01ef\3\2\2\2\u01f1"+
		"Q\3\2\2\2\u01f2\u01fa\5\60\31\2\u01f3\u01fa\5^\60\2\u01f4\u01fa\5T+\2"+
		"\u01f5\u01fa\5l\67\2\u01f6\u01fa\5V,\2\u01f7\u01fa\5X-\2\u01f8\u01fa\7"+
		"O\2\2\u01f9\u01f2\3\2\2\2\u01f9\u01f3\3\2\2\2\u01f9\u01f4\3\2\2\2\u01f9"+
		"\u01f5\3\2\2\2\u01f9\u01f6\3\2\2\2\u01f9\u01f7\3\2\2\2\u01f9\u01f8\3\2"+
		"\2\2\u01faS\3\2\2\2\u01fb\u01ff\5Z.\2\u01fc\u0200\7Y\2\2\u01fd\u01fe\7"+
		"\33\2\2\u01fe\u0200\5\60\31\2\u01ff\u01fc\3\2\2\2\u01ff\u01fd\3\2\2\2"+
		"\u01ff\u0200\3\2\2\2\u0200U\3\2\2\2\u0201\u0202\t\3\2\2\u0202W\3\2\2\2"+
		"\u0203\u0204\t\4\2\2\u0204Y\3\2\2\2\u0205\u0206\t\5\2\2\u0206[\3\2\2\2"+
		"\u0207\u020c\5\f\7\2\u0208\u0209\7;\2\2\u0209\u020b\5\f\7\2\u020a\u0208"+
		"\3\2\2\2\u020b\u020e\3\2\2\2\u020c\u020a\3\2\2\2\u020c\u020d\3\2\2\2\u020d"+
		"]\3\2\2\2\u020e\u020c\3\2\2\2\u020f\u0213\7P\2\2\u0210\u0213\7V\2\2\u0211"+
		"\u0213\5Z.\2\u0212\u020f\3\2\2\2\u0212\u0210\3\2\2\2\u0212\u0211\3\2\2"+
		"\2\u0213_\3\2\2\2\u0214\u0216\5d\63\2\u0215\u0214\3\2\2\2\u0215\u0216"+
		"\3\2\2\2\u0216\u0218\3\2\2\2\u0217\u0219\5b\62\2\u0218\u0217\3\2\2\2\u0218"+
		"\u0219\3\2\2\2\u0219a\3\2\2\2\u021a\u021c\5h\65\2\u021b\u021d\5j\66\2"+
		"\u021c\u021b\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u0223\3\2\2\2\u021e\u0220"+
		"\5j\66\2\u021f\u0221\5h\65\2\u0220\u021f\3\2\2\2\u0220\u0221\3\2\2\2\u0221"+
		"\u0223\3\2\2\2\u0222\u021a\3\2\2\2\u0222\u021e\3\2\2\2\u0223c\3\2\2\2"+
		"\u0224\u0225\7@\2\2\u0225\u0227\7A\2\2\u0226\u0228\5f\64\2\u0227\u0226"+
		"\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u0227\3\2\2\2\u0229\u022a\3\2\2\2\u022a"+
		"e\3\2\2\2\u022b\u022c\t\6\2\2\u022c\u0232\5\24\13\2\u022d\u0230\5\20\t"+
		"\2\u022e\u0230\7N\2\2\u022f\u022d\3\2\2\2\u022f\u022e\3\2\2\2\u0230\u0232"+
		"\3\2\2\2\u0231\u022b\3\2\2\2\u0231\u022f\3\2\2\2\u0232g\3\2\2\2\u0233"+
		"\u0234\7>\2\2\u0234\u0235\7B\2\2\u0235i\3\2\2\2\u0236\u0237\7?\2\2\u0237"+
		"\u0238\7B\2\2\u0238k\3\2\2\2\u0239\u023d\5n8\2\u023a\u023d\5p9\2\u023b"+
		"\u023d\5r:\2\u023c\u0239\3\2\2\2\u023c\u023a\3\2\2\2\u023c\u023b\3\2\2"+
		"\2\u023dm\3\2\2\2\u023e\u023f\t\7\2\2\u023fo\3\2\2\2\u0240\u0241\t\b\2"+
		"\2\u0241q\3\2\2\2\u0242\u0243\t\t\2\2\u0243s\3\2\2\2\66}\u0086\u008b\u008f"+
		"\u0096\u009a\u009d\u00a1\u00af\u00bf\u00c7\u00d7\u00e2\u00e4\u00ec\u00ee"+
		"\u00f8\u0101\u0172\u017b\u0181\u0186\u018d\u0192\u0196\u019d\u019f\u01a6"+
		"\u01a8\u01ae\u01bc\u01c2\u01d0\u01d4\u01dc\u01e3\u01e8\u01ec\u01f0\u01f9"+
		"\u01ff\u020c\u0212\u0215\u0218\u021c\u0220\u0222\u0229\u022f\u0231\u023c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}