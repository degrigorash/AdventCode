package year2023

import tools.readFile

fun main() {
    Day9().part1()
    Day9().part2()
}

class Day9 {

    private val lines = readFile("year2023/day9input")
    private val sequences = lines.map { it.split(" ").map { it.toLong() } }

    fun part1() {
        println(
            sequences.sumOf {
                val lastNumbers = mutableListOf(it.last())
                var sequence = it
                while (true) {
                    val newSequence = sequence.mapIndexedNotNull { index, l ->
                        sequence.getOrNull(index + 1)?.minus(l)
                    }
                    lastNumbers.add(newSequence.last())
                    sequence = newSequence
                    if (newSequence.all { it == 0L }) break
                }
                lastNumbers.reduce { acc, l -> acc + l }
            }
        )
    }

    
    fun part2() {
        println(
            sequences.sumOf {
                val firstNumbers = mutableListOf(it.first())
                var sequence = it
                while (true) {
                    val newSequence = sequence.mapIndexedNotNull { index, l ->
                        sequence.getOrNull(index + 1)?.minus(l)
                    }
                    firstNumbers.add(newSequence.first())
                    sequence = newSequence
                    if (newSequence.all { it == 0L }) break
                }
                firstNumbers.reversed().reduce { acc, l -> l - acc }
            }
        )
    }
}