package year2015


import tools.readFile

fun main() {
    Day6().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day6 {

    private val input = readFile("year2015/day6input")

    fun part1() {
        val grid = Array(1000) { Array(1000) { false } }

        input.forEach { line ->
            val end = line.reversed()
                .substringBefore(" ")
                .reversed()
                .split(",")
                .map { it.toInt() }
            val start = line.reversed()
                .substringAfter("t ")
                .substringBefore(" ")
                .reversed()
                .split(",")
                .map { it.toInt() }

            when {
                line.contains("turn on") -> {
                    (start[0]..end[0]).forEach { x ->
                        (start[1]..end[1]).forEach { y ->
                            grid[x][y] = true
                        }
                    }
                }

                line.contains("turn off") -> {
                    (start[0]..end[0]).forEach { x ->
                        (start[1]..end[1]).forEach { y ->
                            grid[x][y] = false
                        }
                    }
                }

                else -> {
                    (start[0]..end[0]).forEach { x ->
                        (start[1]..end[1]).forEach { y ->
                            grid[x][y] = !grid[x][y]
                        }
                    }
                }
            }
        }

        println(grid.sumOf { it.count { it } })
    }

    fun part2() {
        val grid = Array(1000) { Array(1000) { 0 } }

        input.forEach { line ->
            val end = line.reversed()
                .substringBefore(" ")
                .reversed()
                .split(",")
                .map { it.toInt() }
            val start = line.reversed()
                .substringAfter("t ")
                .substringBefore(" ")
                .reversed()
                .split(",")
                .map { it.toInt() }

            when {
                line.contains("turn on") -> {
                    (start[0]..end[0]).forEach { x ->
                        (start[1]..end[1]).forEach { y ->
                            grid[x][y] += 1
                        }
                    }
                }

                line.contains("turn off") -> {
                    (start[0]..end[0]).forEach { x ->
                        (start[1]..end[1]).forEach { y ->
                            grid[x][y] -= 1
                            if (grid[x][y] < 0) grid[x][y] = 0
                        }
                    }
                }

                else -> {
                    (start[0]..end[0]).forEach { x ->
                        (start[1]..end[1]).forEach { y ->
                            grid[x][y] += 2
                        }
                    }
                }
            }
        }

        println(grid.sumOf { it.sumOf { it } })
    }
}