package wiggly.chapter05

enum LazyList[+A] {
  case Empty
  case Cons(h: () => A, t: () => LazyList[A])

  // Ex 5.1
  def toList: List[A] = {
    this match
      case Empty => Nil
      case Cons(head, tail) => head() :: tail().toList
  }

  // Ex 5.2
  def take(n: Int): LazyList[A] = {
    if(n == 0) {
      Empty
    } else {
      this match {
        case Empty => Empty
        case Cons(head, tail) => Cons(head, () => tail().take(n-1))
      }
    }
  }

  def drop(n: Int): LazyList[A] = {
    if (n == 0) {
      this
    } else {
      this match {
        case Empty => Empty
        case Cons(head, tail) => tail().drop(n - 1)
      }
    }
  }

  // Ex 5.3
//  def takeWhile(p: A => Boolean): LazyList[A] = {
//    this match {
//      case Empty => Empty
//      case Cons(head, tail) => if(p(head())) {
//        Cons(head, () => tail().takeWhile(p))
//      } else {
//        Empty
//      }
//    }
//  }

  def foldRight[B](acc: => B)(f: (A, => B) => B): B = {
    this match
      case Cons(h, t) => f(h(), t().foldRight(acc)(f))
      case _ => acc
  }

  // Ex 5.4
  def forAll(p: A => Boolean): Boolean = {
    this match
      case Empty => true
      case Cons(head, tail) => if(p(head())) {
        tail().forAll(p)
      } else {
        false
      }
  }

  // Ex 5.5
  // this feels mind-bending right now
  def takeWhile(p: A => Boolean): LazyList[A] = {
    this.foldRight(LazyList.empty[A])( (a, b) =>
      if(p(a)) {
        LazyList.cons(a, b)
      } else {
        b
      }
    )
  }

  // Ex 5.6
  def headOption: Option[A] = {
    this.foldRight(Option.empty[A])((a, b) => Option.apply(a))
  }

  // Ex 5.7
  def map[B](fn: A => B): LazyList[B] = {
    this.foldRight(LazyList.empty[B])( (a, b) =>
      LazyList.cons(fn(a), b)
    )
  }

  def filter(p: A => Boolean): LazyList[A] = {
    this.foldRight(LazyList.empty[A])((a, b) =>
      if(p(a)) {
        LazyList.cons(a, b.filter(p))
      } else {
        b.filter(p)
      }
    )
  }

  def append[S >: A](other: => LazyList[S]): LazyList[S] = {
    this.foldRight(other)((a, b) => LazyList.cons(a, b))
  }

  def flatMap[B](fn: A => LazyList[B]): LazyList[B] = {
    this
      .map(fn)
      .foldRight(LazyList.empty[B])( (item, acc) => item.append(acc))
 }

}

object LazyList {

  def cons[A](hd: => A, tl: => LazyList[A]): LazyList[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: LazyList[A] = Empty

  def apply[A](as: A*): LazyList[A] = {
    if as.isEmpty then empty
    else cons(as.head, apply(as.tail*))
  }

}

