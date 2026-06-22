package typeclasses.implicits

import java.io.FileOutputStream
import scala.util.Using

trait GenericChannel {
  def write[A](obj: A)(implicit enc: GenericByteEncoder[A]):Unit
}


object GenericChannelImpl extends GenericChannel {
  override def write[A](obj: A)(implicit enc: GenericByteEncoder[A]): Unit = {
    val bytes = enc.encode(obj)
    val basePath = System.getProperty("user.dir")
    Using(new FileOutputStream(s"$basePath//testFile.txt")) { obj =>
      obj.write(bytes)
      obj.flush()
    }.get
  }
}