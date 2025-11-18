package generator

import parser._
import Ins.*

type Code = List[Ins]

object Generator:
  def gen(aterm: ATerm): Code = aterm match {

    case AVariable(x, index) =>
      List(Search(index))

    case AConstant(n) => List(Ldi(n))

    case ABinaryOperation(left, op, right) =>
      gen(left) ::: List(Push) ::: gen(right) ::: List(gen_op(op))

    case AIfZero(cond, thenBranch, elseBranch) =>
      val condCode = gen(cond)
      val thenCode = gen(thenBranch)
      val elseCode = gen(elseBranch)
      condCode ::: List(Test(thenCode, elseCode))

    case ALet(name, value, body) =>
      val valueCode = gen(value)
      val bodyCode = gen(body)
      List(PushEnv) ::: valueCode ::: List(Extend(name)) ::: bodyCode ::: List(Popenv)

  }

  def gen_op(op: String): Ins = op match {
    case "+" => Add
    case "-" => Sub
    case "*" => Mul
    case "/" => Div
  }