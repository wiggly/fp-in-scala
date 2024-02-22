package wiggly.chapter04

import scala.{None as _, Option as _, Some as _}
import scala.{Left as _, Either as _, Right as _}
import Option.{None, *}
import Either.*

object Main extends App {

  def mean(xs: Seq[Double]): Option[Double] = {
    if xs.isEmpty then None
    else Some(xs.sum / xs.length)
  }

  val answer = Some(42)
  println(answer)

  // Ex 4.2
  def variance(xs: Seq[Double]): Option[Double] = for {
    average <- mean(xs)
    distances = xs.map(x => math.pow(x-average, 2))
    result <- mean(distances)
  } yield result

  val data = Seq(1.2, 3.4, 5.2, 1.3, 5.6)

  val meanData = mean(data)
  println(meanData)

  val varianceData = variance(data)
  println(varianceData)


  val some3 = Some(3)
  val some7 = Some(7)

  val map2Result = map2(some3, some7)(_ + _)

  println(map2Result)

  def sequence[A](as: List[Option[A]]): Option[List[A]] = {
    val acc: Option[List[A]] = as.head.map(x => List(x))
    val rest = as.tail
    rest
      .foldLeft(acc)( (acc, item) =>
        map2(acc, item)( (list, next) => next :: list)
      )
      .map(_.reverse)
  }

  val goodList: List[Option[Int]] = List(Some(3), Some(42), Some(7))
  val goodSequenced = Option.sequence(goodList)
  println(s"good sequenced: ${goodSequenced}")

  val badList: List[Option[Int]] = List(Some(3), None, Some(7))
  val badSequenced = Option.sequence(badList)
  println(s"bad sequenced: ${badSequenced}")


  def weedHigh(n: Int): Option[Int] = {
    if(n < 100) {
      Some(n)
    } else {
      None
    }
  }

  def weedHighEither(n: Int): Either[String, Int] = {
    if (n < 100) {
      Right(n)
    } else {
      Left(s"${n} is too high")
    }
  }


  val goodTraversed = Option.traverse(List(3,42,7))(weedHigh)
  println(s"good traversed: ${goodTraversed}")

  val badTraversed = Option.traverse(List(3, 42, 102, 7))(weedHigh)
  println(s"bad traversed: ${badTraversed}")


  val goodSequenced2 = Option.sequence(goodList)
  println(s"good sequenced 2: ${goodSequenced2}")

  val badSequenced2 = Option.sequence(badList)
  println(s"bad sequenced 2: ${badSequenced2}")


  val goodEitherTraversed = Either.traverse(List(3, 42, 7))(weedHighEither)
  println(s"good either traversed: ${goodEitherTraversed}")

  val badEitherTraversed = Either.traverse(List(3, 503, 42, 102, 7))(weedHighEither)
  println(s"bad either traversed: ${badEitherTraversed}")



  type F[T] = Option[T]

  type G[T] = List[T]

  type App[T] = List[Option[T]]

  val fa: App[Int] = List(Some(-23))

  object App {
    def lift[T](fn: T => T): App[T] => App[T] = _.map(_.map(fn))

//    fn: T => T
//    lift(fn) = App[T] => App[T]
  }


  val appAbs: App[Int] => App[Int] = App.lift(math.abs)

  val xxx = appAbs(fa)

  println(s"App: $xxx")

  val optA = Some(23)

  val optB = Some(42)


  val optC = optA
    .flatMap(a =>
      optB.flatMap(b =>
        Some(a+b)
      )
    )

  val listA = List(1, 2, 3)

  final case class Thing[T](xxx: T)

  object Thing {
    def flatMap[T](fa: Thing[T], fn: T => Thing[T]): Thing[T] = ???
  }

  object thingSyntax {
    implicit class ThingOps[T](thing: Thing[T]) {
      def flatMap(fn: T=> Thing[T]): Thing[T] = Thing.flatMap(thing, fn)
    }
  }

  val forOptC: List[Int] = for {
    x <- listA
  } yield x + 1











}