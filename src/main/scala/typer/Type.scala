package typer

import parser.*
import unify.{Atom, HTerm, Structure, TVar}

type Type = HTerm
type TypeEnv = Map[String, Type]

object INT extends Atom("INT")

class FUNCTION(a: Type, b: Type) extends Structure("function", List(a, b)):
  override def toString = s"($a -> $b)"

object Typer {
  def eval(term: Term, env: TypeEnv = Map.empty): Type = term match {
    case Constant(_) => INT

    case Variable(x) =>
      env.getOrElse(x, throw new RuntimeException(s"Unbound type variable: $x"))

    case BinaryOperation(left, op, right) =>
      val leftType = eval(left, env)
      val rightType = eval(right, env)
      leftType === INT
      rightType === INT
      INT

    case Function(param, body) =>
      val paramType = TVar()
      val bodyType = eval(body, env + (param -> paramType))
      new FUNCTION(paramType, bodyType)

    case Application(func, arg) =>
      val funcType = eval(func, env)
      val argType = eval(arg, env)
      val resultType = TVar()
      val expectedFuncType = new FUNCTION(argType, resultType)
      funcType === expectedFuncType
      resultType

    case IfZero(cond, thenBranch, elseBranch) =>
      val condType = eval(cond, env)
      val thenType = eval(thenBranch, env)
      val elseType = eval(elseBranch, env)
      condType === INT
      thenType === elseType
      thenType

    case Fix(name, body) =>
      val bodyType = eval(body, env)
      val resultType = TVar()
      val expectedBodyType = new FUNCTION(resultType, resultType)
      bodyType === expectedBodyType
      resultType

//    case FixFunction(name, param, body) =>
//      val paramType = TVar()
//      val bodyType = eval(body, env + (name -> new FUNCTION(paramType, bodyType)) + (param -> paramType))
//      new FUNCTION(paramType, bodyType)

    case Let(name, value, body) =>
      val valueType = eval(value, env)
      eval(body, env + (name -> valueType))
  }
}