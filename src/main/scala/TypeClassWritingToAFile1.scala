import java.io.FileOutputStream
import java.nio.ByteBuffer
import scala.util.Using

trait Channel1 {
  def write(obj: Any): Unit
}

object FileChannel extends Channel1   {
  // The problem with this method is there is unhandled cases and it is using two responsibilities
  // getting a byte and writing it into a file
  override def write(obj: Any): Unit = {
    val bytes: Array[Byte] = obj match  {
      case n: Int =>
        val byteBuffer = ByteBuffer.allocate(4)
        byteBuffer.putInt(n)
        byteBuffer.array()

      case s: String =>
        s.getBytes

      case invalid => throw new Exception("unhandled")
    }

    Using(new FileOutputStream("scala-with-cats/test")) { oss =>
      oss.write(bytes)
      oss.flush()
    }
  }
}