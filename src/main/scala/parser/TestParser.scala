package parser

import org.antlr.v4.runtime._
import parserANTLR._

object TestParser {

  private val visitor = new ASTBuilder

  private def parseTerm(input: String): Term = {
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

// (fun x -> 0) + 1 -> error
// (fun y -> let x = 1 in x + 1) 41 -> 2
// let minus = fun x -> fun y -> x - y in let g = minus 68 in g 2 -> 66
// let id = fun x -> x in let inc = fun x -> x + 1 in id (inc 76) -> 77

// fix x 1
// fix x x
// let count = fix f fun n -> ifz n then 0 else f (n - 1) in count 2
// let multiply = fix m fun a -> fun b -> ifz a then 0 else b + m (a - 1) b in multiply 3 4