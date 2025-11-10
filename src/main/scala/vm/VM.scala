package vm

import generator.Ins
import Ins.*
import Value.*

import scala.annotation.tailrec

enum Value:
  case IntValue(n: Int)
  case ClosureValue(body: List[Ins], env: List[Value])
  case EnvValue(env: List[Value])  // New wrapper for environments

// Environment is a list of values
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
      // Push the current environment wrapped in EnvValue
      execute(EnvValue(e), s, e, c)

    case (_, envValue :: s, _, Popenv :: c) =>
      envValue match {
        case EnvValue(savedEnv) => execute(a, s, savedEnv, c)
        case _ => throw new Exception("Expected EnvValue on stack for Popenv")
      }

    // Variable binding - extend environment with new value
    case (_, value :: s, e, Extend(x) :: c) =>
      // For now, we ignore the variable name 'x' and just extend environment
      // In a complete implementation, you'd need proper name-to-index mapping
      execute(a, s, value :: e, c)

    // Variable lookup - search in environment
    case (_, s, e, Search(x) :: c) =>
      // Simple implementation: use position in environment
      // This is a placeholder - needs proper name resolution
      if (e.nonEmpty) execute(e.head, s, e, c)
      else throw new Exception(s"Unbound variable: $x")

    // Closure creation
    case (_, s, e, Mkclos(body) :: c) =>
      execute(ClosureValue(body, e), s, e, c)

    // Function application - FIXED: use EnvValue wrapper
    case (ClosureValue(body, closureEnv), arg :: s, e, Apply :: c) =>
      // Push current environment as EnvValue, setup new environment with argument
      execute(a, EnvValue(e) :: s, arg :: closureEnv, body ::: (Popenv :: c))

    case _ => throw new Exception(s"unexpected VM state: ($a, $s, $e, $c)")
  }