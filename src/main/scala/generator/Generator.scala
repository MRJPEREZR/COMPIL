package generator

import scala.io.Source
import parser.*
import Ins.*

type Code = List[Ins]
type CodeWAT = List[WAT]
enum WAT:
  case Ins(ins: String)
  case Test(code1: CodeWAT, code2: CodeWAT)

object Generator:

  def gen(aTerm: ATerm): String =
    genWAT(genAM(aTerm))

  def genWAT(code: Code): String =
    val pre = prelude()
    val body = format(3, emit(code))
    s"""$pre
       |(func (export "main") (result i32)
       |$body
       |  return)
       |)
       |""".stripMargin

  def prelude(): String =
    val source = Source.fromFile("./src/main/wat/prelude.wat")
    val contents = source.mkString
    source.close()
    contents

  private def spaces(depth: Int): String =
    (for _ <- 0 until depth yield "  ").mkString

  def format(depth: Int, code: CodeWAT): String =
    code.map(formatIns(depth, _)).mkString("\n")

  def formatIns(depth: Int, wat: WAT): String = wat match
    case WAT.Ins(text) =>
      spaces(depth) + text

    case WAT.Test(code1, code2) =>
      val thenPart = format(depth + 1, code1)
      val elsePart = format(depth + 1, code2)

      s"""${spaces(depth)}(if (result i32)
        ${spaces(depth + 1)}(then
        $thenPart
        ${spaces(depth + 1)}) ;; end then
        ${spaces(depth + 1)}(else
        $elsePart
        ${spaces(depth + 1)}) ;; end else
        ${spaces(depth)}) ;; end if"""

  def emit(code: Code): CodeWAT =
    code.flatMap(emitIns)

  def emitIns(ins: Ins): CodeWAT = ins match
    case Ldi(n) =>
      List(WAT.Ins(s"(i32.const $n)"))

    case Add =>
      List(WAT.Ins("i32.add"))

    case Sub =>
      List(WAT.Ins("i32.sub"))

    case Mul =>
      List(WAT.Ins("i32.mul"))

    case Div =>
      List(WAT.Ins("i32.div_s"))

    case Search(n) =>
      List(
        WAT.Ins(s"(i32.const $n)"),
        WAT.Ins("(global.get $ENV)"),
        WAT.Ins("(call $search)")
      )

    case PushEnv =>
      List(WAT.Ins("(global.get $ENV)"))

    case Extend(_) =>
      List(
        WAT.Ins("(global.get $ENV)"),
        WAT.Ins("(call $cons)"),
        WAT.Ins("(global.set $ENV)")
      )

    case Popenv =>
      List(
        WAT.Ins("(global.set $ACC)"),
        WAT.Ins("(global.set $ENV)"),
        WAT.Ins("(global.get $ACC)")
      )

    case Mkclos(_) =>
      sys.error("Closures are not required until Apply is implemented")

    case Apply =>
      sys.error("Apply not yet supported")

    case Test(thenC, elseC) =>
      List(WAT.Test(emit(thenC), emit(elseC)))

  def genAM(aterm: ATerm): Code = aterm match {

    // variable annotated with De Bruijn index
    case AVariable(_, index) =>
      List(Search(index))

    // integer constant
    case AConstant(n) =>
      List(Ldi(n))

    // binary operation
    case ABinaryOperation(left, op, right) =>
      genAM(left) ::: genAM(right) ::: List(gen_op(op))

    // conditional
    case AIfZero(cond, thenBranch, elseBranch) =>
      val condCode = genAM(cond)
      val thenCode = genAM(thenBranch)
      val elseCode = genAM(elseBranch)
      condCode ::: List(Test(thenCode, elseCode))

    // let
    case ALet(_, value, body) =>
      val valueCode = genAM(value)
      val bodyCode = genAM(body)
      List(PushEnv) ::: valueCode ::: List(Extend("")) ::: bodyCode ::: List(Popenv)

    // function 
    case AFunction(_, body) =>
      List(Mkclos(genAM(body)))

    // application
    case AApplication(func, arg) =>
      genAM(func) ::: genAM(arg) ::: List(Apply)

    // fix
    case AFix(_, func @ AFunction(param, funcBody)) =>
      List(Mkclos(genAM(funcBody)))

    // fix case to support also vars (not only fun)
    case AFix(_, other) =>
      List(Mkclos(genAM(other)))

    // fix func
    case AFixFunction(_, _, body) =>
      List(Mkclos(genAM(body)))

    // unexpected cases
    case other =>
      throw new Exception(s"Generator: unsupported AST node: $other")
  }

  def gen_op(op: String): Ins = op match {
    case "+" => Add
    case "-" => Sub
    case "*" => Mul
    case "/" => Div
    case _ => throw new Exception(s"unknown binary operator: $op")
  }
