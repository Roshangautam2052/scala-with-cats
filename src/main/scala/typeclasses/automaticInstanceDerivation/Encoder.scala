package typeclasses.automaticInstanceDerivation

import typeclasses.automaticInstanceDerivation.Encoder.StringEncoder
import typeclasses.laws.Tests.ByteEncoder

import java.nio.ByteBuffer

/*
 If we want to create an encoder which can handle string and an Option[String]
   how can we do that because at the moment we have only StringEncoder which handle a normal string
 */
trait Encoder[A]{
  def encode(a: A):Array[Byte]
}

object Encoder {

  implicit object IntEncoder extends Encoder[Int]{

    override def encode(a: Int): Array[Byte] = {
      val bb = ByteBuffer.allocate(4)
      bb.putInt(a).array()
    }
  }
  implicit object StringEncoder extends Encoder[String]{
    override def encode(a: String): Array[Byte] = {
      a.getBytes
    }
  }
}


// This method creates a lot of duplication so there exists a much better way of handling this
// Lets say if we want to encode Option[Int] then there will be duplication as well

//implicit object OptionStringEncoder extends Encoder[Option[String]] {
//
//  override def encode(a: Option[String]): Array[Byte] = {
//    a match {
//      case None => Array[Byte]() // returning empty array
//      case Some(value) => StringEncoder.encode(value)
//    }
//  }

// more efficient way of handling this looks something like this can handle both Option[A] 

implicit def optionEncoder[A](implicit encA: Encoder[A]): Encoder[Option[A]] = {
  case Some(value) => encA.encode(value)
  case None => Array[Byte]()
}