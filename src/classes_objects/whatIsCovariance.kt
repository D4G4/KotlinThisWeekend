package classes_objects

/**
 * Created by dakshgargas (Apr/2019)
 */
//well, Arrays are Covariant
fun arraysAreCovariant() {
  lateinit var numbers: Array<Number>
  numbers[0] = Integer(10)
  numbers[1] = 3.14
  numbers[2] = 2.toByte()

  val myInts = mutableListOf(1, 2, 3, 5)
  var myNumbers = myInts  //possible
  /*
  Because, according to the subtyping rules in Java,
  an array Integer[] is a subtype of an array Number[]
  because Integer is a subtype of Number.
  */
//But this subtyping rule can lead to an interesting que:
//What would happen if we try to do this
  //myNumbers[0] = 3.14f //attempt of heap pollution

}
