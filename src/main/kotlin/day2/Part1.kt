package day2

import java.io.File

fun main() {
    val possibleGameIds = mutableListOf<Int>()
    val maxValues = mapOf("red" to 12, "green" to 13, "blue" to 14)

    File("src/main/kotlin/day2/input.in").forEachLine {game ->
        val gameId = game.split(": ")[0].split("Game ")[1]
        val hands = mutableListOf<Map<String, Int>>()

        game.split(": ")[1].split("; ").map{hand ->
            val colorCount = mutableMapOf<String, Int>()

            hand.split(", ").forEach(fun(cubeSet: String) {
               val (count, color) = cubeSet.split(" ")

               colorCount[color] = count.toInt()
            })

            hands.add(colorCount)

        }

        var isGamePossible = true;

        hands.forEach { hand ->
            hand.forEach(fun(color, count) {
                val maxCount = maxValues[color]

                if (maxCount != null && count > maxCount) {
                    isGamePossible = false
                }
            })
        }

        if (isGamePossible) {
            possibleGameIds.add(gameId.toInt())
        }
    }

    println(possibleGameIds.reduce{total, count -> total + count})

}