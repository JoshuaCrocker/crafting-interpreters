# Crafting Interpreters

## JLox Specification

```abnf
program         -> declaration* EOF ;
declaration     -> varDecl | statement ;
varDecl         -> "var" IDENTIFIER ( "=" expression )? ";" ;
statement       -> exprStmt | ifStmt | printStmt | block ;
block           -> "{" declaration* "}" ;
exprStmt        -> expression ";" ;
ifStmt          -> "if" "(" expression ")" statement ( "else" statement )? ;
printStmt       -> "print" expression ";" ;
expression      -> assignment ;
assignment      -> IDENTIFIER "=" assignment | logic_or ;
logic_or        -> logic_and ( "or" logic_and )* ;
logic_and       -> equality ( "and" equality )* ;
equality        -> comparison ( ( "!=" | "==" ) comparison )* ;
comparison      -> term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
term            -> factor ( ( "-" | "+" ) factor)* ;
factor          -> unary ( ( "/" | "*" ) unary )* ;
unary           -> ( "-" | "!" ) expression | primary ;
primary         -> NUMBER | STRING | "true" | "false" | "nil" | "(" expression ")" ;
```
