package year2015

import tools.readFile
import kotlin.math.max

fun main() {
    Day15().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day15 {

    private val input = readFile("year2015/day15input")
    private val data = input.map {
        listOf(
            it.substringAfter("capacity ").substringBefore(",").toLong(),
            it.substringAfter("durability ").substringBefore(",").toLong(),
            it.substringAfter("flavor ").substringBefore(",").toLong(),
            it.substringAfter("texture ").substringBefore(",").toLong(),
            it.substringAfter("calories ").substringBefore(",").toLong()
        )
    }

    fun part1() {
        var result = 1L
        // :'D
        (1..100).forEach { i1 ->
            (1..100).forEach { i2 ->
                (1..100).forEach { i3 ->
                    (1..100).forEach { i4 ->
                        if (i1 + i2 + i3 + i4 == 100) {
                            var res = 1L
                            0.until(data[0].size - 1).forEach { index ->
                                var sum =
                                    data[0][index] * i1 + data[1][index] * i2 + data[2][index] * i3 + data[3][index] * i4
                                if (sum <= 0) sum = 0
                                res *= sum
                            }
                            result = max(result, res)
                        }
                    }
                }
            }
        }
        println(result)
    }

    fun part2() {
        var result = 1L
        // :'D
        (1..100).forEach { i1 ->
            (1..100).forEach { i2 ->
                (1..100).forEach { i3 ->
                    (1..100).forEach { i4 ->
                        if (i1 + i2 + i3 + i4 == 100) {
                            val calories =
                                data[0][data[0].size - 1] * i1 + data[1][data[0].size - 1] * i2 + data[2][data[0].size - 1] * i3 + data[3][data[0].size - 1] * i4
                            if (calories == 500L) {
                                var res = 1L
                                0.until(data[0].size - 1).forEach { index ->
                                    var sum =
                                        data[0][index] * i1 + data[1][index] * i2 + data[2][index] * i3 + data[3][index] * i4
                                    if (sum <= 0) sum = 0
                                    res *= sum
                                }
                                result = max(result, res)
                            }
                        }
                    }
                }
            }
        }
        println(result)
    }
}