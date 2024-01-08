package day4.part1

import java.io.File

fun main() {
    var total = 0

    File("src/main/kotlin/day4/input.in").forEachLine { card ->
        var cardPoints = 0;
        val (gameId, numbers) = card.split(": ")
        val (winningNumbersString, myNumbersString) = numbers.split(" | ")
        val winningNumbers = winningNumbersString.split(" ")
        val myNumbers = myNumbersString.split(" ")

        winningNumbers.forEach {
            if (myNumbers.contains(it) && it.isNotBlank()) {
                if (cardPoints == 0) {
                    cardPoints = 1
                } else {
                    cardPoints *= 2
                }
                println("Game ID $gameId has a winning number: $it. Score for this game is now $cardPoints")
            }
        }

        total += cardPoints
    }

    println(total)
}