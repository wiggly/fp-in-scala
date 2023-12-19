package wiggly.chapter03

import wiggly.chapter03.List.{Cons, Nil}

enum Tree[+A] {
  case Leaf(value: A)
  case Branch(left: Tree[A], right: Tree[A])

  def size: Int = this match
    case Leaf(_) => 1
    case Branch(l, r) => 1 + l.size + r.size

  // Ex 3.26
  def depth: Int = this match
    case Leaf(_) => 1
    case Branch(l, r) => 1 + l.depth.max(r.depth)

  // Ex 3.27
  def map[B](fn: A => B): Tree[B] = this match {
    case Leaf(v) => Leaf(fn(v))
    case Branch(l, r) => Branch(l.map(fn), r.map(fn))
  }

  // Ex 3.28
  def fold[B](leafFn: A => B, branchFn: (B, B) => B): B = {
    this match
      case Leaf(a) =>
        leafFn(a)
      case Branch(left, right) =>
        branchFn(left.fold(leafFn, branchFn), right.fold(leafFn, branchFn))
  }

  def gsize: Int = this.fold(
    { _ => 1 },
    { (a, b) => a + b }
  )

  def gdepth: Int = this.fold(
    { _ => 1 },
    { (a, b) => 1 + a.max(b) }
  )

  def gmap[B](fn: A => B): Tree[B] = this.fold(
    { a => Leaf(fn(a)) },
    { (left, right) => Branch(left, right) }
  )

}

object Tree {
  extension (t: Tree[Int]) def firstPositive: Int = {
    t match
      case Leaf(i) => i
      case Branch(l, r) =>
        val lpos = l.firstPositive
        if lpos > 0 then lpos else r.firstPositive
  }

  // Ex 3.25
  extension (t: Tree[Int]) def maximum: Int = {
    t match
      case Leaf(i) => i
      case Branch(l, r) =>
        l.maximum.max(r.maximum)
  }
}

