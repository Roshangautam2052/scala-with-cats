package typeclasses

import java.io.FileOutputStream
import scala.util.Using

trait ByteEncoderForCaseClass[A] {
  def encode(obj:A): Array[Byte]
}

object ByteEncoderForCaseClass {
  implicit object StringByteEncoder extends ByteEncoder4[String]{
    override def encoder(a: String): Array[Byte] = a.getBytes
  }
}

trait CaseClassChannel{
  def writer[A](obj: A)(implicit enc:ByteEncoderForCaseClass[A]): Unit
}


case class Switch(on:Boolean)

object Switch {
  implicit object SwitchByteEncoder extends ByteEncoderForCaseClass[Switch] {
    override def encode(obj: Switch): Array[Byte] = {
      obj.on
        .toString.getBytes
    }
  }
}


object SwitchByteEncoder extends ByteEncoderForCaseClass[Switch]{

  override def encode(obj: Switch): Array[Byte] = {
    obj.on
      .toString.getBytes
  }
}

object CaseClassChannelImpl extends CaseClassChannel {

  override def writer[A](obj: A)(implicit enc: ByteEncoderForCaseClass[A]): Unit = {
    val bytes = enc.encode(obj)
    val basePath = System.getProperty("user.dir")
    Using(new FileOutputStream(s"$basePath//testFile.txt")) { obj =>
      obj.write(bytes)
      obj.flush()
    }.get
  }
}


