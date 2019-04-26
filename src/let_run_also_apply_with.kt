@file:Suppress("NAME_SHADOWING")

/**
 * `let`
 * takes the object it is invoked upon as the parameter
 * and return the result of the lambda expression
 */
fun letTM() {
  val str = "Wssup!"
  str.let {
    print("pussy ")
    println("Yo $it")
  }
  str.let(::println)

  val strLength = str.let(String::length)
  println(strLength)

  val p = 1
  val d3: Int = p.let(Int::inc)
  println(d3)

  /**
   * Chaining let functions */
  var a = 1
  val b = 2

  a = a
      .let aKLaLet@{
        print("inside a's let")
        it + 2
      }
      .let {
        val i = it + b; return@let i
      }
  println("a = $a")

  /**
   * Nesting `let`
   *
   * Don't use `it` keywoard.
   */
  val x = "Daksh"
  val dennis: String = x.let outerLet@{ outer ->
    outer
        .let { inner ->
          println("Inner is $inner and outer is $outer")
          return@let "ok"
        }
    return@outerLet "wow"
  }
  println(dennis)

  /**
   * `let` for null checks
   *
   * This saves us from `if else` null checker too
   */
  var name: String? = "Kotlin let null check"
  name?.let {
    println("dennis")
    //...
    println(it)
  }
  name = null
  name?.let(::println) //nothing happens
}

/**
 * `run` expression can change the outer property.
 */
fun runTM() {
  var tutorial = "This is run ka tutorial"
  println(tutorial)
  tutorial = run {
    tutorial = "dennis"
    val tutorial = "This is a run function"
    tutorial
  }
  println(tutorial)
}

/** RUN vs LET
 * `run` function doesn't support `it` keyword
 */

fun letAndrun() {
  var p: String? = null
  p?.let { println("p is $p") } ?: run {
    println("p was null. Setting default value to: ")
    p = "Kotlin"
  }
  println(p)
}

/**
 * `also` expressions does some additional processing
 * on the object it was invoked
 * Unlike `let`, it returns the original object
 * instead of any new return data.
 *
 * Hence, the return data has always the same type
 */
fun alsoTM() {
  var m = 1
  println("value is $m")

  m = m.also { it + 1 }.also { it + 1 }
  println("value is $m")

  m = m.let { it + 1 }
  println("value is $m")
}

data class Person(var name: String, var tutorial: String)
//region let VS also

fun letVSalso() {
  val person = Person("Daksh", "Kotlin")

  val l = person.let { it.tutorial = "Android" }
  val al = person.also { it.tutorial = "Android" }

  println(l)
  println(al)
  println(person)
  /**
   * The `also` exp returns the data class object
   * whereas the `let` exp returns nothing (Unit)
   * as we didn't specify anything explicitly
   */
}

//endregion

/** apply
 * an extension function on a type.
 * It runs on the object reference into the expression and
 * returns the object reference on completion
 */
fun applyTM() {
  val person = Person("Daksh", "Kotlin")
  person.apply { this.tutorial = "Swift" }
  println(person)
}

/**
 * in `apply` `it` is not allowed
 * If property name in data class is unique,
 * you can ommit `this`
 */
fun applyVSalso() {
  val person = Person("Daksh", "Kotlin")
  person.apply { tutorial = "Swift" }
  println(person)

  person.also { it.tutorial = "Aniroid" }
  println(person)
}

/**
 * `with`
 * Like `apply`, `with` is used to change the instance
 * properties without need to call `dot` operator over the reference everytime
 */
fun withTM() {
  val person = Person("Daksh", "Kotlin")
  with(person) {
    name = "No name"
    tutorial = "Kotlin tutorials"
  }
}

/**
 * `with` runs without an object(receiver)
 * `apply` runs on the object reference, whereas `with` just passes it as argument
 * the last exp of the `with` function returns a result
 */
fun withVSapply() {
  val person = Person("Daksh", "Kotlin")
  val xyz = with(person) {
    val tutorial = "Some tutorial"
    name = "No Name"
    this.tutorial = "Kotlin tutorials"
    tutorial
  }
  println(person)
  println(xyz)
}

fun main() {
  letVSalso()
}
