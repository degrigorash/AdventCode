package year2015

import tools.readFile

fun main() {
    Day18().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day18 {

    private val input = readFile("year2015/day18input")
    private val map = input.map { it.map { it == '#' } }

    fun part1() {
        var myMap = map
        (0..99).forEach {
            val newMap = MutableList(100) { MutableList(100) { false } }
            myMap.forEachIndexed { y, lights ->
                lights.forEachIndexed { x, light ->
                    val neighbors = listOf(
                        myMap.getOrNull(y - 1)?.getOrNull(x - 1) ?: false,
                        myMap.getOrNull(y - 1)?.getOrNull(x) ?: false,
                        myMap.getOrNull(y - 1)?.getOrNull(x + 1) ?: false,
                        myMap.getOrNull(y)?.getOrNull(x - 1) ?: false,
                        myMap.getOrNull(y)?.getOrNull(x + 1) ?: false,
                        myMap.getOrNull(y + 1)?.getOrNull(x - 1) ?: false,
                        myMap.getOrNull(y + 1)?.getOrNull(x) ?: false,
                        myMap.getOrNull(y + 1)?.getOrNull(x + 1) ?: false,
                    )
                    newMap[y][x] = if (myMap[y][x]) {
                        neighbors.count { it } == 2 || neighbors.count { it } == 3
                    } else {
                        neighbors.count { it } == 3
                    }
                }
            }
            myMap = newMap
        }
        println(myMap.sumOf { it.count { it } })
    }

    fun part2() {
        var myMap = map.map { it.toMutableList() }
        myMap[0][0] = true
        myMap[0][99] = true
        myMap[99][0] = true
        myMap[99][99] = true
        repeat(100) {
            val newMap = MutableList(100) { MutableList(100) { false } }
            myMap.forEachIndexed { y, lights ->
                lights.forEachIndexed { x, light ->
                    val neighbors = listOf(
                        myMap.getOrNull(y - 1)?.getOrNull(x - 1) ?: false,
                        myMap.getOrNull(y - 1)?.getOrNull(x) ?: false,
                        myMap.getOrNull(y - 1)?.getOrNull(x + 1) ?: false,
                        myMap.getOrNull(y)?.getOrNull(x - 1) ?: false,
                        myMap.getOrNull(y)?.getOrNull(x + 1) ?: false,
                        myMap.getOrNull(y + 1)?.getOrNull(x - 1) ?: false,
                        myMap.getOrNull(y + 1)?.getOrNull(x) ?: false,
                        myMap.getOrNull(y + 1)?.getOrNull(x + 1) ?: false,
                    )
                    newMap[y][x] = if (myMap[y][x]) {
                        neighbors.count { it } == 2 || neighbors.count { it } == 3
                    } else {
                        neighbors.count { it } == 3
                    }
                }
            }
            newMap[0][0] = true
            newMap[0][99] = true
            newMap[99][0] = true
            newMap[99][99] = true
            myMap = newMap
        }
        println(myMap.sumOf { it.count { it } })
    }
}