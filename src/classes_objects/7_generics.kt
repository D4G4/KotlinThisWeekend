@file:Suppress("UNUSED_VARIABLE")

package classes_objects

import java.util.ArrayList

//region Intro
class Box<T>(t: T) {
  var value = t
}

//To create an instanfce of such a class
//we need to provide the type arguments
val box: Box<Int> = Box(1)

//endregion

/**
 * Generic types in java are 'invariant'
 * i.e. List<String> is not a subtype of List<Object>
 *
 * If List<String> was not invariant, it would have been no better
 * than Java's arrays
 *
 *      List<String> strs = new ArrayList<String();
 *      List<Object> objs = strs;  // Problem
 *      objs.add(1);              // Doesn't make any sense
 *      String s = strs.get(0);  // !!!ClassCastException
 *
 * == == == == == == == == == == == == == == == == == == ==
 *
 * Also,
 * The wildcard type argument '? extends E' makes it covariant
 */

//region Declaration-site variance
//which is in contrast to Java's use-site variance
//(where wildcards in type usage makes the types covariant)

/** Java equivalent code (below)
 _______________________________________________________________
| interface Source<T> {                                         |
|   T nextT();                                                  |
| }                                                             |
| void demo(Source<String> strs) {                              |
|   Source<Object> objects = strs; // !!Not allowed in Java     |
| }                                                             |
|_______________________________________________________________|

To fix this, we have to declare objects of type
._________________________________
|                                 |
|  'Source<? extends Object>'     |
|_________________________________|
which is sort of meaningless,

because we can call all the same methods on such a variable as before,
so there's no value added to it, but compiler does not know that

In Kotlin, there is a way to explain this sort of thing to the compiler.
This is called `Declaration-site variance`
We can annotate the 'type parameter `T`' of Source to make sure
that it is only RETURNED(produced) from members of Source<T>,
and never consumed.
So we use the `out` modifier
 */
interface Source<out T> {
  fun nextT(): T
}

fun demo(strs: Source<String>) {
  val objects: Source<Any> = strs
}
/*
The general ruse is:
When a type parameter 'T' of a class 'C' is declared 'out',
it may occur only in 'out-position' in the members of 'C',
but in return C<Base> can be safely be a supertype of C<Derived>

In "clever words", they say that
'C' is COVARIANT in the paremeter 'T',or that
'T' is covariant type parameter

You can think of 'C' being a PRODUCER of T's, and
NOT a CONSUMER of T's

The 'out' modifer is called a `variance annotation`
 */
//endregion

//region Type Projections
//Some classes can't actually be restricted to only return T's!
//Eg an array
/*
      class Array<T>(val size: Int) {
          fun get(index: Int): T { ... }
          fun set(index: Int, value: T) { ... }
      }
*/
//This class cannot be either co- or contravariant in T
//And this imposes certain inflexibilities
//Consider the following function:
fun copy(fromArray: Array<Any>, to: Array<Any>) {
  assert(fromArray.size == to.size)
  for (i in fromArray.indices)
    to[i] = fromArray[i]
}

fun doCopy() {
  val ints: Array<Int> = arrayOf(1, 2, 3)
  val any = Array<Any>(3) { "" }
//copy(ints, any) //type mismatch
//     ^ type is Array<Int> but Array<Any> was expected
}
/**
 * Here we run into same problem, Array<T> in invariant in T
 * thus neither of Array<Int> and Array<Any> is a subtype of the other.
 *
 * Why? Again, because copy() might be doing bad things,
 * i.e. it might attempt to "write",
 * say, a String to 'formArray',
 * and if we actually passed an array of Int, there's a
 * 'ClassCastException'
 *
 * Then, the only thing we want is to ensure that 'copy()'
 * does not do bad things,
 * we want to prohibit it from WRITING to 'fromArray'
 *
 * And we can
 */
fun copyEquivalent(fromArray: Array<out Any>, to: Array<Any>) {
  assert(fromArray.size == to.size)
  for (i in fromArray.indices)
    to[i] = fromArray[i]
    //fromArray[2] = 1 //!!invalid
}

//We said that 'fromArray' is not simply an array, but a
//restricted(projected) one:
//we can only call those methods
// that RETURN the type parameter T
//in this case, we can only call 'get()'

//endregion


fun main() {

}