package year2025

import tools.readFile

fun main() {
    Day3().part1()
    Day3().part2()
}

class Day3 {

    private val lines = readFile("year2025/day3input")
    var sum = 0L

    fun part1() {
        lines.forEach {
            val max = it.substring(0, it.length - 1).map { it.digitToInt() }.max()
            val index = it.indexOfFirst { it.digitToInt() == max }
            val max2 = it.substring(index + 1).max()
            sum += "$max$max2".toInt()
        }
        println("part1: $sum")
    }

    fun part2() {
        lines.forEach { line ->
            var copy = line
            var left = 12
            var number = ""
            while (left > 0) {
                val max = copy.substring(0, copy.length - left + 1).map { it.digitToInt() }.max()
                number += max
                val index = copy.indexOfFirst { it.digitToInt() == max }
                copy = copy.substring(index + 1)
                left--
            }
            sum += number.toLong()
        }
        println("part2: $sum")
    }
}