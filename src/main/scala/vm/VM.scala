package vm

import generator.Ins
import Ins.*
import Value.*

import scala.annotation.tailrec

enum Value:
  case IntValue(n: Int)
  case EnvValue(env: List[Value])
  case ClosureValue(body: List[Ins], env: List[Value])

type Env = List[Value]

object VM:
  def execute(c: List[Ins]): Value =
    execute(List(), List(), c)

  @tailrec
  def execute(s: List[Value], e: Env, c: List[Ins]): Value = (s, e, c) match {
    // terminated program -> accumulator is result
    case (result :: _, _, List()) => result
    case (Nil, _, List()) => throw new Exception("Empty stack at termination")

    // push accumulator on value stack
    case (s, _, Push :: c) =>
      execute(s, e, c)

    // load integer into accumulator
    case (s, _, Ldi(n) :: c) =>
      execute(IntValue(n) :: s, e, c)

    // arithmetic: accumulator is right operand, left operand on top of stack
    case (IntValue(n) :: IntValue(m) :: s, _, Add :: c) => execute(IntValue(m + n) :: s, e, c)
    case (IntValue(n) :: IntValue(m) :: s, _, Sub :: c) => execute(IntValue(m - n) :: s, e, c)
    case (IntValue(n) :: IntValue(m) :: s, _, Mul :: c) => execute(IntValue(m * n) :: s, e, c)
    case (IntValue(n) :: IntValue(m) :: s, _, Div :: c) =>
      if (n == 0) throw new Exception("Division by zero")
      else execute(IntValue(m / n) :: s, e, c)

    // conditionals: accumulator holds tested integer
    case (IntValue(0) :: s, e, Test(i, _) :: c) => execute(s, e, i ::: c)
    case (IntValue(_) :: s, e, Test(_, j) :: c) => execute(s, e, j ::: c)

    // Push current environment on value stack (used by let and by application sequence)
    case (s, e, PushEnv :: c) =>
      execute(EnvValue(e) :: s, e, c)

    // Restore environment previously saved by PushEnv
    case (result :: EnvValue(savedEnv) :: s, _, Popenv :: c) =>
      execute(result :: s, savedEnv, c)

    // Extend environment with value currently in accumulator (used for let)
    case (value :: s, e, Extend(_) :: c) =>
      execute(s, value :: e, c)

    // Search: non-destructive nth lookup in environment
    case (s, e, Search(n) :: c) =>
      @tailrec
      def nth(envList: Env, k: Int): Value = (envList, k) match {
        case (v :: _, 0) => v
        case (_ :: tail, kk) if kk > 0 => nth(tail, kk - 1)
        case _ => throw new Exception(s"unbound variable at index $n")
      }
      val value = nth(e, n)
      execute(value :: s, e, c)

    // Make closure: accumulator becomes the closure <body, currentEnv>
    case (s, e, Mkclos(body) :: c) =>
      execute(ClosureValue(body, e) :: s, e, c)

    // Apply
    case (arg :: (clos @ ClosureValue(body, envClos)) :: s, e, Apply :: c) =>
      val newEnv: Env = arg :: clos :: envClos
      execute(s, newEnv, body ::: c)

    // anything else is an error
    case _ =>
      throw new Exception(s"unexpected VM state: ($s, $e, $c)")
  }
