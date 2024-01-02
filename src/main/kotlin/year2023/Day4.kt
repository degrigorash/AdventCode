package year2023

import tools.readFile

fun main() {
    Day4().part1()
    Day4().part2()
}

class Day4 {

    private val lines = readFile("year2023/day4input")

    fun part1() {
        println(
            lines.sumOf { line ->
                var points = 0
                line.wins().forEach {
                    if (line.numbers().contains(it)) {
                        if (points == 0) points = 1 else points *= 2
                    }
                }

                points
            }
        )
    }

    fun part2() {
        val copies = MutableList(lines.size) { 1 }
        lines.forEachIndexed { index, line ->
            var points = 0
            line.wins().forEach {
                if (line.numbers().contains(it)) {
                    points++
                }
            }
            (index+1..index+points).forEach {
                copies[it] += copies[index]
            }
        }
        println(copies.sumOf { it })
    }

    private fun String.wins() = substring(
        indexOf(':') + 1,
        indexOf('|')
    ).split(' ').filter { it.isNotEmpty() }

    private fun String.numbers() = substring(
        indexOf('|') + 1,
        length
    ).split(' ').filter { it.isNotEmpty() }
}