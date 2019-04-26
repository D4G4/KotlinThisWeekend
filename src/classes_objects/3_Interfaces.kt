package classes_objects

/**
 * Created by dakshgargas (Apr/2019)
 */

//Interfaces don't have stored properties
interface MyInterface {
  val prop: Int //abstract

  val propertyWithImplementation: String
    get() = "foo"

  fun foo() {
    print(prop)
  }
}

private class Childd : MyInterface {
  override val prop: Int = 29
}