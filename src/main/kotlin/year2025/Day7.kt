package year2025

import tools.readFile
import kotlin.collections.forEach

fun main() {
    Day7().part1()
    Day7().part2()
}

class Day7 {

    private val lines = readFile("year2025/day7input")
//    private val lines = readFile("test")
    private val input = lines.map { it.map { it }.toMutableList() }.toMutableList()
    private var result = 0L

    fun part1() {
        var beams = mutableSetOf<Int>()
        beams.add(input[0].indexOf('S'))
        (1 until input.size).forEach {
            val newBeams = mutableSetOf<Int>()
            beams.forEach { beam ->
                if (input[it][beam] == '.') {
                    newBeams.add(beam)
                } else if (input[it][beam] == '^') {
                    newBeams.add(beam - 1)
                    newBeams.add(beam + 1)
                    result += 1
                }
            }
            beams = newBeams
        }

        println("part1: $result")
    }

    fun part2() {

        println("part2: $result")
    }
}