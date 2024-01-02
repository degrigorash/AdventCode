package year2023

import tools.Directions
import tools.readFile

fun main() {
    Day16().part1()
    Day16().part2()
}

class Day16 {

//    private val lines = readFile("test")
    private val lines = readFile("year2023/day16Input")
    private val map = lines.map { it.toList() }
    private var tiles = map.map { it.map { mutableSetOf<Directions>() } }
    private var direction = Directions.RIGHT
    private var loc = 0 to -1
    private var splits = mutableSetOf(loc to direction)

    fun part1() {
        while (splits.isNotEmpty()) {
            val split = splits.first()
            loc = split.first
            direction = split.second
            splits.remove(split)
            runBeam()
        }
        var sum = 0
        tiles.forEach {
            it.forEach {
                if (it.isNotEmpty()) sum++
            }
        }
        println(sum)
    }

    
    fun part2() {
        val powers = mutableListOf<Int>()

        fun run() {
            while (splits.isNotEmpty()) {
                val split = splits.first()
                loc = split.first
                direction = split.second
                splits.remove(split)
                runBeam()
            }
            var sum = 0
            tiles.forEach {
                it.forEach {
                    if (it.isNotEmpty()) sum++
                }
            }
            powers.add(sum)
            tiles = map.map { it.map { mutableSetOf() } }
        }

        (0.until(map[0].size)).forEach {
            splits = mutableSetOf((-1 to it) to Directions.DOWN)
            run()
        }
        (0.until(map[0].size)).forEach {
            splits = mutableSetOf((map.size to it) to Directions.UP)
            run()
        }
        repeat(map.indices.count()) {
            splits = mutableSetOf((it to -1) to Directions.RIGHT)
            run()
        }
        repeat(map.indices.count()) {
            splits = mutableSetOf((it to map.size) to Directions.LEFT)
            run()
        }

        println(powers.max())
    }

    private fun runBeam() {
        while (true) {
            loc = when (direction) {
                Directions.UP -> loc.first - 1 to loc.second
                Directions.DOWN -> loc.first + 1 to loc.second
                Directions.RIGHT -> loc.first to loc.second + 1
                Directions.LEFT -> loc.first to loc.second - 1
            }
            if (loc.first >= map[0].size ||
                loc.second >= map.size ||
                loc.first < 0 ||
                loc.second < 0
            ) break
            if (!tiles[loc.first][loc.second].add(direction)) break

            when (map[loc.first][loc.second]) {
                '\\' -> {
                    direction = when (direction) {
                        Directions.UP -> Directions.LEFT
                        Directions.DOWN -> Directions.RIGHT
                        Directions.RIGHT -> Directions.DOWN
                        Directions.LEFT -> Directions.UP
                    }
                }

                '/' -> {
                    direction = when (direction) {
                        Directions.UP -> Directions.RIGHT
                        Directions.DOWN -> Directions.LEFT
                        Directions.RIGHT -> Directions.UP
                        Directions.LEFT -> Directions.DOWN
                    }
                }

                '|' -> {
                    direction = when (direction) {
                        Directions.UP -> Directions.UP
                        Directions.DOWN -> Directions.DOWN
                        Directions.RIGHT -> {
                            if (tiles[loc.first][loc.second].contains(Directions.LEFT)) break
                            splits.add(loc to Directions.DOWN)
                            Directions.UP
                        }

                        Directions.LEFT -> {
                            if (tiles[loc.first][loc.second].contains(Directions.RIGHT)) break
                            splits.add(loc to Directions.DOWN)
                            Directions.UP
                        }
                    }
                }

                '-' -> {
                    direction = when (direction) {
                        Directions.UP -> {
                            if (tiles[loc.first][loc.second].contains(Directions.DOWN)) break
                            splits.add(loc to Directions.LEFT)
                            Directions.RIGHT
                        }

                        Directions.DOWN -> {
                            if (tiles[loc.first][loc.second].contains(Directions.UP)) break
                            splits.add(loc to Directions.LEFT)
                            Directions.RIGHT
                        }

                        Directions.RIGHT -> Directions.RIGHT
                        Directions.LEFT -> Directions.LEFT
                    }
                }
            }
        }
    }
}