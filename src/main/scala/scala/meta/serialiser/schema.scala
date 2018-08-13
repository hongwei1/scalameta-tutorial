package scala.meta.serialiser

import scala.annotation.StaticAnnotation
import scala.collection.immutable.Seq
import scala.meta._

class schema extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"..$mods object $ename extends $template" = defn

    val generatedClass: Defn.Class =
      q"""
         case class B(b: Int, bb: Int) {def add = b+bb}
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

    println("============== result ==============")
    println("res: " +res)
    println("====================================")
    res
  }
}
