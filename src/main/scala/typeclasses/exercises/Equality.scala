package typeclasses.exercises

trait Equality[A] {
  def checkEquality(x: A, y: A):Boolean
}

object Equality {
  // TODO #2: Define the method 'apply' so we can summon instances from implicit scope
  def apply[A](implicit ev:Equality[A]): Equality[A] = ev

  // TODO #3: Define the method 'instance' so we can build instances of the Eq typeclass more easily.
  //  It should take as the only parameter a function of type (A, A) => Boolean
  def instance[A](f: (a:A, b:A) => Boolean):Equality[A] = (x: A, y: A) => f(x, y)
  
  // TODO #4: Define an Equality instance for String
  implicit val stringEq:Equality[String] = instance[String]{(a:String, b:String) => a == b }
  
  // TODO #5: Define an Eq instance for Int
  implicit val intEq:Equality[Int] = instance[Int]{(a, b) => a - b == 0}

  // TODO #6: Define an Eq instance for Person. Two persons are equal if both their names and ids are equal.
  //          Extra points: receive implicit instances for String and Int and use them
  implicit val eqPerson:Equality[Person] = instance[Person]{(a:Person, b:Person) =>
    (a.name == b.name) && (a.id == b.id)
  }
  
}
