package year2023

import tools.readFile
import java.lang.Integer.max

fun main() {
    Day2().part1()
    Day2().part2()
}

class Day2 {

    private val lines = readFile("year2023/day2input")
    private val Red = 12
    private val Green = 13
    private val Blue = 14

    fun part1() {
        println(
            lines.sumOf {
                if (
                    it.sets().all {
                        it.cubes().all {
                            it.isValid()
                        }
                    }
                ) it.gameId() else 0
            }
        )
    }

    fun part2() {
        println(
            lines.sumOf {
                var red = 1
                var green = 1
                var blue = 1
                it.sets().forEach {
                    it.cubes().forEach {
                        red = max(red, it.red())
                        green = max(green, it.green())
                        blue = max(blue, it.blue())
                    }
                }
                red * green * blue
            }
        )
    }

    private fun String.gameId() = substring(indexOf(' ') + 1, indexOf(':')).toInt()

    private fun String.sets() = substringAfter(':').split(';')

    private fun String.cubes() = split(',')

    private fun String.number() = filter { it.isDigit() }.toInt()

    private fun String.isValid(): Boolean {
        return when {
            contains("red") -> number() <= Red
            contains("green") -> number() <= Green
            contains("blue") -> number() <= Blue
            else -> true
        }
    }

    private fun String.red() = if (contains("red")) number() else 0
    private fun String.green() = if (contains("green")) number() else 0
    private fun String.blue() = if (contains("blue")) number() else 0
}