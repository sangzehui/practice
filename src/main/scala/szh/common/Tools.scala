package szh.common

import scala.reflect.{ClassTag, _}

object Tools {
  def print2D[T](t: Array[Array[T]]): Unit = {
    println(
      t.map {
        row => row.map(_.toString).reduce(_ + "," + _)
      }.reduce(_ + "\n" + _)
    )
  }

  def print2D[T](t: Traversable[Traversable[T]]): Unit = {
    println(
      t.map {
        row => row.map(_.toString).reduce(_ + "," + _)
      }.reduce(_ + "\n" + _)
    )
  }

  def convert[T](s: String)(implicit tag: ClassTag[T]): T = tag match {
    case ClassTag.Int => s.toInt.asInstanceOf[T]
    case ClassTag.Double => s.toDouble.asInstanceOf[T]
    case _ => s.asInstanceOf[T]
  }

  def get2DList[T](str: String)(implicit tag: ClassTag[T]): List[List[T]] = {
    val f: ClassTag[String] = classTag[String]
    str.replaceFirst("^\\[\\[(.*)\\]\\]$", "$1")
      .split("\\],\\[")
      .map {
        x => x.split(",").map(y => convert(y)).toList
      }.toList
  }

  def get2DArray[T](str: String)(implicit tag: ClassTag[T]): Array[Array[T]] = {
    str
      .replaceAll("\\s", "")
      .replaceFirst("^\\[\\[(.*)\\]\\]$", "$1")
      .split("\\],\\[")
      .map {
        x => x.split(",").map(y => convert(y)).toArray[T]
      }
  }

  def getTime: String = {
    java.time.format.DateTimeFormatter.ofPattern("mm:ss SSS").format(java.time.LocalDateTime.now)
  }

  def main(args: Array[String]): Unit = {
    println(get2DList("[[12,2],[3,4]]"))
    println(get2DList("[[*,-],[1,*]]"))
    println(get2DList[String]("[[**,2],[3,4]]"))
  }
}
