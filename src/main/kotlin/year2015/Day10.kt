package year2015

fun main() {
    Day10().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day10 {

    private val input = "3113322113".map { it.digitToInt() }

    fun part1() {
        var result = input
        repeat((0..39).count()) {
            result = lookAndSay(result)
        }
        println(result.size)
    }

    fun part2() {
        var result = input
        repeat((0..49).count()) {
            result = lookAndSay(result)
        }
        println(result.size)
    }

    private fun lookAndSay(input: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        var ch = input[0]
        var repeats = 1
        input.drop(1).forEach {
            if (ch != it) {
                result.add(repeats)
                result.add(ch)
                ch = it
                repeats = 1
            } else {
                repeats++
            }
        }
        result.add(repeats)
        result.add(ch)

        return result
    }
}