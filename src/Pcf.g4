grammar Pcf;

term: VAR # Variable
    | FUN VAR '->' term # Function
    | term term #App
    | CONST #Constant
    | term OP term #BinaryOperation
    | IFZ term THEN term ELSE term #IfZero
    | FIX VAR term #Fix
    | LET VAR '=' term IN term #Let;


FUN: 'fun';
CONST: '0'|[1-9][0-9]* ;
OP: '+' | '-' | '*' | '/';
IFZ: 'ifz';
THEN: 'then';
ELSE: 'else';
FIX: 'fix';
LET: 'let';
IN: 'in';
VAR: [a-z] [a-z0-9]*;
WS: ('\n' | '\r' | '\t' | ' ') -> skip;