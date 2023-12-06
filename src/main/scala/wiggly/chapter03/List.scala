package wiggly.chapter03

import scala.annotation.tailrec

enum List[+A]:
  case Nil
  case Cons(head: A, tail: List[A])

object List:
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

  def foldLeft[A, B](as: List[A], acc: B, f: (B, A) => B): B =
    as match
      case Nil => acc
      case Cons(x, xs) => foldLeft(xs, f(acc,x), f)