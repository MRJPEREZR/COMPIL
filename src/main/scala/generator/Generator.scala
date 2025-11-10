package generator

import parser._
import Ins.*

type Code = List[Ins]

object Generator:
  def gen(aterm: ATerm): Code = aterm match {
    case AConstant(n) => List(Ldi(n))

    case AVariable(x, index) =>
      List(Search(index))

    case ABinaryOperation(left, op, right) =>
      gen(left) ::: List(Push) ::: gen(right) ::: List(gen_op(op))

    case AFunction(param, body) =>
      val bodyCode = gen(body)
      // The function body executes in an environment where the argument is already
      // at the head (index 0). We just need to execute the body and restore environment.
      List(Mkclos(bodyCode ::: List(Popenv)))

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
      // For recursive functions, create a closure that can reference itself
      List(Mkclos(
        List(PushEnv, Extend(param)) ::: bodyCode ::: List(Popenv)
      ), Extend(name))

    case ALet(name, value, body) =>
      val valueCode = gen(value)
      val bodyCode = gen(body)
      // Push environment to accumulator, then push it to stack, then proceed
      List(PushEnv, Push) ::: valueCode ::: List(Extend(name)) ::: bodyCode ::: List(Popenv)
  }

  def gen_op(op: String): Ins = op match {
    case "+" => Add
    case "-" => Sub
    case "*" => Mul
    case "/" => Div
  }