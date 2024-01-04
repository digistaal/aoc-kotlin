package day2

import java.io.File

fun main() {
    var total = 0

    File("src/main/kotlin/day2/input.in").forEachLine {game ->
        val hands = mutableListOf<Map<String, Int>>()

        game.split(": ")[1].split("; ").map{hand ->
            val colorCount = mutableMapOf<String, Int>()

            hand.split(", ").forEach(fun(cubeSet: String) {
                val (count, color) = cubeSet.split(" ")

                colorCount[color] = count.toInt()
            })

            hands.add(colorCount)

        }


        val highestHands = mutableMapOf<String, Int>()

        hands.forEach{
            it.forEach(fun(color, count) {
                val currentHighestCount = highestHands[color]

                if (currentHighestCount == null || count > currentHighestCount) {
                    highestHands[color] = count
                }
            })
        }

        total += highestHands.values.reduce{total, current -> total * current}
    }

    println(total)
}