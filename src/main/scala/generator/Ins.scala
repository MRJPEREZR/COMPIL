package generator

enum Ins:
    case Add, Sub, Mul, Div, Push, PushEnv, Apply, Popenv, Extend
    case Ldi(n:Int)
    case Test(i:List[Ins], j:List[Ins])
    case Mkclos(t:List[Ins])