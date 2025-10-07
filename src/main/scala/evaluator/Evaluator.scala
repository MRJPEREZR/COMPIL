package evaluator

import parser._

sealed trait Value
case class IntValue(value: Int) extends Value
case class Closure(param: String, body: Term, env: Env) extends Value

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
        case _ => throw new RuntimeException(s"Cannot apply non-function: $f")
      }

    case IfZero(cond, thenBranch, elseBranch) =>
      eval(cond, env) match {
        case IntValue(0) => eval(thenBranch, env)
        case IntValue(_) => eval(elseBranch, env)
        case _ => throw new RuntimeException("Condition in ifz must be integer")
      }

    case Fix(f, body) =>
      lazy val closure: Value = eval(body, env + (f -> closure))
      closure

    case Let(name, value, in) =>
      val v = eval(value, env)
      eval(in, env + (name -> v))
  }
}
