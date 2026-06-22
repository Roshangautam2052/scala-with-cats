import typeclasses.implicits.GenericByteEncoder.GenericByteEncoderStringRotator
import typeclasses.implicits.{GenericByteDecoder, GenericByteEncoder, GenericChannelImpl}
import typeclasses.{ByteEncoder4, ByteEncoderForCaseClass, ByteEncoderForString, CaseClassChannelImpl, FileChannel, FileChannel2, FileChannel3, FileChannel4, FullName, IntByteEncoder, StringByteEncoder, Switch}

object MainClassRunner extends App{

  // First method
  FileChannel.writer("helloWorld")
  val person = FullName("John", "Doe")
  // Second method
  FileChannel2.writer(person)

  // second method
  FileChannel3.writer[Int](5, IntByteEncoder)
  FileChannel3.writer[String]("Hello Mark", StringByteEncoder)

//   Since this implicit encoder is in Direct scope of the runner class the implicit resolution
//   Algorithm it will use this implicit instance rather than the implicit instance in the trait itself

//  implicit object EncodingFiles extends ByteEncoder4[String] {
//    override def encoder(a: String): Array[Byte] =
//      a.getBytes.map(b => (b + 3).toByte)
//  }

  // final method
  FileChannel4.writer("Uthred Bebbenburg")

  // Channel case class method
  private val switch = Switch(true)
  CaseClassChannelImpl.writer(switch)

  //while doing this even if we have EncodingFiles implicit in scope we are not able to use it
  // we can do that by using implicitly
  val result = implicitly[ByteEncoderForString[String]].encoder("100")// This will bring the EncodingFiles implicit at the scope instead of using the default implicit

  ByteEncoderForString[String].encoder("hello")
  println(s"******$result **********")
  // println(s"${ByteEncoderForString.StringEncoder.encoder("Hello").mkString("Array(", ", ", ")")}")

  val randomValue:String =  "123"
  println(randomValue.substring(0, 0))
  println(randomValue.substring(1, 2))
  println(randomValue.substring(1, 3))


  // Running GenericChannel

  private val animal:String = "Lion"

  GenericChannelImpl.write(animal)(GenericByteEncoderStringRotator)

  // Using GenericByteDecoder for reading an Array

  val array:Array[Byte] = Array(78, 105)

  println(GenericByteDecoder[String].decode(array))
}
