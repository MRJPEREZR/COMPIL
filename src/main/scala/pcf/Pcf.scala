package pcf

import parser.*
import typer.*
import evaluator.*
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTree
import parserANTLR.*

import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets

object Pcf {

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
    println("PCF Interpreter - Type expressions (Ctrl+D to exit):")

    Iterator
      .continually(scala.io.StdIn.readLine("> "))
      .takeWhile(_ != null)
      .foreach { line =>
        try {
          val ast = parseTerm(line)
          println(s"AST: $ast")
          val typ = Typer.eval(ast, Map())
          println(s"Type: $typ")
          val result = Evaluator.eval(ast)
          println(s"Result: $result\n")
        } catch {
          case e: Exception =>
            println(s"Error: ${e.getMessage}\n")
        }
      }
  }
}
