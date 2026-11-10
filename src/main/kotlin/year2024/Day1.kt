package year2024

import tools.readFile
import kotlin.math.abs

fun main() {
    Day1().part1()
    Day1().part2()
}

class Day1 {

    private val lines = readFile("year2024/day1input")
//    private val lines = readFile("test")
    private var sum = 0

    fun part1() {
        val coordinates = lines.map { it.split("   ") }
        val coord1 = coordinates.map { it.first().toInt() }.sorted()
        val coord2 = coordinates.map { it[1].toInt() }.sorted()
        coord1.forEachIndexed { index, i ->
            sum += abs(i - coord2[index])
        }

        println("part1: $sum")
    }

    fun part2() {
        val coordinates = lines.map { it.split("   ") }
        val coord1 = coordinates.map { it.first().toInt() }.sorted()
        val coord2 = coordinates.map { it[1].toInt() }.sorted()
        coord1.forEach { i ->
            sum += i * coord2.count { it == i }
        }

        println("part1: $sum")
    }
}