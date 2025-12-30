import java.io.{FileInputStream, FileOutputStream}
import scala.util.Using

trait ByteEncodable {
  def encode(): Array[Byte]
}

trait Channel2 {
  def writer(obj: ByteEncodable): Unit
}

case class FullName(firstName:String, lastName:String) extends ByteEncodable {
  override def encode(): Array[Byte] = {
    firstName.getBytes ++ lastName.getBytes
  }
}

object FileChannel2 extends Channel2{

  override def writer(obj: ByteEncodable): Unit = {
   val bytes: Array[Byte] = obj.encode()

    Using( new FileOutputStream("scala-with-cats/test")) { oss =>
      oss.write(bytes)
      oss.flush()
    }
  }


}