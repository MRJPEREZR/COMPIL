package parser

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

abstract class AbstractParser[T](
                                  createParser: CommonTokenStream => Parser,
                                  entryPoint: Parser => T
                                ) {
  def parse(input: String): T = {
    val inputStream = CharStreams.fromString(input)
    val lexer = createLexer(inputStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = createParser(tokens)
    entryPoint(parser)
  }

  protected def createLexer(input: CharStream): Lexer
}
