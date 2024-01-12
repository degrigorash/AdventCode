package year2015

import tools.readFile

fun main() {
    Day16().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day16 {

    private val input = readFile("year2015/day16input")
    private val theAunt = listOf(3, 7, 2, 3, 0, 0, 5, 3, 2, 1)
    private val aunts = input.map {
        listOf(
            it.count("children: "),
            it.count("cats: "),
            it.count("samoyeds: "),
            it.count("pomeranians: "),
            it.count("akitas: "),
            it.count("vizslas: "),
            it.count("goldfish: "),
            it.count("trees: "),
            it.count("cars: "),
            it.count("perfumes: ")
        )
    }

    fun part1() {
        aunts.forEachIndexed { index, aunt ->
            var thisAunt = true
            aunt.forEachIndexed { i, value ->
                if (value != -1 && value != theAunt[i]) thisAunt = false
            }
            if (thisAunt) {
                println(index + 1)
                return@forEachIndexed
            }
        }
    }

    fun part2() {
        aunts.forEachIndexed { index, aunt ->
            var thisAunt = true
            aunt.forEachIndexed { i, value ->
                if (value != -1) {
                    when {
                        (i == 1 || i == 7) -> if (value <= theAunt[i]) thisAunt = false
                        (i == 3 || i == 6) -> if (value >= theAunt[i]) thisAunt = false
                        value != theAunt[i] -> thisAunt = false
                    }
                }
            }
            if (thisAunt) {
                println(index + 1)
                return@forEachIndexed
            }
        }
    }

    private fun String.count(key: String): Int {
        return if (contains(key)) {
            substringAfter(key).substringBefore(",").toInt()
        } else -1
    }
}