package year2023

import tools.readFile
import kotlin.math.sqrt

fun main() {
    Day6().part1()
    Day6().part2()
}

class Day6 {

    private val lines = readFile("year2023/day6input")

    fun part1() {
        val time = lines[0].substring(
            lines[0].indexOf(':') + 1
        )
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
        val distance = lines[1].substring(
            lines[1].indexOf(':') + 1
        )
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.toLong() }

        var result = 1L
        time.forEachIndexed { i, t ->
            result *= x2(t, distance[i]) - x1(t, distance[i]) + 2
        }

        println(result)
    }

    fun part2() {
        val time = lines[0].substring(
            lines[0].indexOf(':') + 1
        )
            .filter { it != ' ' }
            .toLong()
        val distance = lines[1].substring(
            lines[1].indexOf(':') + 1
        )
            .filter { it != ' ' }
            .toLong()

        var value = 0L
        (0..time).forEach {
            if (it * it - time * it + distance <= 0) {
                value += 1
            }
        }
        println(value)
    }

    private fun x1(t: Long, d: Long): Long {
        return ((t + sqrt(t * t - 4.0 * d)) / 2).toLong()
    }

    private fun x2(t: Long, d: Long): Long {
        return ((t - sqrt(t * t - 4.0 * d)) / 2).toLong()
    }

    // x * x - t * x + d <= 0
    // x2 - x1 + 1
}