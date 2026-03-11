package year2016

import tools.readFile
import kotlin.collections.forEach

fun main() {
    Day6().part1()
    Day6().part2()
}

class Day6 {

    private val input = readFile("year2016/day6input")
//        private val input = readFile("test")
    private var result = ""

    fun part1() {
        (0 until (input[0].length)).forEach { i ->
            val map: MutableMap<Char, Int> = mutableMapOf()
            input.forEach {
                val ch = it[i]
                if (map[ch] != null) {
                    map[ch] = map[ch]!!.plus(1)
                } else {
                    map[ch] = 1
                }
            }
            result += map.toList().first { it.second == map.values.max() }.first
        }
        println("part1: $result")
    }

    fun part2() {
        (0 until (input[0].length)).forEach { i ->
            val map: MutableMap<Char, Int> = mutableMapOf()
            input.forEach {
                val ch = it[i]
                if (map[ch] != null) {
                    map[ch] = map[ch]!!.plus(1)
                } else {
                    map[ch] = 1
                }
            }
            result += map.toList().first { it.second == map.values.min() }.first
        }
        println("part2: $result")
    }
}