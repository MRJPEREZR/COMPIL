package generator

enum Ins:
  case Add, Sub, Mul, Div, PushEnv, Apply, Popenv
  case Ldi(n: Int)
  case Test(i: List[Ins], j: List[Ins])
  case Mkclos(body: List[Ins])
  case Extend(x: String)
  case Search(index: Int)