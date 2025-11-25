package generator

import parser.*
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

  private def compile(in: String): List[Ins] =
    val term = parseTerm(in)
    val aTerm = Term.annotate(term, List())
    println(s"annotated AST = $aTerm")
    val code = Generator.genAM(aTerm)
    val value = Evaluator.eval(term, Map())
    val value1 = vm.VM.execute(code)
    println(s"Evaluator result: $value")
    println(s"VM result: $value1")
    code

  def main(args: Array[String]): Unit = {
    val directory = "test/"
    val color = "green"
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

// Test failing:
// blue 8, 9
// red20
// black 4

/*
red13,18,20,42,43
Binary operations in accumulator-free VM require that each side’s result be on the stack, without interfering with each other.

Right now, generator for ABinaryOperation(left, op, right) just concatenates gen(left) ::: gen(right) ::: List(op)

But if gen(left) is a function application, it leaves its result on top, gen(right) pushes arg and closure on top of that, then Apply pops arg/closure, but leftover values remain under — this corrupts stack for Add.
*/