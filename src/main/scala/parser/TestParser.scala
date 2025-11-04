package parser

import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import org.antlr.v4.runtime._
import parserANTLR._
import org.antlr.v4.runtime.tree.ParseTree

object TestParser {

  private val visitor = new ASTBuilder

  private def parseTerm(input: String): Term = {
    val bytes = input.getBytes(StandardCharsets.UTF_8)
    val stream = new ByteArrayInputStream(bytes)

    try {
      val tree: ParseTree = ConcreteParser.analyze(stream)
      visitor.visit(tree)
    } finally {
      stream.close()
    }
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