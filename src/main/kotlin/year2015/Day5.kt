package year2015

import tools.readFile

fun main() {
    Day5().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day5 {

    private val input = readFile("year2015/day5input")
    private val vowels = listOf('a', 'e', 'i', 'o', 'u')
    private val badStrings = listOf("ab", "cd", "pq", "xy")

    fun part1() {
        println(
            input.sumOf { line ->
                val checkVowels = line.filter { vowels.contains(it) }.length >= 3
                val checkDouble = line.zipWithNext().any { it.first == it.second }
                val checkBad = badStrings.none { line.contains(it) }
                if (checkVowels && checkDouble && checkBad) 1L else 0
            }
        )
    }

    fun part2() {
        println(
            input.sumOf { line ->
                val doubles = line.windowed(2).groupingBy { it }.eachCount().filter { it.value > 1 }
                var checkDouble = false
                doubles.forEach {
                    if (line.lastIndexOf(it.key) - line.indexOf(it.key) >= 2) checkDouble = true
                }

                val repeats = line.groupingBy { it }.eachCount().filter { it.value > 1 }
                var checkRepeats = false
                repeats.forEach { repeat ->
                    line.mapIndexedNotNull { i, c ->
                        if (c == repeat.key) i else null
                    }.sorted().windowed(2).forEach {
                        if (it[1] - it[0] == 2) checkRepeats = true
                    }
                }

                if (checkDouble && checkRepeats) 1L else 0
            }
        )
    }
}