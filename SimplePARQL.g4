grammar SimplePARQL;

query
    : prologue selectQuery EOF
    ;


// Plusieurs préfixes
prologue
    : prefixDecl*
    ;
// plusieurs (ou aucune) variables possible
selectQuery
    : 'SELECT' ( var+ | '*' )
    ;

// PREFIX SECTION
// le namespace est obliagtoire
prefixDecl
    : 'PREFIX' NAMESPACE ':' URI
    ;
// TODO :restriction des namespace par http?
URI
    : '<' HTTP ( ~('<' | '>' | '"' | '{' | '}' | '|' | '^' | '\\' | '`'|' '|'\'') | (PN_CHARS))+ '>'
    ;
NAMESPACE
    : PN_CHARS_BASE ((PN_CHARS|'.')* PN_CHARS)?
    ;

// SELECT SECTION
var
    : '?' VARNAME
    ;
VAR1
    :
    ;
// les variables ne peuvent pas commencer par des chiffres
VARNAME
    :  PN_CHARS_UNDERSCORE ( PN_CHARS_UNDERSCORE | DIGIT | '\u00B7' | ('\u0300'..'\u036F') | ('\u203F'..'\u2040') )*
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
HTTP
    :('http'|'HTTP') ':'
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
fragment
DIGIT
    : '0'..'9'
    ;
// skipped characteres
WS
    : (' '
    | '\t'
    | '\n'
    | '\r')+ ->skip
    ;