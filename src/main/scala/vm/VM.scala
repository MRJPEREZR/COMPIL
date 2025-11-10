package vm

import generator.Ins
import Ins.*
import Value.*

import scala.annotation.tailrec

enum Value:
  case IntValue(n: Int)
  case ClosureValue(body: List[Ins], env: List[Value])
  case EnvValue(env: List[Value])

  override def toString: String = this match {
    case IntValue(n) => s"IntValue($n)"
    case ClosureValue(body, env) => "Closure" // Simplified for comparison
    case EnvValue(env) => "EnvValue"
  }

type Env = List[Value]

object VM:
  def execute(c: List[Ins]): Value =
    execute(IntValue(0), List(), List(), c)

  @tailrec
  def execute(a: Value, s: List[Value], e: Env, c: List[Ins]): Value = (a, s, e, c) match {
    case (_, _, _, List()) => a

    case (_, _, _, Push :: c) => execute(a, a :: s, e, c)
    case (_, _, _, Ldi(n) :: c) => execute(IntValue(n), s, e, c)

    // Binary operations
    case (IntValue(n), IntValue(m) :: s, _, Add :: c) => execute(IntValue(m + n), s, e, c)
    case (IntValue(n), IntValue(m) :: s, _, Sub :: c) => execute(IntValue(m - n), s, e, c)
    case (IntValue(n), IntValue(m) :: s, _, Mul :: c) => execute(IntValue(m * n), s, e, c)
    case (IntValue(n), IntValue(m) :: s, _, Div :: c) =>
      if (n == 0) throw new Exception("Division by zero")
      else execute(IntValue(m / n), s, e, c)

    // Conditionals
    case (IntValue(0), s, e, Test(i, _) :: c) => execute(a, s, e, i ::: c)
    case (IntValue(_), s, e, Test(_, j) :: c) => execute(a, s, e, j ::: c)

    // Environment operations
    case (_, s, e, PushEnv :: c) =>
      execute(EnvValue(e), s, e, c)

    // CORRECTED Popenv: restore environment from stack
    case (result, EnvValue(savedEnv) :: s, _, Popenv :: c) =>
      execute(result, s, savedEnv, c)

    // Variable binding
    case (value, s, e, Extend(x) :: c) =>
      execute(a, s, value :: e, c)

    // Variable Search
    case (_, s, e, Search(index) :: c) =>
      @tailrec
      def SearchEnv(env: Env, idx: Int): Value = env match {
        case head :: tail =>
          if (idx == 0) head
          else SearchEnv(tail, idx - 1)
        case Nil => throw new Exception(s"Variable index out of bounds: $index")
      }
      execute(SearchEnv(e, index), s, e, c)

    // Closure creation
    case (_, s, e, Mkclos(body) :: c) =>
      execute(ClosureValue(body, e), s, e, c)

    // CORRECTED Function application
    case (arg, closure :: s, e, Apply :: c) =>
      closure match {
        case ClosureValue(body, closureEnv) =>
          // Save current environment and execute function body with new environment
          execute(IntValue(0), EnvValue(e) :: s, arg :: closureEnv, body ::: c)
        case _ => throw new Exception(s"Cannot apply non-closure: $closure")
      }

    case _ => throw new Exception(s"unexpected VM state: ($a, $s, $e, $c)")
  }