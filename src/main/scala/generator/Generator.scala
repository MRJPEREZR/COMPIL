package generator

// import AST.{Op,Term}
import parser._
// import Term.*
import Ins.*

type Code = List[Ins]

object Generator:
    def gen(aterm: ATerm): Code = aterm match {
      case AConstant(n) => List(Ldi(n))
      case ABinaryOperation(u, op, v) =>
        val c_u = gen(u)
        val c_v = gen(v)
        c_u ::: (Push  :: c_v) ::: List(gen_op(op))
      case AIfZero(cond, ls, rs) =>
        val c_cond = gen(cond)
        val c_ls = gen(ls)
        val c_rs = gen(rs)
        c_cond ::: List(Test(c_ls, c_rs))
      case ALet (name: String, value: ATerm, in: ATerm) =>
        val t = gen(value)
        val u = gen(in)
        (PushEnv :: t) ::: List(Extend) ::: u ::: List(Popenv)
    }

    def gen_op(op: String) = op match {
      case "+" => Add
      case "-" => Sub
      case "*" => Mul
      case "/" => Div
    }
