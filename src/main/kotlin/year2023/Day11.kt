package year2023

import tools.readFile
import java.lang.Integer.max
import kotlin.math.absoluteValue
import kotlin.math.min

fun main() {
    Day11().part1()
    Day11().part2()
}

class Day11 {

    private val lines = readFile("year2023/day11Input")

    fun part1() {
        val mapPart1 = mutableListOf<List<Char>>()
        lines.forEach { s ->
            mapPart1.add(s.toList())
            if (s.all { it == '.' }) mapPart1.add(s.toList())
        }

        val map = MutableList<MutableList<Char>>(mapPart1.size) { mutableListOf() }
        mutableListOf<List<Char>>()
        (lines[0].indices).forEach { i ->
            val column = mapPart1.map { it[i] }
            val double = column.all { it == '.' }
            map.forEachIndexed { index, chars ->
                chars.add(column[index])
                if (double) chars.add(column[index])
            }
        }

        val coordinates = mutableListOf<Pair<Int, Int>>()
        map.forEachIndexed { index, s ->
            s.forEachIndexed { i, c ->
                if (c == '#') coordinates.add(Pair(index, i))
            }
        }

        var sum = 0
        coordinates.forEachIndexed { index, coordinate ->
            coordinates.forEachIndexed { i, it ->
                if (i > index) {
                    sum += (it.first - coordinate.first).absoluteValue + (it.second - coordinate.second).absoluteValue
                }
            }
        }
        println(sum)
    }

    fun part2() {
        val million = 1000000
        val map = lines.map { it.toList() }
        val vertical = map.mapIndexedNotNull { index, chars ->
            if (chars.all { it == '.' }) index else null
        }
        val horizontal = mutableListOf<Int>()
        map[0].indices.forEach { i ->
            val column = map.map { it[i] }
            if (column.all { it == '.' }) horizontal.add(i)
        }
        horizontal.forEach { println(it) }

        val coordinates = mutableListOf<Pair<Int, Int>>()
        map.forEachIndexed { index, s ->
            s.forEachIndexed { i, c ->
                if (c == '#') coordinates.add(Pair(index, i))
            }
        }

        var sum = 0L
        coordinates.forEachIndexed { index, coordinate ->
            coordinates.forEachIndexed { i, it ->
                if (i > index) {
                    sum += (it.first - coordinate.first).absoluteValue + (it.second - coordinate.second).absoluteValue
                    horizontal.forEach { h ->
                        if (h > min(it.second, coordinate.second) &&
                            h < max(it.second, coordinate.second)
                        ) sum += million - 1
                    }
                    vertical.forEach { v ->
                        if (v > min(it.first, coordinate.first) &&
                            v < max(it.first, coordinate.first)
                        ) sum += million - 1
                    }
                }
            }
        }
        println(sum)
    }
}