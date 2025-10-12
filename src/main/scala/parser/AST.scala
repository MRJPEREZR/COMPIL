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
