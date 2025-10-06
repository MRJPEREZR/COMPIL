package parser

import parserANTLR._

class ASTBuilder extends PcfBaseVisitor[ Term] {

  override def visitVariable(ctx: PcfParser.VariableContext): Term =
    Variable(ctx.VAR().getText)

  override def visitConstant(ctx: PcfParser.ConstantContext): Term =
    Constant(ctx.CONST().getText.toInt)

  override def visitFunction(ctx: PcfParser.FunctionContext): Term =
    Function(ctx.VAR().getText, visit(ctx.term()))

  override def visitApp(ctx: PcfParser.AppContext): Term = {
    val left = visit(ctx.term(0))
    val right = visit(ctx.term(1))
    Application(left, right)
  }

  override def visitBinaryOperation(ctx: PcfParser.BinaryOperationContext): Term = {
    val left = visit(ctx.term(0))
    val right = visit(ctx.term(1))
    val op = ctx.OP().getText
    BinaryOperation(left, op, right)
  }

  override def visitIfZero(ctx: PcfParser.IfZeroContext): Term =
    IfZero(visit(ctx.term(0)), visit(ctx.term(1)), visit(ctx.term(2)))

  override def visitFix(ctx: PcfParser.FixContext): Term =
    Fix(ctx.VAR().getText, visit(ctx.term()))

  override def visitLet(ctx: PcfParser.LetContext): Term =
    Let(ctx.VAR().getText, visit(ctx.term(0)), visit(ctx.term(1)))
}
