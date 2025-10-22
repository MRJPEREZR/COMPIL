package pcf

import org.antlr.v4.runtime._
import parser._
import evaluator._
import parserANTLR._
import org.scalatest.funsuite.AnyFunSuite

class PcfTest extends AnyFunSuite {

  private val visitor = new ASTBuilder

  private def parseTerm(input: String): Term = {
    val inputStream = CharStreams.fromString(input)
    val lexer = new PcfLexer(inputStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new PcfParser(tokens)
    val tree = parser.term()
    visitor.visit(tree)
  }

  private def evalExpr(expr: String): Value =
    Evaluator.eval(parseTerm(expr))

  // PCF blue and green test cases
  test("let x = 1 in x + 1 → 2") {
    assert(evalExpr("let x = 1 in x + 1") == IntValue(2))
  }

  test("let x = 1 in y → error") {
    intercept[RuntimeException] {
      evalExpr("let x = 1 in y")
    }
  }

  test("let x = 1 in let y = 2 in x + y → 3") {
    assert(evalExpr("let x = 1 in let y = 2 in x + y") == IntValue(3))
  }

  test("let x = 1 in let x = 2 in x → 2") {
    assert(evalExpr("let x = 1 in let x = 2 in x") == IntValue(2))
  }

  test("let x = 1 in let x = x + 1 in x → 2") {
    assert(evalExpr("let x = 1 in let x = x + 1 in x") == IntValue(2))
  }

  test("let x = 1 in (let x = 2 in x) + x → 3") {
    assert(evalExpr("let x = 1 in (let x = 2 in x) + x") == IntValue(3))
  }

  test("let x = (let y = 1 in y) in y → error") {
    intercept[RuntimeException] {
      evalExpr("let x = (let y = 1 in y) in y")
    }
  }

  test("let cond = ifz 4 - 2 then 3 - 2 * 2 else 1 in 2 / cond → 2") {
    assert(evalExpr("let cond = ifz 4 - 2 then 3 - 2 * 2 else 1 in 2 / cond") == IntValue(2))
  }

  // PCF red test cases
  test("fun x -> 0 evaluates to a closure") {
    val result = evalExpr("fun x -> 0")
    assert(result.toString == "Closure(x,Constant(0),Map())")
  }

  test("(fun x -> 0) + 1 → error (operands must be integers)") {
    intercept[RuntimeException] {
      evalExpr("(fun x -> 0) + 1")
    }
  }

  test("(fun x -> 0) 1 evaluates to 0") {
    val result = evalExpr("(fun x -> 0) 1")
    assert(result.toString == "IntValue(0)")
  }

  test("(fun x -> x + 1) 1 evaluates to 2") {
    val result = evalExpr("(fun x -> x + 1) 1")
    assert(result.toString == "IntValue(2)")
  }

  test("let zero = fun x -> 0 in zero 1 evaluates to 0") {
    val result = evalExpr("let zero = fun x -> 0 in zero 1")
    assert(result.toString == "IntValue(0)")
  }

  test("(fun y -> let x = 1 in x + 1) 41 evaluates to 2") {
    val result = evalExpr("(fun y -> let x = 1 in x + 1) 41")
    assert(result.toString == "IntValue(2)")
  }

  test("(fun x -> fun y -> x + y) 2 41 evaluates to 43") {
    val result = evalExpr("(fun x -> fun y -> x + y) 2 41")
    assert(result.toString == "IntValue(43)")
  }

  test("let minus = fun x -> fun y -> x - y in let g = minus 68 in g 2 evaluates to 66") {
    val result = evalExpr("let minus = fun x -> fun y -> x - y in let g = minus 68 in g 2")
    assert(result.toString == "IntValue(66)")
  }

  test("let id = fun x -> x in let inc = fun x -> x + 1 in id (inc 76) evaluates to 77") {
    val result = evalExpr("let id = fun x -> x in let inc = fun x -> x + 1 in id (inc 76)")
    assert(result.toString == "IntValue(77)")
  }

  // PCF black test cases
  test("ifz 0 then 1 else (fix x x) evaluates to 1") {
    val result = evalExpr("ifz 0 then 1 else (fix x x)")
    assert(result.toString == "IntValue(1)")
  }

  test("let count = fix f fun n -> ifz n then 0 else f (n - 1) in count 2 evaluates to 0") {
    val result = evalExpr(
      "let count = fix f fun n -> ifz n then 0 else f (n - 1) in count 2"
    )
    assert(result.toString == "IntValue(0)")
  }

  test("let fact = fix f fun n -> ifz n then 1 else n * f (n - 1) in fact 3 evaluates to 6") {
    val result = evalExpr(
      "let fact = fix f fun n -> ifz n then 1 else n * f (n - 1) in fact 3"
    )
    assert(result.toString == "IntValue(6)")
  }

  test("let multiply = fix m fun a -> fun b -> ifz a then 0 else b + m (a - 1) b in multiply 3 4 evaluates to 12") {
    val result = evalExpr(
      "let multiply = fix m fun a -> fun b -> ifz a then 0 else b + m (a - 1) b in multiply 3 4"
    )
    assert(result.toString == "IntValue(12)")
  }

}
