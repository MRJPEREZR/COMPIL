package generator

import parser.*
import typer.*
import evaluator.*
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTree
import parserANTLR.*

import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.jdk.StreamConverters._
import scala.util.Try

object GeneratorTest {

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
    val aTerm = Term.annotate(term, List())
    println(s"annotated AST = $aTerm")
    val code = Generator.gen(aTerm)
    if check(term, code) then code
    else throw Exception("Implementation Error")

  private def check(term: Term, code: List[Ins]): Boolean =
    val value = Evaluator.eval(term, Map())
    println(s"Evaluator result: $value")
    println(code)
    val value2 = vm.VM.execute(code)
    value2.toString == value.toString

  def main(args: Array[String]): Unit = {
    val directory = "./src/test/scala/generator"
    val color = "black"
    println(s"Testing all $color PCF files in directory: $directory")
    println("=" * 60)

    val pcfFiles = Try {
      Files.list(Paths.get(directory))
        .filter(_.getFileName.toString.endsWith(".pcf"))
        .filter(_.getFileName.toString.contains(color))
        .toScala(List)
        .sortBy(_.getFileName.toString)
        .map(_.toString)
    }.getOrElse(List.empty)

    if (pcfFiles.isEmpty) {
      println("No .pcf files found in current directory")
      return
    }

    println(s"Found ${pcfFiles.length} PCF files:")
    pcfFiles.foreach(println)
    println("=" * 60)

    pcfFiles.foreach { filename =>
      try {
        println(s"\n>>> Testing: $filename")
        println("-" * 40)

        // Read the entire file content
        val source = Source.fromFile(filename)
        val content = try {
          source.mkString
        } finally {
          source.close()
        }

        println(s"File content:\n$content")
        println("-" * 40)

        // Test interpretation
        try {
          val interpreted = interpret(content)
          println(s"Interpreted result: $interpreted")
        } catch {
          case e: Exception => println(s"Interpretation failed: ${e.getMessage}")
        }

        println("-" * 40)

        // Test compilation
        try {
          val compiled = compile(content)
          println(s"Compilation successful: $compiled")
        } catch {
          case e: Exception => println(s"Compilation failed: ${e.getMessage}")
        }

        println("=" * 60)

      } catch {
        case e: Exception =>
          println(s"ERROR processing $filename: ${e.getMessage}")
          println("=" * 60)
      }
    }
  }
}