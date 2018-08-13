import scala.meta._

//val q"..$mods class $tName (..$params) extends $template" = q"object MyObj { case class A()}"
val q"..$mods object $ename extends $template" = q"object MyObj { case class A()}"
//mods: scala.collection.immutable.Seq[scala.meta.Mod] = List()
//ename: scala.meta.Term.Name = MyObj
//template: scala.meta.Template = { case class A() }

val generatedClass: Defn.Class =
      q"""
         case class B(b: Int, bb: Int){def add = 1+1}
       """

//the stats that were defined in the annotated object
val existingStats: scala.collection.immutable.Seq[Stat] = template.stats.get


//new stats
    val stats = Some(existingStats :+ generatedClass)

    //the new template
    val newT = template.copy(stats=stats)

    val res =
      q"""
         ..$mods object $ename extends $newT
       """



case class B(b: Int, bb: Int){
  def add = 1+1
}