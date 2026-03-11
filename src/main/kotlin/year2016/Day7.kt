package year2016

import tools.readFile
import kotlin.collections.forEach

fun main() {
    Day7().part1()
    Day7().part2()
}

class Day7 {

        private val input = readFile("year2016/day7input")
//    private val input = readFile("test")
    private var result = 0

    fun part1() {
        val ips = input.map {
            val vals = it.replace("[", ",").replace("]", ",").split(",")
            Ip(
                ips = vals.filterIndexed { index, _ -> index % 2 == 0 },
                hypernets = vals.filterIndexed { index, _ -> index % 2 == 1 },
            )
        }
        ips.forEach {
            if (it.ips.any { it.supports() } && it.hypernets.none { it.supports() }) {
                result++
            }
        }
        println("part1: $result")
    }

    fun part2() {
        val ips = input.map {
            val vals = it.replace("[", ",").replace("]", ",").split(",")
            Ip(
                ips = vals.filterIndexed { index, _ -> index % 2 == 0 },
                hypernets = vals.filterIndexed { index, _ -> index % 2 == 1 },
            )
        }
        ips.forEach { ips ->
            run loop@{
                ips.ips.forEach { ip ->
                    ip.ssls().forEach { ssl ->
                        if (ips.hypernets.any { it.contains("${ssl.v2}${ssl.v1}${ssl.v2}") }) {
                            result++
                            return@loop
                        }
                    }
                }
            }
        }
        println("part2: $result")
    }

    private fun String.supports(): Boolean {
        var sup = false
        (0 until this.length - 3).forEach {
            if (this[it] == this[it + 3] && this[it + 1] == this[it + 2] && this[it] != this[it + 1]) {
                sup = true
                return@forEach
            }
        }
        return sup
    }

    private fun String.ssls(): List<Ssl> {
        val sup = mutableListOf<Ssl>()
        (0 until this.length - 2).forEach {
            if (this[it] == this[it + 2] && this[it] != this[it + 1]) {
                sup.add(Ssl(this[it].toString(), this[it + 1].toString()))
                return@forEach
            }
        }
        return sup
    }

    private data class Ip(
        val ips: List<String>,
        val hypernets: List<String>
    )

    private data class Ssl(
        val v1: String,
        val v2: String
    )
}