package year2025

import tools.readFile
import kotlin.collections.forEach

fun main() {
    Day6().part1()
    Day6().part2()
}

class Day6 {

    private val lines = readFile("year2025/day6input").map { it.clear() }
    private val lines2 = readFile("year2025/day6input")
    private var result = 0L

    fun part1() {
        val size = lines[0].split(" ").size
        val numbers = lines.take(lines.size - 1).map { it.split(" ").map { it.toLong() } }
        val nums = lines.size - 2
        val operators = lines.last().split(" ")
        (0 until size).forEach {
            if (operators[it] == "+") {
                var res = 0L
                (0..nums).forEach { num -> res += numbers[num][it] }
                result += res
            } else if (operators[it] == "*") {
                var res = 1L
                (0..nums).forEach { num -> res *= numbers[num][it] }
                result += res
            }
        }
        println("part1: $result")
    }

    fun part2() {
        val operators = lines2.last()
        val nums = lines2.take(lines2.size - 1)
        val lengths = mutableListOf<Int>()
        operators.forEachIndexed { index, ch ->
            if (ch != " "[0]) lengths.add(index)
        }

        (0 until lengths.size - 1).forEach {
            val size = lengths[it + 1] - lengths[it] - 1
            if (operators[lengths[it]] == "+"[0]) {
                var sum = 0L

                (0 until size).forEach { step ->
                    val i = lengths[it + 1] - 2 - step
                    var number = ""
                    nums.forEach { line ->
                        if (line[i] != " "[0]) number += line[i]
                    }
                    sum += number.toLong()
                }

                result += sum
            } else if (operators[lengths[it]] == "*"[0]) {
                var sum = 1L

                (0 until size).forEach { step ->
                    val i = lengths[it + 1] - 2 - step
                    var number = ""
                    nums.forEach { line ->
                        if (line[i] != " "[0]) number += line[i]
                    }
                    sum *= number.toLong()
                }

                result += sum
            }
        }

        val size = lines2[0].length - lengths.last()
        var sum = 0L
        (0 until size).forEach { step ->
            val i = lines2[0].length - 1 - step
            var number = ""
            nums.forEach { line ->
                if (line[i] != " "[0]) number += line[i]
            }
            sum += number.toLong()
        }

        result += sum

        println("part2: $result")
    }

    private fun String.clear() = trim().replace("\\s+".toRegex(), " ")
}