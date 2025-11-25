package pcf

import parser.*
import typer.*
import evaluator.*
import generator.*
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTree
import parserANTLR.*

import java.io.{ByteArrayInputStream, FileWriter}
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

  def compile(verbose: Boolean, check_am: Boolean, in: String, filename: Option[String]): Unit =
    val term = parseTerm(in)
    val aTerm = Term.annotate(term, List())
    println(s"annotated AST = $aTerm")

    if check_am then
      val code = Generator.genAM(aTerm)
      if verbose then println(s"Code: $code")
      if !check(term, code) then throw Exception("Implementation Error")
    else
      val code = Generator.gen(aTerm)
      if filename.isDefined
      then write(code)
      else println(code)

    // write code to .wat file associated to .pcf file passed as argument,
    // returning .wat file relative filename
    def write(code: String): String =
      val WatFilename = filename.get.replaceFirst("\\.pcf\\z", ".wat")
      if verbose then println("writing .wat code to " + WatFilename)
      val out = new FileWriter(WatFilename)
      out.write(code)
      out.flush()
      out.close()
      WatFilename

  private def check(term: Term, code: List[Ins]): Boolean =
    val value = Evaluator.eval(term, Map())
    println(s"Evaluator result: $value")
    println(code) // in case the execution fails
    val value2 = vm.VM.execute(code)
    println(s"VM result: $value2")
    value2.toString == value.toString // valid only for PCF green and blue

  def main(args: Array[String]): Unit = {
    println("PCF Interpreter - Type expressions (Ctrl+D to exit):")

    val interp = args.contains("-i")
    val verbose = args.length == 0 || args.length > 1 && args.contains("-v")
    val check_am = args.contains("-vm")

    Iterator
      .continually(scala.io.StdIn.readLine("> "))
      .takeWhile(_ != null)
      .foreach { line =>
        try {
          if (interp) println(s"==> ${interpret(line)}")
            else compile(verbose, check_am, line, None)
        } catch {
          case e: Exception =>
            println(s"Error: ${e.getMessage}\n")
        }
      }
  }
}
