package year2015

import tools.readFile

fun main() {
    Day8().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day8 {

    private val input = readFile("year2015/day8input")

    fun part1() {
        val parsed = input.map {
            it.replace("\\\\", "\\")
                .replace("\\\\x[0-9a-f]{2}".toRegex(), "\\\\")
                .replace("\"", "")
        }
        var result = 0
        input.forEachIndexed { index, s ->
            result += s.length - parsed[index].length
        }
        println(result)
    }

    fun part2() {
        val parsed = input.map {
            it.replace("\\", "\\\\")
                .replace("\"","\\\"")
        }
        var result = 0
        input.forEachIndexed { index, s ->
            result += parsed[index].length + 2 - s.length
        }
        println(result)
    }
}