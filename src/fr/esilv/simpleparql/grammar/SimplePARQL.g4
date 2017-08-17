grammar SimplePARQL;

query
    :  prologue selectQuery whereClause solutionModifier EOF
    ;
prologue
    : prefixDecl*
    ;
prefixDecl
    : Prefix PNAME_NS /* prefixname:*/ URI
    ;
selectQuery
    : Select Distinct? ( VAR+ | '*' | truc )
    ;

whereClause
    : Where groupGraphPattern
    ;
groupGraphPattern
    :
    '{'
        triplesBlock? ( ( graphPatternNotTriples ) '.'? triplesBlock? )*  // filter,optional ou union PUIS un autre triplé après (ne pas contraindre l'utilisateur à utiliser les triplé avant les filtres
    '}'
    ;
filter
    : Filter constraint
    ;
constraint
    : '(' expression ')' // (expression)
    | builtInCall // expressions STR,SAMETERM, etc..
    | functionCall // URI or namespace (genre rdf:name)
    ;
functionCall
    : iriRef argList
    ;
brackettedExpression
    : '(' expression ')'
    ;
expression
    : conditionalOrExpression // delete
    ;
conditionalOrExpression
    : conditionalAndExpression ( '||' conditionalAndExpression )* // OR qui est composé de 0 ou plusieurs AND
    ;
conditionalAndExpression
    : valueLogical ( '&&' valueLogical )* // AND qui est composé d'une (valeur logique) (OPERATEUR) (valeur logique)
    ;
valueLogical
    : numericExpression ( '=' numericExpression | '!=' numericExpression | '<' numericExpression | '>' numericExpression | '<=' numericExpression | '>=' numericExpression )? //(numericExpression) (avec ou sans) (OPERATEUR) (numericExpression)
    ;
numericExpression
    : additiveExpression // delete
    ;
additiveExpression
    : multiplicativeExpression ( '+' multiplicativeExpression | '-' multiplicativeExpression | numericLiteralPositive | numericLiteralNegative )*// FILTER(?age < 40 + 5)  quand on a 40 + 5, au lieu d'un seul nombre
    ;

multiplicativeExpression
    : unaryExpression ( '*' unaryExpression | '/' unaryExpression )* // FILTER(?age < 40*2 + 5) si j'avais une multiplication avant ==> FILTER(?age< 85) et non 400 par exemple
    ;
unaryExpression
    :  primaryExpression // normal
    |'!' primaryExpression // négation
    | '+' primaryExpression // ???
    | '-' primaryExpression // ???
    ;
primaryExpression
    : brackettedExpression // (expression)
     | builtInCall //STR, SAMETERM, etc..
     | iriRefOrFunction
     | rdfLiteral // Literal "cat" par exemple
     | numericLiteral // number (positive négative ou unsigned, double integer ou decimal)
     | booleanLiteral // true/false
     | VAR
    ;
builtInCall
    : Str '(' expression ')'
    | Lang '(' expression ')'
    | Langmatches '(' expression ',' expression ')'
    | Datatype '(' expression ')'
    | Contains '(' expression ',' expression ')'
    | Ucase '(' expression ')'
    | Lcase'(' expression ')'
    | Bound '(' VAR ')'
    | Sameterm '(' expression ',' expression ')'
    | Isiri '(' expression ')'
    | Isuri '(' expression ')'
    | Isblank '(' expression ')'
    | Isliteral '(' expression ')'
    | Year'(' expression ')'
    | Month '(' expression ')'
    | Day '(' expression ')'
    | Hours '(' expression ')'
    | Minutes '(' expression ')'
    | Seconds '(' expression ')'
    | Timezone '(' expression ')'
    | Tz '(' expression ')'
    | regexExpression
    ;
regexExpression
    : Regex '(' expression ',' expression ( ',' expression )? ')'
    ;
iriRefOrFunction
    : iriRef argList?
    ;
argList
    : ( NIL | '(' Distinct ? expression ( ',' expression )* ')' ) // ??? NIL == (empty); (expression,expression,...)
    ;
iriRef
    : URI
    | prefixedName
    ;
prefixedName
    : PNAME_LN //rdf:name
    | PNAME_NS //rdf:
    ;
triplesBlock
    : triplesSameSubject ( '.' triplesBlock? )? // le point est obligatoire quand y a un triplé qui le suit, sinon mettre un point est optionnel (PAS DE FAUTE QUAND ON LE MET!)
    ;
triplesSameSubject
    : varOrTerm propertyListNotEmpty | triplesNode propertyListNotEmpty?
    ;
graphPatternNotTriples
    : optionalGraphPattern | groupOrUnionGraphPattern | filter | bind
    ;
bind
    :
     Bind '(' expression 'AS' VAR ')'
    ;
optionalGraphPattern
    : Optional groupGraphPattern
    ;
triplesNode
    : collection
    | blankNodePropertyList
    ;
collection
    : '(' graphNode+ ')'
    ;
blankNodePropertyList
    : '[' propertyListNotEmpty ']'
    ;
propertyListNotEmpty
    : verb objectList ( ';' ( verb objectList )? )*    // same subject, different predicate-object
    ;
objectList
    : object ( ',' object )* // same object, different subject and predicate
    ;
object
    : graphNode
    ;
verb
    : varOrIRIreforTruc
    | 'a'
    ;
varOrIRIreforTruc
    : VAR | iriRef | truc
    ;
graphNode
    : varOrTerm | triplesNode
    ;
varOrTerm
    : VAR
    | graphTerm
    ;

graphTerm
    : iriRef
    | truc
    | rdfLiteral
    | numericLiteral
    | booleanLiteral
    | blankNode
    | NIL
    ;
rdfLiteral
    : string ( LANGTAG | ( '^^' iriRef ) )? //"cat"@en par exemple OU "2005-01-01T00:00:00Z"^^xsd:dateTime
    ;
booleanLiteral
    : 'true'
    | 'false'
    ;
blankNode
    : BLANK_NODE_LABEL
    | ANON
    ;
string
    : STRING_LITERAL1
    | STRING_LITERAL2
    ;
groupOrUnionGraphPattern
    : groupGraphPattern ( Union groupGraphPattern )*
    ;
truc
    : (TRUC_WORD(LANGTAG)? | TRUC_SLASH(LANGTAG)?  | string(LANGTAG)? )
    ;

solutionModifier
    : orderClause? limitOffsetClauses?
    ;

limitOffsetClauses
    : ( limitClause offsetClause? | offsetClause limitClause? )
    ;

orderClause
    : Order By orderCondition+
    ;

orderCondition
    : ( ( Asc | Desc ) brackettedExpression )
    | ( constraint | VAR )
    ;
limitClause
    : Limit INTEGER
    ;

offsetClause
    : Offset INTEGER
    ;
Select: S E L E C T;
Prefix : P R E F I X;
Where : W H E R E;
Distinct : D I S T I N C T;
Filter : F I L T E R;
Str : S T R;
Lang : L A N G;
Langmatches : L A N G M A T C H E S;
Datatype : D A T A T Y P E;
Contains :  C O N T A I N S;
Ucase : U C A S E;
Lcase : L  C A S E;
Bound : B O U N D;
Sameterm : S A M E T E R M ;
Isiri : I S I R I;
Isuri : I S U R I ;
Isblank : I S B L A N K ;
Isliteral : I S L I T E R A L;
Year : Y E A R;
Month : M O N T H ;
Day :  D A Y;
Hours : H O U R S;
Minutes :  M I N U T E S;
Seconds :  S E C O N D S;
Timezone :  T I M E Z O N E;
Tz : T Z;
Regex : R E G E X;
Bind : B I N D;
Optional: O P T I O N A L;
Union : U N I O N;
Asc : A S C;
Desc :  D E S C;
Limit : L I M I T;
Offset : O F F S E T;
Order : O R D E R;
By : B Y;
numericLiteral
    : numericLiteralUnsigned | numericLiteralPositive | numericLiteralNegative
    ;
numericLiteralUnsigned
    : INTEGER
    | DECIMAL
    | DOUBLE
    ;
numericLiteralPositive
    : INTEGER_POSITIVE
    | DECIMAL_POSITIVE
    | DOUBLE_POSITIVE
    ;
numericLiteralNegative
    : INTEGER_NEGATIVE
    | DECIMAL_NEGATIVE
    | DOUBLE_NEGATIVE
    ;
INTEGER
    : DIGIT+
    ;
DECIMAL
    : DIGIT+ '.' DIGIT*
    | '.' DIGIT+
    ;
DOUBLE
    : DIGIT+ '.' DIGIT* EXPONENT
    | '.' DIGIT+ EXPONENT
    | DIGIT+ EXPONENT
    ;
INTEGER_POSITIVE
    : '+' INTEGER
    ;
DECIMAL_POSITIVE
    : '+' DECIMAL
    ;
DOUBLE_POSITIVE
    : '+' DOUBLE
    ;
INTEGER_NEGATIVE
    : '-' INTEGER
    ;
DECIMAL_NEGATIVE
    : '-' DECIMAL
    ;
DOUBLE_NEGATIVE
    : '-' DOUBLE
    ;
EXPONENT
    : ('e'|'E') ('+'|'-')? DIGIT+
    ;
DIGIT
    : '0'..'9'
    ;
URI
    : '<' HTTP ( ~('<' | '>' | '"' | '{' | '}' | '|' | '^' | '\\' | '`'|' '|'\'') | (PN_CHARS))+ '>'
    ;
VAR
    : '?' VARNAME
    ;
NIL
    : '(' WS* ')'
    ;
TRUC_WORD
    :('A'..'Z' | 'a'..'z' |('\u0300'..'\u036F') | ('\u203F'..'\u2040') | DIGIT )+
    ;
// variables cannot start with digits and should have at least one characters
VARNAME
    :  PN_CHARS_UNDERSCORE ( PN_CHARS_UNDERSCORE | DIGIT | '\u00B7' | ('\u0300'..'\u036F') | ('\u203F'..'\u2040') )*
    ;
PNAME_LN // NAMESPACE_PREFIX:TYPE
    : PNAME_NS /*rdf:*/ PN_LOCAL /*type*/
    ;

PN_LOCAL // type after namespace
    : ( PN_CHARS_UNDERSCORE | DIGIT ) ((PN_CHARS|'.')* PN_CHARS)?
    ;
STRING_LITERAL1
    : '\'' ( ~('\u0027' | '\u005C' | '\u000A' | '\u000D') | ECHAR )* '\''
    ;
STRING_LITERAL2
    : '"'  ( ~('\u0022' | '\u005C' | '\u000A' | '\u000D' ) | ECHAR )* '"'
    ;
TRUC_SLASH
    : '/' ('A'..'Z' | 'a'..'z' | ' ' | ECHAR)+ '/'
    ;

ECHAR
    : '\\' ('t' | 'b' | 'n' | 'r' | 'f' | '"' | '\'')
    ;
ANON
    : '[' WS* ']'
    ;
LANGTAG
    : '@' PN_CHARS_BASE+ ('-' (PN_CHARS_BASE DIGIT)+)*
    ;
BLANK_NODE_LABEL
    : '_:' PN_LOCAL
    ;
PNAME_NS
    :
    PN_PREFIX? ':'
    ;
PN_PREFIX
    : PN_CHARS_BASE ((PN_CHARS|'.')* PN_CHARS)?
    ;
fragment
PN_CHARS
    : PN_CHARS_UNDERSCORE
    | '-'
    | DIGIT
    ;
PN_CHARS_UNDERSCORE
    : PN_CHARS_BASE | '_'
    ;
fragment
PN_CHARS_BASE
    : 'A'..'Z'
    | 'a'..'z'
    | '\u00C0'..'\u00D6'
    | '\u00D8'..'\u00F6'
    | '\u00F8'..'\u02FF'
    | '\u0370'..'\u037D'
    | '\u037F'..'\u1FFF'
    | '\u200C'..'\u200D'
    | '\u2070'..'\u218F'
    | '\u2C00'..'\u2FEF'
    | '\u3001'..'\uD7FF'
    | '\uF900'..'\uFDCF'
    | '\uFDF0'..'\uFFFD'
    ;
HTTP
    :('http'|'HTTP') ':'
    ;

fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];

WS
    : (' '
    | '\t'
    | '\n'
    | '\r')+ ->skip
    ;