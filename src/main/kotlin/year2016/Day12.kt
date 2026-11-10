package year2016

import tools.readFile
import kotlin.collections.forEach

fun main() {
    Day12().part1()
    Day12().part2()
}

class Day12 {

    private val input = readFile("year2016/day12input")

    //    private val input = readFile("test")
    private val registers = mutableMapOf(
        "a" to 0,
        "b" to 0,
        "c" to 0,
        "d" to 0
    )

    fun part1() {
        var i = 0
        var y = 0
        while (true) {
            val inst = input[i]
            when {
                inst.startsWith("cpy") -> {
                    val value = inst.split(" ")[1]
                    val reg = inst.split(" ")[2]
                    if (registers[value] == null) {
                        registers[reg] = registers[reg]!!.plus(value.toInt())
                    } else {
                        registers[reg] = registers[reg]!!.plus(registers[value]!!)
                    }
                    i++
                }

                inst.startsWith("inc") -> {
                    val reg = inst.split(" ")[1]
                    registers[reg] = registers[reg]!!.plus(1)
                    i++
                }

                inst.startsWith("dec") -> {
                    val reg = inst.split(" ")[1]
                    registers[reg] = registers[reg]!!.minus(1)
                    i++
                }

                inst.startsWith("jnz") -> {
                    when {
                        inst.split(" ")[1].toIntOrNull() != null &&
                                inst.split(" ")[1].toIntOrNull() != 0 -> {
                            i += inst.split(" ")[2].toInt()
                        }

                        registers[inst.split(" ")[1]] != 0 -> {
                            i += inst.split(" ")[2].toInt()
                        }

                        else -> {
                            i++
                        }
                    }
                }
            }
//            y++
//            println("$y: $registers")
            if (i >= input.size || y == 23) break
        }
        println("part1: ${registers["a"]}")
    }

    fun part2() {

//        println("part2: $result")
    }
}