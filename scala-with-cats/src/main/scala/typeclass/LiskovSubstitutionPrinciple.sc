/**
 *  Liskov substitution principle states that if `s` is a subType of `T`
 *  Then `s` should be able to replace `T` whenever required
 */

abstract class Bird {
  def canFly:String
}

class Sparrow extends Bird{

  override def canFly: String = "I can fly"
}

val sparrow = new Sparrow
println(sparrow.canFly)

/*
 This violates the LSP as we get exception
  when we try to call the method can fly from ostrich
  object. This is not expected as all Birds are meant to fly
 */
class Ostrich extends Bird {

  override def canFly: String = {
    throw new Exception("Sorry I cannot fly")
  }
}

val ostrich: Bird = new Ostrich

println(ostrich.canFly)

/*
 This can be solved by using this approach
 🧩 Why the Fix Still Helps

Design makes intent clear

By having two separate traits (Bird and FlyingBird), we signal to other developers:

“Only certain birds should extend FlyingBird.”

Now, if someone forces an Ostrich into FlyingBird, that’s clearly a design misuse, not the framework’s fault.

Compiler gives stronger guidance

If you only need all birds, you depend on Bird.

If you specifically need flying birds, you depend on FlyingBird.

This avoids unnecessary fly() methods being forced onto every bird subtype.

LSP violation becomes obvious at design time

In the first version (where Bird had fly()), the mistake was silent—you could easily forget Ostrich can’t fly.

In the fixed version, you have to deliberately misuse the abstraction by making Ostrich extend FlyingBird. That’s a much clearer red flag
 */

trait Bird1

trait FlyingBird extends Bird1 {
  def canFly : String
}

class Piegon extends FlyingBird {
  override def canFly: String = "I can fly"
}

class AfricanOstrich extends Bird1 {
  def canRun:String = "I can run"
}

val piegon = new Piegon
println(piegon.canFly)

val africanOstrich = new AfricanOstrich

println(africanOstrich.canRun)