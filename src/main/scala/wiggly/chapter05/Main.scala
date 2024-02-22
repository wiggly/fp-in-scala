package wiggly.chapter05

import scala.{None as _, Option as _, Some as _}
import scala.{LazyList as _}

object Main extends App {

  val x1: LazyList[Int] = LazyList(1,2,3,4,5)

  println(s"x1: $x1")

  println(s"x1 toList: ${x1.toList}")

  println(s"x1.take(3).toList: ${x1.take(3).toList}")

  println(s"x1.drop(3).toList: ${x1.drop(3).toList}")

  println(s"x1.takeWhile(_ <= " +
    s"3).toList: ${x1.takeWhile(_ <= 3).toList}")
}
