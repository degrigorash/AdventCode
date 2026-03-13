package year2016

import tools.readFile
import java.util.Collections
import kotlin.collections.forEach

fun main() {
    Day9().part1()
    Day9().part2()
}

class Day9 {

//    private val input = readFile("year2016/day9input")
        private val input = readFile("test")
    private var result = 0L

    fun part1() {
        input.forEach {
            var temp = it
            var i = 0
            var res = ""
            while (i < it.length) {
                if (it[i] != '(') {
                    res += it[i]
                    i++
                    if (i < it.length) temp = it.substring(i)
                } else {
                    val marker = temp.substring(1, temp.indexOf(")"))
                    val characters = marker.split("x")[0].toInt()
                    val times = marker.split("x")[1].toInt()
                    val copy = temp.substring(temp.indexOf(")") + 1, temp.indexOf(")") + 1 + characters)
                    repeat(times) { res += copy }
                    i += marker.length + 2 + characters
                    if (i < it.length) temp = it.substring(i)
                }
            }
            result += res.length
        }
        println("part1: $result")
    }

    fun part2() {
        val line = input[0].replace(Regex("[^()x0-9]"), ".").split(".").filter { it.isNotBlank() }
        line.forEach { l ->
            val count = l.replace("(", ".").replace(")", ".").split(".").filter { it.isNotBlank() }
            var r = 1L
            count.forEachIndexed { index, c ->
                r *= if (index != count.size - 1) {
                    c.split("x")[1].toInt()
                } else {
                    c.split("x")[0].toInt() * c.split("x")[1].toInt()
                }
            }
            result += r
        }
        println("part2: $result")
    }
}