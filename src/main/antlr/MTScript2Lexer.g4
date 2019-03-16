lexer grammar MTScript2Lexer;

@header {
  package net.rptools.maptool.mtscript;
}



// Default Mode
OPEN_INLINE_SCRIPT                  : '[[[' -> pushMode(INLINE_SCRIPT);
OPEN_INLINE_ROLL                    : '[[' -> pushMode(INLINE_ROLL);
//TEXT                                : .+?;



mode INLINE_ROLL;

CLOSE_INLINE_ROLL                   : ']]' -> popMode;

ROLL_INTEGER_LITERAL                : ROLL_DIGIT+;
ROLL_DOUBLE_LITERAL                 : ROLL_DIGIT+ '.' ROLL_DIGIT+;

ROLL_STRING                         : ROLL_SINGLE_QUOTE (ROLL_SINGLE_STRING_ESC | ~['\\])* ROLL_SINGLE_QUOTE
                                    | ROLL_DOUBLE_QUOTE (ROLL_DOUBLE_STRING_ESC | ~["\\])* ROLL_DOUBLE_QUOTE
                                    ;

ROLL_INSTRUCTION_LEADER             : '%';


ROLL_LOCAL_VARIABLE                 : ROLL_LOCAL_VAR_LEADER ROLL_LETTER ( ROLL_DIGIT | ROLL_LETTER )*;
ROLL_GLOBAL_VARIABLE                : ROLL_GLOBAL_VAR_LEADER ROLL_LETTER ( ROLL_DIGIT | ROLL_LETTER )*;

ROLL_PROPERTY_VARIABLE              : ROLL_PROPERTY_VAR_LEADER ROLL_LETTER ( ROLL_DIGIT | ROLL_LETTER )*
                                    | ROLL_PROPERTY_VAR_LEADER ROLL_STRING
                                    ;



ROLL_SEMI                   : ';';
ROLL_EQ                     : '=';
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
ROLL_LESS_EQ_THAN           : '<=';
ROLL_GREATER_EQ_THAN        : '>=';


ROLL_MULTIlINE_COMMENT           : '/*' .*? '*/' -> channel(HIDDEN);

ROLL_WS                          : [ \t\r\n] -> channel(HIDDEN);


fragment ROLL_DIGIT              : [0-9];
fragment ROLL_SINGLE_QUOTE       : '\'';
fragment ROLL_DOUBLE_QUOTE       : '"';
fragment ROLL_LOCAL_VAR_LEADER   : '$';
fragment ROLL_GLOBAL_VAR_LEADER  : '#';
fragment ROLL_PROPERTY_VAR_LEADER: '@';



fragment ROLL_SINGLE_STRING_ESC  : '\\' (ROLL_STRING_ESC | ROLL_SINGLE_QUOTE);
fragment ROLL_DOUBLE_STRING_ESC  : '\\' (ROLL_STRING_ESC | ROLL_DOUBLE_QUOTE);
fragment ROLL_STRING_ESC         : '\\' ([\\/bfnrt] | ROLL_UNICODE);
fragment ROLL_UNICODE            : 'u' ROLL_HEX ROLL_HEX ROLL_HEX ROLL_HEX;
fragment ROLL_HEX                : [0-9a-fA-F];


fragment ROLL_LETTER             : [a-zA-Z];

ROLL_WORD                        : ROLL_LETTER+
                                 ;







mode INLINE_SCRIPT;
CLOSE_INLINE_SCRIPT                 : ']]]' -> popMode;
//SCRIPT_BODY                         : .+?;

SCRIPT_MULTIlINE_COMMENT            : '/*' .*? '*/';

SCRIPT_STRING                       : SCRIPT_SINGLE_QUOTE (SCRIPT_SINGLE_STRING_ESC | ~['\\])* SCRIPT_SINGLE_QUOTE
                                    | SCRIPT_DOUBLE_QUOTE (SCRIPT_DOUBLE_STRING_ESC | ~["\\])* SCRIPT_DOUBLE_QUOTE
                                    ;
fragment SCRIPT_DIGIT               : [0-9];
fragment SCRIPT_SINGLE_QUOTE        : '\'';
fragment SCRIPT_DOUBLE_QUOTE        : '"';

fragment SCRIPT_SINGLE_STRING_ESC   : '\\' (SCRIPT_STRING_ESC | SCRIPT_SINGLE_QUOTE);
fragment SCRIPT_DOUBLE_STRING_ESC   : '\\' (SCRIPT_STRING_ESC | SCRIPT_DOUBLE_QUOTE);
fragment SCRIPT_STRING_ESC          : '\\' ([\\/bfnrt] | SCRIPT_UNICODE);
fragment SCRIPT_UNICODE             : 'u' SCRIPT_HEX SCRIPT_HEX SCRIPT_HEX SCRIPT_HEX;
fragment SCRIPT_HEX                 : [0-9a-fA-F];


