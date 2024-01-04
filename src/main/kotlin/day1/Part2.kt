package day1

import java.io.File

fun getNumberFromString(string: String): Char? {
    if (string[0].isDigit()) {
        return string[0]
    }

    if (string.startsWith("one")) {
        return '1'
    }

    if (string.startsWith("two")) {
        return '2'
    }

    if (string.startsWith("three")) {
        return '3'
    }

    if (string.startsWith("four")) {
        return '4'
    }

    if (string.startsWith("five")) {
        return '5'
    }

    if (string.startsWith("six")) {
        return '6'
    }

    if (string.startsWith("seven")) {
        return '7'
    }

    if (string.startsWith("eight")) {
        return '8'
    }

    if (string.startsWith("nine")) {
        return '9'
    }

    return null
}

fun main() {

    val numbers = mutableListOf<Int>()

    File("src/main/kotlin/day1/input.in").forEachLine {line ->
        val digits = mutableListOf<Char>();

        for (i in line.indices) {
            val char = getNumberFromString(line.substring(i))

            if (char?.isDigit() == true) {
                digits.add(char)
            }
        }


        val number = "${digits[0]}${digits[digits.size - 1]}"

        numbers.add(number.toInt())
    }

    val total = numbers.reduce { acc, number ->
        acc + number
    }

    println(total)
}