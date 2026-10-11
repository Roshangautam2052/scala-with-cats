package typeclasses.functors

/**
 * Problem statement:
 * I want to create a case class called person and
 * would like to log the information whenever I am reading a name of the person
 * It would be like logger.info(s"Reading person :$person.name" )
 * But this approach has an issue as we don't want to leak personal information
 * What would be the best approach instead would be to define a
 * class Secret[A] (value: A) {
 * private def hashed: String = ??? --> will use some hashing algorithm (How can this be implemented)
 * override def toString: String = hashed
 */
case class Person(name:String)

case class Admin(name:String) {
}
object Admin {
  def fromPerson(person: Person):Admin = {
    Admin(person.name)
  }
}
