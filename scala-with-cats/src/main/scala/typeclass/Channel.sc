
import java.io.{FileInputStream, FileOutputStream}
import java.nio.ByteBuffer
import scala.util.Using

trait Channel {

  def writer(obj: Any): Unit

}

object FileChannel extends Channel {
  override def writer(obj: Any): Unit = {
    val bytes: Array[Byte] = obj match {
      case n: Int =>
        val bb = ByteBuffer.allocate(4)
        bb.putInt(n)
        bb.array()

      case s: String =>
        s.getBytes

      case invalid => throw new Exception("unhandled")
    }

    Using(new FileOutputStream("scala-with-cats/src/test")) { os =>
      os.write(bytes)
      os.flush()

    }
  }
}

FileChannel.writer("HELLO")