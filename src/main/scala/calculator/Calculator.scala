package calculator

import parser._
import evaluator._
import org.antlr.v4.runtime._
import parserANTLR._

object Calculator {

  private val visitor = new ASTBuilder

  private def parseTerm(input: String): Term = {
    val inputStream = CharStreams.fromString(input)
    val lexer = new PcfLexer(inputStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new PcfParser(tokens)
    val tree = parser.term()
    visitor.visit(tree)
  }

  def main(args: Array[String]): Unit = {
    println("PCF Calculator - Type expressions (Ctrl+D to exit):")

    Iterator
      .continually(scala.io.StdIn.readLine("> "))
      .takeWhile(_ != null)
      .foreach { line =>
        try {
          val ast = parseTerm(line)
          println(s"AST: $ast")

          val result = Evaluator.eval(ast)
          println(s"Result: $result\n")
        } catch {
          case e: Exception =>
            println(s"Error: ${e.getMessage}\n")
        }
      }
  }
}
