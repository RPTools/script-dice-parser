lexer grammar MTScript2Lexer;

@header {
package net.rptools.mtscript.parser;
}

@lexer::members {
  public boolean parsingModule = false;
}



// Default Mode
OPEN_SCRIPT_MODE            : '[[' -> pushMode(SCRIPT_MODE);
OPEN_MODULE                 : 'module' { parsingModule }? -> pushMode(MODULE_MODE);
TEXT                        : .+? { !parsingModule }?;

mode MODULE_MODE;
MODULE_STRING          : ( '\'' (~['] | EscapeSequence)* '\''  )
                       | ( '"'  (~["] | EscapeSequence)* '"'   )
                       ;
MODULE_LETTER          : Letter;
MODULE_DIGIT           : Digits;
MODULE_DOT     : '.';
MODULE_DASH    : '-';
MODULE_PLUS    : '+';

MODULE_WS    : [ \t\r\n\u000C]+  -> channel(HIDDEN);
MODULE_SEMI  : ';' -> pushMode(SCRIPT_MODE);

mode SCRIPT_MODE;
CLOSE_SCRIPT_MODE         : ']]' -> popMode;

// Keywords
KEYWORD_ASSERT     : 'assert';
KEYWORD_BREAK      : 'break';
KEYWORD_CASE       : 'case';
KEYWORD_CONST      : 'const';
KEYWORD_CONTINUE   : 'continue';
KEYWORD_DEFAULT    : 'default';
KEYWORD_DO         : 'do';
KEYWORD_ELSE       : 'else';
KEYWORD_FOR        : 'for';
KEYWORD_FOREACH    : 'foreach';
KEYWORD_FUNCTION   : 'function';
KEYWORD_IF         : 'if';
KEYWORD_RETURN     : 'return';
KEYWORD_SWITCH     : 'switch';
KEYWORD_WHILE      : 'while';
KEYWORD_TRY        : 'try';
KEYWORD_CATCH      : 'catch';
KEYWORD_FINALLY    : 'finally';
KEYWORD_THROW      : 'throw';
KEYWORD_INSTANCEOF : 'instanceof';

KEYWORD_USE        : 'uses';
KEYWORD_AS         : 'as';
KEYWORD_EXPORT     : 'export';
KEYWORD_INTERNAL   : 'internal';
KEYWORD_CHAT       : 'chat';
KEYWORD_GM         : 'gm';
KEYWORD_TRUSTED    : 'trusted';


KEYWORD_CONSTANT   : 'constant';
KEYWORD_INTEGER    : 'integer';
KEYWORD_NUMBER     : 'number';
KEYWORD_STRING     : 'string';
KEYWORD_ROLL       : 'roll';
KEYWORD_BOOLEAN    : 'bool';
KEYWORD_DICT       : 'dict';
KEYWORD_TOKEN      : 'token';

KEYWORD_OPEN_OP_MODE : '[[' -> pushMode(SCRIPT_MODE);

// Literals
DECIMAL_LITERAL : ( '0' | [1-9] (Digits? | '_' + Digits) ) ;
HEX_LITERAL     : '0' [xX] [0-9a-fA-F] ([0-9a-fA-F_]* [0-9a-fA-F])? ;
NUMBER_LITERAL  : (Digits '.' Digits? | '.' Digits) ;
BOOL_LITERAL    : 'true' | 'false' ;
STRING_LITERAL  : ( '\'' (~['] | EscapeSequence)* '\''  )
                | ( '"'  (~["] | EscapeSequence)* '"'   )
                ;
NULL_LITERAL    : 'null';

// Separators
LPAREN  : '(';
RPAREN  : ')';
LBRACE  : '{';
RBRACE  : '}';
LBRACK  : '[';
RBRACK  : ']';
SEMI    : ';';
COMMA   : ',';
DOT     : '.';

// Operators
OP_ASSIGN  : '=';
OP_GT      : '>';
OP_LT      : '<';
OP_BANG    : '!';
OP_TILDE   : '~';
OP_QUESTION: '?';
OP_COLON   : ':';
OP_EQUAL   : '==';
OP_LE      : '<=';
OP_GE      : '>=';
OP_NOTEQUAL: '!=';
OP_AND     : '&&';
OP_OR      : '||';
OP_INC     : '++';
OP_DEC     : '--';
OP_ADD     : '+';
OP_SUB     : '-';
OP_MUL     : '*';
OP_DIV     : '/';
OP_BITAND  : '&';
OP_BITOR   : '|';
OP_CARET   : '^';
OP_MOD     : '%';

OP_ADD_ASSIGN : '+=';
OP_SUB_ASSIGN : '-=';
OP_MUL_ASSIGN : '*=';
OP_DIV_ASSIGN : '/=';
OP_AND_ASSIGN : '&=';
OP_OR_ASSIGN  : '|=';
OP_XOR_ASSIGN : '^=';
OP_MOD_ASSIGN : '%=';

EXT_PROP_PREFIX : '#';

// Whitespace and comments
WS           : [ \t\r\n\u000C]+  -> channel(HIDDEN);
COMMENT      : '/*' .*? '*/'     -> channel(HIDDEN);
LINE_COMMENT : '//' ~[\r\n]*     -> channel(HIDDEN);

// Identifiers
IDENTIFIER   : Letter LetterOrDigit* ;

LOCAL_VAR_LEADER   : '$';
GLOBAL_VAR_LEADER  : '$$';
PROPERTY_VAR_LEADER: '@';

// Fragment rules
fragment EscapeSequence : '\\' [btnfr"'\\] ;

fragment Digits         : [0-9] ([0-9_]* [0-9])?
                        ;

fragment LetterOrDigit  : Letter
                        | [0-9]
                        ;

fragment Letter         : [a-zA-Z_] // Java letters below 0x7F
                        | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
                        | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
                        ;
