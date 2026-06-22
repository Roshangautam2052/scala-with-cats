package typeclasses


trait ByteEncoderForString[B]{
  def encoder (value:B) :Array[Byte]
}

object ByteEncoderForString {
  implicit object StringEncoder extends ByteEncoderForString[String] {
    override def encoder (value: String): Array[Byte] = {
      value.getBytes
    }
  }

  //This can be renamed to apply so that it can be accessed independently without using this call
  // This is a Helper method
  def apply[A](implicit ev:ByteEncoderForString[A]):ByteEncoderForString[A] = ev
}




