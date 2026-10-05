import cats.Monoid

case class IntMonoid(a1: Int)

object IntMonoid {

  def combine(value1:IntMonoid, value2:IntMonoid):IntMonoid = {
    IntMonoid(value1.a1 + value2.a1)
  }
  implicit val sumMonoid: Monoid[IntMonoid] = Monoid.instance(IntMonoid(0), combine)
}

println(s"Int monoid :: ${Monoid[IntMonoid].combine(IntMonoid(12), IntMonoid(2))}")



val sumMonoid: Monoid[Int] = Monoid.instance(0, _ + _)
val minMonoid: Monoid[Int] = Monoid.instance(Int.MaxValue, _ min _)
def listMonoid[A]: Monoid[List[A]] = Monoid.instance(Nil, _ ++ _)
val stringMonoid:Monoid[String] = Monoid.instance("", _ + _)

sumMonoid.combine(2, 3)
minMonoid.combine(6, minMonoid.empty)
listMonoid[Boolean].combine(List(true, false), List(false, true))
stringMonoid.combine("hello", " world")

