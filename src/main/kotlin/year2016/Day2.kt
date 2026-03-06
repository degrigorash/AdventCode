package year2016

import tools.readFile
import kotlin.math.max
import kotlin.math.min

fun main() {
    Day2().part1()
    Day2().part2()
}

class Day2 {

    private val input = readFile("year2016/day2input")
    private var code = ""
    private val keypad = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))
    private val keypad2 = listOf(
        listOf(null, null, "1", null, null),
        listOf(null, "2", "3", "4", null),
        listOf("5", "6", "7", "8", "9"),
        listOf(null, "A", "B", "C", null),
        listOf(null, null, "D", null, null),
    )

    fun part1() {
        var location = Pair(1, 1)
        input.forEach { inst ->
            inst.forEach { move ->
                when (move) {
                    'U' -> location = location.first to max(location.second - 1, 0)
                    'D' -> location = location.first to min(location.second + 1, 2)
                    'L' -> location = max(location.first - 1, 0) to location.second
                    'R' -> location = min(location.first + 1, 2) to location.second
                }
            }
            code += keypad[location.second][location.first]
        }
        println("part1: $code")
    }

    fun part2() {
        var location = Pair(0, 2)
        input.forEach { inst ->
            inst.forEach { move ->
                val newLocation = when (move) {
                    'U' -> location.first to max(location.second - 1, 0)
                    'D' -> location.first to min(location.second + 1, 4)
                    'L' -> max(location.first - 1, 0) to location.second
                    else -> min(location.first + 1, 4) to location.second
                }
                if (keypad2[newLocation.second][newLocation.first] != null) location = newLocation
            }
            code += keypad2[location.second][location.first]
        }
        println("part2: $code")
    }
}