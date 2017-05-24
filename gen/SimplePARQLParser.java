// Generated from C:/Users/Abdel/Desktop/Stage_A4/Codes/Parser/SimplePARQL\SimplePARQL.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

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
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, INTEGER=63, DECIMAL=64, DOUBLE=65, INTEGER_POSITIVE=66, 
		DECIMAL_POSITIVE=67, DOUBLE_POSITIVE=68, INTEGER_NEGATIVE=69, DECIMAL_NEGATIVE=70, 
		DOUBLE_NEGATIVE=71, EXPONENT=72, DIGIT=73, URI=74, VAR=75, NIL=76, TRUC_WORD=77, 
		VARNAME=78, PNAME_LN=79, PN_LOCAL=80, STRING_LITERAL1=81, STRING_LITERAL2=82, 
		TRUC_DIESE=83, ECHAR=84, ANON=85, LANGTAG=86, BLANK_NODE_LABEL=87, PNAME_NS=88, 
		PN_PREFIX=89, PN_CHARS_UNDERSCORE=90, HTTP=91, WS=92;
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
		null, "'PREFIX'", "'SELECT'", "'DISTINCT'", "'*'", "'WHERE'", "'{'", "'.'", 
		"'}'", "'FILTER'", "'('", "')'", "'||'", "'&&'", "'='", "'!='", "'<'", 
		"'>'", "'<='", "'>='", "'+'", "'-'", "'/'", "'!'", "'STR'", "'LANG'", 
		"'LANGMATCHES'", "','", "'DATATYPE'", "'CONTAINS'", "'UCASE'", "'LCASE'", 
		"'BOUND'", "'sameTerm'", "'isIRI'", "'isURI'", "'isBLANK'", "'isLITERAL'", 
		"'YEAR'", "'MONTH'", "'DAY'", "'HOURS'", "'MINUTES'", "'SECONDS'", "'TIMEZONE'", 
		"'TZ'", "'REGEX'", "'BIND'", "'AS'", "'OPTIONAL'", "'['", "']'", "';'", 
		"'a'", "'^^'", "'true'", "'false'", "'UNION'", "'ORDER BY'", "'ASC'", 
		"'DESC'", "'LIMIT'", "'OFFSET'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "INTEGER", "DECIMAL", "DOUBLE", "INTEGER_POSITIVE", 
		"DECIMAL_POSITIVE", "DOUBLE_POSITIVE", "INTEGER_NEGATIVE", "DECIMAL_NEGATIVE", 
		"DOUBLE_NEGATIVE", "EXPONENT", "DIGIT", "URI", "VAR", "NIL", "TRUC_WORD", 
		"VARNAME", "PNAME_LN", "PN_LOCAL", "STRING_LITERAL1", "STRING_LITERAL2", 
		"TRUC_DIESE", "ECHAR", "ANON", "LANGTAG", "BLANK_NODE_LABEL", "PNAME_NS", 
		"PN_PREFIX", "PN_CHARS_UNDERSCORE", "HTTP", "WS"
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitPrologue(this);
			else return visitor.visitChildren(this);
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
			while (_la==T__0) {
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitPrefixDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixDeclContext prefixDecl() throws RecognitionException {
		PrefixDeclContext _localctx = new PrefixDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_prefixDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__0);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitSelectQuery(this);
			else return visitor.visitChildren(this);
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
			match(T__1);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(131);
				match(T__2);
				}
			}

			setState(140);
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
			case T__3:
				{
				setState(139);
				match(T__3);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitWhereClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(T__4);
			setState(143);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitGroupGraphPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupGraphPatternContext groupGraphPattern() throws RecognitionException {
		GroupGraphPatternContext _localctx = new GroupGraphPatternContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_groupGraphPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(T__5);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__49) | (1L << T__54) | (1L << T__55) | (1L << INTEGER))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (URI - 64)) | (1L << (VAR - 64)) | (1L << (NIL - 64)) | (1L << (TRUC_WORD - 64)) | (1L << (PNAME_LN - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (TRUC_DIESE - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (PNAME_NS - 64)))) != 0)) {
				{
				setState(146);
				triplesBlock();
				}
			}

			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__8) | (1L << T__46) | (1L << T__48))) != 0)) {
				{
				{
				{
				setState(149);
				graphPatternNotTriples();
				}
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__6) {
					{
					setState(150);
					match(T__6);
					}
				}

				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__49) | (1L << T__54) | (1L << T__55) | (1L << INTEGER))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (URI - 64)) | (1L << (VAR - 64)) | (1L << (NIL - 64)) | (1L << (TRUC_WORD - 64)) | (1L << (PNAME_LN - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (TRUC_DIESE - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (PNAME_NS - 64)))) != 0)) {
					{
					setState(153);
					triplesBlock();
					}
				}

				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(161);
			match(T__7);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(T__8);
			setState(164);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constraint);
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				match(T__9);
				setState(167);
				expression();
				setState(168);
				match(T__10);
				}
				break;
			case T__23:
			case T__24:
			case T__25:
			case T__27:
			case T__28:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				builtInCall();
				}
				break;
			case URI:
			case PNAME_LN:
			case PNAME_NS:
				enterOuterAlt(_localctx, 3);
				{
				setState(171);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			iriRef();
			setState(175);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitBrackettedExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BrackettedExpressionContext brackettedExpression() throws RecognitionException {
		BrackettedExpressionContext _localctx = new BrackettedExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_brackettedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__9);
			setState(178);
			expression();
			setState(179);
			match(T__10);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitConditionalOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalOrExpressionContext conditionalOrExpression() throws RecognitionException {
		ConditionalOrExpressionContext _localctx = new ConditionalOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_conditionalOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			conditionalAndExpression();
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(184);
				match(T__11);
				setState(185);
				conditionalAndExpression();
				}
				}
				setState(190);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitConditionalAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalAndExpressionContext conditionalAndExpression() throws RecognitionException {
		ConditionalAndExpressionContext _localctx = new ConditionalAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_conditionalAndExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			valueLogical();
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(192);
				match(T__12);
				setState(193);
				valueLogical();
				}
				}
				setState(198);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitValueLogical(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueLogicalContext valueLogical() throws RecognitionException {
		ValueLogicalContext _localctx = new ValueLogicalContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_valueLogical);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			numericExpression();
			setState(212);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				{
				setState(200);
				match(T__13);
				setState(201);
				numericExpression();
				}
				break;
			case T__14:
				{
				setState(202);
				match(T__14);
				setState(203);
				numericExpression();
				}
				break;
			case T__15:
				{
				setState(204);
				match(T__15);
				setState(205);
				numericExpression();
				}
				break;
			case T__16:
				{
				setState(206);
				match(T__16);
				setState(207);
				numericExpression();
				}
				break;
			case T__17:
				{
				setState(208);
				match(T__17);
				setState(209);
				numericExpression();
				}
				break;
			case T__18:
				{
				setState(210);
				match(T__18);
				setState(211);
				numericExpression();
				}
				break;
			case T__10:
			case T__11:
			case T__12:
			case T__26:
			case T__47:
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitNumericExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericExpressionContext numericExpression() throws RecognitionException {
		NumericExpressionContext _localctx = new NumericExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_numericExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			multiplicativeExpression();
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (T__19 - 20)) | (1L << (T__20 - 20)) | (1L << (INTEGER_POSITIVE - 20)) | (1L << (DECIMAL_POSITIVE - 20)) | (1L << (DOUBLE_POSITIVE - 20)) | (1L << (INTEGER_NEGATIVE - 20)) | (1L << (DECIMAL_NEGATIVE - 20)) | (1L << (DOUBLE_NEGATIVE - 20)))) != 0)) {
				{
				setState(223);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__19:
					{
					setState(217);
					match(T__19);
					setState(218);
					multiplicativeExpression();
					}
					break;
				case T__20:
					{
					setState(219);
					match(T__20);
					setState(220);
					multiplicativeExpression();
					}
					break;
				case INTEGER_POSITIVE:
				case DECIMAL_POSITIVE:
				case DOUBLE_POSITIVE:
					{
					setState(221);
					numericLiteralPositive();
					}
					break;
				case INTEGER_NEGATIVE:
				case DECIMAL_NEGATIVE:
				case DOUBLE_NEGATIVE:
					{
					setState(222);
					numericLiteralNegative();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(227);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			unaryExpression();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==T__21) {
				{
				setState(233);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__3:
					{
					setState(229);
					match(T__3);
					setState(230);
					unaryExpression();
					}
					break;
				case T__21:
					{
					setState(231);
					match(T__21);
					setState(232);
					unaryExpression();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(237);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_unaryExpression);
		try {
			setState(245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
			case T__23:
			case T__24:
			case T__25:
			case T__27:
			case T__28:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__54:
			case T__55:
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
				setState(238);
				primaryExpression();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(T__22);
				setState(240);
				primaryExpression();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 3);
				{
				setState(241);
				match(T__19);
				setState(242);
				primaryExpression();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 4);
				{
				setState(243);
				match(T__20);
				setState(244);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitPrimaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_primaryExpression);
		try {
			setState(254);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
				brackettedExpression();
				}
				break;
			case T__23:
			case T__24:
			case T__25:
			case T__27:
			case T__28:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
				enterOuterAlt(_localctx, 2);
				{
				setState(248);
				builtInCall();
				}
				break;
			case URI:
			case PNAME_LN:
			case PNAME_NS:
				enterOuterAlt(_localctx, 3);
				{
				setState(249);
				iriRefOrFunction();
				}
				break;
			case STRING_LITERAL1:
			case STRING_LITERAL2:
				enterOuterAlt(_localctx, 4);
				{
				setState(250);
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
				setState(251);
				numericLiteral();
				}
				break;
			case T__54:
			case T__55:
				enterOuterAlt(_localctx, 6);
				{
				setState(252);
				booleanLiteral();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 7);
				{
				setState(253);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode VAR() { return getToken(SimplePARQLParser.VAR, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitBuiltInCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuiltInCallContext builtInCall() throws RecognitionException {
		BuiltInCallContext _localctx = new BuiltInCallContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_builtInCall);
		try {
			setState(367);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				match(T__23);
				setState(257);
				match(T__9);
				setState(258);
				expression();
				setState(259);
				match(T__10);
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 2);
				{
				setState(261);
				match(T__24);
				setState(262);
				match(T__9);
				setState(263);
				expression();
				setState(264);
				match(T__10);
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(266);
				match(T__25);
				setState(267);
				match(T__9);
				setState(268);
				expression();
				setState(269);
				match(T__26);
				setState(270);
				expression();
				setState(271);
				match(T__10);
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 4);
				{
				setState(273);
				match(T__27);
				setState(274);
				match(T__9);
				setState(275);
				expression();
				setState(276);
				match(T__10);
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 5);
				{
				setState(278);
				match(T__28);
				setState(279);
				match(T__9);
				setState(280);
				expression();
				setState(281);
				match(T__26);
				setState(282);
				expression();
				setState(283);
				match(T__10);
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 6);
				{
				setState(285);
				match(T__29);
				setState(286);
				match(T__9);
				setState(287);
				expression();
				setState(288);
				match(T__10);
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 7);
				{
				setState(290);
				match(T__30);
				setState(291);
				match(T__9);
				setState(292);
				expression();
				setState(293);
				match(T__10);
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 8);
				{
				setState(295);
				match(T__31);
				setState(296);
				match(T__9);
				setState(297);
				match(VAR);
				setState(298);
				match(T__10);
				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 9);
				{
				setState(299);
				match(T__32);
				setState(300);
				match(T__9);
				setState(301);
				expression();
				setState(302);
				match(T__26);
				setState(303);
				expression();
				setState(304);
				match(T__10);
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 10);
				{
				setState(306);
				match(T__33);
				setState(307);
				match(T__9);
				setState(308);
				expression();
				setState(309);
				match(T__10);
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 11);
				{
				setState(311);
				match(T__34);
				setState(312);
				match(T__9);
				setState(313);
				expression();
				setState(314);
				match(T__10);
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 12);
				{
				setState(316);
				match(T__35);
				setState(317);
				match(T__9);
				setState(318);
				expression();
				setState(319);
				match(T__10);
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 13);
				{
				setState(321);
				match(T__36);
				setState(322);
				match(T__9);
				setState(323);
				expression();
				setState(324);
				match(T__10);
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 14);
				{
				setState(326);
				match(T__37);
				setState(327);
				match(T__9);
				setState(328);
				expression();
				setState(329);
				match(T__10);
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 15);
				{
				setState(331);
				match(T__38);
				setState(332);
				match(T__9);
				setState(333);
				expression();
				setState(334);
				match(T__10);
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 16);
				{
				setState(336);
				match(T__39);
				setState(337);
				match(T__9);
				setState(338);
				expression();
				setState(339);
				match(T__10);
				}
				break;
			case T__40:
				enterOuterAlt(_localctx, 17);
				{
				setState(341);
				match(T__40);
				setState(342);
				match(T__9);
				setState(343);
				expression();
				setState(344);
				match(T__10);
				}
				break;
			case T__41:
				enterOuterAlt(_localctx, 18);
				{
				setState(346);
				match(T__41);
				setState(347);
				match(T__9);
				setState(348);
				expression();
				setState(349);
				match(T__10);
				}
				break;
			case T__42:
				enterOuterAlt(_localctx, 19);
				{
				setState(351);
				match(T__42);
				setState(352);
				match(T__9);
				setState(353);
				expression();
				setState(354);
				match(T__10);
				}
				break;
			case T__43:
				enterOuterAlt(_localctx, 20);
				{
				setState(356);
				match(T__43);
				setState(357);
				match(T__9);
				setState(358);
				expression();
				setState(359);
				match(T__10);
				}
				break;
			case T__44:
				enterOuterAlt(_localctx, 21);
				{
				setState(361);
				match(T__44);
				setState(362);
				match(T__9);
				setState(363);
				expression();
				setState(364);
				match(T__10);
				}
				break;
			case T__45:
				enterOuterAlt(_localctx, 22);
				{
				setState(366);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitRegexExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegexExpressionContext regexExpression() throws RecognitionException {
		RegexExpressionContext _localctx = new RegexExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_regexExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			match(T__45);
			setState(370);
			match(T__9);
			setState(371);
			expression();
			setState(372);
			match(T__26);
			setState(373);
			expression();
			setState(376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__26) {
				{
				setState(374);
				match(T__26);
				setState(375);
				expression();
				}
			}

			setState(378);
			match(T__10);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitIriRefOrFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IriRefOrFunctionContext iriRefOrFunction() throws RecognitionException {
		IriRefOrFunctionContext _localctx = new IriRefOrFunctionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_iriRefOrFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			iriRef();
			setState(382);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9 || _la==NIL) {
				{
				setState(381);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NIL:
				{
				setState(384);
				match(NIL);
				}
				break;
			case T__9:
				{
				setState(385);
				match(T__9);
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(386);
					match(T__2);
					}
				}

				setState(389);
				expression();
				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__26) {
					{
					{
					setState(390);
					match(T__26);
					setState(391);
					expression();
					}
					}
					setState(396);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(397);
				match(T__10);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitIriRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IriRefContext iriRef() throws RecognitionException {
		IriRefContext _localctx = new IriRefContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_iriRef);
		try {
			setState(403);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case URI:
				enterOuterAlt(_localctx, 1);
				{
				setState(401);
				match(URI);
				}
				break;
			case PNAME_LN:
			case PNAME_NS:
				enterOuterAlt(_localctx, 2);
				{
				setState(402);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitPrefixedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixedNameContext prefixedName() throws RecognitionException {
		PrefixedNameContext _localctx = new PrefixedNameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_prefixedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitTriplesBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TriplesBlockContext triplesBlock() throws RecognitionException {
		TriplesBlockContext _localctx = new TriplesBlockContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_triplesBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			triplesSameSubject();
			setState(412);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(408);
				match(T__6);
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__49) | (1L << T__54) | (1L << T__55) | (1L << INTEGER))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (URI - 64)) | (1L << (VAR - 64)) | (1L << (NIL - 64)) | (1L << (TRUC_WORD - 64)) | (1L << (PNAME_LN - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (TRUC_DIESE - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (PNAME_NS - 64)))) != 0)) {
					{
					setState(409);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitTriplesSameSubject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TriplesSameSubjectContext triplesSameSubject() throws RecognitionException {
		TriplesSameSubjectContext _localctx = new TriplesSameSubjectContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_triplesSameSubject);
		int _la;
		try {
			setState(421);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__54:
			case T__55:
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
				setState(414);
				varOrTerm();
				setState(415);
				propertyListNotEmpty();
				}
				break;
			case T__9:
			case T__49:
				enterOuterAlt(_localctx, 2);
				{
				setState(417);
				triplesNode();
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 53)) & ~0x3f) == 0 && ((1L << (_la - 53)) & ((1L << (T__52 - 53)) | (1L << (URI - 53)) | (1L << (VAR - 53)) | (1L << (TRUC_WORD - 53)) | (1L << (PNAME_LN - 53)) | (1L << (STRING_LITERAL1 - 53)) | (1L << (STRING_LITERAL2 - 53)) | (1L << (TRUC_DIESE - 53)) | (1L << (PNAME_NS - 53)))) != 0)) {
					{
					setState(418);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitGraphPatternNotTriples(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GraphPatternNotTriplesContext graphPatternNotTriples() throws RecognitionException {
		GraphPatternNotTriplesContext _localctx = new GraphPatternNotTriplesContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_graphPatternNotTriples);
		try {
			setState(427);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__48:
				enterOuterAlt(_localctx, 1);
				{
				setState(423);
				optionalGraphPattern();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(424);
				groupOrUnionGraphPattern();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(425);
				filter();
				}
				break;
			case T__46:
				enterOuterAlt(_localctx, 4);
				{
				setState(426);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitBind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BindContext bind() throws RecognitionException {
		BindContext _localctx = new BindContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_bind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			match(T__46);
			setState(430);
			match(T__9);
			setState(431);
			expression();
			setState(432);
			match(T__47);
			setState(433);
			match(VAR);
			setState(434);
			match(T__10);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitOptionalGraphPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionalGraphPatternContext optionalGraphPattern() throws RecognitionException {
		OptionalGraphPatternContext _localctx = new OptionalGraphPatternContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_optionalGraphPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			match(T__48);
			setState(437);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitTriplesNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TriplesNodeContext triplesNode() throws RecognitionException {
		TriplesNodeContext _localctx = new TriplesNodeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_triplesNode);
		try {
			setState(441);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(439);
				collection();
				}
				break;
			case T__49:
				enterOuterAlt(_localctx, 2);
				{
				setState(440);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitCollection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CollectionContext collection() throws RecognitionException {
		CollectionContext _localctx = new CollectionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_collection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443);
			match(T__9);
			setState(445); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(444);
				graphNode();
				}
				}
				setState(447); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__49) | (1L << T__54) | (1L << T__55) | (1L << INTEGER))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (URI - 64)) | (1L << (VAR - 64)) | (1L << (NIL - 64)) | (1L << (TRUC_WORD - 64)) | (1L << (PNAME_LN - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (TRUC_DIESE - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (PNAME_NS - 64)))) != 0) );
			setState(449);
			match(T__10);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitBlankNodePropertyList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlankNodePropertyListContext blankNodePropertyList() throws RecognitionException {
		BlankNodePropertyListContext _localctx = new BlankNodePropertyListContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_blankNodePropertyList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			match(T__49);
			setState(452);
			propertyListNotEmpty();
			setState(453);
			match(T__50);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitPropertyListNotEmpty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyListNotEmptyContext propertyListNotEmpty() throws RecognitionException {
		PropertyListNotEmptyContext _localctx = new PropertyListNotEmptyContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_propertyListNotEmpty);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			verb();
			setState(456);
			objectList();
			setState(465);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__51) {
				{
				{
				setState(457);
				match(T__51);
				setState(461);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 53)) & ~0x3f) == 0 && ((1L << (_la - 53)) & ((1L << (T__52 - 53)) | (1L << (URI - 53)) | (1L << (VAR - 53)) | (1L << (TRUC_WORD - 53)) | (1L << (PNAME_LN - 53)) | (1L << (STRING_LITERAL1 - 53)) | (1L << (STRING_LITERAL2 - 53)) | (1L << (TRUC_DIESE - 53)) | (1L << (PNAME_NS - 53)))) != 0)) {
					{
					setState(458);
					verb();
					setState(459);
					objectList();
					}
				}

				}
				}
				setState(467);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitObjectList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectListContext objectList() throws RecognitionException {
		ObjectListContext _localctx = new ObjectListContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_objectList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(468);
			object();
			setState(473);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__26) {
				{
				{
				setState(469);
				match(T__26);
				setState(470);
				object();
				}
				}
				setState(475);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(476);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitVerb(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VerbContext verb() throws RecognitionException {
		VerbContext _localctx = new VerbContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_verb);
		try {
			setState(480);
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
				setState(478);
				varOrIRIreforTruc();
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 2);
				{
				setState(479);
				match(T__52);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitVarOrIRIreforTruc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarOrIRIreforTrucContext varOrIRIreforTruc() throws RecognitionException {
		VarOrIRIreforTrucContext _localctx = new VarOrIRIreforTrucContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_varOrIRIreforTruc);
		try {
			setState(485);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(482);
				match(VAR);
				}
				break;
			case URI:
			case PNAME_LN:
			case PNAME_NS:
				enterOuterAlt(_localctx, 2);
				{
				setState(483);
				iriRef();
				}
				break;
			case TRUC_WORD:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case TRUC_DIESE:
				enterOuterAlt(_localctx, 3);
				{
				setState(484);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitGraphNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GraphNodeContext graphNode() throws RecognitionException {
		GraphNodeContext _localctx = new GraphNodeContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_graphNode);
		try {
			setState(489);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__54:
			case T__55:
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
				setState(487);
				varOrTerm();
				}
				break;
			case T__9:
			case T__49:
				enterOuterAlt(_localctx, 2);
				{
				setState(488);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitVarOrTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarOrTermContext varOrTerm() throws RecognitionException {
		VarOrTermContext _localctx = new VarOrTermContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_varOrTerm);
		try {
			setState(493);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(491);
				match(VAR);
				}
				break;
			case T__54:
			case T__55:
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
				setState(492);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitGraphTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GraphTermContext graphTerm() throws RecognitionException {
		GraphTermContext _localctx = new GraphTermContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_graphTerm);
		try {
			setState(502);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(495);
				iriRef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(496);
				truc();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(497);
				rdfLiteral();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(498);
				numericLiteral();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(499);
				booleanLiteral();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(500);
				blankNode();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(501);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitRdfLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RdfLiteralContext rdfLiteral() throws RecognitionException {
		RdfLiteralContext _localctx = new RdfLiteralContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_rdfLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504);
			string();
			setState(508);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LANGTAG:
				{
				setState(505);
				match(LANGTAG);
				}
				break;
			case T__53:
				{
				{
				setState(506);
				match(T__53);
				setState(507);
				iriRef();
				}
				}
				break;
			case T__3:
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
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__26:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__52:
			case T__54:
			case T__55:
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitBooleanLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
		BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
			_la = _input.LA(1);
			if ( !(_la==T__54 || _la==T__55) ) {
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitBlankNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlankNodeContext blankNode() throws RecognitionException {
		BlankNodeContext _localctx = new BlankNodeContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_blankNode);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(512);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitGroupOrUnionGraphPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupOrUnionGraphPatternContext groupOrUnionGraphPattern() throws RecognitionException {
		GroupOrUnionGraphPatternContext _localctx = new GroupOrUnionGraphPatternContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_groupOrUnionGraphPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(516);
			groupGraphPattern();
			setState(521);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__56) {
				{
				{
				setState(517);
				match(T__56);
				setState(518);
				groupGraphPattern();
				}
				}
				setState(523);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitTruc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrucContext truc() throws RecognitionException {
		TrucContext _localctx = new TrucContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_truc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(527);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUC_WORD:
				{
				setState(524);
				match(TRUC_WORD);
				}
				break;
			case TRUC_DIESE:
				{
				setState(525);
				match(TRUC_DIESE);
				}
				break;
			case STRING_LITERAL1:
			case STRING_LITERAL2:
				{
				setState(526);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitSolutionModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SolutionModifierContext solutionModifier() throws RecognitionException {
		SolutionModifierContext _localctx = new SolutionModifierContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_solutionModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__57) {
				{
				setState(529);
				orderClause();
				}
			}

			setState(533);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__60 || _la==T__61) {
				{
				setState(532);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitLimitOffsetClauses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitOffsetClausesContext limitOffsetClauses() throws RecognitionException {
		LimitOffsetClausesContext _localctx = new LimitOffsetClausesContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_limitOffsetClauses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__60:
				{
				setState(535);
				limitClause();
				setState(537);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__61) {
					{
					setState(536);
					offsetClause();
					}
				}

				}
				break;
			case T__61:
				{
				setState(539);
				offsetClause();
				setState(541);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__60) {
					{
					setState(540);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitOrderClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderClauseContext orderClause() throws RecognitionException {
		OrderClauseContext _localctx = new OrderClauseContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_orderClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(545);
			match(T__57);
			setState(547); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(546);
				orderCondition();
				}
				}
				setState(549); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__58) | (1L << T__59))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (URI - 74)) | (1L << (VAR - 74)) | (1L << (PNAME_LN - 74)) | (1L << (PNAME_NS - 74)))) != 0) );
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitOrderCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderConditionContext orderCondition() throws RecognitionException {
		OrderConditionContext _localctx = new OrderConditionContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_orderCondition);
		int _la;
		try {
			setState(557);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__58:
			case T__59:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(551);
				_la = _input.LA(1);
				if ( !(_la==T__58 || _la==T__59) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(552);
				brackettedExpression();
				}
				}
				break;
			case T__9:
			case T__23:
			case T__24:
			case T__25:
			case T__27:
			case T__28:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case URI:
			case VAR:
			case PNAME_LN:
			case PNAME_NS:
				enterOuterAlt(_localctx, 2);
				{
				setState(555);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__9:
				case T__23:
				case T__24:
				case T__25:
				case T__27:
				case T__28:
				case T__29:
				case T__30:
				case T__31:
				case T__32:
				case T__33:
				case T__34:
				case T__35:
				case T__36:
				case T__37:
				case T__38:
				case T__39:
				case T__40:
				case T__41:
				case T__42:
				case T__43:
				case T__44:
				case T__45:
				case URI:
				case PNAME_LN:
				case PNAME_NS:
					{
					setState(553);
					constraint();
					}
					break;
				case VAR:
					{
					setState(554);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitLimitClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitClauseContext limitClause() throws RecognitionException {
		LimitClauseContext _localctx = new LimitClauseContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_limitClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(559);
			match(T__60);
			setState(560);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitOffsetClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OffsetClauseContext offsetClause() throws RecognitionException {
		OffsetClauseContext _localctx = new OffsetClauseContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_offsetClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562);
			match(T__61);
			setState(563);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitNumericLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_numericLiteral);
		try {
			setState(568);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
			case DECIMAL:
			case DOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(565);
				numericLiteralUnsigned();
				}
				break;
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(566);
				numericLiteralPositive();
				}
				break;
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(567);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitNumericLiteralUnsigned(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericLiteralUnsignedContext numericLiteralUnsigned() throws RecognitionException {
		NumericLiteralUnsignedContext _localctx = new NumericLiteralUnsignedContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_numericLiteralUnsigned);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(570);
			_la = _input.LA(1);
			if ( !(((((_la - 63)) & ~0x3f) == 0 && ((1L << (_la - 63)) & ((1L << (INTEGER - 63)) | (1L << (DECIMAL - 63)) | (1L << (DOUBLE - 63)))) != 0)) ) {
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitNumericLiteralPositive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericLiteralPositiveContext numericLiteralPositive() throws RecognitionException {
		NumericLiteralPositiveContext _localctx = new NumericLiteralPositiveContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_numericLiteralPositive);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(572);
			_la = _input.LA(1);
			if ( !(((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (INTEGER_POSITIVE - 66)) | (1L << (DECIMAL_POSITIVE - 66)) | (1L << (DOUBLE_POSITIVE - 66)))) != 0)) ) {
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SimplePARQLVisitor ) return ((SimplePARQLVisitor<? extends T>)visitor).visitNumericLiteralNegative(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericLiteralNegativeContext numericLiteralNegative() throws RecognitionException {
		NumericLiteralNegativeContext _localctx = new NumericLiteralNegativeContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_numericLiteralNegative);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(574);
			_la = _input.LA(1);
			if ( !(((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (INTEGER_NEGATIVE - 69)) | (1L << (DECIMAL_NEGATIVE - 69)) | (1L << (DOUBLE_NEGATIVE - 69)))) != 0)) ) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3^\u0243\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\7\3|\n\3\f\3\16\3\177\13\3\3\4\3\4\3\4\3\4\3\5\3\5\5\5\u0087"+
		"\n\5\3\5\6\5\u008a\n\5\r\5\16\5\u008b\3\5\5\5\u008f\n\5\3\6\3\6\3\6\3"+
		"\7\3\7\5\7\u0096\n\7\3\7\3\7\5\7\u009a\n\7\3\7\5\7\u009d\n\7\7\7\u009f"+
		"\n\7\f\7\16\7\u00a2\13\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5"+
		"\t\u00af\n\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\7\r\u00bd"+
		"\n\r\f\r\16\r\u00c0\13\r\3\16\3\16\3\16\7\16\u00c5\n\16\f\16\16\16\u00c8"+
		"\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\5\17\u00d7\n\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00e2"+
		"\n\21\f\21\16\21\u00e5\13\21\3\22\3\22\3\22\3\22\3\22\7\22\u00ec\n\22"+
		"\f\22\16\22\u00ef\13\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00f8"+
		"\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0101\n\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u0172\n\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u017b\n\26\3\26\3\26\3\27\3\27\5\27"+
		"\u0181\n\27\3\30\3\30\3\30\5\30\u0186\n\30\3\30\3\30\3\30\7\30\u018b\n"+
		"\30\f\30\16\30\u018e\13\30\3\30\3\30\5\30\u0192\n\30\3\31\3\31\5\31\u0196"+
		"\n\31\3\32\3\32\3\33\3\33\3\33\5\33\u019d\n\33\5\33\u019f\n\33\3\34\3"+
		"\34\3\34\3\34\3\34\5\34\u01a6\n\34\5\34\u01a8\n\34\3\35\3\35\3\35\3\35"+
		"\5\35\u01ae\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 "+
		"\3 \5 \u01bc\n \3!\3!\6!\u01c0\n!\r!\16!\u01c1\3!\3!\3\"\3\"\3\"\3\"\3"+
		"#\3#\3#\3#\3#\3#\5#\u01d0\n#\7#\u01d2\n#\f#\16#\u01d5\13#\3$\3$\3$\7$"+
		"\u01da\n$\f$\16$\u01dd\13$\3%\3%\3&\3&\5&\u01e3\n&\3\'\3\'\3\'\5\'\u01e8"+
		"\n\'\3(\3(\5(\u01ec\n(\3)\3)\5)\u01f0\n)\3*\3*\3*\3*\3*\3*\3*\5*\u01f9"+
		"\n*\3+\3+\3+\3+\5+\u01ff\n+\3,\3,\3-\3-\3.\3.\3/\3/\3/\7/\u020a\n/\f/"+
		"\16/\u020d\13/\3\60\3\60\3\60\5\60\u0212\n\60\3\61\5\61\u0215\n\61\3\61"+
		"\5\61\u0218\n\61\3\62\3\62\5\62\u021c\n\62\3\62\3\62\5\62\u0220\n\62\5"+
		"\62\u0222\n\62\3\63\3\63\6\63\u0226\n\63\r\63\16\63\u0227\3\64\3\64\3"+
		"\64\3\64\5\64\u022e\n\64\5\64\u0230\n\64\3\65\3\65\3\65\3\66\3\66\3\66"+
		"\3\67\3\67\3\67\5\67\u023b\n\67\38\38\39\39\3:\3:\3:\2\2;\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^"+
		"`bdfhjlnpr\2\n\4\2QQZZ\3\29:\4\2WWYY\3\2ST\3\2=>\3\2AC\3\2DF\3\2GI\2\u026b"+
		"\2t\3\2\2\2\4}\3\2\2\2\6\u0080\3\2\2\2\b\u0084\3\2\2\2\n\u0090\3\2\2\2"+
		"\f\u0093\3\2\2\2\16\u00a5\3\2\2\2\20\u00ae\3\2\2\2\22\u00b0\3\2\2\2\24"+
		"\u00b3\3\2\2\2\26\u00b7\3\2\2\2\30\u00b9\3\2\2\2\32\u00c1\3\2\2\2\34\u00c9"+
		"\3\2\2\2\36\u00d8\3\2\2\2 \u00da\3\2\2\2\"\u00e6\3\2\2\2$\u00f7\3\2\2"+
		"\2&\u0100\3\2\2\2(\u0171\3\2\2\2*\u0173\3\2\2\2,\u017e\3\2\2\2.\u0191"+
		"\3\2\2\2\60\u0195\3\2\2\2\62\u0197\3\2\2\2\64\u0199\3\2\2\2\66\u01a7\3"+
		"\2\2\28\u01ad\3\2\2\2:\u01af\3\2\2\2<\u01b6\3\2\2\2>\u01bb\3\2\2\2@\u01bd"+
		"\3\2\2\2B\u01c5\3\2\2\2D\u01c9\3\2\2\2F\u01d6\3\2\2\2H\u01de\3\2\2\2J"+
		"\u01e2\3\2\2\2L\u01e7\3\2\2\2N\u01eb\3\2\2\2P\u01ef\3\2\2\2R\u01f8\3\2"+
		"\2\2T\u01fa\3\2\2\2V\u0200\3\2\2\2X\u0202\3\2\2\2Z\u0204\3\2\2\2\\\u0206"+
		"\3\2\2\2^\u0211\3\2\2\2`\u0214\3\2\2\2b\u0221\3\2\2\2d\u0223\3\2\2\2f"+
		"\u022f\3\2\2\2h\u0231\3\2\2\2j\u0234\3\2\2\2l\u023a\3\2\2\2n\u023c\3\2"+
		"\2\2p\u023e\3\2\2\2r\u0240\3\2\2\2tu\5\4\3\2uv\5\b\5\2vw\5\n\6\2wx\5`"+
		"\61\2xy\7\2\2\3y\3\3\2\2\2z|\5\6\4\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2"+
		"}~\3\2\2\2~\5\3\2\2\2\177}\3\2\2\2\u0080\u0081\7\3\2\2\u0081\u0082\7Z"+
		"\2\2\u0082\u0083\7L\2\2\u0083\7\3\2\2\2\u0084\u0086\7\4\2\2\u0085\u0087"+
		"\7\5\2\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u008e\3\2\2\2\u0088"+
		"\u008a\7M\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008b\u008c\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008f\7\6\2\2\u008e"+
		"\u0089\3\2\2\2\u008e\u008d\3\2\2\2\u008f\t\3\2\2\2\u0090\u0091\7\7\2\2"+
		"\u0091\u0092\5\f\7\2\u0092\13\3\2\2\2\u0093\u0095\7\b\2\2\u0094\u0096"+
		"\5\64\33\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u00a0\3\2\2\2"+
		"\u0097\u0099\58\35\2\u0098\u009a\7\t\2\2\u0099\u0098\3\2\2\2\u0099\u009a"+
		"\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u009d\5\64\33\2\u009c\u009b\3\2\2\2"+
		"\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e\u0097\3\2\2\2\u009f\u00a2"+
		"\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a3\u00a4\7\n\2\2\u00a4\r\3\2\2\2\u00a5\u00a6\7\13\2"+
		"\2\u00a6\u00a7\5\20\t\2\u00a7\17\3\2\2\2\u00a8\u00a9\7\f\2\2\u00a9\u00aa"+
		"\5\26\f\2\u00aa\u00ab\7\r\2\2\u00ab\u00af\3\2\2\2\u00ac\u00af\5(\25\2"+
		"\u00ad\u00af\5\22\n\2\u00ae\u00a8\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00ad"+
		"\3\2\2\2\u00af\21\3\2\2\2\u00b0\u00b1\5\60\31\2\u00b1\u00b2\5.\30\2\u00b2"+
		"\23\3\2\2\2\u00b3\u00b4\7\f\2\2\u00b4\u00b5\5\26\f\2\u00b5\u00b6\7\r\2"+
		"\2\u00b6\25\3\2\2\2\u00b7\u00b8\5\30\r\2\u00b8\27\3\2\2\2\u00b9\u00be"+
		"\5\32\16\2\u00ba\u00bb\7\16\2\2\u00bb\u00bd\5\32\16\2\u00bc\u00ba\3\2"+
		"\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\31\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c6\5\34\17\2\u00c2\u00c3\7\17"+
		"\2\2\u00c3\u00c5\5\34\17\2\u00c4\u00c2\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6"+
		"\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\33\3\2\2\2\u00c8\u00c6\3\2\2"+
		"\2\u00c9\u00d6\5\36\20\2\u00ca\u00cb\7\20\2\2\u00cb\u00d7\5\36\20\2\u00cc"+
		"\u00cd\7\21\2\2\u00cd\u00d7\5\36\20\2\u00ce\u00cf\7\22\2\2\u00cf\u00d7"+
		"\5\36\20\2\u00d0\u00d1\7\23\2\2\u00d1\u00d7\5\36\20\2\u00d2\u00d3\7\24"+
		"\2\2\u00d3\u00d7\5\36\20\2\u00d4\u00d5\7\25\2\2\u00d5\u00d7\5\36\20\2"+
		"\u00d6\u00ca\3\2\2\2\u00d6\u00cc\3\2\2\2\u00d6\u00ce\3\2\2\2\u00d6\u00d0"+
		"\3\2\2\2\u00d6\u00d2\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\35\3\2\2\2\u00d8\u00d9\5 \21\2\u00d9\37\3\2\2\2\u00da\u00e3\5\"\22\2"+
		"\u00db\u00dc\7\26\2\2\u00dc\u00e2\5\"\22\2\u00dd\u00de\7\27\2\2\u00de"+
		"\u00e2\5\"\22\2\u00df\u00e2\5p9\2\u00e0\u00e2\5r:\2\u00e1\u00db\3\2\2"+
		"\2\u00e1\u00dd\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e5"+
		"\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4!\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e6\u00ed\5$\23\2\u00e7\u00e8\7\6\2\2\u00e8\u00ec\5$"+
		"\23\2\u00e9\u00ea\7\30\2\2\u00ea\u00ec\5$\23\2\u00eb\u00e7\3\2\2\2\u00eb"+
		"\u00e9\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2"+
		"\2\2\u00ee#\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f8\5&\24\2\u00f1\u00f2"+
		"\7\31\2\2\u00f2\u00f8\5&\24\2\u00f3\u00f4\7\26\2\2\u00f4\u00f8\5&\24\2"+
		"\u00f5\u00f6\7\27\2\2\u00f6\u00f8\5&\24\2\u00f7\u00f0\3\2\2\2\u00f7\u00f1"+
		"\3\2\2\2\u00f7\u00f3\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8%\3\2\2\2\u00f9"+
		"\u0101\5\24\13\2\u00fa\u0101\5(\25\2\u00fb\u0101\5,\27\2\u00fc\u0101\5"+
		"T+\2\u00fd\u0101\5l\67\2\u00fe\u0101\5V,\2\u00ff\u0101\7M\2\2\u0100\u00f9"+
		"\3\2\2\2\u0100\u00fa\3\2\2\2\u0100\u00fb\3\2\2\2\u0100\u00fc\3\2\2\2\u0100"+
		"\u00fd\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3\2\2\2\u0101\'\3\2\2\2"+
		"\u0102\u0103\7\32\2\2\u0103\u0104\7\f\2\2\u0104\u0105\5\26\f\2\u0105\u0106"+
		"\7\r\2\2\u0106\u0172\3\2\2\2\u0107\u0108\7\33\2\2\u0108\u0109\7\f\2\2"+
		"\u0109\u010a\5\26\f\2\u010a\u010b\7\r\2\2\u010b\u0172\3\2\2\2\u010c\u010d"+
		"\7\34\2\2\u010d\u010e\7\f\2\2\u010e\u010f\5\26\f\2\u010f\u0110\7\35\2"+
		"\2\u0110\u0111\5\26\f\2\u0111\u0112\7\r\2\2\u0112\u0172\3\2\2\2\u0113"+
		"\u0114\7\36\2\2\u0114\u0115\7\f\2\2\u0115\u0116\5\26\f\2\u0116\u0117\7"+
		"\r\2\2\u0117\u0172\3\2\2\2\u0118\u0119\7\37\2\2\u0119\u011a\7\f\2\2\u011a"+
		"\u011b\5\26\f\2\u011b\u011c\7\35\2\2\u011c\u011d\5\26\f\2\u011d\u011e"+
		"\7\r\2\2\u011e\u0172\3\2\2\2\u011f\u0120\7 \2\2\u0120\u0121\7\f\2\2\u0121"+
		"\u0122\5\26\f\2\u0122\u0123\7\r\2\2\u0123\u0172\3\2\2\2\u0124\u0125\7"+
		"!\2\2\u0125\u0126\7\f\2\2\u0126\u0127\5\26\f\2\u0127\u0128\7\r\2\2\u0128"+
		"\u0172\3\2\2\2\u0129\u012a\7\"\2\2\u012a\u012b\7\f\2\2\u012b\u012c\7M"+
		"\2\2\u012c\u0172\7\r\2\2\u012d\u012e\7#\2\2\u012e\u012f\7\f\2\2\u012f"+
		"\u0130\5\26\f\2\u0130\u0131\7\35\2\2\u0131\u0132\5\26\f\2\u0132\u0133"+
		"\7\r\2\2\u0133\u0172\3\2\2\2\u0134\u0135\7$\2\2\u0135\u0136\7\f\2\2\u0136"+
		"\u0137\5\26\f\2\u0137\u0138\7\r\2\2\u0138\u0172\3\2\2\2\u0139\u013a\7"+
		"%\2\2\u013a\u013b\7\f\2\2\u013b\u013c\5\26\f\2\u013c\u013d\7\r\2\2\u013d"+
		"\u0172\3\2\2\2\u013e\u013f\7&\2\2\u013f\u0140\7\f\2\2\u0140\u0141\5\26"+
		"\f\2\u0141\u0142\7\r\2\2\u0142\u0172\3\2\2\2\u0143\u0144\7\'\2\2\u0144"+
		"\u0145\7\f\2\2\u0145\u0146\5\26\f\2\u0146\u0147\7\r\2\2\u0147\u0172\3"+
		"\2\2\2\u0148\u0149\7(\2\2\u0149\u014a\7\f\2\2\u014a\u014b\5\26\f\2\u014b"+
		"\u014c\7\r\2\2\u014c\u0172\3\2\2\2\u014d\u014e\7)\2\2\u014e\u014f\7\f"+
		"\2\2\u014f\u0150\5\26\f\2\u0150\u0151\7\r\2\2\u0151\u0172\3\2\2\2\u0152"+
		"\u0153\7*\2\2\u0153\u0154\7\f\2\2\u0154\u0155\5\26\f\2\u0155\u0156\7\r"+
		"\2\2\u0156\u0172\3\2\2\2\u0157\u0158\7+\2\2\u0158\u0159\7\f\2\2\u0159"+
		"\u015a\5\26\f\2\u015a\u015b\7\r\2\2\u015b\u0172\3\2\2\2\u015c\u015d\7"+
		",\2\2\u015d\u015e\7\f\2\2\u015e\u015f\5\26\f\2\u015f\u0160\7\r\2\2\u0160"+
		"\u0172\3\2\2\2\u0161\u0162\7-\2\2\u0162\u0163\7\f\2\2\u0163\u0164\5\26"+
		"\f\2\u0164\u0165\7\r\2\2\u0165\u0172\3\2\2\2\u0166\u0167\7.\2\2\u0167"+
		"\u0168\7\f\2\2\u0168\u0169\5\26\f\2\u0169\u016a\7\r\2\2\u016a\u0172\3"+
		"\2\2\2\u016b\u016c\7/\2\2\u016c\u016d\7\f\2\2\u016d\u016e\5\26\f\2\u016e"+
		"\u016f\7\r\2\2\u016f\u0172\3\2\2\2\u0170\u0172\5*\26\2\u0171\u0102\3\2"+
		"\2\2\u0171\u0107\3\2\2\2\u0171\u010c\3\2\2\2\u0171\u0113\3\2\2\2\u0171"+
		"\u0118\3\2\2\2\u0171\u011f\3\2\2\2\u0171\u0124\3\2\2\2\u0171\u0129\3\2"+
		"\2\2\u0171\u012d\3\2\2\2\u0171\u0134\3\2\2\2\u0171\u0139\3\2\2\2\u0171"+
		"\u013e\3\2\2\2\u0171\u0143\3\2\2\2\u0171\u0148\3\2\2\2\u0171\u014d\3\2"+
		"\2\2\u0171\u0152\3\2\2\2\u0171\u0157\3\2\2\2\u0171\u015c\3\2\2\2\u0171"+
		"\u0161\3\2\2\2\u0171\u0166\3\2\2\2\u0171\u016b\3\2\2\2\u0171\u0170\3\2"+
		"\2\2\u0172)\3\2\2\2\u0173\u0174\7\60\2\2\u0174\u0175\7\f\2\2\u0175\u0176"+
		"\5\26\f\2\u0176\u0177\7\35\2\2\u0177\u017a\5\26\f\2\u0178\u0179\7\35\2"+
		"\2\u0179\u017b\5\26\f\2\u017a\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b"+
		"\u017c\3\2\2\2\u017c\u017d\7\r\2\2\u017d+\3\2\2\2\u017e\u0180\5\60\31"+
		"\2\u017f\u0181\5.\30\2\u0180\u017f\3\2\2\2\u0180\u0181\3\2\2\2\u0181-"+
		"\3\2\2\2\u0182\u0192\7N\2\2\u0183\u0185\7\f\2\2\u0184\u0186\7\5\2\2\u0185"+
		"\u0184\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u018c\5\26"+
		"\f\2\u0188\u0189\7\35\2\2\u0189\u018b\5\26\f\2\u018a\u0188\3\2\2\2\u018b"+
		"\u018e\3\2\2\2\u018c\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018f\3\2"+
		"\2\2\u018e\u018c\3\2\2\2\u018f\u0190\7\r\2\2\u0190\u0192\3\2\2\2\u0191"+
		"\u0182\3\2\2\2\u0191\u0183\3\2\2\2\u0192/\3\2\2\2\u0193\u0196\7L\2\2\u0194"+
		"\u0196\5\62\32\2\u0195\u0193\3\2\2\2\u0195\u0194\3\2\2\2\u0196\61\3\2"+
		"\2\2\u0197\u0198\t\2\2\2\u0198\63\3\2\2\2\u0199\u019e\5\66\34\2\u019a"+
		"\u019c\7\t\2\2\u019b\u019d\5\64\33\2\u019c\u019b\3\2\2\2\u019c\u019d\3"+
		"\2\2\2\u019d\u019f\3\2\2\2\u019e\u019a\3\2\2\2\u019e\u019f\3\2\2\2\u019f"+
		"\65\3\2\2\2\u01a0\u01a1\5P)\2\u01a1\u01a2\5D#\2\u01a2\u01a8\3\2\2\2\u01a3"+
		"\u01a5\5> \2\u01a4\u01a6\5D#\2\u01a5\u01a4\3\2\2\2\u01a5\u01a6\3\2\2\2"+
		"\u01a6\u01a8\3\2\2\2\u01a7\u01a0\3\2\2\2\u01a7\u01a3\3\2\2\2\u01a8\67"+
		"\3\2\2\2\u01a9\u01ae\5<\37\2\u01aa\u01ae\5\\/\2\u01ab\u01ae\5\16\b\2\u01ac"+
		"\u01ae\5:\36\2\u01ad\u01a9\3\2\2\2\u01ad\u01aa\3\2\2\2\u01ad\u01ab\3\2"+
		"\2\2\u01ad\u01ac\3\2\2\2\u01ae9\3\2\2\2\u01af\u01b0\7\61\2\2\u01b0\u01b1"+
		"\7\f\2\2\u01b1\u01b2\5\26\f\2\u01b2\u01b3\7\62\2\2\u01b3\u01b4\7M\2\2"+
		"\u01b4\u01b5\7\r\2\2\u01b5;\3\2\2\2\u01b6\u01b7\7\63\2\2\u01b7\u01b8\5"+
		"\f\7\2\u01b8=\3\2\2\2\u01b9\u01bc\5@!\2\u01ba\u01bc\5B\"\2\u01bb\u01b9"+
		"\3\2\2\2\u01bb\u01ba\3\2\2\2\u01bc?\3\2\2\2\u01bd\u01bf\7\f\2\2\u01be"+
		"\u01c0\5N(\2\u01bf\u01be\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1\u01bf\3\2\2"+
		"\2\u01c1\u01c2\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4\7\r\2\2\u01c4A"+
		"\3\2\2\2\u01c5\u01c6\7\64\2\2\u01c6\u01c7\5D#\2\u01c7\u01c8\7\65\2\2\u01c8"+
		"C\3\2\2\2\u01c9\u01ca\5J&\2\u01ca\u01d3\5F$\2\u01cb\u01cf\7\66\2\2\u01cc"+
		"\u01cd\5J&\2\u01cd\u01ce\5F$\2\u01ce\u01d0\3\2\2\2\u01cf\u01cc\3\2\2\2"+
		"\u01cf\u01d0\3\2\2\2\u01d0\u01d2\3\2\2\2\u01d1\u01cb\3\2\2\2\u01d2\u01d5"+
		"\3\2\2\2\u01d3\u01d1\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4E\3\2\2\2\u01d5"+
		"\u01d3\3\2\2\2\u01d6\u01db\5H%\2\u01d7\u01d8\7\35\2\2\u01d8\u01da\5H%"+
		"\2\u01d9\u01d7\3\2\2\2\u01da\u01dd\3\2\2\2\u01db\u01d9\3\2\2\2\u01db\u01dc"+
		"\3\2\2\2\u01dcG\3\2\2\2\u01dd\u01db\3\2\2\2\u01de\u01df\5N(\2\u01dfI\3"+
		"\2\2\2\u01e0\u01e3\5L\'\2\u01e1\u01e3\7\67\2\2\u01e2\u01e0\3\2\2\2\u01e2"+
		"\u01e1\3\2\2\2\u01e3K\3\2\2\2\u01e4\u01e8\7M\2\2\u01e5\u01e8\5\60\31\2"+
		"\u01e6\u01e8\5^\60\2\u01e7\u01e4\3\2\2\2\u01e7\u01e5\3\2\2\2\u01e7\u01e6"+
		"\3\2\2\2\u01e8M\3\2\2\2\u01e9\u01ec\5P)\2\u01ea\u01ec\5> \2\u01eb\u01e9"+
		"\3\2\2\2\u01eb\u01ea\3\2\2\2\u01ecO\3\2\2\2\u01ed\u01f0\7M\2\2\u01ee\u01f0"+
		"\5R*\2\u01ef\u01ed\3\2\2\2\u01ef\u01ee\3\2\2\2\u01f0Q\3\2\2\2\u01f1\u01f9"+
		"\5\60\31\2\u01f2\u01f9\5^\60\2\u01f3\u01f9\5T+\2\u01f4\u01f9\5l\67\2\u01f5"+
		"\u01f9\5V,\2\u01f6\u01f9\5X-\2\u01f7\u01f9\7N\2\2\u01f8\u01f1\3\2\2\2"+
		"\u01f8\u01f2\3\2\2\2\u01f8\u01f3\3\2\2\2\u01f8\u01f4\3\2\2\2\u01f8\u01f5"+
		"\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8\u01f7\3\2\2\2\u01f9S\3\2\2\2\u01fa"+
		"\u01fe\5Z.\2\u01fb\u01ff\7X\2\2\u01fc\u01fd\78\2\2\u01fd\u01ff\5\60\31"+
		"\2\u01fe\u01fb\3\2\2\2\u01fe\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ffU"+
		"\3\2\2\2\u0200\u0201\t\3\2\2\u0201W\3\2\2\2\u0202\u0203\t\4\2\2\u0203"+
		"Y\3\2\2\2\u0204\u0205\t\5\2\2\u0205[\3\2\2\2\u0206\u020b\5\f\7\2\u0207"+
		"\u0208\7;\2\2\u0208\u020a\5\f\7\2\u0209\u0207\3\2\2\2\u020a\u020d\3\2"+
		"\2\2\u020b\u0209\3\2\2\2\u020b\u020c\3\2\2\2\u020c]\3\2\2\2\u020d\u020b"+
		"\3\2\2\2\u020e\u0212\7O\2\2\u020f\u0212\7U\2\2\u0210\u0212\5Z.\2\u0211"+
		"\u020e\3\2\2\2\u0211\u020f\3\2\2\2\u0211\u0210\3\2\2\2\u0212_\3\2\2\2"+
		"\u0213\u0215\5d\63\2\u0214\u0213\3\2\2\2\u0214\u0215\3\2\2\2\u0215\u0217"+
		"\3\2\2\2\u0216\u0218\5b\62\2\u0217\u0216\3\2\2\2\u0217\u0218\3\2\2\2\u0218"+
		"a\3\2\2\2\u0219\u021b\5h\65\2\u021a\u021c\5j\66\2\u021b\u021a\3\2\2\2"+
		"\u021b\u021c\3\2\2\2\u021c\u0222\3\2\2\2\u021d\u021f\5j\66\2\u021e\u0220"+
		"\5h\65\2\u021f\u021e\3\2\2\2\u021f\u0220\3\2\2\2\u0220\u0222\3\2\2\2\u0221"+
		"\u0219\3\2\2\2\u0221\u021d\3\2\2\2\u0222c\3\2\2\2\u0223\u0225\7<\2\2\u0224"+
		"\u0226\5f\64\2\u0225\u0224\3\2\2\2\u0226\u0227\3\2\2\2\u0227\u0225\3\2"+
		"\2\2\u0227\u0228\3\2\2\2\u0228e\3\2\2\2\u0229\u022a\t\6\2\2\u022a\u0230"+
		"\5\24\13\2\u022b\u022e\5\20\t\2\u022c\u022e\7M\2\2\u022d\u022b\3\2\2\2"+
		"\u022d\u022c\3\2\2\2\u022e\u0230\3\2\2\2\u022f\u0229\3\2\2\2\u022f\u022d"+
		"\3\2\2\2\u0230g\3\2\2\2\u0231\u0232\7?\2\2\u0232\u0233\7A\2\2\u0233i\3"+
		"\2\2\2\u0234\u0235\7@\2\2\u0235\u0236\7A\2\2\u0236k\3\2\2\2\u0237\u023b"+
		"\5n8\2\u0238\u023b\5p9\2\u0239\u023b\5r:\2\u023a\u0237\3\2\2\2\u023a\u0238"+
		"\3\2\2\2\u023a\u0239\3\2\2\2\u023bm\3\2\2\2\u023c\u023d\t\7\2\2\u023d"+
		"o\3\2\2\2\u023e\u023f\t\b\2\2\u023fq\3\2\2\2\u0240\u0241\t\t\2\2\u0241"+
		"s\3\2\2\2\66}\u0086\u008b\u008e\u0095\u0099\u009c\u00a0\u00ae\u00be\u00c6"+
		"\u00d6\u00e1\u00e3\u00eb\u00ed\u00f7\u0100\u0171\u017a\u0180\u0185\u018c"+
		"\u0191\u0195\u019c\u019e\u01a5\u01a7\u01ad\u01bb\u01c1\u01cf\u01d3\u01db"+
		"\u01e2\u01e7\u01eb\u01ef\u01f8\u01fe\u020b\u0211\u0214\u0217\u021b\u021f"+
		"\u0221\u0227\u022d\u022f\u023a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}