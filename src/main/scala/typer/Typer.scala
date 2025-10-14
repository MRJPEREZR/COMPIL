package typer

import parser._
import evaluator._

type TypeEnv = Map[String, Type]

object Typer {
  def eval(t: Term, env: TypeEnv): Type = t match
    case Constant(_) => INT

    case BinaryOperation(left, op, right) =>
      val leftType = eval(left, env)
      val rightType = eval(right, env)
      (leftType, rightType) match
        case (INT, INT) => INT
        case _ => throw new RuntimeException(s"Unexpected type(s) for operator $op: $leftType and $rightType (expected INT and INT)")

    case IfZero(cond, thenBranch, elseBranch) =>
      val condType = eval(cond, env)
      val thenType = eval(thenBranch, env)
      val elseType = eval(elseBranch, env)
      condType match
        case INT => Evaluator.eval(cond, Map()) match {
          case IntValue(0) => thenType
          case _ => elseType
        }
        case _ => throw new RuntimeException(s"Unexpected type in ifz condition: $condType (expected INT)")

    case _ => ???
}
