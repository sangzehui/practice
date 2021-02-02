package szh.algorithm

import szh.common.Tools
import szh.leetcode.Solution37.solveSudoku

import scala.collection.mutable


class Sudoku(var board: Array[Array[Char]]) {
  val row = Array.fill(9)(mutable.Set[Char]())
  val col = Array.fill(9)(mutable.Set[Char]())
  val area = Array.fill(9)(mutable.Set[Char]())

  for (i <- 0 until board.size) {
    for (j <- 0 until board.size) {
      if (board(i)(j) != '.') {
        row(i) += board(i)(j)
        col(j) += board(i)(j)
        area(getArea(i, j)) += board(i)(j)
      }
    }
  }

  def doChange(i: Int, j: Int, ch: Char): Boolean = {
    if (row(i).contains(ch) || col(j).contains(ch) || area(getArea(i, j)).contains(ch)) {
      false
    } else {
      board(i)(j) = ch
      row(i) += ch
      col(j) += ch
      area(getArea(i, j)) += ch
      true
    }
  }

  def undoChange(i: Int, j: Int, ch: Char): Sudoku = {
    board(i)(j) = '.'
    row(i).remove(ch)
    col(j).remove(ch)
    area(getArea(i, j)).remove(ch)
    this
  }


  def getArea(i: Int, j: Int): Int = {
    i / 3 * 3 + j / 3 % 3
  }

  def solution2: Sudoku = {
    var done = false
    while (!done) {

    }
    this
  }

  def solution: Sudoku = {
    var done = false // 是否解决问题
    var funInvokeCount = 0

    def dfs(sudoku: Sudoku): Boolean = {
      funInvokeCount += 1
      val board = sudoku.board
      var isContinue = true // 如果一个空位没有解，放弃后续操作
      for (i <- 0 until board.size if isContinue && !done) {
        for (j <- 0 until board.size if isContinue && !done) {
          if (board(i)(j) == '.') {
            var solvable = false // 这个空位是否有解
            for (k <- 1 to board.size if !done) {
              if (sudoku.doChange(i, j, (k + 48).toChar)) {
                solvable = solvable || dfs(sudoku)
                if (!done)
                  sudoku.undoChange(i, j, (k + 48).toChar)
              }
            }
            if (!solvable)
              isContinue = false
          }
        }
      }
      // 如果所有空位都有解，且目前没有解
      if (isContinue && !done) {
        done = true
        true
      } else {
        false
      }
    }

    dfs(this)
    this
  }

}
object Sudoku{
  def main(args: Array[String]): Unit = {
    val str = """[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]"""
    val arr = Tools.get2DArray[String](str).map(_.map(_.charAt(1)))
    new Sudoku(arr).solution
    println(arr.map(_.map(_.toString).reduce(_ + "," + _)).reduce(_ + "\n" + _))
  }
}

