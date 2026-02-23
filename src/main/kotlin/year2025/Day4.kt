package year2025

import tools.readFile

fun main() {
    Day4().part1()
    Day4().part2()
}

class Day4 {

    private val lines = readFile("year2025/day4input")
    private var result = 0

    fun part1() {
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, dig ->
                if (dig == "@"[0]) {
                    var sum = 0
                    lines.getOrNull(y - 1)?.getOrNull(x - 1)?.let { if (it == "@"[0]) sum++ }
                    lines.getOrNull(y - 1)?.getOrNull(x)?.let { if (it == "@"[0]) sum++ }
                    lines.getOrNull(y - 1)?.getOrNull(x + 1)?.let { if (it == "@"[0]) sum++ }
                    lines.getOrNull(y)?.getOrNull(x - 1)?.let { if (it == "@"[0]) sum++ }
                    lines.getOrNull(y)?.getOrNull(x + 1)?.let { if (it == "@"[0]) sum++ }
                    lines.getOrNull(y + 1)?.getOrNull(x - 1)?.let { if (it == "@"[0]) sum++ }
                    lines.getOrNull(y + 1)?.getOrNull(x)?.let { if (it == "@"[0]) sum++ }
                    lines.getOrNull(y + 1)?.getOrNull(x + 1)?.let { if (it == "@"[0]) sum++ }
                    if (sum < 4) result++
                }
            }
        }
        println("part1: $result")
    }

    fun part2() {
        var step = -1
        var matrix = lines.map { it.map { it }.toMutableList() }.toMutableList()
        var copy = matrix.map { it.toMutableList() }.toMutableList()
        while (step != result) {
            step = result
            matrix.forEachIndexed { x, line ->
                line.forEachIndexed { y, dig ->
                    if (dig == "@"[0]) {
                        var sum = 0
                        matrix.getOrNull(x - 1)?.getOrNull(y - 1)?.let {
                            if (it == "@"[0]) {
                                sum++
                            }
                        }
                        matrix.getOrNull(x - 1)?.getOrNull(y)?.let {
                            if (it == "@"[0]) {
                                sum++
                            }
                        }
                        matrix.getOrNull(x - 1)?.getOrNull(y + 1)?.let {
                            if (it == "@"[0]) {
                                sum++
                            }
                        }
                        matrix.getOrNull(x)?.getOrNull(y - 1)?.let {
                            if (it == "@"[0]) {
                                sum++
                            }
                        }
                        matrix.getOrNull(x)?.getOrNull(y + 1)?.let {
                            if (it == "@"[0]) {
                                sum++
                            }
                        }
                        matrix.getOrNull(x + 1)?.getOrNull(y - 1)?.let {
                            if (it == "@"[0]) {
                                sum++
                            }
                        }
                        matrix.getOrNull(x + 1)?.getOrNull(y)?.let {
                            if (it == "@"[0]) {
                                sum++
                            }
                        }
                        matrix.getOrNull(x + 1)?.getOrNull(y + 1)?.let {
                            if (it == "@"[0]) {
                                sum++
                            }
                        }
                        if (sum < 4) {
                            result++
                            copy[x][y] = "."[0]
                        }
                    }
                }
            }
            matrix = copy.map { it.toMutableList() }.toMutableList()
        }
        println("part2: $result")
    }
}