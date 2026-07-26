package wellKnownTypeClasses.eq

import cats._
import cats.implicits._

case class Account(id:Long, number:String, balance:Double, owner:String)

object Account {

  implicit val universalEq:Eq[Account] = Eq.fromUniversalEquals // ==

  object Instances {
    implicit def bvIdEq(implicit eqLong:Eq[Long]):Eq[Account] = Eq.instance[Account]((a1, a2) => eqLong.eqv(a1.id , a2.id))

    implicit def byIdEq2(implicit eqLong:Eq[Long]):Eq[Account] = Eq.by(_.id)
    
    // compare of accounts by number
    implicit def byNumberEq1(implicit eqString:Eq[String]):Eq[Account] = Eq.instance[Account]((a1, a2) => eqString.eqv(a1.number, a2.number))
    implicit def byNumberEq2(implicit eqString:Eq[String]):Eq[Account] = Eq.by(_.number)
  }
}
