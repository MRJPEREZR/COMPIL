package generator

import parser._
import Ins.*

type Code = List[Ins]

object Generator:
  def gen(aterm: ATerm): Code = aterm match {

    // variable annotated with De Bruijn index
    case AVariable(_, index) =>
      List(Search(index))

    // integer constant
    case AConstant(n) =>
      List(Ldi(n))

    // binary operation
    case ABinaryOperation(left, op, right) =>
      gen(left) ::: gen(right) ::: List(gen_op(op))

    // conditional
    case AIfZero(cond, thenBranch, elseBranch) =>
      val condCode = gen(cond)
      val thenCode = gen(thenBranch)
      val elseCode = gen(elseBranch)
      condCode ::: List(Test(thenCode, elseCode))

    // let
    case ALet(_, value, body) =>
      val valueCode = gen(value)
      val bodyCode = gen(body)
      List(PushEnv) ::: valueCode ::: List(Extend("")) ::: bodyCode ::: List(Popenv)

    // function 
    case AFunction(_, body) =>
      List(Mkclos(gen(body)))

    // application
    case AApplication(func, arg) =>
      gen(func) ::: gen(arg) ::: List(Apply)

    // fix
    case AFix(_, func @ AFunction(param, funcBody)) =>
      List(Mkclos(gen(funcBody)))

    // fix case to support also vars (not only funcs)
    case AFix(_, other) =>
      List(Mkclos(gen(other)))

    // fix func
    case AFixFunction(_, _, body) =>
      List(Mkclos(gen(body)))

    // unexpected cases
    case other =>
      throw new Exception(s"Generator: unsupported AST node: $other")
  }

  def gen_op(op: String): Ins = op match {
    case "+" => Add
    case "-" => Sub
    case "*" => Mul
    case "/" => Div
    case _ => throw new Exception(s"unknown binary operator: $op")
  }
