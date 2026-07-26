package wellKnownTypeClasses.order

import cats.*
import cats.implicits.*
import cats.syntax.OrderSyntax

case class FinanceAccount(id:Long, number:String, balance:Double, owner:String) {


}

object FinanceAccount {

  implicit val orderById:Order[FinanceAccount] = Order.from((a1, a2) => Order[Long].compare(a1.id, a2.id))

  object Instances {
    implicit val orderByNumber: Order[FinanceAccount] = Order.by(account => account.number)
    implicit val orderByBalance: Order[FinanceAccount] = Order.by(account => account.balance)
  }

  // Order returns the sorted by ascending order by default
  def sort[A](list: List[A])(implicit orderA: Order[A]): List[A] = {
    list.sorted(orderA.toOrdering)
  }


}


