package scala.meta.serialiser
//https://www.michaelpollmeier.com/2016/12/01/scalameta-code-generation-tutorial
import scala.annotation.StaticAnnotation
import scala.collection.immutable.Seq
import scala.meta._

class mappable extends StaticAnnotation {
  //incline/meta will be invoked at compile time. 
  inline def apply(defn: Any): Any = meta {
    //match a class and extracting the parameters, after this, we have mods, tName,params and template in scope.
    //(..$params) --> this is the quasiquote way to indicate that we are expecting a Seq[Param],
    //eg: examples/src/test/scala/scala/meta/serialiser/MappableTest.scala
    val q"..$mods class $tName (..$params) extends $template" = defn

    val keyValues: Seq[Term] = params.map { param =>
      val memberName = Term.Name(param.name.value)
      q"${param.name.value} -> $memberName"
    }

    //..$keyValues --> it is a Seq. 
    //...$keyValues --> it is a Seq[Seq]. 
    val res = q"""..$mods class $tName(..$params) {
        def toMap(): Map[String, Any] = Map[String, Any](..$keyValues)
        def toMap2(): Map[String, Any] = Map[String, Any](..$keyValues)
      }
    """

    println("============== result mappable ==============")
    println(res)
    println("====================================")
    res
  }
}
