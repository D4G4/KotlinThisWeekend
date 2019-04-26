package classes_objects

private class Demo {
  //var allByDefault: Int?  //Error: explicit initializer required
  var initialized = 1

  var setterVisibility: String = "abc"
    private set

  var counter = 0
    set(value) {
      print("setter called ")
      if (value >= 0) field = value
    }

  lateinit var subject: String

  fun isSetupInitialized() = ::subject.isInitialized

  fun setup() {
    subject = "wow"
  }

}

fun main() {
  val demo = Demo()
  demo.counter = 20
  println(demo.counter)

  //Use lateinit variable
  if (demo.isSetupInitialized()) {
    println(demo.subject)
  }


}