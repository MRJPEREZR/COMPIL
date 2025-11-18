package vm

import generator.Ins
import Ins.*
import Value.*

import scala.annotation.tailrec

enum Value:
  case IntValue(n: Int)
  case EnvValue(env: List[Value])
  case ClosureValue(body: List[Ins], env: List[Value])


  override def toString: String = this match {
    case IntValue(n) => s"IntValue($n)"
    case ClosureValue(body, env) => "Closure"
    case EnvValue(env) => "EnvValue"
  }

type Env = List[Value]

object VM:
  def execute(c: List[Ins]): Value =
    execute(IntValue(0), List(), List(), c)

  @tailrec
  def execute(a: Value, s: List[Value], e: Env, c: List[Ins]): Value = (a, s, e, c) match {
    case (_, _, _, List()) => a

    case (_, _, _, Push :: c) =>
      println(s"DEBUG: Pushing $a onto stack")
      execute(a, a :: s, e, c)
    case (_, _, _, Ldi(n) :: c) => execute(IntValue(n), s, e, c)

    // Binary operations
    case (IntValue(n), IntValue(m) :: s, _, Add :: c) =>
      println(s"DEBUG: Adding $m + $n = ${m + n}")
      execute(IntValue(m + n), s, e, c)
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
      execute(a, EnvValue(e) :: s, e, c)

    // Popenv
    case (result, EnvValue(savedEnv) :: s, _, Popenv :: c) =>
      execute(result, s, savedEnv, c)

    // Variable binding
    case (value, s, e, Extend(x) :: c) =>
      execute(value, s, value :: e, c)

    // Variable lookup
    case (_, s, value :: env, Search(0) :: c) =>
      execute(value, s, e, c)
    case (_, s, head :: env, Search(n) :: c) if n > 0 =>
      execute(a, s, env, Search(n - 1) :: c)

    // Other cases
    case _ =>
      println(s"DEBUG: Unhandled state - acc: $a, stack: $s, env: $e, code: $c")
      throw new Exception(s"unexpected VM state: ($a, $s, $e, $c)")
  }