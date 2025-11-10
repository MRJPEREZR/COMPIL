package generator

enum Ins:
  case Add, Sub, Mul, Div, Push, PushEnv, Apply, Popenv
  case Ldi(n: Int)
  case Test(i: List[Ins], j: List[Ins])
  case Mkclos(body: List[Ins])
  case Extend(x: String)  // Still needed for naming during extension
  case Search(index: Int)  // Only this for variable access