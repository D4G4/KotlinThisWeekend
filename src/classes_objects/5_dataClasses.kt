package classes_objects

/**
 * - Primary constructor needs to have at least one param
 * - All primary constructor parameters need to be marked as `val` or `var`
 * - Cannot be abstract, open, sealed or inner
 */
data class User(val name: String, val age: Int)

//If generated class needs to have a parameterless constructor
//default values for all properties have to be specified
data class UserEquivalent(val name: String = "", val age: Int = 0)

/** Properties declared in the Class body */
/*
The compiler only uses the properties defined inside the
primary constructor for the AUTOMATICALLY GENERATED FUNCTIONS.
To exclude a property from the generated implementations,
declare it inside the class body!
 */
data class Insaan(val name: String) {
  var age: Int = 0

  fun myCopy(name: String = this.name, age: Int = this.age): Insaan {
    val insaan = Insaan(name)
    insaan.age = age
    return insaan
  }
}
/*
Only the property 'name' will be used inside
'toString()', 'equals()', 'hashCode()', and 'copy()'
 */

fun main() {
  val person1 = Insaan("Daksh")
  val person2 = Insaan("Daksh")
  person1.age = 10
  person2.age = 20

  val olderPerson1 = person1.myCopy(age = 5)

  println(person1 == person2)
  println(olderPerson1.name)
  println(olderPerson1.age)
}
