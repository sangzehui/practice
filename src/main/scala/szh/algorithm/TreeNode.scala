package szh.algorithm

import scala.collection.mutable.ListBuffer


class TreeNode(var _value: Int) {
  var value: Int = _value
  var left: TreeNode = null
  var right: TreeNode = null

  def traverseBreadthFirst(fun: TreeNode => Unit): TreeNode = {
    val list = new ListBuffer[TreeNode]()
    list += this
    var index = 1
    var depth = 1
    while (!list.isEmpty) {
      val node = list.remove(0)
      fun(node)
      if (node.left != null) list += node.left
      if (node.right != null) list += node.right
      if (index >= Math.pow(2, depth - 1).toInt) {
        index = 0
        depth += 1
      }
      index += 1
    }
    this
  }

  def clear: TreeNode = traverseBreadthFirst(x => {
    if (x.left != null && x.left.value == -1) x.left = null
    if (x.right != null && x.right.value == -1) x.right = null
  })

}

object TreeNode {
  def build(arr: Array[Int], isClear: Boolean = true): TreeNode = {
    val stack = new ListBuffer[TreeNode]()
    val root = new TreeNode(arr(0))
    stack += root
    var index = 1
    var depth = 1
    for (item <- arr) {
      if (index != 1 || depth != 1) {
        val parent = stack.head
        val node = new TreeNode(item)
        if (index % 2 == 1) {
          parent.left = node
        } else {
          parent.right = node
          stack.remove(0)
        }
        stack.append(node)
      }
      if (index >= Math.pow(2, depth - 1).toInt) {
        index = 0
        depth += 1
      }
      index += 1
    }
    if (isClear) root.clear
    root
  }

  def apply(value: String, isClear: Boolean = true): TreeNode = {
    val arr = value.stripPrefix("[").stripSuffix("]")
      .split(",")
      .filter(!_.isEmpty)
      .map(x => if (x == "null") -1 else x.toInt)
    if (arr.size <= 0) null
    else build(arr, isClear)
  }

  def main(args: Array[String]): Unit = {
    TreeNode("[1,2,3,null,5,null,4]", false)
      .traverseBreadthFirst(node => print(node.value + " "))
  }
}
