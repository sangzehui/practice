package scala.szh.leetcode

import scala.szh.algorithm.ListNode

/**
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 * Definition for singly-linked list.
 * class ListNode(_x: Int = 0, _next: ListNode = null) {
 * var next: ListNode = _next
 * var x: Int = _x
 * }
 */
object Solution23 {
  def mergeKLists(lists: Array[ListNode]): ListNode = {
    var done = false
    var first: ListNode = null
    var prev: ListNode = null
    while (!done) {
      var minIndex = -1
      for (i <- 0 until lists.size) {
        if (lists(i) != null) {
          if (minIndex == -1 || lists(i).x < lists(minIndex).x)
            minIndex = i
        }
      }
      if (minIndex == -1) {
        done = true
      } else {
        if (first == null)
          first = lists(minIndex)
        if (prev != null)
          prev.next = lists(minIndex)
        prev = lists(minIndex)
        lists(minIndex) = lists(minIndex).next
      }
    }
    first
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(
      ListNode(List(1, 2, 3)),
      ListNode(List(7, 11)),
      ListNode(List(4)),
      ListNode(List(0)),
      ListNode(List(-1, 5)),
      ListNode(List(6, 9))
    )
    println(mergeKLists(arr))
    println(mergeKLists(Array()))
  }
}
