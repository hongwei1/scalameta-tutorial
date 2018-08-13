import scala.collection.immutable.Seq
import scala.meta._

val q"..$mods class $tName (..$params) extends $template" = q"case class SimpleCaseClass(i: Int, s: String)"
//mods: scala.collection.immutable.Seq[scala.meta.Mod] = List()
//tName: meta.Type.Name = SimpleCaseClass
//params: scala.collection.immutable.Seq[scala.meta.Term.Param] = List(i: Int, s: String)
//template: scala.meta.Template = 

val keyValues: Seq[Term] = params.map { param =>
      val memberName = Term.Name(param.name.value)
      q"${param.name.value} -> $memberName"
    }