package wiggly.chapter05

import scala.{None as _, Option as _, Some as _}
import scala.{LazyList as _}

object Main extends App {

  val x1: LazyList[Int] = LazyList(1,2,3,4,5)

  println(s"x1: $x1")

  println(s"x1 toList: ${x1.toList}")

  println(s"x1.take(3).toList: ${x1.take(3).toList}")

  println(s"x1.drop(3).toList: ${x1.drop(3).toList}")

  println(s"x1.takeWhile(_ <= 3).toList: ${x1.takeWhile(_ <= 3).toList}")

  println(s"x1.forAll(_ < 3): ${x1.forAll(_ <= 3)}")

  println(s"x1.forAll(_ < 100): ${x1.forAll(_ <= 100)}")

  println(s"x1.headOption: ${x1.headOption}")

  println(s"x1.drop(10).headOption: ${x1.drop(10).headOption}")

  println(s"x1.map(_ + 50): ${x1.map(_ + 50).toList}")

  println(s"x1.map(_ * 23 / 7 * 89).map(_.toString.reverse): ${x1.map(_ * 23 / 7 * 89).map(_.toString.reverse).toList}")

  println(s"x1.filter(_ - 3 > 0): ${x1.filter(_ - 3 > 0).toList}")

  println(s"x1.append(x1): ${x1.append(x1).toList}")

  println(s"x1.flatMap({n => LazyList(n * -1, n)}): ${x1.flatMap({ n => LazyList(n * -1, n) }).toList}")


  def str(value: String): String = {
    println(s"EVAL ${value}")
    value
  }

  val foo: LazyList[String] = LazyList(str("a"),str("b"),str("c"))


}
