package typeclasses.laws

object Tests {

  trait ByteEncoder[A] {
    def encode(a: A): Array[Byte]
  }

  trait ByteDecoder[A] {
    def decode(a: Array[Byte]): Option[A]
  }

  trait ByteCodec[A] extends ByteEncoder[A] with ByteDecoder[A]


  trait ByteCodecLaws[A] {
    def codec: ByteCodec[A]

    def isomorphism(a : A): Boolean =
      codec.decode(codec.encode(a)).contains(a)
  }
  
}
