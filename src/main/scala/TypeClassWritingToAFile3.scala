import java.io.FileOutputStream
import java.nio.ByteBuffer
import scala.util.Using

trait ByteEncoder[A] {
  def encoder(a: A): Array[Byte]
}

trait Channel {
  def writer[A](obj:A, enc:ByteEncoder[A]): Unit
}

object FileChannelEfficient extends Channel {

  override def writer[A](obj: A, enc: ByteEncoder[A]): Unit = {
    val bytes: Array[Byte] = enc.encoder(obj)

    Using(new FileOutputStream("scala-with-cats/write")) { oss =>
      oss.write(bytes)
      oss.flush()
    }
  }
}

object IntByteEncoder extends ByteEncoder[Int] {
  override def encoder(a: Int): Array[Byte] = {
    val bb = ByteBuffer.allocate(4)
    bb.putInt(a)
    bb.array()
  }
}

object StringByteEncoder extends ByteEncoder[String] {
  override def encoder(a: String): Array[Byte] = {
    a.getBytes
  }
}