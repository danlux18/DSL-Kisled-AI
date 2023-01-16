# DSL-Kisled-AI
DSL for Data Mining

## Authors
* Alexandre Arcil
* Gabriel Cogne
* Thomas Di Grande
* Dan Nakache

## Domain Model

## Backus-Naur Form
Version 1
```bnf
app ::= stmts final_result ;

stmts ::= stmt stmts | stmt ;

stmt ::= var_stmt | result_stmt ;

var_stmt ::= NAME '=' init_stmt ;

init_stmt ::= INT | '"' STRING '"' | array | function ;

array ::= '[' array_elems ']' | '[' dict_elems ']' | '[' ']' ;

array_elems ::= init_stmt ',' array_elems | init_stmt ;

dict_elems ::= '"' STRING '"' ':' init_stmt ',' dict_elems |  '"' STRING '"' ':' init_stmt ;

function ::= NAME '(' params ')' | NAME params | NAME '(' ')' ;

params ::= param ',' params | param ;

param ::= NAME ':' init_stmt | init_stmt ;

result_stmt ::= disp | chart ;

disp ::= 'disp' params | 'disp' '(' params ')' ;

chart ::= 'chart' TYPE "," values | 'chart' '(' TYPE ',' values ')' ;

values ::= value ',' values | value ;

value ::= NAME | init_stmt ;

final_result ::= /* Left by default */ ;
```