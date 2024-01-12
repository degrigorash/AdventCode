package year2015

import tools.readFile
import kotlin.math.pow

fun main() {
    Day17().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day17 {

    private val input = readFile("year2015/day17input").map { it.toInt() }

    fun part1() {
        var sum = 0
        (1..((2.0.pow(input.size) - 1).toInt())).forEach {
            val binary = it.toString(2).map { it.digitToInt() }
            val fullBinary = List(20 - binary.size) { 0 } + binary
            var liters = 0
            fullBinary.forEachIndexed { index, i ->
                if (i == 1) liters += input[index]
            }
            if (liters == 150) sum++
        }
        println(sum)
    }

    fun part2() {
        val result = MutableList(20) { 0 }
        (1..((2.0.pow(input.size) - 1).toInt())).forEach {
            val binary = it.toString(2).map { it.digitToInt() }
            val fullBinary = List(20 - binary.size) { 0 } + binary
            var liters = 0
            fullBinary.forEachIndexed { index, i ->
                if (i == 1) liters += input[index]
            }
            if (liters == 150) result[binary.count { it == 1 }]++
        }
        println(result.first { it != 0 })
    }
}