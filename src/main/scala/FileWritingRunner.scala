object FileWritingRunner extends App{
  FileChannelEfficient.writer[String]("Hello Roshan", StringByteEncoder)
}
