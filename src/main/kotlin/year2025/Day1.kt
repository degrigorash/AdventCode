package year2025

import tools.readFile

fun main() {
    Day1().part1()
    Day1().part2()
}

class Day1 {

    private val lines = readFile("year2025/day1input")
    private var current = 50
    private var password = 0

    fun part1() {
        lines.forEach {
            val key = if (it.first() == 'L') -1 else 1
            current += key * it.substring(1).toInt()
            while (current >= 100) {
                current -= 100
            }
            while (current < 0) {
                current += 100
            }
            if (current == 0) password++
        }
        println("part1: $password")
    }

    fun part2() {
        lines.forEach {
            val key = if (it.first() == 'L') -1 else 1
            val rotation = key * it.substring(1).toInt()
            val prev = current
            current += rotation
            when {
                current == 0 -> {
                    password++
                }

                current >= 100 -> {
                    while (current >= 100) {
                        current -= 100
                        password++
                    }
                }

                current < 0 -> {
                    if (prev == 0) password-- // skip initial 0
                    while (current < 0) {
                        current += 100
                        password++
                    }
                    if (current == 0) password++ // count final 0
                }
            }
        }
        println("part2: $password")
    }
}