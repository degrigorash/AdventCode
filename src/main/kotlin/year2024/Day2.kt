package year2024

import tools.readFile
import kotlin.math.abs

fun main() {
    Day2().part1()
    Day2().part2()
}

class Day2 {

            private val lines = readFile("year2024/day2input")
//    private val lines = readFile("test")
    private var sum = 0

    fun part1() {
        val levels = lines.map { it.split(" ").map { it.toInt() } }
        levels.forEach { level ->
            val safe = isSafe(level)
            if (safe) sum++
        }

        println("part1: $sum")
    }

    fun part2() {
        val levels = lines.map { it.split(" ").map { it.toInt() } }
        levels.forEach { level ->
            val safe = isSafe(level)
            if (safe) {
                sum++
            } else {
                run loop@{
                    level.indices.forEach {
                        val l = level.toMutableList()
                        l.removeAt(it)
                        val safe = isSafe(l)
                        if (safe) {
                            sum++
                            return@loop
                        }
                    }
                }
            }
        }

        println("part2: $sum")
    }

    private fun isSafe(level: List<Int>): Boolean {
        val res = mutableListOf<Int>()
        (0 until level.size - 1).forEach {
            res.add(level[it] - level[it + 1])
        }
        var safe = true
        res.forEach {
            if (abs(it) !in 1..3) safe = false
        }
        if (res.any { it < 0 } && res.any { it > 0 }) safe = false
        return safe
    }
}