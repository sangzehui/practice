package szh.leetcode

import szh.common.MyAssert

/**
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
object Solution10 {

  def isMatch(s: String, p: String): Boolean = {
    val set = collection.mutable.Set[(String, String)]()

    def _isMatch(s: String, p: String): Boolean = {
      set.+=((s, p))
      val sArr = s.toCharArray
      val pArr = p.toCharArray
      var sIndex = 0
      var pIndex = 0
      var isFinal = false
      var isSuccess = false
      while (sIndex < sArr.size && pIndex < pArr.size && !isFinal) {
        lazy val s1 = s.slice(sIndex + 1, s.size)
        lazy val s2 = s.slice(sIndex, s.size)
        lazy val p1 = p.slice(pIndex, p.size)
        lazy val p2 = p.slice(pIndex + 2, p.size)
        pArr(pIndex) match {
          case '.' => {
            if (pIndex + 1 < pArr.size && pArr(pIndex + 1) == '*') {
              val res = {
                !set.contains((s1, p1)) && _isMatch(s1, p1) || !set.contains((s2, p2)) && _isMatch(s2, p2)
              }
              if (res) {
                isSuccess = true
                isFinal = true
              }
              pIndex += 1
            } else {
              sIndex += 1
            }
          }
          case _ => {
            if (pIndex + 1 < pArr.size && pArr(pIndex + 1) == '*') {
              val res = {
                if (sArr(sIndex) == pArr(pIndex)) {
                  !set.contains((s1, p1)) && _isMatch(s1, p1) || !set.contains((s2, p2)) && _isMatch(s2, p2)
                } else {
                  !set.contains((s2, p2)) && _isMatch(s2, p2)
                }
              }
              if (res) {
                isSuccess = true
                isFinal = true
              }
              pIndex += 1
            } else {
              if (pArr(pIndex) == sArr(sIndex)) {
                sIndex += 1
              } else {
                isSuccess = false
                isFinal = true
              }
            }
          }
        }
        pIndex += 1
      }
      while (pIndex + 1 < pArr.size && pArr(pIndex + 1) == '*') {
        pIndex += 2
      }
      isSuccess || (sIndex == sArr.size && pIndex == pArr.size)
    }

    _isMatch(s, p)
  }

  def test01(): Unit = {
    MyAssert.isTrue(isMatch("a", "a*"))
    MyAssert.isTrue(isMatch("aa", "a*"))
    MyAssert.isTrue(isMatch("ab", "ab"))
    MyAssert.isTrue(isMatch("ab", "a*b"))
    MyAssert.isTrue(isMatch("aab", "a*b"))
    MyAssert.isTrue(isMatch("b", "a*b"))

    MyAssert.isFalse(isMatch("ab", "aa"))
    MyAssert.isFalse(isMatch("abb", "a*b"))
    MyAssert.isFalse(isMatch("abab", "ab*"))
  }

  def test02(): Unit = {
    MyAssert.isTrue(isMatch("a", "."))
    MyAssert.isTrue(isMatch("ab", ".b"))
    MyAssert.isTrue(isMatch("ab", "a."))
    MyAssert.isTrue(isMatch("abc", "a.c"))

    MyAssert.isFalse(isMatch("ac", "a.c"))
    MyAssert.isFalse(isMatch("abc", "abc."))
    MyAssert.isFalse(isMatch("abc", ".abc"))
  }

  def test03(): Unit = {
    MyAssert.isTrue(isMatch("ab", "a.*b"))
    MyAssert.isTrue(isMatch("ab", ".*b"))
    MyAssert.isTrue(isMatch("ab", "a.*"))
    MyAssert.isTrue(isMatch("abc", ".*"))
    MyAssert.isTrue(isMatch("ababababab", "a.*b"))

    MyAssert.isFalse(isMatch("ab", "a.*c"))
    MyAssert.isFalse(isMatch("ab", ".*a"))
    MyAssert.isFalse(isMatch("ab", "b.*"))
  }

  def test04(): Unit = {
    MyAssert.isFalse(isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*c"))
  }

  def main(args: Array[String]): Unit = {
    //        test01()
    //        test02()
    //        test03()
    test04()
  }
}
