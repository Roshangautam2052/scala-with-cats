package typeclasses

import java.io.FileOutputStream
import java.nio.ByteBuffer
import scala.util.Using

trait Channel {
  def writer(obj:Any):Unit
}

object FileChannel extends Channel{

  override def writer(obj: Any): Unit = {
    // grab array of byte of an object, and we need to identify the type of object before writing it to a file

    val bytes: Array[Byte] = obj match  {
      case n:Int =>
        val bb = ByteBuffer.allocate(4)
        // save bytes of Int in buffer
        bb.putInt(n)
        // converts the bytes into array
        bb.array()
      case s:String =>
        s.getBytes("UTF-8")
      case invalid => throw new Exception("unhandled array type")
    }

    // Handle automatic closing of stream using utility function
    // called Using which handles closing of the file and resource as well
    val basePath = System.getProperty("user.dir")
    Using(new FileOutputStream(s"$basePath\\testFile.txt")) { os =>
      os.write(bytes)
      os.flush()
    }.get

  }
}

// Disadvantages of using this approach
//Minimal type handling unhandled type
//Two responsibilities in the method getting the bytes and writing the byt


