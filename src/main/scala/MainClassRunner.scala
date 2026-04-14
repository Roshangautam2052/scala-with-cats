import typeclasses.{FileChannel, FileChannel2, FileChannel3, FullName, IntByteEncoder, StringByteEncoder, StringByteEnconder}

object MainClassRunner extends App{

  // First method
  FileChannel.writer("helloWorld")
  val person = FullName("John", "Doe")
  // Second method
  FileChannel2.writer(person)

  // last method

  FileChannel3.writer[Int](5, IntByteEncoder)
  FileChannel3.writer[String]("Hello Roshan", StringByteEncoder)
}
