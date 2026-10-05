package typeclasses.monoid

import cats.Monoid
import cats.implicits._

case class Speed(metersPerSecond: Double) {
  def kilometersPerSec: Double = metersPerSecond / 1000.00

  def milesPerSec: Double = metersPerSecond / 1609.34
}

object Speed {

  def addSpeeds(s1: Speed, s2: Speed): Speed =
    Speed(s1.metersPerSecond + s2.metersPerSecond)


//  implicit val monoidSpeed: Monoid[Speed] = new Monoid[Speed]{
//    override def combine(x: Speed, y: Speed): Speed = ???
//
//    override def empty: Speed = ???
//  }

  implicit val monoidSpeed: Monoid[Speed] = Monoid.instance(Speed(0), addSpeeds)
  

}


