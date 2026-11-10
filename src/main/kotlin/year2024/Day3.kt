package year2024

import tools.readFileText

fun main() {
    Day3().part1()
    Day3().part2()
}

class Day3 {

    private val line = readFileText("year2024/day3input")
//        private val line = readFileText("test")
    private var sum = 0

    fun part1() {
        val regex = """mul\(([0-9]|[1-9][0-9]|[1-9][0-9][0-9]),([0-9]|[1-9][0-9]|[1-9][0-9][0-9])\)""".toRegex()
        val match = regex.findAll(line).map { it.value }
        match.forEach {
            val length = it.length
            it.substring(4, length - 1).split(",").let {
                sum += it[0].toInt() * it[1].toInt()
            }
        }

        println("part1: $sum")
    }

    fun part2() {
        val regex = """mul\(([0-9]|[1-9][0-9]|[1-9][0-9][0-9]),([0-9]|[1-9][0-9]|[1-9][0-9][0-9])\)|do\(\)|don't\(\)""".toRegex()
        val match = regex.findAll(line).map { it.value }
        var dodo = true
        match.forEach {
            when (it) {
                "do()" -> dodo = true
                "don't()" -> dodo = false
                else -> {
                    if (dodo) {
                        val length = it.length
                        it.substring(4, length - 1).split(",").let {
                            sum += it[0].toInt() * it[1].toInt()
                        }
                    }
                }
            }
        }

        println("part2: $sum")
    }
}