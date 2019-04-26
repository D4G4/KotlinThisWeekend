package classes_objects

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator
import kotlin.reflect.KClass

enum class Direction {
  NORTH,
  SOUTH,
  WEST,
  EAST
}

//Since each enum is an instance of the enum class,
//they can be initialized as
enum class Color(val rgb: Int) {
  RED(0xFF000),
  GREEN(0x00FF00),
  BLUE(0x000FF)
}

/**
 * Anonymous Classes
 *
 * Enum constants can also declare their own Anonymous classes
 * Enum entries cannot contain nested types other than inner classes
 */
enum class ProtocolState {
  WAITING {
    override fun signal() = TALKING
  },

  TALKING {
    override fun signal() = WAITING
  };

  abstract fun signal(): ProtocolState
}

val test: ProtocolState = ProtocolState.TALKING.signal()

/**
Implementing Interfaces in Enum Classes
 */
enum class InitArithmetics : BinaryOperator<Int>, IntBinaryOperator {
  PLUS {
    override fun apply(t: Int, u: Int): Int = t + u
  },
  TIMES {
    override fun apply(t: Int, u: Int): Int = t * u
  };

  override fun apply(t: Int, u: Int): Int {
    return 0
  }

  override fun applyAsInt(left: Int, right: Int): Int {
    return apply(left, right)
  }
}

val plus: InitArithmetics = InitArithmetics.PLUS
val p = plus.applyAsInt(1, 2)

val multiply: InitArithmetics = InitArithmetics.TIMES
val m = multiply.apply(2, 2)

/**
 * Working with enum constants
 *
 * It's possible to access the constants in an enum class
 * in a generic way, using
 * `enumValues<T>()` and `enumValueOf<T>()`
 */
enum class RGB {
  RED,
  GREEN,
  BLUE
}


/*
fun <T> myGenericFunction(c: KClass<Enum<T>>) {
  print(enumValues<T>().joinToString { it.name })
}
wtf!
*/


inline fun <reified T : Enum<T>> printAllValues() {
  print(enumValues<T>().joinToString { it.name })
}

/**
 * `reified`??
 * The generic type of the Object
 * must be known at the compile time;
 *
 *
 * In the body of generic function like
 * |-----------------------------------|
 * | fun <T> myGenericFun(c: Class<T>) |
 * |-----------------------------------|
 * you can't access the type 'T' because
 * it's only available at compile time and erased at runtime.
 *
 * Therefore,
 */
 //if you want to use the generic type as a normal class
 //in the function body:
 //you need to explicitly pass the class as a parameter.
 /**
 *
 * But if you create an 'inline' function with a 'reified' T
 * though, the type of 'T' can be accessed even at runtime.
 * Thus you don' need to pass the Class<T> additionally
 * You can work with 'T' as if it was a normal class,
 * e.g you might want to check whether
 * a variable is an instance of 'T'
 * You can easily do then: 'myVar is T'
 */
inline fun <reified T> myGenericFun() {}
/**
 * How reified works?
 *
 * You can use 'reified' in combination with an 'inline' fucntion
 * When you call an inline function with reified type,
 * the compiler knows the actual type used as a type argument
 * and modifies the generated bytecode
 * to use the corresponding class directly.
 */
//NOTE: inlined function with 'reified' type is not callable from java code

fun main() {
  println(p)
  println(m)

  val test = InitArithmetics.valueOf("PLUS")
  println(test.apply(1, 2))

  printAllValues<RGB>()
}
