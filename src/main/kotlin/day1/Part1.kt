package day1

import java.io.File

fun main() {

    val numbers = mutableListOf<Int>()

    File("src/main/kotlin/day1/input.in").forEachLine {line ->
        val digits = mutableListOf<Char>();

        line.forEach {char ->
            if (char.isDigit()) {
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