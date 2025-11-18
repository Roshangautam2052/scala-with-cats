package typeclass

sealed trait Json
final case class JsObject(get:Map[String, Json]) extends Json
final case class JsString(get:String) extends Json
final case class JsNumber(get:Double) extends Json
case  object JsNull extends Json

// To serialise to JSON behaviour is encoded in this trait
trait JsonWriter[A] {
  def writer(value:A):Json
}