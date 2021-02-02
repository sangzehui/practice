package szh.spark

import scala.util.parsing.combinator.PackratParsers
import scala.util.parsing.combinator.syntactical.StandardTokenParsers

class ParserPractice extends StandardTokenParsers with PackratParsers {
  lexical.delimiters ++= List(".", ";", "+", "-", "*")
  lazy val expr: PackratParser[Int] = {
    add | minus
  }

  lazy val add: PackratParser[Int] =
    num ~ "+" ~ num ^^ {
      case n1 ~ "+" ~ n2 => n1.toInt + n2.toInt
    }

  lazy val minus: PackratParser[Int] =
    num ~ "-" ~ num ^^ {
      case n1 ~ "-" ~ n2 => n1.toInt - n2.toInt
    }

  lazy val num = numericLit

  def parse(input: String) =
    phrase(expr)(new lexical.Scanner(input)) match {
      case Success(r, _) => Some(r)
      case Failure(_, _) => None
    }

}

object Test {
  def main(args: Array[String]): Unit = {
    val p = new ParserPractice
    val prg = "1 + 1" :: "3-2" :: Nil
    prg.foreach(x => println(p.parse(x).get))
  }
}
