package generator

import parser._
import Ins.*

type Code = List[Ins]

object Generator:
  def gen(aterm: ATerm): Code = aterm match {
    case AConstant(n) => List(Ldi(n))

    case AVariable(x, index) =>
      List(Search(index))  // Just use the de Bruijn index

    case ABinaryOperation(left, op, right) =>
      gen(left) ::: List(Push) ::: gen(right) ::: List(gen_op(op))

    case AFunction(param, body) =>
      val bodyCode = gen(body)
      List(PushEnv, Extend(param)) ::: bodyCode ::: List(Popenv, Mkclos(bodyCode))

    case AApplication(func, arg) =>
      gen(func) ::: List(Push) ::: gen(arg) ::: List(Apply)

    case AIfZero(cond, thenBranch, elseBranch) =>
      val condCode = gen(cond)
      val thenCode = gen(thenBranch)
      val elseCode = gen(elseBranch)
      condCode ::: List(Test(thenCode, elseCode))

    case AFix(name, body) =>
      val bodyCode = gen(body)
      List(Mkclos(bodyCode), Extend(name))

    case AFixFunction(name, param, body) =>
      val bodyCode = gen(body)
      List(PushEnv, Extend(param)) ::: bodyCode ::: List(Popenv, Mkclos(bodyCode), Extend(name))

    case ALet(name, value, body) =>
      gen(value) ::: List(Extend(name)) ::: gen(body) ::: List(Popenv)
  }

  def gen_op(op: String): Ins = op match {
    case "+" => Add
    case "-" => Sub
    case "*" => Mul
    case "/" => Div
  }