grammar SimplePARQL;

query
    :  prologue selectQuery whereClause solutionModifier EOF
    ;
prologue
    : prefixDecl*
    ;
prefixDecl
    : 'PREFIX' PNAME_NS /* prefixname:*/ URI
    ;
selectQuery
    : 'SELECT' 'DISTINCT'? ( VAR+ | '*' )
    ;
whereClause
    : 'WHERE' groupGraphPattern
    ;
groupGraphPattern
    :
    '{'
        triplesBlock? ( ( graphPatternNotTriples ) '.'? triplesBlock? )*  // filter,optional ou union PUIS un autre triplé après (ne pas contraindre l'utilisateur à utiliser les triplé avant les filtres
    '}'
    ;
filter
    : 'FILTER' constraint
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
    : 'STR' '(' expression ')'
    | 'LANG' '(' expression ')'
    | 'LANGMATCHES' '(' expression ',' expression ')'
    | 'DATATYPE' '(' expression ')'
    | 'BOUND' '(' VAR ')'
    | 'sameTerm' '(' expression ',' expression ')'
    | 'isIRI' '(' expression ')'
    | 'isURI' '(' expression ')'
    | 'isBLANK' '(' expression ')'
    | 'isLITERAL' '(' expression ')'
    | 'YEAR' '(' expression ')'
    | 'MONTH' '(' expression ')'
    | 'DAY' '(' expression ')'
    | 'HOURS' '(' expression ')'
    | 'MINUTES' '(' expression ')'
    | 'SECONDS' '(' expression ')'
    | 'TIMEZONE' '(' expression ')'
    | 'TZ' '(' expression ')'
    | regexExpression
    ;
regexExpression
    : 'REGEX' '(' expression ',' expression ( ',' expression )? ')'
    ;
iriRefOrFunction
    : iriRef argList?
    ;
argList
    : ( NIL | '(' 'DISTINCT'? expression ( ',' expression )* ')' ) // ??? NIL == (empty); (expression,expression,...)
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
     'BIND' '(' expression 'AS' VAR ')'
    ;
optionalGraphPattern
    : 'OPTIONAL' groupGraphPattern
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
    : groupGraphPattern ( 'UNION' groupGraphPattern )*
    ;
truc
    : (TRUC_WORD | TRUC_DIESE | string)
    ;

solutionModifier
    : orderClause? limitOffsetClauses?
    ;

limitOffsetClauses
    : ( limitClause offsetClause? | offsetClause limitClause? )
    ;

orderClause
    : 'ORDER BY' orderCondition+
    ;

orderCondition
    : ( ( 'ASC' | 'DESC' ) brackettedExpression )
    | ( constraint | VAR )
    ;
limitClause
    : 'LIMIT' INTEGER
    ;

offsetClause
    : 'OFFSET' INTEGER
    ;
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
TRUC_DIESE
    : '#' ( ~('\u0022' | '\u005C' | '\u000A' | '\u000D' | '(' | ')') | ' ' | ECHAR)* '#'
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
WS
    : (' '
    | '\t'
    | '\n'
    | '\r')+ ->skip
    ;