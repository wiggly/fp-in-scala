import scala.{Option => _, Some => _, None => _}

val answer = Some(42)


answer.get


def mean(xs: Seq[Double]): Option[Double] = {
  if xs.isEmpty then None
  else Some(xs.sum / xs.length)
}

def variance(xs: Seq[Double]): Option[Double] = {
  ???
}