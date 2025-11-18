package test

trait TestRed {
  val verbose : Boolean
  def report(): Unit
  def test(verbose: Boolean, filename: String, message: String, expectation: Option[Int]): Unit
  def test(): Unit = {
    test(verbose, "src/test/red0.pcf", "a simple constant function", Option(0))
    test(verbose, "src/test/red1.pcf", "calling a simple constant function", Option(11))
    test(verbose, "src/test/red2.pcf", "calling a simple function", Option(22))
    test(verbose, "src/test/red3.pcf", "calling a simple named function", Option(33))
    test(verbose, "src/test/red4.pcf", "calling a function defined as a let", Option(44))
    test(verbose, "src/test/red40.pcf", "defining 2 functions without using them", Option(3))
    test(verbose, "src/test/red41.pcf", "defining 2 functions and using one", Option(2))
    test(verbose, "src/test/red42.pcf", "defining 2 functions and using both", Option(3))
    test(verbose, "src/test/red5.pcf", "calling a currified function", Option(55))
    test(verbose, "src/test/red6.pcf", "calling a currified function", Option(66))
    test(verbose, "src/test/red7.pcf", "calling a currified function", Option(77))

    test(verbose, "src/test/red11.pcf", "type error", None)
    test(verbose, "src/test/red13.pcf", "function/ifz typing", Some(1))
    test(verbose, "src/test/red14.pcf", "function/ifz typing", Some(11))
    test(verbose, "src/test/red15.pcf", "function/ifz typing", Some(11))
    test(verbose, "src/test/red16.pcf", "function/ifz typing", Some(16))
    test(verbose, "src/test/red17.pcf", "static scope", Some(2))
    test(verbose, "src/test/red18.pcf", "type error", None)
    test(verbose, "src/test/red19.pcf", "type error", None)
    report()
  }
}
