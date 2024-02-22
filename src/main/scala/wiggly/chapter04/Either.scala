package wiggly.chapter04

import wiggly.chapter04.Option.None

// Ex 4.6
enum Either[+E, +A] {
  case Left(value: E)
  case Right(value: A)

  def map[B](fn: A => B): Either[E, B] = this match
    case Left(e) => Left(e)
    case Right(value) => Right(fn(value))

  def flatMap[EE >: E, B](fn: A => Either[EE, B]): Either[EE, B] =
    map(fn) match {
      case Left(e) => Left(e)
      case Right(value) => value
    }

  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] =
    this match {
      case Left(e) => b
      case Right(value) => Right(value)
    }

  def map2[EE >: E, B, C](that: Either[EE, B])(fn: (A, B) => C): Either[EE, C] = {
    this.flatMap(x =>
      that.map(y =>
        fn(x, y)
      )
    )
  }

}

object Either {
  // Ex 4.7
  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = {
    as
      .foldLeft[Either[E, List[B]]](Right(List[B]()))((acc, a) =>
        acc.map2(f(a))((list, next) => next :: list)
      )
      .map(_.reverse)
  }

  def sequence[E, A](as: List[Either[E, A]]): Either[E, List[A]] =
    traverse(as)(identity)
}