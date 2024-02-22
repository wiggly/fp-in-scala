package wiggly.chapter04

// Ex 4.1
enum Option[+A] {
  case Some(get: A)
  case None

  def map[B](fn: A => B): Option[B] = this match
    case None => None
    case Some(value) => Some(fn(value))

  def flatMap[B](fn: A => Option[B]): Option[B] =
    map(fn).getOrElse(None)

  def getOrElse[B >: A](default: => B): B = this match
    case None => default
    case Some(value) => value

  def orElse[B >: A](ob: => Option[B]): Option[B] =
    map(Some.apply).getOrElse(ob)

  def filter(fn: A => Boolean): Option[A] =
    flatMap(a => if(fn(a)) { Some(a) } else { None })
}

object Option {
  // Ex 4.3
  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    a.flatMap(x =>
      b.map(y =>
        f(x, y)
      )
    )
  }

  // Ex 4.5
  def traverse[A, B](as: List[A])(f: A => Option[B]): Option[List[B]] = {
    as
      .foldLeft(Some(List[B]()))((acc, a) =>
        map2(acc, f(a))((list, next) => next :: list)
      )
      .map(_.reverse)
  }

  def sequence[A](as: List[Option[A]]): Option[List[A]] =
    traverse(as)(identity)
}