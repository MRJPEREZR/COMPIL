package parser

import org.antlr.v4.runtime._
import parserANTLR._
import org.scalatest.funsuite.AnyFunSuite

class ParserTest extends AnyFunSuite {

  private val visitor = new ASTBuilder

  private def parseTerm(input: String): Term = {
    val inputStream = CharStreams.fromString(input)
    val lexer = new PcfLexer(inputStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new PcfParser(tokens)
    val tree = parser.term()
    visitor.visit(tree)
  }

  private def testAst(expr: String, expectedAst: Term): Unit = {
    val ast = parseTerm(expr)
    assert(ast == expectedAst, s"AST mismatch for `$expr`\nExpected: $expectedAst\nFound: $ast")
  }

  test("let x = 1 in x + 1") {
    testAst("let x = 1 in x + 1",
      Let("x", Constant(1),
        BinaryOperation(Variable("x"), "+", Constant(1))))
  }

  test("let x = 1 in y") {
    testAst("let x = 1 in y",
      Let("x", Constant(1), Variable("y")))
  }

  test("let x = 1 in let y = 2 in x + y") {
    testAst("let x = 1 in let y = 2 in x + y",
      Let("x", Constant(1),
        Let("y", Constant(2),
          BinaryOperation(Variable("x"), "+", Variable("y")))))
  }

  test("let x = 1 in let x = 2 in x") {
    testAst("let x = 1 in let x = 2 in x",
      Let("x", Constant(1),
        Let("x", Constant(2), Variable("x"))))
  }

  test("let x = 1 in let x = x + 1 in x") {
    testAst("let x = 1 in let x = x + 1 in x",
      Let("x", Constant(1),
        Let("x", BinaryOperation(Variable("x"), "+", Constant(1)), Variable("x"))))
  }

  test("let x = 1 in (let x = 2 in x) + x") {
    testAst("let x = 1 in (let x = 2 in x) + x",
      Let("x", Constant(1),
        BinaryOperation(Let("x", Constant(2), Variable("x")), "+", Variable("x"))))
  }

  test("let x = (let y = 1 in y) in y") {
    testAst("let x = (let y = 1 in y) in y",
      Let("x", Let("y", Constant(1), Variable("y")), Variable("y")))
  }

  test("let cond = ifz 4 - 2 then 3 - 2 * 2 else 1 in 2 / cond") {
    testAst(
      "let cond = ifz 4 - 2 then 3 - 2 * 2 else 1 in 2 / cond",
      Let("cond",
        IfZero(
          BinaryOperation(Constant(4), "-", Constant(2)),
          BinaryOperation(Constant(3), "-", BinaryOperation(Constant(2), "*", Constant(2))),
          Constant(1)
        ),
        BinaryOperation(Constant(2), "/", Variable("cond"))
      )
    )
  }
  
  test("let fact = fix fun f n -> ifz n then 1 else n * f (n - 1) in fact 4") {
    testAst(
      "let fact = fix fun f n -> ifz n then 1 else n * f (n - 1) in fact 4",
      Let("fact",
        FixFunction(
          "f",
          "n",
          IfZero(
            Variable("n"),
            Constant(1),
            BinaryOperation(Variable("n"),"*",Application(Variable("f"),BinaryOperation(Variable("n"),"-",Constant(1))))
          )
        ),
        Application(
          Variable("fact"),
          Constant(4)
        )
      )
    )
  }
}
