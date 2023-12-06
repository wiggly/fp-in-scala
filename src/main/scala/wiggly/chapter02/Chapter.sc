import scala.annotation.tailrec

// Ex 2.1
def fib(n: Int): Int = {
  @tailrec
  def go(current: Int, next: Int, n: Int): Int = {
      if(n <= 0) {
        current
      } else {
        go(next, next+1, n-1)
      }
   }
  go(0, 1, n)
}

val first = fib(1)

val second = fib(2)

val third = fib(3)

val fourth = fib(4)

val fifth = fib(5)

val tenth = fib(10)

val twentieth = fib(20)


// Ex 2.2

def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
  if(as.length < 2) {
    true
  } else {
    as.sliding(2).forall(pair => gt(pair(1), pair(0)))
  }
}

isSorted(Array(1, 2, 3), _ > _)
isSorted(Array(1, 2, 1), _ > _)
isSorted(Array(3, 2, 1), _ < _)
isSorted(Array(1, 2, 3), _ < _)
// Bonus check
isSorted(Array(1), _ > _)
// Bonus check
isSorted(Array[Int](), _ > _)

// Ex 2.3

def curry[A, B, C](f: (A,B) => C): A => (B => C) = {
  { a =>
    { b =>
      f(a,b)
    }
  }
}

def mul(a: Int, b: Int): Int = {
  a * b
}

val curryMul = curry(mul)

val mul45 = mul(4, 5)

val curryMul45 = curryMul(4)(5)

val fn: Int => Int = curryMul(4)


def processStuff(config: String, param1: Int): Int = ???


val curryProcesStuff: String => Int => Int = curry(processStuff)

def needsProcessor(fn: Int => Int): Int = ???

needsProcessor( curryProcesStuff("config") )









// Ex 2.4

def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
  {
    (a, b) => f(a)(b)
  }
}

val uncurryCurryMul = uncurry(curryMul)

val uncurryCurryMul45 = uncurryCurryMul(4, 5)


// Ex 2.5

def compose[A, B, C](f: B => C, g: A => B): A => C = {
  { a =>
    f(g(a))
  }
}

def mul5(n: Int): Int = n * 5

def add3(n: Int): Int = n + 3

def mul5Add3 = compose(add3, mul5)

val uncomposed = add3(mul5(6))

val composed = mul5Add3(6)










