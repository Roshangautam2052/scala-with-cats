package typeclasses.functors

object FunctorRunner extends App {

  val person = Person("Sheetal")

  private val readNameHashed: Secret[Person] = SecretImpl[Person](person)

  private val hashedNameChangedIntoUpper: Secret[String] = readNameHashed.toUpper(new SecretImpl[String](person.name))

  private val admin: Secret[Admin] = readNameHashed.map[Admin](Admin.fromPerson)


  println(s"The name after adminName: ${person.name}, AdminName : ${admin.toString}")

  private val hashedNameChangedIntoLower: Secret[String] = readNameHashed.toLower(new SecretImpl[String](person.name))



  println(s"The name after hashing is OriginalName: ${person.name}, HashedName : ${readNameHashed.toString}")
  println(s"The name after hashing after converting into LowerCase  OriginalName: ${person.name}, HashedName : ${hashedNameChangedIntoUpper.toString}")
  println(s"The name after hashing after converting into UpperCase  OriginalName: ${person.name}, HashedName : ${hashedNameChangedIntoLower.toString}")


}
