@file:Suppress("UNUSED_PARAMETER")

package classes_objects

//region demo
private class CannotInherit(p: Int)

open private class CanInherit(p: Int)

private class Derived(p: Int, q: Int) : CanInherit(p)

private class DerivedWithNoPrimaryConstructor : CanInherit {
  constructor(p: Int) : super(p)

  constructor(p: Int, attrs: String) : super(p)
}
//endregion

open private class Base {
  open fun v() {}
  fun nv() {}

  open val x: Int get() = 21
}

private class Sub : Base() {
  override fun v() {}

  override val x: Int = 45
}

interface Foo {
  val count: Int
}

private class Bar1(override val count: Int) : Foo

private class Bar2 : Foo {
  override val count: Int = 0
}

//Derived private class initialization order
open class Parent(private val name: String) {
  init {
    println("Initializing Super")
  }

  open val size: Int =
      name.length.also { println("Initilizing size in Super: $it") }
}

private class Child(
  name: String,
  private val lastName: String
) : Parent(name.capitalize().also { println("Argument for Super: $it") }) {
  init {
    println("Initilizing child")
  }

  override val size: Int =
      (super.size + lastName.length).also { println("Initilizing size in child private class $it") }
}

open class Foo2 {
  open fun f() {
    println("Foo.f()")
  }

  open val x: Int get() = 1
}

private class BarFoo : Foo2() {
  override fun f() {
    super.f()
    println("BarrFoo.f()")
  }

  override val x: Int
    get() = super.x + 1
}

//Inner Class
private class BarFooInner : Foo2() {
  override fun f() {
    println("daksh")
  }

  override val x: Int
    get() = 0

  private inner class Baz {
    fun g() {
      super@BarFooInner.f()
      f()
      println(super@BarFooInner.x)
    }
  }
}

/**
 * Overriding rules
 * Implementation inheritence is regulated by the following rule:
 * If a private class inherits many implementations of a same member from its immediate superclasses,
 * it must override that member and provide its own implementation.
 * To denote the supertype from which the inherited implementation is taken,
 * we use `super` qualified by the supertype name in angled brackets
 */
private open class A {
  open fun f() { print("A") }
  fun a() { print("a") }
}

interface B {
  fun f(){ print("B") }
  fun b() { print("b") }
}

private class C: A(), B {
  override fun f() {
    super<A>.f()
    super<B>.f()
  }
}

//Abstract classes
private open class AbstractBase {
  open fun f() {}
}

private abstract class AbstractDerived: AbstractBase() {
  abstract override fun f()
}

private class SomeOtherClass: AbstractDerived() {
  override fun f() {
    print("wiw")
  }
}


fun main() {
//  var abstractDerived = object: AbstractDerived() {
//  }

  var someOtherClass = SomeOtherClass()
  someOtherClass.f()
}







