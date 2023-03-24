package qyndrix
import qyndrix.Lexer

object Main:
  def main(args: Array[String]): Unit =
    println(Lexer("{3 4 5} (+)"))
