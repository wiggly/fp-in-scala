import scala.annotation.tailrec
// 0, 1, 1,  2,  3,  5,  8

def fib(n: Int): Int = {

  @tailrec
  def recurse(previous: (Int, Int), position: Int): Int = {
    if(position == 1) {
      previous._1
    } else {
      recurse((previous._2 ,previous._1 + previous._2)  , position - 1)
    }
  }

  recurse( (0, 1), n)
}

var previous = (0,1)
for( ; n != 1; n-- ) {
  previous = (previous._2 ,previous._1 + previous._2)
}
previous._1

//recurse( (0, 1), 5 )
//recurse( (1, 1), 4 )
//recurse( (1, 2), 3 )
//recurse( (2, 3), 2 )
//recurse( (3, 5), 1 )


val fib1 = fib(1)
val fib2 = fib(2)
val fib3 = fib(3)
val fib4 = fib(4)
val fib5 = fib(5)
val fib6 = fib(6)
val fib20 = fib(20)