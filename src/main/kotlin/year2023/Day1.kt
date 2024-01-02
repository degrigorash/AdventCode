package year2023

import tools.readFile

fun main() {
    Day1().part1()
    Day1().part2()
}

class Day1 {

    private val lines = readFile("year2023/day1input")
    private val numbers = listOf(
        "zero",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine"
    )

    fun part1() {
        lines.sumOf { "${it.first { it.isDigit() }}${it.last { it.isDigit() }}".toLong() }.apply {
            println(this)
        }
    }

    fun part2() {
        lines.sumOf { "${it.findNumber1()}${it.findNumber2()}".toLong() }.apply {
            println(this)
        }
    }

    private fun String.findNumber1(): Int {
        val wordsIndexes = numbers.map {
            indexOf(it)
        }
        val digit = "${first { it.isDigit() }}".toInt()
        val digitIndex = indexOf("$digit")
        val wordIndex = wordsIndexes.filter { it != -1 }.minOrNull()
        return if (wordIndex != null && wordIndex < digitIndex) wordsIndexes.indexOf(wordIndex) else digit
    }

    private fun String.findNumber2(): Int {
        val wordsIndexes = numbers.map {
            lastIndexOf(it)
        }
        val digit = "${last { it.isDigit() }}".toInt()
        val digitIndex = lastIndexOf("$digit")
        val wordIndex = wordsIndexes.filter { it != -1 }.maxOrNull()
        return if (wordIndex != null && wordIndex > digitIndex) wordsIndexes.indexOf(wordIndex) else digit
    }
}