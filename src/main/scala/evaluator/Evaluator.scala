package evaluator

import parser._

sealed trait Value
case class IntValue(value: Int) extends Value
case class Closure(param: String, body: Term, env: Env) extends Value
case class IceCube(param: String, body: Term, env: Env) extends Value

type Env = Map[String, Value]

object Evaluator {

  def eval(term: Term, env: Env = Map.empty): Value = term match {

    case Constant(n) =>
      IntValue(n)

    case Variable(x) =>
      env.getOrElse(x, throw new RuntimeException(s"Unbound variable: $x"))

    case BinaryOperation(left, op, right) =>
      (eval(left, env), eval(right, env)) match {
        case (IntValue(l), IntValue(r)) =>
          op match {
            case "+" => IntValue(l + r)
            case "-" => IntValue(l - r)
            case "*" => IntValue(l * r)
            case "/" =>
              if (r == 0) throw new RuntimeException("Division by zero")
              IntValue(l / r)
            case _ => throw new RuntimeException(s"Unknown operator: $op")
          }
        case _ => throw new RuntimeException("Operands must be integers")
      }

    case Function(param, body) =>
      Closure(param, body, env)

    case Application(func, arg) =>
      val f = eval(func, env)
      val v = eval(arg, env)
      f match {
        case Closure(param, body, closureEnv) =>
          eval(body, closureEnv + (param -> v))
        case IceCube(param, body, closureEnv) =>
          // When applying a recursive function, ensure the function refers to itself
          val recEnv = closureEnv + (param -> v)
          eval(body, closureEnv + (param -> v) + ("fixself" -> f))
        case _ =>
          throw new RuntimeException(s"Cannot apply non-function: $f")
      }

    case IfZero(cond, thenBranch, elseBranch) =>
      eval(cond, env) match {
        case IntValue(0) => eval(thenBranch, env)
        case IntValue(_) => eval(elseBranch, env)
        case _ => throw new RuntimeException("Condition in ifz must be integer")
      }

    case Fix(name, body) =>
      eval(body, env) match {
        case Closure(param, innerBody, closureEnv) =>
          lazy val recCube: IceCube = IceCube(param, innerBody, closureEnv + (name -> recCube))
          recCube
        case v => v
      }

    case FixFunction(name, param, body) =>
      eval(Fix(name, Function(param, body)), env)

    case Let(name, value, body) =>
      val v = eval(value, env)
      eval(body, env + (name -> v))
  }
}
