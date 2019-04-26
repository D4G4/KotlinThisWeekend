@file:Suppress("UNUSED_VARIABLE")

package basics

import java.io.File
import java.lang.IllegalArgumentException

/**
 * Created by dakshgargas (Apr/2019)
 */

//region Creating DTOs(POJOs/POCOs)

data class Customer(val name: String, var email: String)
/*
Provides a basics.Customer class with the following functionality:
- getters (setters in case of `var`)
- equals()
- hashCode()
- toString()
- copy()
- component1(), component2(),...,for all properties
 */

//endregion

/** Default properties for function params */
fun foo(a: Int = 0, b: String = "") {}

/** Filtering a basics.getList  */
val positives = listOf(1, -2, 3).filter { x -> x > 0 }
//or alternatively even shorter
val positives_equivalent = listOf(1, -2, 3).filter { it > 0 }

/** Read-only basics.getList and map */
val list = listOf(1, 2, 3)

fun accessingMap() {
  val immutableMap = mapOf("a" to 1, "b" to 3, "c" to 2)
  val mutableMap = mutableMapOf("a" to 1, "b" to 3, "c" to 2)
  println(mutableMap["a"])
  mutableMap["a"] = 4
  println(mutableMap["a"])
}

/** Lazy property */
val p: String by lazy {
  "dennis"
}

/** Extension functions */
fun String.spaceToUnderScore(): String {
  return this.replace(" ", "_")
}
fun String.spaceToCamelCase(): String = this.replace(" ", "_")

/** Creating a singleton */
object Resource {
  const val name = "Name"
}


/** If not null and else shorthand */
fun ifNotNullShorthand() {
  val files = File("Test").listFiles();

  println(files?.size ?: "empty")
}

fun getFirstItemOfPossiblyEmptyCollection() {
  val emails = emptyList<Int>()
  val mainEmail = emails.firstOrNull() ?: "error"

  println(mainEmail)
}

fun executeIfNotNull(value: String?) {
  value?.let {
    println("Executing a block if not null")
  }
  println("Oh well!")
}

/** Return on `when` statement */
fun transform(color: String): Int {
  return when(color.toLowerCase()) {
    "red" -> 0
    "green" -> 1
    "blue" -> 2
    else -> throw IllegalArgumentException("Bsdk color to daal")
  }
}

/** Builder-style usage of methods that return Unit */
fun arrayOfMinusOnes(size: Int): IntArray {
  return IntArray(size).apply { fill(-1) }
}

fun main() {
  println(Int.MIN_VALUE)
}







