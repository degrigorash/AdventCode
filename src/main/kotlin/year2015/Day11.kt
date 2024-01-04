package year2015

fun main() {
    Day11().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day11 {

    private val input = "cqjxjnds"

    fun part1() {
        var chars = input.map { it.code }
        while (true) {
            val inc = chars.inc()
            if (inc.second) {
                println(inc.first.map { it.toChar() }.joinToString(""))
                break
            }
            chars = inc.first
        }
    }

    fun part2() {
        var chars = "cqjxxyzz".map { it.code }
        while (true) {
            val inc = chars.inc()
            if (inc.second) {
                println(inc.first.map { it.toChar() }.joinToString(""))
                break
            }
            chars = inc.first
        }
    }

    private fun List<Int>.inc(): Pair<List<Int>, Boolean> {
        val chars = toMutableList()
        chars[chars.size - 1]++
        for (i in chars.size - 1 downTo 0) {
            if (chars[i] > 'z'.code) {
                chars[i] = 'a'.code
                if (i > 0) {
                    chars[i - 1]++
                }
            }
        }

        val three = chars.windowed(3).any { it[0] == it[1] - 1 && it[1] == it[2] - 1 }
        val forbidden = chars.none { it == 'i'.code || it == 'o'.code || it == 'l'.code }
        val pairs = chars.windowed(2).filter { it[0] == it[1] }.distinct().count() >= 2

        return chars to (three && forbidden && pairs)
    }
}