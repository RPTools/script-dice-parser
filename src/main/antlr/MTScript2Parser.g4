parser grammar MTScript2Parser;


@header {
  package net.rptools.maptool.mtscript;
}

options { tokenVocab=MTScript2Lexer; }

chat                : (script | inlineRoll | text)* ;

script              : OPEN_INLINE_SCRIPT scriptBody CLOSE_INLINE_SCRIPT;

text                : TEXT+;

inlineRoll          : OPEN_INLINE_ROLL diceRolls CLOSE_INLINE_ROLL;


scriptBody          : (SCRIPT_MULTIlINE_COMMENT | SCRIPT_STRING | SCRIPT_BODY)+;


diceRolls                   : diceExprTopLevel ( ';' diceExprTopLevel)* ';'?
                            ;

diceExprTopLevel            : diceExpr
                            ;

diceExpr                    : assignment
                            | expr
                            | instruction
                            ;


assignment                  : variable '=' right=expr
                            ;

variable                    : localVariable
                            | globalVariable
                            | propertyVariable
                            ;

localVariable               : ROLL_LOCAL_VARIABLE
                            ;

globalVariable              : ROLL_GLOBAL_VARIABLE
                            ;

propertyVariable            : ROLL_PROPERTY_VARIABLE
                            ;


expr                        : left=expr op='^' right=expr                   # binaryExpr
                            | left=expr op=('*' | '/') right=expr           # binaryExpr
                            | left=expr op=('+' | '-') right=expr           # binaryExpr
                            | op='-' right=expr                             # unaryExpr
                            | dice                                          # diceSpec
                            | group                                         # groupExpr
                            | variable                                      # symbol
                            | integerValue                                  # integerVal
                            | doubleValue                                   # doubleVal
                            | ROLL_STRING                                   # string
                            ;

group                       : '(' val=diceExpr ')'                          # parenGroup
                            | '{' val=diceExpr '}'                          # braceGroup
                            ;

dice                        : numDice? diceName diceSides diceArguments?
                            ;


numDice                     : integerValue
                            | group
                            ;


diceSides                   : integerValue
                            | group
                            ;


instruction                 : ROLL_INSTRUCTION_LEADER instructionName=identifier instructionArgumentList
                            ;

instructionArgumentList     : (instructionArgument ( ',' instructionArgument))*
                            ;

instructionArgument         : identifier
                            | variable
                            | ROLL_STRING
                            ;

integerValue                : ROLL_INTEGER_LITERAL
                            ;

doubleValue                 : ROLL_DOUBLE_LITERAL
                            ;



diceArguments               : diceArgumentList
                            ;

diceArgumentList            : '{' diceArgument ( ',' diceArgument )* '}'
                            | '(' diceArgument ( ',' diceArgument )* ')'
                            ;


diceArgument                : argName=ROLL_WORD op=( '<' | '>' | '<=' | '>=' | '=' )? val=diceArgumentVal?
                            ;


diceArgumentVal             : identifier                                        # dargIdentifier
                            | variable                                          # dargVariable
                            | ROLL_STRING                                       # dargString
                            | integerValue                                      # dargInteger
                            | doubleValue                                       # dargDouble
                            ;

diceName                    : ROLL_WORD
                            | ROLL_WORD ( integerValue | ROLL_WORD)* ROLL_WORD
                            ;


identifier                  :  ROLL_LETTER ( integerValue | ROLL_LETTER)*
                            ;
