package typeclasses

import java.io.FileOutputStream
import scala.util.Using


trait ByteEncoder4[A] {
  def encoder(a: A): Array[Byte]
}

object ByteEncoder4 {
  implicit object StringByteEncoder4 extends ByteEncoder4[String]{
    override def encoder(a: String): Array[Byte] = a.getBytes
  }
}

trait Channel4 {
  def writer[A](obj: A)(implicit enc:ByteEncoder4[A]): Unit
}


object FileChannel4 extends Channel4 {
  override def writer[A](obj: A)(implicit enc: ByteEncoder4[A]): Unit = {
    val bytes = enc.encoder(obj)
    val basePath = System.getProperty("user.dir")
    Using(new FileOutputStream(s"$basePath//testFile.txt")) { obj =>
      obj.write(bytes)
      obj.flush()
    }.get
  }

}