package scala.meta.serialiser


@schema
object MyObj
{
  
  case class A()
  
}


object Main extends App
{
  val b = new MyObj.B(2, 22)
  println("#####:"+b.add)
  val q = MyObj.A()
  println(b)
  
  @mappable case class SimpleCaseClass(
    i: Int,
    s: String
  )
  
  val testInstance = SimpleCaseClass(i = 42, s = "something")
  println(testInstance.toMap)
  
}
  
