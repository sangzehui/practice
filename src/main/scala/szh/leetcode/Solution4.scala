package szh.leetcode

import szh.common.Tools

/**
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 */
object Solution4 {
  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    val len = nums1.size + nums2.size
    var i = 0
    var j = 0
    var r1 = 0
    var r2 = 0
    while (i + j <= len / 2 && len != 0) {
      if (i < nums1.size && j < nums2.size) {
        if (nums1(i) < nums2(j)) {
          r2 = r1
          r1 = nums1(i)
          i += 1
        } else {
          r2 = r1
          r1 = nums2(j)
          j += 1
        }
      } else if (i < nums1.size) {
        r2 = r1
        r1 = nums1(i)
        i += 1
      } else if (j < nums2.size) {
        r2 = r1
        r1 = nums2(j)
        j += 1
      }
    }
    if (len % 2 == 0) {
      (r1 + r2) / 2.0
    } else {
      r1
    }
  }

  def main(args: Array[String]): Unit = {
    println(findMedianSortedArrays(Array(1), Array(2, 3)))
    println(findMedianSortedArrays(Array(1, 2), Array(3)))
    println(findMedianSortedArrays(Array(2), Array(1, 3)))
    println(findMedianSortedArrays(Array(1, 3), Array(2)))

    println(findMedianSortedArrays(Array(1), Array(2, 3, 4)))
    println(findMedianSortedArrays(Array(2, 3, 4), Array(1)))

    println(findMedianSortedArrays(Array(2), Array(1)))
    println(findMedianSortedArrays(Array(1), Array(2)))

    println(findMedianSortedArrays(Array(), Array()))
    println(findMedianSortedArrays(Array(1), Array()))
    println(findMedianSortedArrays(Array(), Array(1)))
  }
}
