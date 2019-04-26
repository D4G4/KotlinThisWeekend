@file:Suppress("UNUSED_VARIABLE")

package basics

/**
 * Created by dakshgargas (Apr/2019)
 */

//region Defining Functions
fun sum(a: Int, b: Int): Int {
  return a + b
}

//Function with an expression body and inferred return type
fun sum1(a: Int, b: Int) = a + b

//Function returning no meaningful value i.e. `Unit`
//`Unit` return type can be ommited
fun printSum(a: Int, b: Int): Unit /*Optional*/ {
  println("basics.sum of $a and $b = ${a + b}")
}
//endregion

//region Using nullable values and checking for null

fun returnSomeNullValue(shouldReturnNull: Boolean): Int? {
  if (shouldReturnNull) {
    return null
  }
  return 22
}

fun showMeHowToHandleNullValues() {
  val nullableX = returnSomeNullValue(true)
  val nullableY = returnSomeNullValue(false)

  // Using `nullableX * nullableY` yields error because they may hold nulls
  //println(nullableX * nullableY) //Error
  if (nullableX != null && nullableY != null) {
    println(nullableX * nullableY)
  } else {
    println("either `$nullableX` or `$nullableY` is null")
  }
}
//endregion

//region Using type checks and automatic casts
fun getStringLength(obj: Any): Int? {
  if (obj is String) {
    // `obj` is automatically case to `String` in this branch
    return obj.length
  }

  //Kotlin class reference is not same as Java class reference
  println(obj.javaClass.simpleName)
  println(obj::class.java.simpleName)
  println(obj::class.simpleName)
  println("Obj is of type: ${obj::class::qualifiedName}")
  println("Obj is of type: ${obj::class.qualifiedName}")
  return null
}
//endregion

//region Function References

//When we have a named function declared like this
fun isOdd(x: Int) = x % 2 != 0

//We can easily call it directly, but we can use it as
//a function type value, e.g. pass it to another funciton
//to use this, we use `::` operator
fun functionReferences() {
  val numbers = listOf(1, 2, 3)
  println(numbers.filter(::isOdd))
  println(numbers.filter(fun(value: Int): Boolean {
    return value % 2 != 0
  }))
  println(numbers.filter { integerValue -> isOdd(integerValue) })
}

/*
Here, `::basics.isOdd` is a value of function type (Int) -> Boolean
 */

/*
`::` can wqe used with overloaded functions when the expected type
is known from the context
For example:
 */
fun isEven(x: Int) = x % 2 == 0

fun isEven(s: String) = s == "daksh" || s == "dennis"

fun functionReferences2() {
  val numbers = listOf(1, 2, 3)
  println(numbers.filter(::isEven)) //refers to basics.isEven(x: Int)

  //Alternatives, you can provide the necessary context by storing
  //the method reference in a variable with an explicitely specified type.
  val predicate: (String) -> Boolean = ::isEven

  //If we need to use a member of a class, or an extension function,
  //it needs to be qualified
  //e.d String::toCharArray

  /*NOTE:
  Even if you initialize a variable with a referene to an
  EXTENSION FUNCTION, the inferred function type will have no
  receiver(it will have an additional parameter accepting a receiver object).
  To have a function type with receiver instead, specify the type explicitely
  */
  val isEmptyStringList: List<String>.() -> Boolean = List<String>::isEmpty
}
//endregion

//region Loops
fun useLoop() {
  val items = listOf("apple", "banana", "kiwifruit")

  val items2 = Array(5, fun(index: Int): String {
    return "val$index"
  })

  val items3 = Array(5) { index -> "val$index" }

  for (item in items) {
    println(item)
  }

  for (index in items.indices) {
    println("item at $index is ${items[index]}")
  }
}

@Suppress("IMPLICIT_CAST_TO_ANY") fun useWhileLoop() {
  val items = Array(7) { index ->
    print("test")
    when (index) {
      0, 1 -> "One"
      2 -> "Two"
      3 -> 3
      in 4..6 -> "Lies between 4 and 6"
      else -> "Last?"
    }
    //return@Array "test $index"
  }
  println()

  var index = 0
  while (index < items.size) {
    println("item at $index is ${items[index]}")
    index++
  }
}
//endregion

//region Range
fun checkNumberIsWithinRange() {
  val x = 10
  val y = 9

  if (x in 1..y + 1) {
    println("fits in range")
  }
}

fun checkNumberIsOutOfRange() {
  val list = listOf("a", "b", "c")

  if (-1 !in 0..list.lastIndex) {
    println("-1 is out of range")
  }
  val listIndicesRange = 0..list.lastIndex

  if (list.indices == listIndicesRange) {
    println("yes")
  }

  if (list.size !in list.indices) {
    println("basics.getList size is out of valid basics.getList indices")
  }
}

fun iteratingOverProgression() {
  for (x in 1..10 step 2) {
    print("$x ")
  }
  println()

  for (x in 9 downTo 0 step 3) {
    print("$x ")
  }
}
//endregion

//region Using Collections
fun usingCollections() {
  val items = listOf(1, 2, 3)
  when /*(true)*/ {
    1 !in items -> println("damm")
    2 in items -> println("ok")
  }

  val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
  fruits
      .filter { it.startsWith("a") }
      .sortedBy { it }
      .map(String::toUpperCase)
      .forEach(::println)
}
//endregion

fun main() {
  iteratingOverProgression()
}











