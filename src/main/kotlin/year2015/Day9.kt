package year2015

import tools.permutations
import tools.readFile
import kotlin.math.max
import kotlin.math.min

fun main() {
    Day9().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day9 {

    private val input = readFile("year2015/day9input")
    private val paths = input.associate {
        val key = it.substringBefore(" to") to it.substringAfter("to ").substringBefore(" =")
        val distance = it.substringAfter("= ").toInt()
        key to distance
    }
    private val cities = (paths.map { it.key.first }.toSet() + paths.map { it.key.second }.toSet()).toList()

    fun part1() {
        var shortest = Int.MAX_VALUE
        cities.permutations().forEach {
            var sum = 0
            it.windowed(2).forEach { window ->
                sum += getDistance(window[0], window[1])
            }
            shortest = min(shortest, sum)
        }
        println(shortest)
    }

    fun part2() {
        var longest = 0
        cities.permutations().forEach {
            var sum = 0
            it.windowed(2).forEach { window ->
                sum += getDistance(window[0], window[1])
            }
            longest = max(longest, sum)
        }
        println(longest)
    }

    private fun getDistance(city1: String, city2: String): Int {
        return paths.getOrElse(city1 to city2) { paths[city2 to city1]!! }
    }
}