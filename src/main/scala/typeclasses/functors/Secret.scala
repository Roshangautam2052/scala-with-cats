package typeclasses.functors

abstract class Secret[A](protected val value: A) {

  def toUpper(name: Secret[String]): Secret[String]

  def toLower(name: Secret[String]): Secret[String]

  def map[B](f: A => B): Secret[B]

  protected def hashed: String

  override def toString: String = hashed

}

class SecretImpl[A](value: A) extends Secret[A](value: A) {
  private val emptyString = ""

  override def toLower(name: Secret[String]): Secret[String] =
    value match
      case value: Person => new SecretImpl(value.name.toLowerCase)
      case _ => new SecretImpl[String](emptyString)

  override def toUpper(name: Secret[String]): Secret[String] = {
    value match
      case value: Person => new SecretImpl(value.name.toUpperCase)
      case _ => new SecretImpl[String](emptyString)
  }

  override protected def hashed: String = value.hashCode().toString

  override def toString: String = super.toString

  override def map[B](f: A => B): Secret[B] = {
    new SecretImpl[B](f(value))
  }
}