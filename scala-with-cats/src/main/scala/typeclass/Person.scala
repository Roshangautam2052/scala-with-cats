package typeclass

final case class Person(name: String, email: String) {

  object JsonWriterInstances {
    implicit val stringWriter: JsonWriter[String] =
      new JsonWriter[String] {
        override def writer(value: String): Json = JsString(value)
      }
    // These are the concrete instance of TypeClasses
    implicit val personWriter: JsonWriter[Person] =
      (value: Person) => JsObject(Map(
        "name" -> JsString(value.name),
        "email" -> JsString(value.email)
      ))
  }

}
