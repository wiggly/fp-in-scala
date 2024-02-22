package wiggly.chapter03

import scala.annotation.tailrec

enum List[+A]:
  case Nil
  case Cons(head: A, tail: List[A])

object List {
  def apply[A](as: A*): List[A] =
    if as.isEmpty then List.Nil
    else Cons(as.head, apply(as.tail*))

  def sum(ints: List[Int]): Int = ints match
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)

  def product(doubles: List[Double]): Double = doubles match
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)

  def tail[T](list: List[T]): List[T] = list match
    case Nil => sys.error("Non-total function")
    case Cons(_, tail) => tail

  def setHead[T](list: List[T], value: T): List[T] = list match
    case Nil => sys.error("Non-total function")
    case Cons(_, tail) => Cons(value, tail)

  @tailrec
  def drop[T](list: List[T], n: Int): List[T] = list match
    case Nil => Nil
    case Cons(current, tail) =>
      if n == 1 then
        tail
      else
        drop(tail, n-1)

  def dropWhile[T](list: List[T], p: T => Boolean): List[T] = list match
    case Nil => Nil
    case Cons(current, tail) =>
      if p(current) then
        Cons(current, dropWhile(tail, p))
      else
        dropWhile(tail, p)


  def init[T](list: List[T]): List[T] = list match
    case Nil => Nil
    case Cons(current, Nil) => Nil
    case Cons(current, tail) => Cons(current, init(tail))

  def foldRight[A, B](as: List[A], acc: B, f: (A, B) => B): B =
    as match
      case Nil => acc
      case Cons(x, xs) => f(x, foldRight(xs, acc, f))

  def length[T](list: List[T]): Int =
    foldRight[T,Int](list, 0, { (_, acc) => 1 + acc })

  @tailrec
  def foldLeft[A, B](as: List[A], acc: B, f: (B, A) => B): B =
    as match
      case Nil => acc
      case Cons(x, xs) => foldLeft(xs, f(acc,x), f)

  def sumFL(ns: List[Int]): Int =
    foldLeft(ns, 0, _ + _)

  def productFL(ns: List[Int]): Int =
    foldLeft(ns, 1, _ * _)

  def lengthFL(ns: List[Int]): Int =
    foldLeft(ns, 0, { (acc, _) => acc + 1 })

  def reverse[A](as: List[A]): List[A] = {
    foldLeft(as, Nil: List[A], { (acc, a) => Cons(a, acc) })
  }

  // Ex 3.13
  def foldRightX[A, B](as: List[A], acc: B, fn: (A, B) => B): B = {
    foldLeft(reverse(as), acc, { (a,b) => fn(b, a) })
  }

  // Ex 3.14
  def append[A](a: List[A], b: List[A]): List[A] = {
    foldRight(a, b, Cons.apply)
  }

  // Ex 3.15
  def flatten[A](list: List[List[A]]): List[A] =
    foldLeft(list, Nil: List[A], append)

  // Ex 3.16
  def mapAddOne(ns: List[Int]): List[Int] = ns match {
    case Nil => Nil
    case Cons(n, rest) => Cons(n+1, mapAddOne(rest))
  }

  // Ex 3.17
  def mapDoubleToString(ns: List[Double]): List[String] = ns match {
    case Nil => Nil
    case Cons(d, rest) => Cons(d.toString, mapDoubleToString(rest))
  }

  // Ex 3.18
  def map[A,B](as: List[A], fn: A => B): List[B] = as match {
    case Nil => Nil
    case Cons(v, rest) => Cons(fn(v), map(rest,fn))
  }

  // Ex 3.19
  def filter[A](as: List[A], fn: A => Boolean): List[A] = as match {
    case Nil => Nil
    case Cons(a, rest) => if(fn(a)) { Cons(a, filter(rest, fn)) } else { filter(rest, fn) }
  }

  // Ex 3.20
  def flatMap[A, B](as: List[A], fn: A => List[B]): List[B] =
    flatten(map(as,fn))

  // Ex 3.21
  def filterFM[A](as: List[A], fn: A => Boolean): List[A] = {
    flatMap(as, (a) => {
      if(fn(a)) { Cons(a,Nil) } else { Nil }
    })
  }

  // Ex 3.22
  def zipAdd(as: List[Int], bs: List[Int]): List[Int] = {
    as match
      case Nil => Nil
      case Cons(a, aRest) =>
        bs match
          case Nil => Nil
          case Cons(b, bRest) => Cons(a+b, zipAdd(aRest, bRest))
  }

  // Ex 3.23
  def zipWith[A,B,C](as: List[A], bs: List[B], fn: (A,B) => C): List[C] = {
    as match
      case Nil => Nil
      case Cons(a, aRest) =>
        bs match
          case Nil => Nil
          case Cons(b, bRest) => Cons(fn(a, b), zipWith(aRest, bRest, fn))
  }
  
  def zip[A, B](as: List[A], bs: List[B]): List[(A, B)] =
    zipWith(as, bs, Tuple2.apply)
  
  // Ex 3.24 - fixed
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = {
    // is a a prefix of b
    def isPrefix(a: List[A], b: List[A]): Boolean =
      (length(b) >= length(a)) && foldLeft(zipWith(a, b, _ == _), true, _ && _)

    @tailrec
    def go(current: List[A]): Boolean = {
      if(isPrefix(sub, current)) {
        true
      } else {
        current match
          case Nil => false
          case Cons(_, rest) => go(rest)
      }
    }

    go(sup)
  }
}
