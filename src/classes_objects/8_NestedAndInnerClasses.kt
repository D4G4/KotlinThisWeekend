@file:Suppress("UNUSED_VARIABLE")

package classes_objects

import java.awt.Window
import java.awt.event.ActionListener
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

/**
 * Nested class
 */
class Outer {
  private val bar: Int = 1

  class Nested {
    fun foo() = 2
  }
}
val demo = Outer.Nested().foo() // == 2

/**
 * Inner class
 * can access members of outer class.
 * Carry a reference to an object of an outer class:
 */
class OuterAgain {
  private val bar: Int = 1

  inner class Inner {
    fun foo() = bar
  }
}
val demoAgain = OuterAgain().Inner().foo() // ==1

/**
 * Anonymous inner classes
 *
 * Instances are created using object expression
 */
fun showAnonymousInnerclass() {
  lateinit var window: Window
  window.addMouseListener(object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {}

    override fun mouseEntered(e: MouseEvent) {}
  })

  /*
  If the object is an instance of a functional Java Interface
  (i.e. a Java interface with a single abstract method),
  you can create it using a LAMBDA EXPRESSION prefixxed
  with the type of the interface
   */
  val listener = ActionListener { println("clicked") }
}
