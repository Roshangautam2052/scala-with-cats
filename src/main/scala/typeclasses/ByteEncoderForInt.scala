package typeclasses

import java.nio.ByteBuffer

trait ByteEncoderForInt [A]{
  def encode(a: A):Array[Byte]
}

object ByteEncoderForInt {
  implicit object ByteEncodingMethodForInt extends ByteEncoderForInt[Int] {
    override def encode(a: Int): Array[Byte] = {
      val bb = ByteBuffer.allocate(4)
      bb.putInt(a)
      bb.array()
    }
  }
}