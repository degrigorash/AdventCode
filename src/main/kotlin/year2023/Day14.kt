package year2023

import tools.readFile
import tools.rotate90right

fun main() {
    Day14().part1()
    Day14().part2()
}

class Day14 {

    private val lines = readFile("year2023/day14Input")
    private val map = lines.map { it.toList() }

    fun part1() {
        val tiltedMap = MutableList<MutableList<Char>>(map.size) { mutableListOf() }
        (0.until(map[0].size)).forEach { column ->
            var rounds = 0
            var rock = -1
            map.indices.forEach { row ->
                when {
                    row == map.size - 1 -> {
                        if (map[row][column] == 'O') rounds++
                        ((rock + 1).until(rock + 1 + rounds)).forEach {
                            tiltedMap[it].add('O')
                        }
                        ((rock + 1 + rounds).until(row)).forEach {
                            tiltedMap[it].add('.')
                        }

                        if (tiltedMap[row].size == column) {
                            if (map[row][column] == '#') {
                                tiltedMap[row].add('#')
                            } else {
                                tiltedMap[row].add('.')
                            }
                        }
                    }

                    map[row][column] == '#' -> {
                        ((rock + 1).until(rock + 1 + rounds)).forEach {
                            tiltedMap[it].add('O')
                        }
                        ((rock + 1 + rounds).until(row)).forEach {
                            tiltedMap[it].add('.')
                        }
                        tiltedMap[row].add('#')

                        rock = row
                        rounds = 0
                    }

                    map[row][column] == 'O' -> rounds++
                    else -> {}
                }
            }

        }

        tiltedMap.forEachIndexed { index, chars -> println("$index: $chars") }
        var sum = 0
        tiltedMap.forEachIndexed { index, chars ->
            sum += chars.count { it == 'O' } * (tiltedMap.size - index)
        }
        println(sum)
    }

    fun part2() {
        var crazyMap: List<List<Char>> = map
        val mapomap: MutableList<List<List<Char>>> = mutableListOf()
        var repeats = 0
        run breaking@{
            repeat((0..1000000000).count()) { big ->
                repeat((0..3).count()) {
                    val tiltedMap =
                        MutableList<MutableList<Char>>(crazyMap.size) { mutableListOf() }
                    (0.until(crazyMap[0].size)).forEach { column ->
                        var rounds = 0
                        var rock = -1
                        crazyMap.indices.forEach { row ->
                            when {
                                row == crazyMap.size - 1 -> {
                                    if (crazyMap[row][column] == 'O') rounds++
                                    ((rock + 1).until(rock + 1 + rounds)).forEach {
                                        tiltedMap[it].add('O')
                                    }
                                    ((rock + 1 + rounds).until(row)).forEach {
                                        tiltedMap[it].add('.')
                                    }

                                    if (tiltedMap[row].size == column) {
                                        if (crazyMap[row][column] == '#') {
                                            tiltedMap[row].add('#')
                                        } else {
                                            tiltedMap[row].add('.')
                                        }
                                    }
                                }

                                crazyMap[row][column] == '#' -> {
                                    ((rock + 1).until(rock + 1 + rounds)).forEach {
                                        tiltedMap[it].add('O')
                                    }
                                    ((rock + 1 + rounds).until(row)).forEach {
                                        tiltedMap[it].add('.')
                                    }
                                    tiltedMap[row].add('#')

                                    rock = row
                                    rounds = 0
                                }

                                crazyMap[row][column] == 'O' -> rounds++
                                else -> {}
                            }
                        }
                    }
                    crazyMap = tiltedMap.rotate90right()
                }
                mapomap.add(crazyMap)
                if (repeats == mapomap.groupingBy { it }.eachCount().size) {
                    val repeatIndex = mapomap.groupingBy { it }.eachCount().map { it.value }
                        .indexOfFirst { it == 2 }
                    val finalCount = (1000000000 - big - 1) % (big - repeatIndex)
                    crazyMap = mapomap[repeatIndex + finalCount]

//                    mapomap.groupingBy { it }.eachCount().map {
//                        var sum = 0
//                        it.key.forEachIndexed { index, chars ->
//                            sum += chars.count { it == 'O' } * (it.key.size - index)
//                        }
//                        sum
//                    }.forEach { println(it) }
//                    println()

//                    mapomap.groupingBy { it }.eachCount().map {
//                        it.key
//                    }.forEach {
//                        it.forEach { println(it) }
//                        println()
//                    }
                    return@breaking
                }

                repeats = mapomap.groupingBy { it }.eachCount().size
            }
        }

        var sum = 0
        crazyMap.forEachIndexed { index, chars ->
            sum += chars.count { it == 'O' } * (crazyMap.size - index)
        }
        println(sum)
    }
}