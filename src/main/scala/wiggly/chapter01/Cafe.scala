package wiggly.chapter01

class Coffee:
  val price: Double = 2.0

class CreditCard

case class Charge(cc: CreditCard, amount: Double):
  def combine(other: Charge): Charge =
    if cc == other.cc then
      Charge(cc, amount + other.amount)
    else
      throw Exception("Can't combine charges with different cards")

class Cafe:
  def buyCoffee(cc: CreditCard): (Coffee, Charge) =
    val cup = Coffee()
    (cup, Charge(cc, cup.price))

val cc = CreditCard()
val cafe = Cafe()
val result = cafe.buyCoffee(cc)
