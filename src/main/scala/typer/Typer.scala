package typer

import parser.*
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

    case Application(func, arg) =>
      val funcType = eval(func, env)
      val argType = eval(arg, env)
      val resultType = TVar()
      if (funcType === FUNCTION(argType, resultType)) then resultType
      else throw new RuntimeException(s"Function has type $funcType, but got argument of type $argType")

    case Function(param, body) =>
      val paramType = TVar()
      val bodyType = eval(body, env + (param -> paramType))
      FUNCTION(paramType, bodyType)

    //case Fix(param, body) =>

    case BinaryOperation(left, op, right) =>
      val leftType = eval(left, env)
      val rightType = eval(right, env)
      if (leftType === rightType) then INT
      else throw new RuntimeException(s"Unexpected type(s) for operator $op: $leftType and $rightType (expected INT and INT)")

    case IfZero(cond, thenBranch, elseBranch) =>
      val condType = eval(cond, env)
      val thenType = eval(thenBranch, env)
      val elseType = eval(elseBranch, env)
      condType match
        case INT =>
          if (thenType === elseType) then thenType
          else throw new RuntimeException(s"Types of IFZ branches are different: $thenType and $elseType")
        case _ => throw new RuntimeException(s"Unexpected type in ifz condition: $condType (expected INT)")
}
