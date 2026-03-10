package year2015

import tools.md5
import java.security.MessageDigest

fun main() {
    Day4().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day4 {

    private val input = "yzbqklnj"

    fun part1() {
        var i = 0
        while (true) {
            val md5 = md5(input + i)
            if (md5.startsWith("00000")) {
                println(i)
                break
            }
            i++
        }
    }

    fun part2() {
        var i = 0
        while (true) {
            val md5 = md5(input + i)
            if (md5.startsWith("000000")) {
                println(i)
                break
            }
            i++
        }
    }
}