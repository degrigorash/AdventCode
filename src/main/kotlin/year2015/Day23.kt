package year2015

import tools.readFile

fun main() {
    Day23().part1()
    Day23().part2()
}

class Day23 {

    private val input = readFile("year2015/day23input")
    private val instructions = listOf("hlf", "tpl", "inc", "jmp", "jie", "jio")
    var a = 0
    var b = 0

    fun part1() {
        var index = 0
        while (index < input.size) {
            val instruction = input[index]
            when {
                instruction.startsWith("hlf") -> {
                    val register = instruction.split(" ")[1]
                    if (register == "a") a /= 2 else b /= 2
                    index++
                }

                instruction.startsWith("tpl") -> {
                    val register = instruction.split(" ")[1]
                    if (register == "a") a *= 3 else b *= 2
                    index++
                }

                instruction.startsWith("inc") -> {
                    val register = instruction.split(" ")[1]
                    if (register == "a") a += 1 else b += 1
                    index++
                }

                instruction.startsWith("jmp") -> {
                    val value = instruction.split(" ")[1]
                    if (value[0] == '+') {
                        index += value.substring(1).toInt()
                    } else {
                        index -= value.substring(1).toInt()
                    }
                }

                instruction.startsWith("jie") -> {
                    val register = instruction.split(" ")[1]
                    val go = if (register.startsWith("a")) a % 2 == 0 else b % 2 == 0
                    if (go) {
                        val value = instruction.split(" ")[2]
                        if (value[0] == '+') {
                            index += value.substring(1).toInt()
                        } else {
                            index -= value.substring(1).toInt()
                        }
                    } else {
                        index++
                    }
                }

                instruction.startsWith("jio") -> {
                    val register = instruction.split(" ")[1]
                    val go = if (register.startsWith("a")) a == 1 else b == 1
                    if (go) {
                        val value = instruction.split(" ")[2]
                        if (value[0] == '+') {
                            index += value.substring(1).toInt()
                        } else {
                            index -= value.substring(1).toInt()
                        }
                    } else {
                        index++
                    }
                }
            }
        }

        println(b)
    }

    fun part2() {
        a = 1
        part1()
    }
}