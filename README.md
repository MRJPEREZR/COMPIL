# PCF Interpreter — ANTLR + Scala

## Authors

* PUNZANO CORIMBABA Leandro
* PEREZ RAMIREZ Julian

## Descriptions

This project implements a small interpreter for the **PCF (Programming Computable Functions)** language using **ANTLR 4** for parsing and **Scala 3** for evaluation.

It includes:
- A grammar definition (`Pcf.g4`)
- ANTLR-generated parser/lexer classes
- An AST builder in Scala
- A typer for parsed expressions
- An evaluator for parsed and well-typed expressions
- A test suite for parsing and evaluation

## To test

Please run the main function located at `src/main/scala/pcf/Pcf.scala`. There, we call the parser, typer and evaluator objects to prompt the final expression parsed and its result evaluated. 

According to the example provided, the test suite contains the following cases:

### PCF blue and green
```sh
> let x = 1 in x + 1
AST: Let(x,Constant(1),BinaryOperation(Variable(x),+,Constant(1)))
Result: IntValue(2)
> let x = 1 in y
AST: Let(x,Constant(1),Variable(y))
Error: Unbound variable: y
> let x = 1 in let y = 2 in x + y
AST: Let(x,Constant(1),Let(y,Constant(2),BinaryOperation(Variable(x),+,Variable(y))))
Result: IntValue(3)
> let x = 1 in let x = 2 in x
AST: Let(x,Constant(1),Let(x,Constant(2),Variable(x)))
Result: IntValue(2)
> let x = 1 in let x = x + 1 in x
AST: Let(x,Constant(1),Let(x,BinaryOperation(Variable(x),+,Constant(1)),Variable(x)))
Result: IntValue(2)
> let x = 1 in (let x = 2 in x) + x
AST: Let(x,Constant(1),BinaryOperation(Let(x,Constant(2),Variable(x)),+,Variable(x)))
Result: IntValue(3)
> let x = (let y = 1 in y) in y
AST: Let(x,Let(y,Constant(1),Variable(y)),Variable(y))
Error: Unbound variable: y
> let cond = ifz 4 - 2 then 3 - 2 * 2 else 1 in 2 / cond
AST: Let(cond,IfZero(BinaryOperation(Constant(4),-,Constant(2)),BinaryOperation(Constant(3),-,BinaryOperation(Constant(2),*,Constant(2))),Constant(1)),BinaryOperation(Constant(2),/,Variable(cond)))
Result: IntValue(2)
```

### Pcf red

```sh
> fun x -> 0
AST: Function(x,Constant(0))
Result: Closure(x,Constant(0),Map())
> (fun x -> 0) + 1
AST: BinaryOperation(Function(x,Constant(0)),+,Constant(1))
Error: Operands must be integers
> (fun x -> 0) 1
AST: Application(Function(x,Constant(0)),Constant(1))
Result: IntValue(0)
> (fun x -> x + 1) 1
AST: Application(Function(x,BinaryOperation(Variable(x),+,Constant(1))),Constant(1))
Result: IntValue(2)
> let zero = fun x -> 0 in zero 1
AST: Let(zero,Function(x,Constant(0)),Application(Variable(zero),Constant(1)))
Result: IntValue(0)
> (fun y -> let x = 1 in x + 1) 41
AST: Application(Function(y,Let(x,Constant(1),BinaryOperation(Variable(x),+,Constant(1)))),Constant(41))
Result: IntValue(2)
>  (fun x -> fun y -> x + y) 2 41
AST: Application(Application(Function(x,Function(y,BinaryOperation(Variable(x),+,Variable(y)))),Constant(2)),Constant(41))
Result: IntValue(43)
> let minus = fun x -> fun y -> x - y in let g = minus 68 in g 2
AST: Let(minus,Function(x,Function(y,BinaryOperation(Variable(x),-,Variable(y)))),Let(g,Application(Variable(minus),Constant(68)),Application(Variable(g),Constant(2))))
Result: IntValue(66)
> let id = fun x -> x in let inc = fun x -> x + 1 in id (inc 76)
AST: Let(id,Function(x,Variable(x)),Let(inc,Function(x,BinaryOperation(Variable(x),+,Constant(1))),Application(Variable(id),Application(Variable(inc),Constant(76)))))
Result: IntValue(77)
```

### Pcf black
```sh
> fix x 1
AST: Fix(x,Constant(1))
Error: Fix expects a function, got: Constant(1)
> fix x x
AST: Fix(x,Variable(x))
Error: Unbound variable: x
> ifz 0 then 1 else (fix x x)
AST: IfZero(Constant(0),Constant(1),Fix(x,Variable(x)))
Result: IntValue(1)
> let count = fix f fun n -> ifz n then 0 else f (n - 1) in count 2
AST: Let(count,Fix(f,Function(n,IfZero(Variable(n),Constant(0),Application(Variable(f),BinaryOperation(Variable(n),-,Constant(1)))))),Application(Variable(count),Constant(2)))
Result: IntValue(0)
> let fact = fix f fun n -> ifz n then 1 else n * f (n - 1) in fact 3
AST: Let(fact,Fix(f,Function(n,IfZero(Variable(n),Constant(1),BinaryOperation(Variable(n),*,Application(Variable(f),BinaryOperation(Variable(n),-,Constant(1))))))),Application(Variable(fact),Constant(3)))
Result: IntValue(6)
> let multiply = fix m fun a -> fun b -> ifz a then 0 else b + m (a - 1) b in multiply 3 4
AST: Let(multiply,Fix(m,Function(a,Function(b,IfZero(Variable(a),Constant(0),BinaryOperation(Variable(b),+,Application(Application(Variable(m),BinaryOperation(Variable(a),-,Constant(1))),Variable(b))))))),Application(Application(Variable(multiply),Constant(3)),Constant(4)))
Result: IntValue(12)
```
Test files are located under src/test/scala:

* parser/ParserTest.scala — validates parsing (AST structure)
* pcf/PcfTest.scala — validates full evaluation

**Dependency:** Make sure you have ScalaTest 3.2.19 (org.scalatest:scalatest_3:3.2.19) configured in your project.

## Project Structure

```sh
src
├── main
│   ├── java
│   │   └── parserANTLR
│   │       ├── ConcreteParser.java
│   │       ├── ErrorFlag.java
│   │       ├── ErrorListener.java
│   │       ├── PcfBaseListener.java
│   │       ├── PcfBaseVisitor.java
│   │       ├── Pcf.interp
│   │       ├── PcfLexer.interp
│   │       ├── PcfLexer.java
│   │       ├── PcfLexer.tokens
│   │       ├── PcfListener.java
│   │       ├── PcfParser.java
│   │       ├── Pcf.tokens
│   │       ├── PcfVisitor.java
│   │       ├── ReportingPcfLexer.java
│   │       └── SyntaxError.java
│   └── scala
│       ├── evaluator
│       │   └── Evaluator.scala
│       ├── parser
│       │   ├── AbstractParser.scala
│       │   ├── ASTBuilder.scala
│       │   ├── AST.scala
│       │   └── TestParser.scala
│       ├── pcf
│       │   └── Pcf.scala
│       └── typer
│           └── Typer.scala
├── Pcf.g4
└── test
└── scala
    ├── parser
    │   └── ParserTest.scala
    └── pcf
        └── PcfTest.scala
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

# What is working?

PCF blue, green and red are working totally, while PCF black is partially due to some misbehavior with FIX (it accepts only FUN as argument, not INT). At the same time, for typing, in some expressions it returns a generic value "X" because it is not able to reduce it to one root type.

# Polymorphism of `fun x -> x`
The identity `fun x -> x` is polymorphic in the sense that it can be used with different types, but each particular use must have a consistent type.
```sh
> fun x -> x
AST: Function(x,Variable(x))
Type: (X0 -> X0)
Result: Closure(x,Variable(x),Map())
```
For each application, the variable of type X0 is instantiated with the concrete type of the argument:

Applied to 5 (INT): X0 becomes INT type (INT -> INT)
```sh
> let id = fun x -> x in id 5
AST: Let(id,Function(x,Variable(x)),Application(Variable(id),Constant(5)))
Type: Atom(INT)
Result: IntValue(5)
```

# Use of Generative AI

Yes, we used it in order to help us to fix some problems with Scala syntax (specially to implement recursion), and also to explain to us with concrete examples of how to use the ANTLR autogenerated code.