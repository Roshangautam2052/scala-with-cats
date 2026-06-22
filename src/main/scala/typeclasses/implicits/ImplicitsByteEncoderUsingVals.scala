package typeclasses.implicits

import java.nio.ByteBuffer

trait ImplicitsByteEncoderUsingVals [A]{
  def encode(a: A):Array[Byte]
}

object ImplicitByteEncoderUsingVals {
  implicit val stringEncoder:ImplicitsByteEncoderUsingVals[String] = instance[String](_.getBytes)

  implicit val intEncoder: ImplicitsByteEncoderUsingVals[Int] = instance[Int](s => {
    val bb = ByteBuffer.allocate(4)
    bb.putInt(s).array()
  }
  )

  // Instance method
  private def instance[A](f: A => Array[Byte]): ImplicitsByteEncoderUsingVals[A] = (a: A) => f(a)


}
