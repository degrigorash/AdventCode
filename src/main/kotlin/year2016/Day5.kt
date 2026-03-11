package year2016

import tools.md5
import tools.readFileText

fun main() {
    Day5().part1()
    Day5().part2()
}

class Day5 {

    private val input = readFileText("year2016/day5input")
    private var result = ""
    private val result2 = MutableList(8) { "" }

    fun part1() {
        var i = 0
        while (true) {
            val md5 = md5(input + i)
            if (md5.startsWith("00000")) {
                result += md5[5]
            }
            i++
            if (result.length == 8) break
        }
        println("part1: $result")
    }

    fun part2() {
        var i = 0
        while (true) {
            val md5 = md5(input + i)
            if (md5.startsWith("00000")) {
                val index = md5[5]
                if (index.isDigit() && index.digitToInt() < 8 && result2[index.digitToInt()].isEmpty())
                    result2[index.digitToInt()] = md5[6].toString()
            }
            i++
            if (result2.none { it.isEmpty() }) break
        }
        println("part2: $result2")
    }
}