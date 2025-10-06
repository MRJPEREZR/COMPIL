grammar Pcf;

term
    : letTerm
    | funTerm
    | addTerm
    ;

letTerm
    : LET VAR '=' term IN term          # Let
    | FIX VAR term                      # Fix
    | IFZ term THEN term ELSE term      # IfZero
    ;

funTerm
    : FUN VAR '->' term                 # Function
    | addTerm                           # BinTerm
    ;

addTerm
    : mulTerm ((PLUS | MINUS) mulTerm)* # AddSub
    ;

mulTerm
    : appTerm ((MULT | DIV) appTerm)*   # MulDiv
    ;

appTerm
    : atom+                             # Application
    ;

atom
    : VAR                               # Variable
    | CONST                             # Constant
    | '(' term ')'                      # Parentheses
    ;

// ===== LEXER RULES =====

FUN: 'fun';
IFZ: 'ifz';
THEN: 'then';
ELSE: 'else';
FIX: 'fix';
LET: 'let';
IN: 'in';

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';

CONST: '0' | [1-9][0-9]*;
VAR: [a-z][a-z0-9]*;
WS: [ \t\r\n]+ -> skip;
