package year2023

import tools.readFile

fun main() {
    Day5().part1()
    Day5().part2()
}

class Day5 {

    private val lines = readFile("year2023/day5input")
    private val seeds = lines[0].let {
        it.substring(it.indexOf(":") + 1, it.length)
    }.split(" ").filterNot { it.isEmpty() }
    private var maps = lines.subList(2, lines.size)

    fun part1() {
        var numbers = seeds.map { it.toLong() }.toMutableList()
        while (true) {
            val endIndex = maps.indexOf("").takeIf { it != -1 } ?: (maps.size)
            val map = maps.subList(1, endIndex)

            val newNumbers = mutableListOf<Long>()
            numbers.forEach { number ->
                var value = number
                map.forEach {
                    val ranges = it.split(" ").map { it.toLong() }
                    if (number >= ranges[1] && number <= ranges[1] + ranges[2]) {
                        value = ranges[0] + number - ranges[1]
                    }
                }
                newNumbers.add(value)
            }
            numbers = newNumbers

            if (endIndex == maps.size) break
            maps = maps.subList(endIndex + 1, maps.size)
        }

        println(numbers.min())
    }

    fun part2() {
        val numbers = seeds.map { it.toLong() }
        val ranges = mutableListOf<Pair<Long, Long>>()
        for (i in numbers.indices step 2) {
            ranges.add(numbers[i] to numbers[i] + numbers[i + 1] - 1)
        }

        while (true) {
            val endIndex = maps.indexOf("").takeIf { it != -1 } ?: (maps.size)
            val map = maps.subList(1, endIndex)

            val newRange = mutableListOf<Pair<Long, Long>>()
            ranges.forEach { range ->
                map.forEach {
                    val nextRanges = it.split(" ").map { it.toLong() }
                    val start = nextRanges[1]
                    val end = nextRanges[1] + nextRanges[2] - 1
                    if(start <= range.second && end >= range.first) {
                        when {
                            start <= range.first && end >= range.second -> {
                                newRange.add(nextRanges[0] to nextRanges[0] + nextRanges[2] - 1)
                            }
                            start <= range.first -> {
                                newRange.add(nextRanges[0] to nextRanges[0] + range.second - nextRanges[1])
                            }
                        }
                    }
                }

            }
            // numbers = newNumbers

            if (endIndex == maps.size) break
            maps = maps.subList(endIndex + 1, maps.size)
        }
    }
}