package classes_objects

import java.awt.Window
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

/*
Sometimes we need to create an object of slight modification
of some class, without explicitly declaring new subclass for it.
Java handles this with anonymous inner classes.
Kotlin slightly generalizes this concept with
'object expressions and object declarations'
 */
//To create an object of an anonymous class that inherits
//form some type(or types), we write:
fun explainTheAboveThing(window: Window) {
  window.addMouseListener(object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent?) {
      super.mouseClicked(e)
      print("bla")
    }

    override fun mouseEntered(e: MouseEvent?) {
      super.mouseExited(e)
    }
  })
}

//If a supertype has a constructor, appropriate constructor
//parameters must be passed to it.
open class ClassA(x: Int) {
  open val y: Int = x
}

interface InterfaceB {
  fun implementMe()
}

val ab: ClassA = object : ClassA(x = 1), InterfaceB{
  override val y: Int = 15

  override fun implementMe() = print("implemented")
}

//If by any chance, we need "just an object", with no
//trivial supertypes, we can simply say
fun foo() {
  val adHoc = object {
    var x: Int = 0
    var y: Int = 1
  }
  print(adHoc.x + adHoc.y)  // 1
}















