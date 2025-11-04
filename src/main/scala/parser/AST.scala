package parser

sealed trait Term

case class Variable(name: String) extends Term
case class Constant(value: Int) extends Term
case class Function(param: String, body: Term) extends Term
case class Application(func: Term, arg: Term) extends Term
case class BinaryOperation(left: Term, op: String, right: Term) extends Term
case class IfZero(cond: Term, thenBranch: Term, elseBranch: Term) extends Term
case class Fix(param: String, body: Term) extends Term
case class Let(name: String, value: Term, in: Term) extends Term
case class FixFunction(name: String, param: String, body: Term) extends Term

sealed trait ATerm

case class AVariable(name: String, i: Int) extends ATerm
case class AConstant(value: Int) extends ATerm
case class AFunction(param: String, body: ATerm) extends ATerm
case class AApplication(func: ATerm, arg: ATerm) extends ATerm
case class ABinaryOperation(left: ATerm, op: String, right: ATerm) extends ATerm
case class AIfZero(cond: ATerm, thenBranch: ATerm, elseBranch: ATerm) extends ATerm
case class AFix(param: String, body: ATerm) extends ATerm
case class ALet(name: String, value: ATerm, in: ATerm) extends ATerm
case class AFixFunction(name: String, param: String, body: ATerm) extends ATerm

object Term {
  def annotate(term: Term, env: List[String]): ATerm = term match {
    case Variable(name) =>
      val idx = env.indexOf(name)
      if (idx == -1)
        throw new RuntimeException(s"Unbound variable: $name")
      else
        AVariable(name, idx)

    case Constant(value) =>
      AConstant(value)

    case Function(param, body) =>
      AFunction(param, annotate(body, param :: env))

    case Application(func, arg) =>
      AApplication(annotate(func, env), annotate(arg, env))

    case BinaryOperation(left, op, right) =>
      ABinaryOperation(annotate(left, env), op, annotate(right, env))

    case IfZero(cond, thenBranch, elseBranch) =>
      AIfZero(annotate(cond, env), annotate(thenBranch, env), annotate(elseBranch, env))

    case Fix(param, body) =>
      AFix(param, annotate(body, param :: env))

    case Let(name, value, in) =>
      ALet(name, annotate(value, env), annotate(in, name :: env))

    case FixFunction(name, param, body) =>
      AFixFunction(name, param, annotate(body, param :: name :: env))
  }
}
