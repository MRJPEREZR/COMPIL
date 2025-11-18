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
      execute(a, EnvValue(e) :: s, e, c)

    // Popenv
    case (result, EnvValue(savedEnv) :: s, _, Popenv :: c) =>
      execute(result, s, savedEnv, c)

    // Variable binding
    case (value, s, e, Extend(x) :: c) =>
      execute(value, s, value :: e, c)

    // Variable lookup
    case (_, s, e, Search(n) :: c) =>
      @tailrec
      def nth(envList: Env, k: Int): Value = (envList, k) match {
        case (v :: _, 0) => v
        case (_ :: tail, kk) if kk > 0 => nth(tail, kk - 1)
        case _ => throw new Exception(s"unbound variable at index $n")
      }
      val value = nth(e, n)
      execute(value, s, e, c)
    
    // For red and black cases
      
    // Functions
    case (_, s, e, Mkclos(body) :: c) =>
      execute(ClosureValue(body, e), s, e, c)

    // Function applications
    case (ClosureValue(body, envClos), arg :: s, e, Apply :: c) =>
      val newEnv = arg :: ClosureValue(body, envClos) :: envClos
      execute(ClosureValue(body, envClos), s, newEnv, body ::: c)
      
    // Other cases
    case _ => throw new Exception(s"unexpected VM state: ($a, $s, $e, $c)")
  }