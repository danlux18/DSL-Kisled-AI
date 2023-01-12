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
app::= inputs vars stmts ;

text::= "text" TEXT ;

inputs::= text inputs | input inputs | input ;

input::= "input" PATH "as" NAME ;

vars::= text vars | var vars | ;

var::= "set" NAME "=" var_ops ;

var_ops::= NAME | "map" dict NAME | "map" fct NAME | "appy" fct NAME | "select" filter NAME | "drop" NAME NAME ;

dict::= "{" dict_items "}"

dict_items::= dict_item | dict_item "," dict_items

dict_item::= key "," value

fct::= "func" NAME "->" OPS ";"

stmts::= 

```