package szh.leetcode

import szh.algorithm.TreeNode

import scala.collection.mutable

/**
 * Definition for a binary tree node.
 * class TreeNode(var _value: Int) {
 * var value: Int = _value
 * var left: TreeNode = null
 * var right: TreeNode = null
 * }
 */


object Solution199 {
  def rightSideView(root: TreeNode): List[Int] = {
    val res = mutable.Map[Int, Int]()
    val depths = mutable.Set[Int]()

    def traverse(node: TreeNode, depth: Int): Unit = {
      if (!depths.contains(depth)) {
        res += (depth -> node.value)
        depths += depth
      }
      if (node.right != null) {
        traverse(node.right, depth + 1)
      }
      if (node.left != null) {
        traverse(node.left, depth + 1)
      }
    }

    if (root == null) {
      Nil
    } else {
      traverse(root, 0)
      res.toList.sortBy(_._1).map(_._2)
    }
  }

  def main(args: Array[String]): Unit = {
    val root = TreeNode("[]")
    println(rightSideView(root))
  }
}

