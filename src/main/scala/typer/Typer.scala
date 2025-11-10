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
      env.getOrElse(x, throw new RuntimeException(s"Unbound variable: $x"))

    case BinaryOperation(left, op, right) =>
      val leftType = eval(left, env)
      val rightType = eval(right, env)

      if (!(leftType === INT)) {
        throw new RuntimeException(s"Left operand of '$op' must be INT, got: $leftType")
      }
      if (!(rightType === INT)) {
        throw new RuntimeException(s"Right operand of '$op' must be INT, got: $rightType")
      }
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

      if (!(funcType === expectedFuncType)) {
        throw new RuntimeException(s"Cannot apply function of type $funcType to argument of type $argType")
      }
      resultType

    case IfZero(cond, thenBranch, elseBranch) =>
      val condType = eval(cond, env)
      val thenType = eval(thenBranch, env)
      val elseType = eval(elseBranch, env)

      if (!(condType === INT)) {
        throw new RuntimeException(s"Condition in ifz must be INT, got: $condType")
      }
      if (!(thenType === elseType)) {
        throw new RuntimeException(s"Branches in ifz must have same type, got: $thenType and $elseType")
      }
      thenType

    case Fix(name, body) =>
      val resultType = TVar()
      val funcType = TVar()
      val newEnv = env + (name -> funcType)
      val bodyType = eval(body, newEnv)
      val expectedBodyType = new FUNCTION(resultType, resultType)

      if (!(bodyType === expectedBodyType)) {
        throw new RuntimeException(s"Fixpoint body must be a recursive function (${resultType} -> ${resultType}), got: $bodyType")
      }
      if (!(funcType === expectedBodyType)) {
        throw new RuntimeException(s"Recursive function type mismatch in fixpoint")
      }
      resultType

    case FixFunction(name, param, body) =>
      val paramType = TVar()
      val funcType = TVar()
      val newEnv = env + (name -> funcType) + (param -> paramType)
      val bodyType = eval(body, newEnv)
      val expectedFuncType = new FUNCTION(paramType, bodyType)

      if (!(funcType === expectedFuncType)) {
        throw new RuntimeException(s"Recursive function type mismatch in fix function")
      }
      new FUNCTION(paramType, bodyType)

    case Let(name, value, body) =>
      val valueType = eval(value, env)
      eval(body, env + (name -> valueType))
  }
}