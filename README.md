# PCF Interpreter — ANTLR + Scala

This project implements a small interpreter for the **PCF (Programming Computable Functions)** language using **ANTLR 4** for parsing and **Scala 3** for evaluation.

It includes:
- A grammar definition (`Pcf.g4`)
- ANTLR-generated parser/lexer classes
- An AST builder in Scala
- An evaluator for expressions
- A test suite for parsing and evaluation

## Project Structure

```sh
src
├── main
│   ├── java
│   │   └── parserANTLR
│   │       ├── PcfBaseListener.java
│   │       ├── PcfBaseVisitor.java
│   │       ├── Pcf.interp
│   │       ├── PcfLexer.interp
│   │       ├── PcfLexer.java
│   │       ├── PcfLexer.tokens
│   │       ├── PcfListener.java
│   │       ├── PcfParser.java
│   │       ├── Pcf.tokens
│   │       └── PcfVisitor.java
│   └── scala
│       ├── calculator
│       │   └── Calculator.scala
│       ├── evaluator
│       │   └── Evaluator.scala
│       └── parser
│           ├── AbstractParser.scala
│           ├── ASTBuilder.scala
│           ├── AST.scala
│           └── TestParser.scala
├── Pcf.g4
└── test
    └── scala
        ├── calculator
        │   └── CalculatorTest.scala
        └── parser
            └── ParserTest.scala
```

# Grammar definition


```antlr
term
    : letTerm
    | binaryTerm
    ;

letTerm
    : LET VAR '=' term IN term # Let
    | FIX VAR term # Fix
    | IFZ term THEN term ELSE term # IfZero
    | funTerm # FuncTerm
    ;

funTerm
    : FUN VAR '->' term # Function
    | binaryTerm # BinTerm
    ;

binaryTerm
    : appTerm (OP appTerm)* # BinaryOperation
    ;

appTerm
    : atom+ # Application
    ;

atom
    : VAR # Variable
    | CONST # Constant
    | '(' term ')' # Parentheses
    ;
```

# Generating ANTLR Parser Files

To generate the Java parser and lexer classes under src/main/java/parserANTLR:

1. Install the ANTLR v4 plugin for IntelliJ (Preferences → Plugins → Search for “ANTLR v4”).
2. Open Pcf.g4.
3. Click the “Generate ANTLR Recognizer” icon (the green “G”).
Make sure Output directory is set to:

```sh
src/main/java/parserANTLR
```

# Scala components

* AST.scala
  Defines the abstract syntax tree (AST) for PCF terms:
* ASTBuilder.scala
  A visitor that walks the ANTLR parse tree and constructs the AST in Scala.
* Evaluator.scala
  Interprets (evaluates) AST nodes in an environment.
* Calculator.scala
  Combines parsing + evaluation, printing both the AST and final result.
  **Run this file to test the REPL and test your PCF expressions.**

# Test Suite

Test files are located under src/test/scala:

* parser/ParserTest.scala — validates parsing (AST structure)
* calculator/CalculatorTest.scala — validates full evaluation

**Dependency:** Make sure you have ScalaTest 3.2.19 configured in your project.

Test case example:

```sh
> let x = 1 in x + 1
AST:
Let(x, Constant(1), BinaryOperation(Variable(x), +, Constant(1)))
Result:
IntValue(2)
```