package typer

import parser.*
import evaluator.*
import unify.TVar

type TypeEnv = Map[String, Type]

object Typer {
  def eval(t: Term, env: TypeEnv): Type = t match
    case Constant(_) => INT

    case Variable(name) =>
      env.getOrElse(name, throw new RuntimeException(s"Unbound variable: $name"))

    case Let(name, value, body) =>
      val valueType = eval(value, env)
      eval(body, env + (name -> valueType))

    //case Application(func, arg) =>
      //val funcType = eval(func, env)
      //funcType

    //case Function(param, body) =>
      //FUNCTION()
      //eval(body, env + (param -> TVar()))

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
