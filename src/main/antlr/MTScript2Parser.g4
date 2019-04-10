parser grammar MTScript2Parser;


@header {
package net.rptools.mtscript.parser;
}

options { tokenVocab=MTScript2Lexer; }

chat                                      : (script | inlineRoll | text)* ;

inlineRoll                                : OPEN_INLINE_ROLL diceRolls CLOSE_INLINE_ROLL;

script                                    : OPEN_INLINE_SCRIPT scriptBody CLOSE_INLINE_SCRIPT;

text                                      : TEXT+;

diceRolls                                 : diceExprTopLevel ( ROLL_SEMI diceExprTopLevel)* ROLL_SEMI?;

diceExprTopLevel                          : diceExpr
                                          ;

diceExpr                                  : assignment
                                          | expr
                                          | instruction
                                          ;


assignment                                : variable ROLL_ASSIGN right=expr
                                          ;

variable                                  : localVariable
                                          | globalVariable
                                          | propertyVariable
                                          ;

localVariable                             : ROLL_LOCAL_VARIABLE
                                          ;

globalVariable                            : ROLL_GLOBAL_VARIABLE
                                          ;

propertyVariable                          : ROLL_PROPERTY_VARIABLE
                                          ;


expr                                      : left=expr op=ROLL_POWER right=expr                   # binaryExpr
                                          | left=expr op=(ROLL_MULTIPLY | ROLL_DIVIDE) right=expr           # binaryExpr
                                          | left=expr op=(ROLL_PLUS | ROLL_MINUS) right=expr           # binaryExpr
                                          | op=ROLL_MINUS right=expr                             # unaryExpr
                                          | dice                                          # diceSpec
                                          | group                                         # groupExpr
                                          | variable                                      # symbol
                                          | integerValue                                  # integerVal
                                          | doubleValue                                   # doubleVal
                                          | ROLL_STRING                                   # string
                                          | ROLL_EXT_PROP_PREFIX	extPropName             # rollExtPprop
                                          ;

extPropName                               : (ROLL_WORD ROLL_DOT)* ROLL_WORD
                                          ;

group                                     : ROLL_LPAREN val=diceExpr ROLL_RPAREN                          # parenGroup
                                          | ROLL_LBRACE val=diceExpr ROLL_RBRACE                          # braceGroup
                                          ;

dice                                      : numDice? diceName diceSides diceArguments?
                                          ;


numDice                                   : integerValue
                                          | group
                                          ;


diceSides                                 : integerValue
                                          | group
                                          ;


instruction                               : ROLL_INSTRUCTION_LEADER instructionName=identifier instructionArgumentList
                                          ;

instructionArgumentList                   : (instructionArgument ( ROLL_COMMA instructionArgument))*
                                          ;

instructionArgument                       : identifier
                                          | variable
                                          | ROLL_STRING
                                          ;

integerValue                              : ROLL_INTEGER_LITERAL
                                          ;

doubleValue                               : ROLL_DOUBLE_LITERAL
                                          ;



diceArguments                             : diceArgumentList
                                          ;

diceArgumentList                          : ROLL_LBRACE diceArgument ( ROLL_COMMA diceArgument )* ROLL_RBRACE
                                          | ROLL_LPAREN diceArgument ( ROLL_COMMA diceArgument )* ROLL_RPAREN
                                          ;


diceArgument                              : argName=ROLL_WORD op=( ROLL_LESS_THAN | ROLL_GREATER_THAN | ROLL_LESS_EQ_TO | ROLL_GREATER_EQ_TO | ROLL_NOT_EQUAL | ROLL_ASSIGN )? val=diceArgumentVal?
                                          ;


diceArgumentVal                           : identifier                                        # dargIdentifier
                                          | variable                                          # dargVariable
                                          | ROLL_STRING                                       # dargString
                                          | integerValue                                      # dargInteger
                                          | doubleValue                                       # dargDouble
                                          ;

diceName                                  : ROLL_WORD
                                          | ROLL_WORD ( integerValue | ROLL_WORD)* ROLL_WORD
                                          ;


identifier                                :  ROLL_WORD ( integerValue | ROLL_WORD)*
                                          ;
////////////////////////////////////////////////////////////////////////////////
startModule                               : OPEN_MODULE scriptModule;

scriptModule                              : scriptModuleDefinition scriptImports scriptModuleBody* scriptExports*;

scriptModuleDefinition                    : name=SCRIPT_IDENTIFIER version=SCRIPT_NUMBER_LITERAL desc=SCRIPT_STRING_LITERAL SCRIPT_SEMI;

scriptImports                             : scriptUses*;

scriptUses                                : SCRIPT_USE name=SCRIPT_IDENTIFIER scriptVersion (SCRIPT_AS SCRIPT_IDENTIFIER)? SCRIPT_SEMI;

scriptModuleBody                          :  constantDeclaration
                                          | fieldDeclaration
                                          | methodDeclaration
                                          ;


scriptVersion                             : SCRIPT_NUMBER_LITERAL
                                          | SCRIPT_STRING_LITERAL
                                          ;

scriptExports                             : SCRIPT_EXPORT SCRIPT_LBRACE (exported (SCRIPT_COMMA exported)*) SCRIPT_RBRACE SCRIPT_SEMI;

exported                                  : (SCRIPT_IDENTIFIER | externalProperty) (SCRIPT_AS SCRIPT_IDENTIFIER)? (SCRIPT_LBRACK exportDest SCRIPT_RBRACK)?;

exportDest                                : SCRIPT_INTERNAL
                                          | SCRIPT_CHAT (SCRIPT_LPAREN perm=(SCRIPT_GM | SCRIPT_TRUSTED) SCRIPT_RPAREN)?
                                          | SCRIPT_ROLL SCRIPT_LPAREN SCRIPT_DEFAULT SCRIPT_ASSIGN def=SCRIPT_DECIMAL_LITERAL SCRIPT_COMMA
                                            SCRIPT_ROLL SCRIPT_ASSIGN rollName=SCRIPT_IDENTIFIER
                                          ;

scriptBody                                : (statement | fieldDeclaration | constantDeclaration)* ;

literal			                              : integerLiteral
				                                  | SCRIPT_NUMBER_LITERAL
				                                  | SCRIPT_STRING_LITERAL
				                                  | SCRIPT_BOOL_LITERAL
				                                  | SCRIPT_NULL_LITERAL
				                                  ;

integerLiteral	                          : SCRIPT_DECIMAL_LITERAL
				                                  | SCRIPT_HEX_LITERAL
				                                  ;

methodDeclaration	                        : SCRIPT_FUNCTION SCRIPT_IDENTIFIER formalParameters block ;

formalParameters	                        : SCRIPT_LPAREN formalParameterList? SCRIPT_RPAREN ;

formalParameterList	                      : formalParameter (SCRIPT_COMMA formalParameter)* ;

formalParameter		                        : type variableDeclaratorId;

block	                                    : SCRIPT_LBRACE blockStatement* SCRIPT_RBRACE ;

blockStatement	                          : localVariableDeclaration SCRIPT_SEMI
				                                  | statement
				                                  ;

localVariableDeclaration	                : type variableDeclarators ;

statement		                              : blockLabel=block
				                                  | SCRIPT_ASSERT expression (SCRIPT_COLON expression)? SCRIPT_SEMI
				                                  | SCRIPT_IF parExpression block (SCRIPT_ELSE SCRIPT_IF parExpression block)* (SCRIPT_ELSE block)?
				                                  | SCRIPT_FOR SCRIPT_LPAREN forControl SCRIPT_RPAREN block
				                                  | SCRIPT_WHILE parExpression block
				                                  | SCRIPT_DO block SCRIPT_WHILE parExpression SCRIPT_SEMI
				                                  | SCRIPT_TRY block (catchClause+ finallyBlock? | finallyBlock)
				                                  | SCRIPT_SWITCH parExpression SCRIPT_LBRACE switchBlockStatementGroup* switchLabel* SCRIPT_RBRACE
				                                  | SCRIPT_RETURN expression? SCRIPT_SEMI
				                                  | SCRIPT_THROW expression SCRIPT_SEMI
				                                  | SCRIPT_BREAK SCRIPT_SEMI
				                                  | SCRIPT_CONTINUE SCRIPT_SEMI
				                                  | SCRIPT_SEMI
				                                  | statementExpression=expression SCRIPT_SEMI
				                                  ;



catchClause		                            : SCRIPT_CATCH SCRIPT_LPAREN SCRIPT_IDENTIFIER SCRIPT_RPAREN block ;

finallyBlock	                            : SCRIPT_FINALLY block ;

switchBlockStatementGroup	                : switchLabel+ blockStatement+ ;

switchLabel		                            : SCRIPT_CASE constantExpression=expression SCRIPT_COLON
				                                  | SCRIPT_DEFAULT SCRIPT_COLON
				                                  ;

forControl		                            : foreachControl
				                                  | forInit? SCRIPT_SEMI expression? SCRIPT_SEMI forUpdate=expressionList?
				                                  ;

forInit                                   : localVariableDeclaration
                                          | expressionList
                                          ;

foreachControl 	                          : type variableDeclaratorId ':' expression ;

parExpression	                            : SCRIPT_LPAREN expression SCRIPT_RPAREN ;

expressionList                            : expression (SCRIPT_COMMA expression)* ;

methodCall 		                            : SCRIPT_IDENTIFIER SCRIPT_LPAREN expressionList? SCRIPT_RPAREN ;

expression                                : SCRIPT_LPAREN expression SCRIPT_RPAREN
                                          | literal
                                          | SCRIPT_IDENTIFIER
                                          | expression bop=SCRIPT_DOT ( SCRIPT_IDENTIFIER | methodCall )
                                          | expression SCRIPT_LBRACK expression SCRIPT_RBRACK
                                          | methodCall
                                          | prefix=SCRIPT_BANG expression
                                          | expression bop=(SCRIPT_MUL | SCRIPT_DIV | SCRIPT_MOD) expression
                                          | expression bop=(SCRIPT_ADD | SCRIPT_SUB) expression
                                          | expression bop=(SCRIPT_LE | SCRIPT_GE | SCRIPT_GT | SCRIPT_LT) expression
                                          | expression bop=SCRIPT_INSTANCEOF type
                                          | expression bop=(SCRIPT_EQUAL | SCRIPT_NOTEQUAL) expression
                                          | expression bop=SCRIPT_BITAND expression
                                          | expression bop=SCRIPT_CARET expression
                                          | expression bop=SCRIPT_BITOR expression
                                          | expression bop=SCRIPT_AND expression
                                          | expression bop=SCRIPT_OR expression
                                          | expression bop=SCRIPT_QUESTION expression ':' expression
                                          | expression postfix=(SCRIPT_INC | SCRIPT_DEC)
                                          | <assoc=right> expression bop=(SCRIPT_ASSIGN | SCRIPT_ADD_ASSIGN | SCRIPT_SUB_ASSIGN | SCRIPT_MUL_ASSIGN | SCRIPT_DIV_ASSIGN | SCRIPT_AND_ASSIGN | SCRIPT_OR_ASSIGN | SCRIPT_XOR_ASSIGN | SCRIPT_MOD_ASSIGN ) expression
                                          | externalProperty
                                          | SCRIPT_OPEN_INLINE_ROLL diceRolls CLOSE_INLINE_ROLL
                                          ;

externalProperty                          : SCRIPT_EXT_PROP_PREFIX externalPropertyName;

externalPropertyName                      : (SCRIPT_IDENTIFIER SCRIPT_DOT)* SCRIPT_IDENTIFIER
                                          ;

fieldDeclaration                          : type variableDeclarators SCRIPT_SEMI;

constantDeclaration                       : type constantDeclarator (SCRIPT_COMMA constantDeclarator)* SCRIPT_SEMI;

constantDeclarator                        : SCRIPT_IDENTIFIER (SCRIPT_LBRACK SCRIPT_RBRACK)* SCRIPT_ASSIGN variableInitializer ;

variableDeclarators                       : variableDeclarator (SCRIPT_COMMA variableDeclarator)* ;

variableDeclarator                        : variableDeclaratorId ( SCRIPT_ASSIGN variableInitializer)? ;

variableDeclaratorId                      : SCRIPT_IDENTIFIER ( SCRIPT_LBRACK SCRIPT_RBRACK )* ;

variableInitializer                       : arrayInitializer
                                          | expression
                                          ;

arrayInitializer                          : SCRIPT_LBRACE (variableInitializer ( SCRIPT_COMMA variableInitializer )* (SCRIPT_COMMA)? )? SCRIPT_RBRACE ;

////////

arguments                                 : SCRIPT_LPAREN expressionList? SCRIPT_RPAREN ;

type                                      : SCRIPT_BOOLEAN
                                          | SCRIPT_INTEGER
                                          | SCRIPT_NUMBER
                                          | SCRIPT_STRING
                                          | SCRIPT_ROLL
                                          | SCRIPT_DICT
                                          ;
