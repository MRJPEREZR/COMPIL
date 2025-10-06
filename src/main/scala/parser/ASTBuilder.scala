package parser

import parserANTLR._
import org.antlr.v4.runtime.tree.TerminalNode
import scala.jdk.CollectionConverters._

class ASTBuilder extends PcfBaseVisitor[Term] {

  // LET x = e1 IN e2
  override def visitLet(ctx: PcfParser.LetContext): Term = {
    val name = ctx.VAR().getText
    val value = visit(ctx.term(0))
    val inTerm = visit(ctx.term(1))
    Let(name, value, inTerm)
  }

  // FIX x e
  override def visitFix(ctx: PcfParser.FixContext): Term = {
    val name = ctx.VAR().getText
    val body = visit(ctx.term())
    Fix(name, body)
  }

  // IFZ e1 THEN e2 ELSE e3
  override def visitIfZero(ctx: PcfParser.IfZeroContext): Term = {
    val cond = visit(ctx.term(0))
    val thenTerm = visit(ctx.term(1))
    val elseTerm = visit(ctx.term(2))
    IfZero(cond, thenTerm, elseTerm)
  }

  // FUN x -> body
  override def visitFunction(ctx: PcfParser.FunctionContext): Term = {
    val param = ctx.VAR().getText
    val body = visit(ctx.term())
    Function(param, body)
  }

  // APPLICATION: e1 e2 e3 ...
  override def visitApplication(ctx: PcfParser.ApplicationContext): Term = {
    val terms = ctx.atom().asScala.map(visit)
    terms.reduceLeft(parser.Application.apply)
  }

  // MULT/DIV level
  override def visitMulDiv(ctx: PcfParser.MulDivContext): Term = {
    val terms = ctx.appTerm().asScala.map(visit)
    val ops = ctx.children.asScala.collect {
      case t: TerminalNode if Set("*", "/").contains(t.getText) => t.getText
    }
    terms.tail.zip(ops).foldLeft(terms.head) {
      case (left, (right, op)) => BinaryOperation(left, op, right)
    }
  }

  // ADD/SUB level
  override def visitAddSub(ctx: PcfParser.AddSubContext): Term = {
    val terms = ctx.mulTerm().asScala.map(visit)
    val ops = ctx.children.asScala.collect {
      case t: TerminalNode if Set("+", "-").contains(t.getText) => t.getText
    }
    terms.tail.zip(ops).foldLeft(terms.head) {
      case (left, (right, op)) => BinaryOperation(left, op, right)
    }
  }

  // VARIABLES, CONSTANTS, PARENTHESIS
  override def visitVariable(ctx: PcfParser.VariableContext): Term =
    Variable(ctx.VAR().getText)

  override def visitConstant(ctx: PcfParser.ConstantContext): Term =
    Constant(ctx.CONST().getText.toInt)

  override def visitParentheses(ctx: PcfParser.ParenthesesContext): Term =
    visit(ctx.term())

  // Pass-through (just delegate to the child)
  override def visitBinTerm(ctx: PcfParser.BinTermContext): Term =
    visit(ctx.getChild(0))
}
