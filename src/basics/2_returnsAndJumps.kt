@file:Suppress("unused")

package basics

/**
 * Created by dakshgargas (Apr/2019)
 */

/** break and continue labels */
fun breakLabels() {
  outerLoop@ for (i in 1..3) {
    println("outerLoop = $i ")
    innerLoop@ for (j in 1..10) {
      if (j == 4) {
        println()
        break@outerLoop
      }
      print("$j ")
    }
  }
  println("out of fkin loop")
}

fun continueLabel() {
  outerLoop@ for (i in 1..3) {
    println("outerLoop = $i ")
    innerLoop@ for (j in 1..10) {
      if (j == 4) {
        println()
        continue@outerLoop
      }
      print("$j ")
    }
  }
  println("out of fkin loop")
}

/** Return at labels */
fun foo() {
  listOf(1, 2, 3, 4, 5).forEach /*lit@*/{
    if (it == 3) return@forEach
    print(it)
  }
  println(" done with explicit label")
}

//Alternatively we can replace lambda expression with an anonymous function
fun fooEquivalent() {
  listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
    if (value == 3) return //local return to the caller of anonymous fun, i.e. forEach loop
    print(value)
  })
  print(" done with anonymous function")
}

/**
 * The use of local returns in previous examples is similar to
 * the use of `continue` in regular loops.
 * There is NO DIRECT EQUIVALENT of `break`, but it can be
 * stimulated by adding another nesting lambda and non-locally returnin from it
 */
fun fooWithContinueEquivalent() {
  run loopBlock@{
    listOf(1, 2, 3, 4, 5, 6).forEach {
      if (it == 3) return@forEach
      if (it == 5) return@loopBlock
      print(it)
    }
  }
  print(" done with nested loop")
}

fun main() {
}















