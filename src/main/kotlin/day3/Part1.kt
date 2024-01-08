package day3

import java.io.File

val DIRECTIONS = mapOf(
    "UP" to listOf(0, -1),
    "UP_RIGHT" to listOf(1, -1),
    "RIGHT" to listOf(1, 0),
    "RIGHT_DOWN" to listOf(1, 1),
    "DOWN" to listOf(0, 1),
    "LEFT_UP" to listOf(-1, -1),
    "LEFT_DOWN" to listOf(-1, 1),
    "LEFT" to listOf(-1, 0),
)

fun isSymbol(char: Char): Boolean {
    return !char.isDigit() && char != '.'
}

typealias Input  = MutableList<List<Char>>

fun get(input: Input, x: Int, y: Int): Char? {
    return try {
        input[y][x]
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun isPositionAdjacentToSymbol(currentX: Int, currentY: Int, input: Input): Boolean {
    var isAdjacent = false

    DIRECTIONS.values.forEach {
        val (xOffset, yOffset) = it
        val adjacentChar = get(input, currentX + xOffset, currentY + yOffset)

        if (adjacentChar != null && isSymbol(adjacentChar)) {
            isAdjacent = true
        }
    }


    return isAdjacent
}

fun parseInput(): MutableList<List<Char>>{
    val result = mutableListOf<List<Char>>()

    File("src/main/kotlin/day3/input.in").forEachLine {line ->
        result.add(line.toCharArray().toList())
    }

    return result
}

fun main() {
    val input = parseInput();
    var numberString = ""
    var y = 0
    var x = 0
    var isAdjacentToSymbol = false
    var result = 0;

    File("src/main/kotlin/day3/input.in").forEachLine { line ->
        line.forEach { char ->
            val isLastChar = x == line.length - 1


            if (char.isDigit()) {
                numberString += char
                isAdjacentToSymbol = isAdjacentToSymbol || isPositionAdjacentToSymbol(x, y, input)
            }


            if (numberString != "" && (!char.isDigit() || isLastChar) && isAdjacentToSymbol) {
                result += numberString.toInt()
                println("added $numberString to result. result is now $result")
            }

            if (!char.isDigit()) {
                numberString = ""
                isAdjacentToSymbol = false
            }

            x++
        }

        numberString = ""
        isAdjacentToSymbol = false
        x = 0

        y++
    }

    println(result)
}