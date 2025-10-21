package generator

import parser._

object Generator:
  def gen(term: Term): Code = term match
    case Number(n) => Ldi(n)
