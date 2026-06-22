package typeclasses.implicits

import java.nio.ByteBuffer

trait GenericByteEncoder[A] {
  def encode(a:A):Array[Byte]
}

object GenericByteEncoder {
  
  implicit object GenericByteEncoderInt extends GenericByteEncoder[Int] {
    override def encode(a: Int): Array[Byte] = {
      val bb = ByteBuffer.allocate(4)
      bb.putInt(a).array()
    }
  }
  
  implicit object GenericByteEncoderString extends GenericByteEncoder[String] {
    override def encode(a: String): Array[Byte] = {
      a.getBytes()
    }
  }

  implicit object GenericByteEncoderStringRotator extends GenericByteEncoder[String] {
    override def encode(a: String): Array[Byte] = {
      a.getBytes.map(b => (b + 3).toByte)
    }
  }
  
}





