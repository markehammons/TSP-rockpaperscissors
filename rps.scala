import scala.util.Random
import org.scalajs.dom.window.prompt

val rock = "rock"
val paper = "paper"
val scissors = "scissors"
val invalid = "invalid"

def userPlay() = 
  val selection = prompt("Please choose rock, paper, or scissors.", rock)
  selection match 
    case `rock` | `paper` | `scissors` => selection
    case _ => invalid

val choices = List(rock,paper,scissors)
def computerPlay() = choices(Random.nextInt(3))

val win = (1,0,0)
val loss = (0,1,0)
val tie = (0,0,1)

type Record = (Int, Int, Int)

def combineRecords(a: (Int, Int, Int), b: (Int, Int, Int)) = 
  (a._1 + b._1, a._2 + b._2, a._3 + b._3)

def round(playerSelection: String, computerSelection: String) =
  (playerSelection, computerSelection) match
    case (`paper`, `rock`) | (`rock`, `scissors`) |
        (`scissors`, `paper`) =>
      (s"You Win! $playerSelection beats $computerSelection", win)
    case (`computerSelection`, _) =>
      (s"You Tied! You both chose $playerSelection", tie)
    case _ => (s"You lose! $computerSelection beats $playerSelection", loss)

def showResults(record: Record) = 
  val (wins, losses, ties) = record
  if wins > losses then 
    println("You won!!")
  else if losses > wins then 
    println("You lost!!")
  else 
    println("You tied!!")
  println(s"Scoreboard: $wins wins, $losses losses, $ties ties")

def game() =
  val scoreRecord =
    for roundNum <- 1 to 5
    yield
      val playerChoice = userPlay()
      val computerChoice = computerPlay()
      val (result, record) = round(playerChoice, computerChoice)
      println(s"Round $roundNum: $result")
      record

  showResults(scoreRecord.reduce(combineRecords))

@main def main = game()