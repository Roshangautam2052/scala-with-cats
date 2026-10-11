import cats.Functor

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class Secret[A](val value: A) {

  private def hashed: String = {
    val s = value.toString
    val bytes = s.getBytes(StandardCharsets.UTF_8)
    val d = MessageDigest.getInstance("SHA-1")
    val hashBytes = d.digest(bytes)
    new String(hashBytes, StandardCharsets.UTF_8)
  }

  override def toString: String = hashed
}

object Secret {
  implicit val secretFunctor: Functor[Secret] = new Functor[Secret] {
    override def map[A, B](fa: Secret[A])(f: A => B): Secret[B] = {
      new Secret(f(fa.value))
    }
  }
}

val leonardoSecret: Secret[String] = new Secret(value = "Leonardo")

leonardoSecret.value

val lowerCase = Functor[Secret].map(leonardoSecret)(_.toLowerCase)
lowerCase.value

//Implementation for option and list

val optionFunctor: Functor[Option] = new Functor[Option] {
  override def map[A, B](fa: Option[A])(f: A => B): Option[B] = {
    Option(f(fa.getOrElse(throw new Error("value not defined"))))
  }
}

val listFunctor: Functor[List] = new Functor[List] {
  override def map[A, B](fa: List[A])(f: A => B): List[B] = {
    fa match {
      case Nil => Nil
      case hd :: tail => f(hd) :: map(tail)(f)
    }
  }

}