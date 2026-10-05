package typeclasses.monoid

import cats.implicits.catsSyntaxSemigroup
import cats._
import cats.implicits._
import cats.kernel.Eq._

object TestMonoid extends App {

  println(Monoid[Speed].combine(Speed(1000), Speed(2000)))
  println(Monoid[Speed].empty)
  
  //Operations in Monoid
  Speed(1000) |+| Speed(2000)
  Monoid[Speed].combineAll(List(Speed(10), Speed(12), Speed(13)))
  List(Speed(1), Speed(2), Speed(3)).combineAll
//  Monoid[Speed].isEmpty(Speed(100))
//  Monoid[Speed].isEmpty(Speed(0))

}
