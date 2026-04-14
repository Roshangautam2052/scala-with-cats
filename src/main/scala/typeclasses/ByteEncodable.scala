package typeclasses

import java.io.FileOutputStream
import scala.util.Using

trait ByteEncodable {
  def encoder(): Array[Byte]
}

trait Channel2 {
  def writer(obj: ByteEncodable):Unit
}

case class FullName(firstName:String, lastName:String) extends ByteEncodable {

  override def encoder(): Array[Byte] = {
    firstName.getBytes ++ lastName.getBytes
  }
}

object FileChannel2 extends Channel2 {

  override def writer(obj: ByteEncodable): Unit = {
    val bytes :Array[Byte] = obj.encoder()
    val basePath = System.getProperty("user.dir")
    Using(new FileOutputStream(s"$basePath//testFile.txt")) { os =>
      os.write(bytes)
      os.flush()
    }.get
  }
}

