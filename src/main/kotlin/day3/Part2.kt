package day3

import java.io.File

val DIRECTIONS = listOf(
 listOf(0, -1),
 listOf(1, -1),
 listOf(1, 0),
 listOf(1, 1),
 listOf(0, 1),
 listOf(-1, -1),
 listOf(-1, 1),
 listOf(-1, 0),
)

typealias Input  = MutableList<List<Char>>

fun get(input: Input, x: Int, y: Int): Char? {
    return try {
        input[y][x]
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun getAdjacentGearPositions(currentX: Int, currentY: Int, input: Input): MutableList<List<Int>> {
    val adjacentGearPositions = mutableListOf<List<Int>>()

    DIRECTIONS.forEach {
        val (xOffset, yOffset) = it
        val xToCheck = currentX + xOffset
        val yToCheck = currentY + yOffset
        val adjacentChar = get(input, xToCheck, yToCheck)

        if (adjacentChar != null && adjacentChar == '*') {
            adjacentGearPositions.add(listOf(xToCheck, yToCheck))
        }
    }


    return adjacentGearPositions
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
    val gearNumbersMap = mutableMapOf<List<Int>, MutableList<Int>>()

    input.forEach{ line ->
        val adjacentGears = mutableSetOf<List<Int>>()

        line.forEach { char ->
            val isLastChar = x == line.size - 1


            if (char.isDigit()) {
                numberString += char
                val adjacentGearsForPosition = getAdjacentGearPositions(x, y, input)
                adjacentGearsForPosition.forEach {
                    adjacentGears.add(it)
                }
            }


            if (numberString != "" && (!char.isDigit() || isLastChar) && adjacentGears.size > 0) {
                adjacentGears.forEach {position ->
                    val currentNumbers = gearNumbersMap[position]

                    if (currentNumbers == null) {
                        gearNumbersMap[position] = mutableListOf(numberString.toInt())
                    } else {
                        currentNumbers.add(numberString.toInt())
                    }
                }
            }

            if (!char.isDigit()) {
                numberString = ""

                adjacentGears.clear()
            }

            x++
        }

        numberString = ""
        x = 0

        y++
    }


    var result = 0;

    gearNumbersMap.values.forEach{
        if (it.size == 2) {
            result += (it[0] * it[1])
        }
    }

    println(result)
}