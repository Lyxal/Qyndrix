package qyndrix
import java.util.regex.Pattern
import scala.util.matching.Regex
import scala.util.parsing.combinator.*
import Token.*

enum Token(val value: Any):
  case SingleUnit(override val value: String) extends Token(value)
  case Number(override val value: String) extends Token(value)
  case StringLiteral(override val value: String) extends Token(value)

object Lexer extends RegexParsers:
  override def skipWhitespace = true
  override val whiteSpace: Regex = "[ \t\r\f]+".r

  def decimalRegex = raw"((0|[1-9][0-9_]*)?\.[0-9]*|0|[1-9][0-9_]*)"
  def number[Token] = decimalRegex.r ^^ { case x => Number(x) }
  def stringLiteral[Token] = raw"""(\(.*\))""".r ^^ { case x =>
    StringLiteral(x)
  }
  def horizontalUnit = raw"(\{.*\})".r ^^ { case x => SingleUnit(x) }
  def other = raw"""[^\s]+""".r ^^ { case x => SingleUnit(x) }

  def tokens: Parser[List[Token]] = phrase(
    rep(horizontalUnit | number | stringLiteral | other)
  )

  def apply(input: String): List[Token] =
    (parse(tokens, input): @unchecked) match
      case Success(result, _) => result
      case NoSuccess(msg, _)  => throw new Exception(msg)
end Lexer
