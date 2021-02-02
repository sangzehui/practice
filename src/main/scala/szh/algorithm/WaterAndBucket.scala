package szh.algorithm

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class Stage(b1: Bucket, b2: Bucket) {
  var parent: Stage = null
  var operate: String = _

  def transferList(): List[Stage] = {
    val list = List(
      (Stage(b1.fill, b2), "b1 fill"),
      (Stage(b1, b2.fill), "b2 fill"),
      (Stage(b1.clear, b2), "b1 clear"),
      (Stage(b1, b2.clear), "b2 clear"),
      (b1.transfer(b2), "b1 to b2"),
      (b2.transfer(b1, true), "b2 to b1")
    )
    list.foreach(x => {
      x._1.parent = this
      x._1.operate = x._2
    })
    list.map(_._1)
  }

  def log(): List[String] = {
    def _log(stage: Stage): ListBuffer[String] = {
      if (stage.parent != null) _log(stage.parent) += stage.toString
      else new ListBuffer[String] += stage.toString
    }

    _log(this).toList
  }

  override def toString: String = s"${operate} => b1: ${b1.current} b2: ${b2.current}"

  override def equals(obj: Any): Boolean = {
    val obj2 = obj.asInstanceOf[Stage]
    this.b1.current == obj2.b1.current && this.b2.current == obj2.b2.current
  }
}


case class Bucket(capacity: Int, var current: Int = 0) {
  def fill: Bucket = {
    Bucket(this.capacity, this.capacity)
  }

  def transfer(to: Bucket, isReverse: Boolean = false): Stage = {
    val maxTrans = to.capacity - to.current
    val trans = maxTrans min this.current
    if (isReverse)
      Stage(Bucket(to.capacity, to.current + trans), Bucket(this.capacity, this.current - trans))
    else
      Stage(Bucket(this.capacity, this.current - trans), Bucket(to.capacity, to.current + trans))
  }

  def clear: Bucket = {
    Bucket(this.capacity, 0)
  }

  override def toString: String = {
    s"capacity:${capacity} current: ${current}"
  }
}

object WaterAndBucket {
  def solution(bucket1: Int, bucket2: Int, target: Int): List[String] = {
    var res: List[String] = Nil
    val visited = mutable.Set[Stage]()
    val queue = new ListBuffer[Stage]()
    queue.append(Stage(Bucket(bucket1), Bucket(bucket2)))

    def check(stage: Stage): Boolean = {
      if (!visited.contains(stage)) {
        visited += stage
        true
      } else {
        false
      }
    }

    check(queue.head)
    while (!queue.isEmpty) {
      val head = queue.remove(0)
      if (head.b1.current == target || head.b2.current == target) {
        res = head.log
        queue.clear()
      } else {
        queue ++= head.transferList().filter(x => check(x))
      }
    }
    res
  }

  def main(args: Array[String]): Unit = {
    solution(4, 9, 6).foreach(println)
  }
}
