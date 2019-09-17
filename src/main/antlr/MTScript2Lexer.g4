lexer grammar MTScript2Lexer;

@header {
package net.rptools.mtscript.parser;
}

@lexer::members {
  public boolean parsingModule = false;
}



// Default Mode
OPEN_INLINE_ROLL            : '[[' -> pushMode(INLINE_ROLL);
OPEN_INLINE_SCRIPT          : '[[[' -> pushMode(INLINE_SCRIPT);
OPEN_MODULE                 : 'module' { parsingModule }? -> pushMode(INLINE_SCRIPT);
TEXT                        : .+? { !parsingModule }?;



mode INLINE_ROLL;

CLOSE_INLINE_ROLL           : ']]' -> popMode;

ROLL_INTEGER_LITERAL        : ROLL_DIGIT+;
ROLL_DOUBLE_LITERAL         : ROLL_DIGIT+ '.' ROLL_DIGIT+;

ROLL_STRING                 : ROLL_SINGLE_QUOTE (ROLL_SINGLE_STRING_ESC | ~['\\])* ROLL_SINGLE_QUOTE
                            | ROLL_DOUBLE_QUOTE (ROLL_DOUBLE_STRING_ESC | ~["\\])* ROLL_DOUBLE_QUOTE
                            ;

ROLL_INSTRUCTION_LEADER     : '%';


ROLL_LOCAL_VARIABLE         : ROLL_LOCAL_VAR_LEADER ROLL_LETTER ( ROLL_DIGIT | ROLL_LETTER )*;
ROLL_GLOBAL_VARIABLE        : ROLL_GLOBAL_VAR_LEADER ROLL_LETTER ( ROLL_DIGIT | ROLL_LETTER )*;

ROLL_PROPERTY_VARIABLE      : ROLL_PROPERTY_VAR_LEADER ROLL_LETTER ( ROLL_DIGIT | ROLL_LETTER )*
                            | ROLL_PROPERTY_VAR_LEADER ROLL_STRING
                            ;

ROLL_DOT                    : '.' ;

ROLL_EXT_PROP_PREFIX        : '#';



ROLL_SEMI                   : ';';
ROLL_ASSIGN                 : '=';
ROLL_POWER                  : '^';
ROLL_MULTIPLY               : '*';
ROLL_DIVIDE                 : '/';
ROLL_PLUS                   : '+';
ROLL_MINUS                  : '-';
ROLL_LPAREN                 : '(';
ROLL_RPAREN                 : ')';
ROLL_LBRACE                 : '{';
ROLL_RBRACE                 : '}';
ROLL_COMMA                  : ',';
ROLL_LESS_THAN              : '<';
ROLL_GREATER_THAN           : '>';
ROLL_LESS_EQ_TO             : '<=';
ROLL_GREATER_EQ_TO          : '>=';
ROLL_NOT_EQUAL              : '!=';


ROLL_MULTIlINE_COMMENT      : '/*' .*? '*/' -> channel(HIDDEN);

ROLL_WS                     : [ \t\r\n] -> channel(HIDDEN);


fragment ROLL_DIGIT              : [0-9];
fragment ROLL_SINGLE_QUOTE       : '\'';
fragment ROLL_DOUBLE_QUOTE       : '"';
fragment ROLL_LOCAL_VAR_LEADER   : '$';
fragment ROLL_GLOBAL_VAR_LEADER  : '$$';
fragment ROLL_PROPERTY_VAR_LEADER: '@';



fragment ROLL_SINGLE_STRING_ESC  : '\\' (ROLL_STRING_ESC | ROLL_SINGLE_QUOTE);
fragment ROLL_DOUBLE_STRING_ESC  : '\\' (ROLL_STRING_ESC | ROLL_DOUBLE_QUOTE);
fragment ROLL_STRING_ESC         : '\\' ([\\/bfnrt] | ROLL_UNICODE);
fragment ROLL_UNICODE            : 'u' ROLL_HEX ROLL_HEX ROLL_HEX ROLL_HEX;
fragment ROLL_HEX                : [0-9a-fA-F];


fragment ROLL_LETTER        : [a-zA-Z];

ROLL_WORD                   : ROLL_LETTER+;


mode INLINE_SCRIPT;
CLOSE_INLINE_SCRIPT         : ']]]' -> popMode;
//SCRIPT_BODY               : .+?;

//SCRIPT_MULTIlINE_COMMENT  : '/*' .*? '*/';

/*
SCRIPT_STRING               : SCRIPT_SINGLE_QUOTE (SCRIPT_SINGLE_STRING_ESC | ~['\\])* SCRIPT_SINGLE_QUOTE
                            | SCRIPT_DOUBLE_QUOTE (SCRIPT_DOUBLE_STRING_ESC | ~["\\])* SCRIPT_DOUBLE_QUOTE
                            ;
fragment SCRIPT_DIGIT       : [0-9];
fragment SCRIPT_SINGLE_QUOTE: '\'';
fragment SCRIPT_DOUBLE_QUOTE: '"';

fragment SCRIPT_SINGLE_STRING_ESC: '\\' (SCRIPT_STRING_ESC | SCRIPT_SINGLE_QUOTE);
fragment SCRIPT_DOUBLE_STRING_ESC: '\\' (SCRIPT_STRING_ESC | SCRIPT_DOUBLE_QUOTE);
fragment SCRIPT_STRING_ESC       : '\\' ([\\/bfnrt] | SCRIPT_UNICODE);
fragment SCRIPT_UNICODE          : 'u' SCRIPT_HEX SCRIPT_HEX SCRIPT_HEX SCRIPT_HEX;
fragment SCRIPT_HEX              : [0-9a-fA-F];
*/



// Keywords
SCRIPT_ASSERT     : 'assert';
SCRIPT_BREAK      : 'break';
SCRIPT_CASE       : 'case';
SCRIPT_CONST      : 'const';
SCRIPT_CONTINUE   : 'continue';
SCRIPT_DEFAULT    : 'default';
SCRIPT_DO         : 'do';
SCRIPT_ELSE       : 'else';
SCRIPT_FOR        : 'for';
SCRIPT_FOREACH    : 'foreach';
SCRIPT_FUNCTION   : 'function';
SCRIPT_IF         : 'if';
SCRIPT_RETURN     : 'return';
SCRIPT_SWITCH     : 'switch';
SCRIPT_WHILE      : 'while';
SCRIPT_TRY        : 'try';
SCRIPT_CATCH      : 'catch';
SCRIPT_FINALLY    : 'finally';
SCRIPT_THROW      : 'throw';
SCRIPT_INSTANCEOF : 'instanceof';
//SCRIPT_MODULE   : 'module';
SCRIPT_USE        : 'uses';
SCRIPT_AS         : 'as';
SCRIPT_EXPORT     : 'export';
SCRIPT_INTERNAL   : 'internal';
SCRIPT_CHAT       : 'chat';
SCRIPT_GM         : 'gm';
SCRIPT_TRUSTED    : 'trusted';


SCRIPT_CONSTANT   : 'constant';
SCRIPT_INTEGER    : 'integer';
SCRIPT_NUMBER     : 'number';
SCRIPT_STRING     : 'string';
SCRIPT_ROLL       : 'roll';
SCRIPT_BOOLEAN    : 'bool';
SCRIPT_DICT       : 'dict';

SCRIPT_OPEN_INLINE_ROLL : '[[' -> pushMode(INLINE_ROLL);

// Literals
SCRIPT_DECIMAL_LITERAL : ( '0' | [1-9] (Digits? | '_' + Digits) ) ;
SCRIPT_HEX_LITERAL     : '0' [xX] [0-9a-fA-F] ([0-9a-fA-F_]* [0-9a-fA-F])? ;
SCRIPT_NUMBER_LITERAL  : (Digits '.' Digits? | '.' Digits) ;
SCRIPT_BOOL_LITERAL    : 'true' | 'false' ;
SCRIPT_STRING_LITERAL  : ( '\'' (~['] | EscapeSequence)* '\''  )
                       | ( '"'  (~["] | EscapeSequence)* '"'   )
                       ;
SCRIPT_NULL_LITERAL    : 'null';

// Separators
SCRIPT_LPAREN  : '(';
SCRIPT_RPAREN  : ')';
SCRIPT_LBRACE  : '{';
SCRIPT_RBRACE  : '}';
SCRIPT_LBRACK  : '[';
SCRIPT_RBRACK  : ']';
SCRIPT_SEMI    : ';';
SCRIPT_COMMA   : ',';
SCRIPT_DOT     : '.';

// Operators
SCRIPT_ASSIGN  : '=';
SCRIPT_GT      : '>';
SCRIPT_LT      : '<';
SCRIPT_BANG    : '!';
SCRIPT_TILDE   : '~';
SCRIPT_QUESTION: '?';
SCRIPT_COLON   : ':';
SCRIPT_EQUAL   : '==';
SCRIPT_LE      : '<=';
SCRIPT_GE      : '>=';
SCRIPT_NOTEQUAL: '!=';
SCRIPT_AND     : '&&';
SCRIPT_OR      : '||';
SCRIPT_INC     : '++';
SCRIPT_DEC     : '--';
SCRIPT_ADD     : '+';
SCRIPT_SUB     : '-';
SCRIPT_MUL     : '*';
SCRIPT_DIV     : '/';
SCRIPT_BITAND  : '&';
SCRIPT_BITOR   : '|';
SCRIPT_CARET   : '^';
SCRIPT_MOD     : '%';

SCRIPT_ADD_ASSIGN : '+=';
SCRIPT_SUB_ASSIGN : '-=';
SCRIPT_MUL_ASSIGN : '*=';
SCRIPT_DIV_ASSIGN : '/=';
SCRIPT_AND_ASSIGN : '&=';
SCRIPT_OR_ASSIGN  : '|=';
SCRIPT_XOR_ASSIGN : '^=';
SCRIPT_MOD_ASSIGN : '%=';

SCRIPT_EXT_PROP_PREFIX : '#';

// Whitespace and comments
SCRIPT_WS           : [ \t\r\n\u000C]+  -> channel(HIDDEN);
SCRIPT_COMMENT      : '/*' .*? '*/'     -> channel(HIDDEN);
SCRIPT_LINE_COMMENT : '//' ~[\r\n]*     -> channel(HIDDEN);

// Identifiers
SCRIPT_IDENTIFIER   : Letter LetterOrDigit* ;

// Fragment rules
fragment EscapeSequence : '\\' [btnfr"'\\] ;

fragment Digits         : [0-9] ([0-9_]* [0-9])?
                        ;

fragment LetterOrDigit  : Letter
                        | [0-9]
                        ;

fragment Letter         : [a-zA-Z$_] // Java letters below 0x7F
                        | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
                        | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
                        ;
