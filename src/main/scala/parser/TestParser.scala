package parser

import org.antlr.v4.runtime._
import parserANTLR._

object TestParser {

  private val visitor = new ASTBuilder

  def parseTerm(input: String): Term = {
    val inputStream = CharStreams.fromString(input)
    val lexer = new PcfLexer(inputStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new PcfParser(tokens)
    val tree = parser.term() // root rule
    visitor.visit(tree)
  }

  def main(args: Array[String]): Unit = {
    println("PCF Parser CLI. Type expressions (Ctrl+D to exit):")

    Iterator
      .continually(scala.io.StdIn.readLine("> "))
      .takeWhile(_ != null)
      .foreach { line =>
        try {
          val ast = parseTerm(line)
          println("AST:")
          println(ast)
        } catch {
          case e: Exception => println(s"Error: ${e.getMessage}")
        }
      }
  }
}
