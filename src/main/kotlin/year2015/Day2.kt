package year2015

import tools.readFile

fun main() {
    Day2().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day2 {

    private val input = readFile("year2015/day2input")

    fun part1() {
        println(
            input.sumOf { line ->
                val (l, w, h) = line.split("x").map { it.toInt() }
                val sides = listOf(l * w, w * h, h * l)
                2 * sides.sum() + sides.min()
            }
        )
    }

    fun part2() {
        println(
            input.sumOf { line ->
                val (l, w, h) = line.split("x").map { it.toInt() }.sorted()
                2 * (l + w) + l * w * h
            }
        )
    }
}