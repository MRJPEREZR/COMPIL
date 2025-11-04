package pcf

import parser.*
import typer.*
import evaluator.*
import generator._
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

  private def interpret(in: String): String =
    val term = parseTerm(in)
    val typ = Typer.eval(term, Map())
    val value = Evaluator.eval(term, Map())
    s"$value:$typ"

  private def compile(in: String): List[Ins] =
    val term = parseTerm(in)
    val code = Generator.gen(term)
    if check(term, code) then code
    else throw Exception("Implementation Error")

  private def check(term: Term, code: List[Ins]): Boolean =
    val value = Evaluator.eval(term, Map())
    println(value)
    //println(code) // in case the execution fails
    val value2 = vm.VM.execute(code)
    value2.toString == value.toString // valid only for PCF green and blue

  def main(args: Array[String]): Unit = {
    println("PCF Interpreter - Type expressions (Ctrl+D to exit):")

    Iterator
      .continually(scala.io.StdIn.readLine("> "))
      .takeWhile(_ != null)
      .foreach { line =>
        try {
          if (args.contains("-i")) println(s"==> ${interpret(line)}")
          else println(compile(line))
        } catch {
          case e: Exception =>
            println(s"Error: ${e.getMessage}\n")
        }
      }
  }
}
