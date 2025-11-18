/***
 * Suppose if we want to build and app and want to save different kinds of data(Int, String, Person, etc)
 * as Json
 * Then we need to create  three different methods to implement the save method(which is messy)
 * If we create a generic method such as def toJson(s:A):String then we don't know how to implement a method
 * for each of the types as the types will be different based on the input
 * In this case we can use types-classes to achieve adhoc polymorphism
 */

trait JsonWriter[A] {
  def writer(value:A):String
}

implicit val intWriter:JsonWriter[Int] = (x:Int) => s"""{"int": $x"}"""
implicit val stringWriter:JsonWriter[String] = (x:String) => s"""{"string": $x"}"""

case class Person(name:String, age:Int)
implicit val personWriter:JsonWriter[Person] =
  (p:Person) => s"""{"name": ${p.name}", "age": ${p.age}"""
  
def toJson[A](value: A)(implicit writer: JsonWriter[A]):String =
  writer.writer(value)
  
  
println(toJson(42))
println(toJson("hello"))
println(toJson(Person("Alice", 30)))