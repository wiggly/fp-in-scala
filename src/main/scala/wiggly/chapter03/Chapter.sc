
import wiggly.chapter03.List
import wiggly.chapter03.List.*

val a = "hello"
val b = "bye-bye"

val nums: List[Int] = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))

val ex1: List[Double] = Nil
val ex2: List[Int] = Cons(1, Nil)
val ex3: List[String] = Cons(a, Cons(b, Nil))


// Ex 3.1

val ex3expected: Int = 3

val ex3actual: Int = List(1,2,3,4,5) match
case Cons(x, Cons(2, Cons(4, _))) => x
case Nil => 42
case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
case Cons(h, t) => h + sum(t)
case _ => 101

assert(ex3expected == ex3actual)

// Ex3.2
val ex4 = List.tail(ex3)

// Ex 3.3
val ex5 = List.setHead(ex3, "wiggly")

// ex 3.4
val some = List.drop(nums, 2)

// ex 3.5
val evens = List.dropWhile(nums, _ % 2 == 0)

// ex 3.6
val start = List.init(nums)

// Ex 3.7
// Not without changing the signature of the generic fold since it has no idea the semantics of product or why 0.0 is special.

// Ex 3.8
val identNums = List.foldRight[Int,List[Int]](nums, Nil, Cons(_, _))

// Ex 3.9
val numsLength = length(nums)

