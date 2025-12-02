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
    val (code, nClosures) = genAM(aTerm, 0)
    genWAT(code, nClosures)

  def genWAT(code: Code, nClosures: Int): String =
    val pre = prelude().trim
    val table = emitTable(nClosures)
    val body = format(3, emit(code))
  
    s"""(module
       |$pre
       |
       |$table
       |
       |  (func (export "main") (result i32)
       |$body
       |    return)
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

    case Mkclos(body, index) =>
      List(
        WAT.Ins(s"(i32.const $index)"),
        WAT.Ins("(global.get $ENV)"),
        WAT.Ins("(call $pair)")
      )

    case Apply =>
      List(WAT.Ins("(call $apply)"))

    case Test(thenC, elseC) =>
      List(
        WAT.Ins("i32.eqz"),
        WAT.Test(emit(thenC), emit(elseC))
      )

  private def emitTable(n: Int): String =
    if n <= 0 then "" else {
      val elems = (0 until n).mkString(" ")
      s"""  (table funcref
         |    (elem $elems)
         |  )""".stripMargin
    }

  def genAM(aterm: ATerm, idx: Int): (Code, Int) = aterm match {
    // variable annotated with De Bruijn index
    case AVariable(_, index) =>
      (List(Search(index)), idx)

    // integer constant
    case AConstant(n) =>
      (List(Ldi(n)),idx)

    // binary operation
    case ABinaryOperation(left, op, right) =>
      val (c1, idx1) = genAM(left, idx)
      val (c2, idx2) = genAM(right, idx1)
      (c1 ::: c2 ::: List(gen_op(op)), idx2)

    // conditional
    case AIfZero(cond, thenBranch, elseBranch) =>
      val (condCode, idx1) = genAM(cond, idx)
      val (thenCode, idx2) = genAM(thenBranch, idx1)
      val (elseCode, idx3) = genAM(elseBranch, idx2)
      (condCode ::: List(Test(thenCode, elseCode)), idx3)

    // let
    case ALet(_, value, body) =>
      val (valueCode, idx1) = genAM(value, idx)
      val (bodyCode, idx2) = genAM(body, idx1)
      (List(PushEnv) ::: valueCode ::: List(Extend("")) ::: bodyCode ::: List(Popenv), idx2)

    // function 
    case AFunction(_, body) =>
      val (bodyCode, newIdx) = genAM(body, idx)
      (List(Mkclos(bodyCode, idx)), newIdx)

    // application
    case AApplication(func, arg) =>
      val (funcCode, idx1) = genAM(func, idx)
      val (argCode, idx2) = genAM(arg, idx1)
      (funcCode ::: argCode ::: List(Apply), idx2)

    // fix func
    case AFixFunction(_, _, body) =>
      val (bodyCode, newIdx) = genAM(body, idx)
      (List(Mkclos(bodyCode, idx)), newIdx)

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
