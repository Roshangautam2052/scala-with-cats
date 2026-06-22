package typeclasses.implicits

import scala.util.Try

trait GenericByteDecoder[A] {
  def decode(bytes: Array[Byte]):Option[A]
}

object GenericByteDecoder {

  def apply[A](implicit ev: GenericByteDecoder[A]):GenericByteDecoder[A] = ev

  def instance[A](f: Array[Byte] => Option[A]):GenericByteDecoder[A] = (s: Array[Byte]) => f(s)

  implicit val GenericStringDecoder: GenericByteDecoder[String] = GenericByteDecoder.instance[String](s =>
    Try {
      new String(s)
    }.toOption
  )

}

