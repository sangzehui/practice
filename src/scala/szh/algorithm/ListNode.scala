package scala.szh.algorithm


class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x

  def traverse(node: ListNode, f: ListNode => Unit): Unit = {
    f(node)
    if (node.next != null)
      traverse(node.next, f)
  }

  def toList: List[Int] = {
    ListNode._toList(this)
  }

  override def toString: String = toList.toString
}

object ListNode {
  def apply(list: List[Int]): ListNode = {
    var first: ListNode = null
    var pre: ListNode = null
    for (v <- list) {
      val node = new ListNode(v)
      if (first == null)
        first = node
      if (pre != null)
        pre.next = node
      pre = node
    }
    first
  }

  private def _toList(node: ListNode): List[Int] = {
    if (node != null)
      node.x :: _toList(node.next)
    else
      Nil
  }

}
