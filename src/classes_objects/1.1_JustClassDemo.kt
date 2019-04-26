@file:Suppress("UNUSED_PARAMETER")

package classes_objects

/**
 * Created by dakshgargas (Apr/2019)
 */

/**
 * A class can have a Primary constructor and
 * 1+ secondary constructors.
 *
 * The Primary constructor is part of the private class header
 */
private class PersonDemo constructor(firstName: String) {

}

//If the primary constructor does not have any annotations
//or visibility modifiers, the `constructor` keyword can be omitted
private class PersonDemoEquivalent(firsName: String) {

}

/**
 * The primary constructor cannot contain any code.
 * Initilization code can be placed in `initializer blocks`
 * which are prefixed with `init` keyword
 */
private class InitOrderDemo(name: String) {
  val firstProperty = "First property: $name".also(::println)

  init {
    println("First initializer block that prints $name")
  }

  val secondProperty = "Second property: ${name.length}".also(::println)

  init {
    println("Second initializer block that prints ${name.length}")
  }
}
//NOTE: Params of constructor can be used inside initializer blocks
//They can also be used in property initializers declaerd in the class body

//Infact, for declaring properties and initializing them from the primary constructor
class Person(val firstName: String, val lastName: String, val age: Int)

//Secondary constructors
//If the private class has a primary constructor, each secondary constructor
//needs to delegate to the primary constructor,
//either directly or indirectly through secondary constructor(s).
//Deligation can be done using `this`
private class Person2(val name: String) {
  constructor(name: String, parent: Person) : this(name)
}

@Suppress("ConvertSecondaryConstructorToPrimary")
private class Constructors {
  init {
    println("Init block")
  }

  constructor(i: Int) {
    println("Constructor")
  }
}

private class DontCreateMe private constructor()

/**
 * If all of the parameters of the primary constructors have default values,
 * the compiler will generate an additional parameterless constructor
 * which will use the default values.
 */
private class Customer2(val customerName: String = "dennis")

fun main() {
  val p = Customer2()
  println(p.customerName)
}










