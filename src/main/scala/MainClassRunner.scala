import typeclasses.exercises.{Equality, Person}
import cats.*
import cats.implicits.*
import typeclasses.implicits.GenericByteEncoder.GenericByteEncoderStringRotator
import typeclasses.implicits.{GenericByteDecoder, GenericChannelImpl}
import typeclasses.*
import wellKnownTypeClasses.eq.Account
import wellKnownTypeClasses.order.FinanceAccount

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


  // Running GenericChannel

  private val animal:String = "Lion"

  GenericChannelImpl.write(animal)(GenericByteEncoderStringRotator)

  // Using GenericByteDecoder for reading an Array

  val array:Array[Byte] = Array(78, 105)

  println(GenericByteDecoder[String].decode(array))
  val person1 = Person("hello", 1)
  val person2 = Person("hello", 1)

  // Test
  println(Equality[Person].checkEquality(person1, person2))
  println(Equality[Int].checkEquality(11, 12))
  println(Equality[String].checkEquality("11", "11"))

  // Testing Eq for two accounts

  val account1 = Account(
    id = 12L,
    number = "1209787",
    balance = 67997,
    owner = "Sarala Gautam")
  val account2 = Account(
    id = 12L,
    number = "12097870",
    balance = 679976,
    owner = "Pushpa Gautam")

  // Since we are using universal equality these two accounts are not equal as each fields need to be equal
  println(s" Two accounts Equal: ${Eq[Account].eqv(account1, account2)}") // false

  // Let's use some other instances by checking the id of the users
  println(s"Two accounts Equal based on ID: ${Account.Instances.byIdEq2.eqv(account1, account2)}") // true

  // Still we are using universal equality these two accounts are not equal as each fields need to be equal
  println(s"Two accounts Equal based universal equality : ${account1 === account2}") // false

  // what if we want to use === for the implicit instances of which we have created we have to import the implicit instance
  // This simple import will now point the === to use the instance we have defined in the implicit scope
  import Account.Instances.bvIdEq
  println(s"Two accounts Equal based universal equality : ${account1 === account2}") //true


  // Testing Order for two Finance Accounts

  val financeAccount1 = FinanceAccount(
    id = 12L,
    number = "1209787",
    balance = 67997,
    owner = "Sarala Gautam")

  val financeAccount2 = FinanceAccount(
    id = 18L,
    number = "12097870",
    balance = 679976,
    owner = "Aakriti Gautam")


  val sortedById = FinanceAccount.sort(List(financeAccount1, financeAccount2))

  println(s"Two finance Account sorted by ID: $sortedById")



}
