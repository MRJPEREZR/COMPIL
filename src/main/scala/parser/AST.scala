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

  // closure itself.
  private val CLOS_PLACEHOLDER = "<clos>"

  def annotate(term: Term, env: List[String]): ATerm = term match {

    case Variable(name) =>
      val idx = env.indexOf(name)
      if (idx == -1)
        throw new RuntimeException(s"Unbound variable: $name")
      else
        AVariable(name, idx)

    case Constant(value) =>
      AConstant(value)

    // fun x -> body
    case Function(param, body) =>
      val newEnv = param :: CLOS_PLACEHOLDER :: env
      AFunction(param, annotate(body, newEnv))

    // t u
    case Application(func, arg) =>
      AApplication(annotate(func, env), annotate(arg, env))

    // left op right
    case BinaryOperation(left, op, right) =>
      ABinaryOperation(annotate(left, env), op, annotate(right, env))

    // ifz
    case IfZero(cond, thenBranch, elseBranch) =>
      AIfZero(
        annotate(cond, env),
        annotate(thenBranch, env),
        annotate(elseBranch, env)
      )

    // fix x -> body (to support recursive function)
    case Fix(name, Function(param, body)) =>
      val newEnv = param :: name :: CLOS_PLACEHOLDER :: env
      AFix(name, AFunction(param, annotate(body, newEnv)))

    // fix x -> body (to support just vars)
    case Fix(param, body) =>
      val newEnv = param :: CLOS_PLACEHOLDER :: env
      AFix(param, annotate(body, newEnv))

    // let x = value in body
    case Let(name, value, in) =>
      ALet(
        name,
        annotate(value, env),
        annotate(in, name :: env)
      )

    // fixfun f x -> body   (recursive function)
    case FixFunction(name, param, body) =>
      val newEnv = param :: name :: env
      AFixFunction(name, param, annotate(body, newEnv))
  }
}


