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
  
  test("let fact = fix fun f n -> ifz n then 1 else n * f (n - 1) in fact 4 → 24") {
    assert(evalExpr("let fact = fix fun f n -> ifz n then 1 else n * f (n - 1) in fact 4") == IntValue(24))
  }
}
