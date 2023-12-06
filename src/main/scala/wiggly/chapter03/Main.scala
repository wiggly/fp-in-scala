package wiggly.chapter03

import wiggly.chapter03.List
import wiggly.chapter03.List.*


object Main extends App {
  
  val a = "hello"
  val b = "bye-bye"

  val ex1: List[Double] = Nil
  val ex2: List[Int] = Cons(1, Nil)
  val ex3: List[String] = Cons(a, Cons(b, Nil))

    
}