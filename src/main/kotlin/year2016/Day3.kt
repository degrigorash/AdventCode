package year2016

import tools.readFile

fun main() {
    Day3().part1()
    Day3().part2()
}

class Day3 {

    private val input = readFile("year2016/day3input")
    private var result = 0

    fun part1() {
        result = input.map {
            val sides = it.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
            var maxIndex = 0
            if (sides[1] > sides[0]) maxIndex = 1
            if (sides[2] > sides[maxIndex]) maxIndex = 2
            when (maxIndex) {
                0 -> sides[0] < sides[1] + sides[2]
                1 -> sides[1] < sides[0] + sides[2]
                else -> sides[2] < sides[0] + sides[1]
            }
        }.filter { it }.size
        println("part1: $result")
    }

    fun part2() {
        val values = input.map {
            it.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        }
        val triangles = mutableListOf<List<Int>>()
        (0 until (values.size) step 3).forEach {
            triangles.add(listOf(values[it][0], values[it + 1][0], values[it + 2][0]))
            triangles.add(listOf(values[it][1], values[it + 1][1], values[it + 2][1]))
            triangles.add(listOf(values[it][2], values[it + 1][2], values[it + 2][2]))
        }
        result = triangles.map {
            val sides = it
            var maxIndex = 0
            if (sides[1] > sides[0]) maxIndex = 1
            if (sides[2] > sides[maxIndex]) maxIndex = 2
            when (maxIndex) {
                0 -> sides[0] < sides[1] + sides[2]
                1 -> sides[1] < sides[0] + sides[2]
                else -> sides[2] < sides[0] + sides[1]
            }
        }.filter { it }.size
        println("part2: $result")
    }
}