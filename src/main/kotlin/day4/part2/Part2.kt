package day4.part2

import java.io.File
import java.io.InvalidObjectException

class Card(val id: Int, val winningNumbers: List<Int>, val myNumbers: List<Int>) {
    override fun toString(): String {
        return """{
            |   "id": $id,
            |   "winningNumbers": ${winningNumbers.toString()},
            |   "myNumbers": ${myNumbers.toString()}
            |}""".trimMargin()
}

}
fun getAmountOfWinningNumbers(card: Card): Int {
    return card.winningNumbers.toSet().intersect(card.myNumbers.toSet()).size
}

fun getCardResult(card: Card, cards: List<Card>): List<Card>? {
    val numberOfWins = getAmountOfWinningNumbers(card)
    val idxOfCard = cards.indexOf(card)

    if (numberOfWins == 0) {
            return null
    }

    return cards.slice(idxOfCard + 1..<idxOfCard + numberOfWins + 1)
}

fun getCardCollectionResult(cardCollection: List<Card>, allCards: List<Card>): Int {
    var count = 0

    cardCollection.forEach {
        val wonCards = getCardResult(it, allCards)


        if (wonCards != null) {
            count += wonCards.size
            count += getCardCollectionResult(wonCards, allCards)
        }
    }

    return count
}

fun main() {

    val cards = mutableListOf<Card>()


    File("src/main/kotlin/day4/input.in").forEachLine { card ->

        val (gameIdString, numbers) = card.split(": ")
        val (winningNumbersString, myNumbersString) = numbers.split(" | ")
        val gameId = Regex("\\d+").find(gameIdString)?.value?.toInt() ?: throw Exception("No game id found");

        val winningNumbers = winningNumbersString.split(" ").filter {
            it.isNotBlank()
        }.map {
            it.toInt()
        }
        val myNumbers = myNumbersString.split(" ").filter {
            it.isNotBlank()
        }.map {
            it.toInt()
        }

        cards.add(Card(gameId, winningNumbers, myNumbers))
    }

    val total = getCardCollectionResult(cards, cards) + cards.size

    println(total)


}