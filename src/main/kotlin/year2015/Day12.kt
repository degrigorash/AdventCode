package year2015

import tools.readFileText

fun main() {
    Day12().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day12 {

    private val input = readFileText("year2015/day12input")

    fun part1map() {
        val digits = input.map { if (it == '-') it else it.digitToIntOrNull() }
        var sum = 0
        var number = ""
        var plus = true
        digits.forEach {
            when (it) {
                '-' -> plus = false
                null -> {
                    if (number.isNotEmpty()) {
                        if (plus) sum += number.toInt() else sum -= number.toInt()
                        number = ""
                        plus = true
                    }
                }

                else -> number += it
            }
        }

        println(sum)
    }

    fun part1() {
        val regex = Regex("-?\\d+")
        val numbers = regex.findAll(input).map { it.value.toInt() }.toList()
        println(numbers.sum())
    }

    fun part2() {

    }
}