import scala.util.Random
import org.scalajs.dom.window.prompt

val rock = "rock"
val paper = "paper"
val scissors = "scissors"
val invalid = "invalid"

def userPlay() =
  prompt("Please choose rock, paper, or scissors.", rock).toLowerCase

val choices = List(rock, paper, scissors)
def computerPlay() = Random.shuffle(choices).head

def round(playerSelection: String, computerSelection: String) =
  if (playerSelection == paper && computerSelection == rock) ||
    (playerSelection == rock && computerSelection == scissors) ||
    (playerSelection == scissors && computerSelection == paper)
  then s"You Win! $playerSelection beats $computerSelection"
  else if playerSelection == computerSelection then
    s"You Tied! You both chose $playerSelection"
  else s"You Lose! $computerSelection beats $playerSelection"

def showResults(record: List[String]) =
  val wins = record.count(string => string.startsWith("You Win!"))
  val losses = record.count(string => string.startsWith("You Lose!"))
  val ties = record.count(string => string.startsWith("You Tied!"))
  if wins > losses then println("You won!!")
  else if losses > wins then println("You lost!!")
  else println("You tied!!")
  println(
    s"Scoreboard: $wins wins, $losses losses, $ties ties"
  )

def game() =
  val scoreRecord =
    for roundNum <- List.range(1, 6)
    yield
      val playerChoice = userPlay()
      val computerChoice = computerPlay()
      val result = round(playerChoice, computerChoice)
      println(s"Round $roundNum: $result")
      result

  showResults(scoreRecord)

@main def main = game()