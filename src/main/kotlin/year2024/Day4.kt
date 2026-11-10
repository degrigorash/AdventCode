package year2024

import tools.readFile

fun main() {
    Day4().part1()
    Day4().part2()
}

class Day4 {

            private val lines = readFile("year2024/day4input")
//    private val lines = readFile("test")
    private val letters = lines.map { it.map { it } }
    private var sum = 0
    private val XMAS = "XMAS"

    fun part1() {
        letters.forEachIndexed { x, chars ->
            chars.forEachIndexed { y, letter ->
                if (letter == 'X') sum += letters.check(x, y)
            }
        }
        println("part1: $sum")
    }

    fun part2() {
        letters.forEachIndexed { x, chars ->
            chars.forEachIndexed { y, letter ->
                if (letter == 'A' && letters.check2(x, y)) sum += 1
            }
        }
        println("part2: $sum")
    }

    private fun List<List<Char>>.safeGet(x: Int, y: Int) = getOrNull(x)?.getOrNull(y)

    private fun List<List<Char>>.check(x: Int, y: Int) =
        listOf(
            safeGet(x - 1, y - 1) == XMAS[1] && safeGet(x - 2, y - 2) == XMAS[2] && safeGet(x - 3, y - 3) == XMAS[3],
            safeGet(x, y - 1) == XMAS[1] && safeGet(x, y - 2) == XMAS[2] && safeGet(x, y - 3) == XMAS[3],
            safeGet(x + 1, y - 1) == XMAS[1] && safeGet(x + 2, y - 2) == XMAS[2] && safeGet(x + 3, y - 3) == XMAS[3],
            safeGet(x - 1, y) == XMAS[1] && safeGet(x - 2, y) == XMAS[2] && safeGet(x - 3, y) == XMAS[3],
            safeGet(x + 1, y) == XMAS[1] && safeGet(x + 2, y) == XMAS[2] && safeGet(x + 3, y) == XMAS[3],
            safeGet(x - 1, y + 1) == XMAS[1] && safeGet(x - 2, y + 2) == XMAS[2] && safeGet(x - 3, y + 3) == XMAS[3],
            safeGet(x, y + 1) == XMAS[1] && safeGet(x, y + 2) == XMAS[2] && safeGet(x, y + 3) == XMAS[3],
            safeGet(x + 1, y + 1) == XMAS[1] && safeGet(x + 2, y + 2) == XMAS[2] && safeGet(x + 3, y + 3) == XMAS[3]
        ).count { it }

    private fun List<List<Char>>.check2(x: Int, y: Int) =
        (safeGet(x - 1, y - 1) == 'M' && safeGet(x + 1, y + 1) == 'S' ||
                safeGet(x - 1, y - 1) == 'S' && safeGet(x + 1, y + 1) == 'M'
                ) && (
                safeGet(x - 1, y + 1) == 'M' && safeGet(x + 1, y - 1) == 'S' ||
                        safeGet(x - 1, y + 1) == 'S' && safeGet(x + 1, y - 1) == 'M'
                )
}