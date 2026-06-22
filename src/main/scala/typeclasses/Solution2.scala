package typeclasses

import java.io.FileOutputStream
import java.nio.ByteBuffer
import scala.util.Using

trait ByteEncoder3[A] {
  def encoder(a: A): Array[Byte]
}

trait Channel3 {
  def writer[A](obj: A, enc:ByteEncoder3[A]):Unit
}


object FileChannel3 extends Channel3 {

  override def writer[A](obj: A, enc: ByteEncoder3[A]): Unit = {
    val bytes = enc.encoder(obj)
    val basePath = System.getProperty("user.dir")
    Using(new FileOutputStream(s"$basePath//testFile.txt")) { obj =>
      obj.write(bytes)
      obj.flush()
    }.get
  }
}

object IntByteEncoder extends ByteEncoder3[Int]{
  override def encoder(n: Int): Array[Byte] = {
    val bb = ByteBuffer.allocate(4)
    bb.putInt(n)
    bb.array()
  }
}

object StringByteEncoder extends ByteEncoder3[String]{

  override def encoder(a: String): Array[Byte] =  {
    a.map(_.toByte).toArray
  }
}