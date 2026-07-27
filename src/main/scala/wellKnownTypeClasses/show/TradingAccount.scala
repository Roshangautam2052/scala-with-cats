package wellKnownTypeClasses.show

import cats.Show

case class TradingAccount(id:BigDecimal, owner:String, balance:String, sortCode:String, accountNumber:String)


object TradingAccount {
  implicit val toStringTradingAccount:Show[TradingAccount] = Show.fromToString

  object Instances {
    implicit val byOwnerAndBalance:Show[TradingAccount] = Show.show { account =>
      s"${account.owner} - $$${account.balance}"
    }

    // Write an instance which will output This account belongs to "Owner"
    implicit val byOwner:Show[TradingAccount] = Show.show { account =>
      s"This account belongs to : ${account.owner}"

    }
  }
}
