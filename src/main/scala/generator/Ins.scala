package generator

enum Ins:
    case Add, Sub, Mul, Div, Push
    case Ldi(n:Int)
    case Test(i:List[Ins], j:List[Ins])