package year2025

import tools.readFile
import kotlin.collections.forEach
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    Day9().part1()
    Day9().part2()
}

class Day9 {

        private val lines = readFile("year2025/day9input")
//    private val lines = readFile("test")
    private val input = lines.map { it.split(",").let { it[0].toLong() to it[1].toLong() } }
    private var result = 0L

    fun part1() {
        input.indices.forEach { x ->
            (x + 1 until input.size).forEach { y ->
                val ar = ((input[y].first - input[x].first).absoluteValue + 1) *
                        ((input[y].second - input[x].second).absoluteValue + 1)
                if (result < ar) result = ar
            }
        }

        println("part1: $result")
    }

    fun part2() {

        println("part2: $result")
    }
}