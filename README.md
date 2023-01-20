# DSL-Kisled-AI
DSL for Data Mining

## Authors
* Alexandre Arcil
* Gabriel Cogne
* Thomas Di Grande
* Dan Nakache

## Domain Model

## Backus-Naur Form
```bnf
app ::= read_stmt stmts char_stmt ;

stmts ::= stmt stmts | /* No statements */ ;

stmt ::= read_stmt | op_stmt | algo_stmt | validation_stmt | disp_stmt ;

read_stmt ::= "read" "(" PATH ")" ">>" var ;

op_stmt ::= (select_op | apply_op | mapping_op) ">>" var ;

select_op ::= var "[" selectors "]" ;
selectors ::= selector "," selectors | selector ;
selector ::= INT | STRING | slice ;
slice ::= "r" "(" (INT "," INT "," INT | INT "," INT | INT | /* All elements slice */) ")" ;

apply_op ::= var ("+" value | "-" value | "-" STRING | "*" value | "/" value | "**" value | ".apply" "{" lambda "}") ;

mapping_op ::= var ">>" dict ;

algo_stmt ::=   ( "KNN" "(" "n_neighbors" ":" (value | random) "," "algorithm" ":" (STRING | choice | list) ")" 
                | "RandomForest" "(" "n_estimators" ":" (value | random) "," "max_depth" ":" (value | random) ")"
                | "LogisticRegression" "(" "max_iter" ":" (value | random) ")"
                | "GaussianNB" "(" ")"
                | "DecisionTreeClassifier" "(" ")"
                | "GradientBoostingClassifier" "(" "n_estimators" ":" (value | random) ")"
                | "LinearSVC" "(" "C" ":" (value | random) ")"
                | "MLPClassifier" "(" "max_iter" ":" (value | random) ")"
                ) ">>" var
                ;

random ::= "btw" "(" value "," value ")" ;
choice ::= "choice" "(" list ")" ;

validation_stmt ::= "validate" "(" var "," var "," var ")" ">>" var

disp_stmt ::= "disp" names | "disp" "(" names ")"

char_stmt ::= "chart" "(" STRING "," STRING "," STRING ")" | "chart" STRING "," STRING "," STRING ;

lambda ::= names "->" expr
names ::= NAME ", " names | NAME
expr ::=      value 
            | BOOLEAN 
            | STRING
            | "++" var
            | var "++"
            | "--" var
            | var "--"
            | var
            | call
            | "-" var
            | "+" var
            | expr "+" expr
            | expr "-" expr
            | expr "*" expr
            | expr "/" expr
            | expr "%" expr
            | expr "<" expr
            | expr ">" expr
            | expr "==" expr
            | expr "!=" expr
            | expr "<=" expr
            | expr ">=" expr
            | expr "**" expr
            ;

var ::= NAME
call ::= var "(" eparam_list  ")"
eparam_list ::= expr ("," eparam_list | /* End of list */) | /* No parameters */

value ::= INT | FLOAT ;
list ::= "[" (list_elems | /* Empty list */) "]" ;
list_elems ::= (value | STRING) "," (list_elems | /* End of list */) ;
dict ::= "[" (dict_elems | ":") "]" ;
dict_elems ::= "'" NAME "'" ":" (value | STRING | dict | list) ("," dict_elems | /* End of dict */ ) ;
```