package generator

// import AST.{Op,Term}
import parser._
// import Term.*
import Ins.*

type Code = List[Ins]

object Generator:
    def gen(term: Term): Code = term match {
      case Constant(n) => List(Ldi(n))
      case BinaryOperation(u, op, v) =>
        val c_u = gen(u)
        val c_v = gen(v)
        c_u ::: (Push  :: c_v) ::: List(gen_op(op))
      case
    }
    
    def gen_op(op: String) = op match {
      case "+" => Add
      case "-" => Sub
      case "*" => Mul
      case "/" => Div
    }
            