import scala.util.Random
import org.scalajs.dom.window.prompt
import scala.util.chaining.*

enum RPS:
  case Rock
  case Paper
  case Scissors

object RPS:
   def fromString(string: String) = string.toLowerCase match
     case "rock" => Some(Rock)
     case "paper" => Some(Paper)
     case "scissors" => Some(Scissors)
     case _ => None

case class ScoreRecord(wins: Int, losses: Int, ties: Int):
   def +(o: ScoreRecord) = ScoreRecord(wins + o.wins, losses + o.losses, ties + o.ties)
   override def toString = s"""|==== Score Board ====
                               | wins: $wins
                               | losses: $losses
                               | ties: $ties""".stripMargin

object ScoreRecord:
   val win = ScoreRecord(1,0,0)
   val loss = ScoreRecord(0,1,0)
   val tie = ScoreRecord(0,0,1)

def getRPS(roundNum: Int): RPS = 
   prompt(s"""Round $roundNum: Choose between "Rock", "Paper", and "Scissors"""").pipe(RPS.fromString).getOrElse{
      println("Invalid input, please try again")
      getRPS(roundNum)
   }

def computerPlay() = RPS.fromOrdinal(Random.nextInt(3))

def round(playerSelection: RPS, computerSelection: RPS) =
  (playerSelection, computerSelection) match
    case (RPS.Paper, RPS.Rock) | (RPS.Rock, RPS.Scissors) |
        (RPS.Scissors, RPS.Paper) =>
      (s"You Win! $playerSelection beats $computerSelection", ScoreRecord.win)
    case (RPS.Paper, RPS.Scissors) | (RPS.Rock, RPS.Paper) |
         (RPS.Scissors, RPS.Rock) => 
      (s"You Lose! $computerSelection beats $playerSelection", ScoreRecord.loss)
    case _ => (s"You Tied!", ScoreRecord.tie)


def game() = 
   val scoreRecord = for roundNum <- (1 to 5) 
   yield 
      val playerChoice = getRPS(roundNum)
      val computerChoice = computerPlay()
      val (result, record) = round(playerChoice, computerChoice)
      println(s"Round $roundNum: $result")
      record

   println(scoreRecord.reduce(_+_).toString)

      
@main def main = game()
