package classes_objects

//region Extension Functions
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
  if (index2 <= this.lastIndex) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
  } else {
    println("Index out of range")
  }
}

fun useExtension() {
  val l = mutableListOf(1, 2, 3)
  l.swap(0, 3)
}

fun Any?.myToString(): String {
  if (this == null) return "null"
  // after the null check, 'this' is autocast to a non-null type,
  // so the toString() below resolves to the member function of Any private class
  return toString()
}
//endregion

// region Extension Properties
//Initializers are not allowed for extension properties
//Since extensions do not actually insert members into classes,
//there's no efficient way for an extension property to have a 'backing field'
val <T> List<T>.aakhriIndex: Int
  get() = size - 1

//endregion

//region Companion Object Extensions
class MyClass {
  companion object Factory {
    fun create(): MyClass = MyClass()
  }

  fun foo() {
    println("Hey!! :]")
  }
}

fun MyClass.Factory.daksh() {
  println("Daksh")
}

class MyClassEquivalent {
  companion object {
    fun create(): MyClassEquivalent = MyClassEquivalent()
  }

  fun foo() {
    println("Bund marao ji!")
  }

  private constructor()
}

fun MyClassEquivalent.Companion.daksh() {
  println("Daksh")
}

//endregion

//region Declaring extensions as Members and name conflicts
private class Daksh {
  fun bar() {
    println("bar(): inside Daksh")
  }

  override fun toString(): String {
    return "Daksh"
  }
}

private class Caksh {
  fun baz() {
    println("baz(): inside Caksh")
  }

  //extension func
  private fun Daksh.foo() {
    bar()
    baz()
  }

  private fun Daksh.customToString(): String {
    var str: String = toString()
    str = str + " " + this@Caksh.toString()
    return str
  }

  override fun toString(): String {
    return "Caskh"
  }

  fun caller(d: Daksh) {
    //d.foo()
    print(d.customToString())
  }
}
//endregion

//region Extensions are resolved STATICALLY
//extensions functions being called is determined by the type
//of the expression on which the function is Invoked
//NOT by the type of the result of evaluating that expression at runtime
open class FirstClass

class SecondClass : FirstClass()

fun FirstClass.foo() = "firstClass"

fun SecondClass.foo() = "secondClass"

fun printFoo(firstClassObj: FirstClass) {
  println(firstClassObj.foo())
}
//endregion

fun main() {
  printFoo(FirstClass())
  printFoo(SecondClass())
}
