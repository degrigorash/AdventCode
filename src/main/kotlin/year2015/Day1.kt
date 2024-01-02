package year2015

import tools.readFileText

fun main() {
    Day1().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day1 {

    private val input = readFileText("year2015/day1input")

    fun part1() {
        println(
            input.map { if (it == '(') 1 else -1 }.sum()
        )
    }

    fun part2() {
        run breaker@{
            input.map { if (it == '(') 1 else -1 }
                .foldIndexed(0) { index, sum, i ->
                    if (sum < 0) {
                        println(index)
                        return@breaker sum
                    }
                    sum + i
                }
        }
    }
}