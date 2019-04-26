@file:Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "UNUSED_VALUE")

package classes_objects

abstract class Animal(val size: Int)

class Dog(val cuteness: Int) : Animal(size = 100)

class Spider(val terrorFactor: Int) : Animal(1)

//Contravariance
interface Compare<T> {
  fun compare(first: T, second: T): Int
}

fun main() {
  val dog = Dog(10)
  val spider: Spider = Spider(terrorFactor = 9000)
  var animal: Animal = dog
  animal = spider

  //Covariant(subType to superType relationship) ->
  val dogList: List<Dog> = listOf(Dog(10), Dog(20))
  val animalList: List<Animal> = dogList

  //Invariance: But for java
  /*
  List<Dog> dogList = new ArrayList<>();
  List<Animal> animalList = dogList; //Compilation error

  This is because,
  generics in Java ignore 'type vs subtype' relation b/w it's components
   */

  //Contravariance
  val dogCompare: Compare<Dog> = object : Compare<Dog> {
    override fun compare(first: Dog, second: Dog)
        = first.cuteness - second.cuteness
  }

  //val animalCompare: Compare<Animal> = dogCompare //Compile Error

  val animalCompare: Compare<Animal> = object: Compare<Animal> {
    override fun compare(first: Animal, second: Animal): Int {
      return first.size - second.size
    }
  }

  //Covariant in java
  /*
  List<Dog> dogs = new ArrayList<>();
  List<? extends Animal> animals = dogs;

  For contravariant, you write
  Compare<Animal> animalCompare = (first, second) -> first.getSize() - second.getSize();
  Compare<? super Spider> spiderCompare = animalCompare;

  'super' makes animal and spider compare contravariant
   */
  //This way of creating type variance at their point of use is called
  //use-site variance
}


//Making convariant in Kollin
//OUT
interface MyList<out E> {
  fun get(index: Int): E
}
/*
The 'out' keyword says that methods in a List can only return E and they cannot
take any 'E' types as an argument
 */

//Making Contravariant in Kotlin
//IN
interface MyCompare<in T> {
  fun compare(first: T, second: T): Int
}

/**
 * In Kotlin, the Developer who writes the class 'declaration' needs to consider
 * the variance, and not the developer who uses the code.
 *
 * That is why it is called 'Declaration-site variance'
 */

/**
 * NUTSHELL:
 *
 * Covariance:
 *  Casting downards will cause problem
 *
 * Contravariance:
 *  Exact opposite, we deal with this during
 */







