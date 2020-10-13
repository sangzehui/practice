package scala.szh.common

object MyAssert {
  def equals[A](a: A, b: A): Boolean = {
    val r = a.equals(b)
    if (r) {
      println("test is success")
    } else {
      println("test is fail")
      println("left: " + a)
      println("right: " + b)
    }
    r
  }

  def isTrue(a: Boolean): Boolean = {
    equals(a, true)
  }

  def isFalse(a: Boolean): Boolean = {
    equals(a, false)
  }
}
