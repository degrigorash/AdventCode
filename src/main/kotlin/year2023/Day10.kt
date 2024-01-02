package year2023

import tools.Directions
import tools.readFile
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

fun main() {
    Day10().part1()
    Day10().part2()
}

class Day10 {

    private val lines = readFile("year2023/day10input")
    private val map = lines.map { it.map { it } }
    
    fun part1() {
        var startLocation: Pair<Int, Int> = 0 to 0
        map.forEachIndexed { index, chars ->
            chars.forEachIndexed { index2, c ->
                if (c == 'S') {
                    startLocation = index to index2
                }
            }
        }

        var location = startLocation.first + 1 to startLocation.second
        var steps = 1
        var direction = Directions.DOWN
        while(location != startLocation) {
            when(map[location.first][location.second]) {
                '|' -> {
                    location = if(direction == Directions.DOWN) {
                        location.first + 1 to location.second
                    } else {
                        location.first - 1 to location.second
                    }
                }
                '-' -> {
                    location = if(direction == Directions.RIGHT) {
                        location.first to location.second + 1
                    } else {
                        location.first to location.second - 1
                    }
                }
                'L' -> {
                    location = if(direction == Directions.DOWN) {
                        direction = Directions.RIGHT
                        location.first to location.second + 1
                    } else {
                        direction = Directions.UP
                        location.first - 1 to location.second
                    }
                }
                'J' -> {
                    location = if(direction == Directions.DOWN) {
                        direction = Directions.LEFT
                        location.first to location.second - 1
                    } else {
                        direction = Directions.UP
                        location.first - 1 to location.second
                    }
                }
                '7' -> {
                    location = if(direction == Directions.RIGHT) {
                        direction = Directions.DOWN
                        location.first + 1 to location.second
                    } else {
                        direction = Directions.LEFT
                        location.first to location.second - 1
                    }
                }
                'F' -> {
                    location = if(direction == Directions.LEFT) {
                        direction = Directions.DOWN
                        location.first + 1 to location.second
                    } else {
                        direction = Directions.RIGHT
                        location.first to location.second + 1
                    }
                }
                else -> {
                    println("Error")
                    break
                }
            }
            steps++
        }
        println(steps / 2)
    }

    fun part2() {
        var startLocation: Pair<Int, Int> = 0 to 0
        map.forEachIndexed { index, chars ->
            chars.forEachIndexed { index2, c ->
                if (c == 'S') {
                    startLocation = index to index2
                }
            }
        }
        val path = mutableListOf<Pair<Int,Int>>()

        var location = startLocation.first + 1 to startLocation.second
        var direction = Directions.DOWN
        while(location != startLocation) {
            path.add(location)
            when(map[location.first][location.second]) {
                '|' -> {
                    location = if(direction == Directions.DOWN) {
                        location.first + 1 to location.second
                    } else {
                        location.first - 1 to location.second
                    }
                }
                '-' -> {
                    location = if(direction == Directions.RIGHT) {
                        location.first to location.second + 1
                    } else {
                        location.first to location.second - 1
                    }
                }
                'L' -> {
                    location = if(direction == Directions.DOWN) {
                        direction = Directions.RIGHT
                        location.first to location.second + 1
                    } else {
                        direction = Directions.UP
                        location.first - 1 to location.second
                    }
                }
                'J' -> {
                    location = if(direction == Directions.DOWN) {
                        direction = Directions.LEFT
                        location.first to location.second - 1
                    } else {
                        direction = Directions.UP
                        location.first - 1 to location.second
                    }
                }
                '7' -> {
                    location = if(direction == Directions.RIGHT) {
                        direction = Directions.DOWN
                        location.first + 1 to location.second
                    } else {
                        direction = Directions.LEFT
                        location.first to location.second - 1
                    }
                }
                'F' -> {
                    location = if(direction == Directions.LEFT) {
                        direction = Directions.DOWN
                        location.first + 1 to location.second
                    } else {
                        direction = Directions.RIGHT
                        location.first to location.second + 1
                    }
                }
                else -> {
                    println("Error")
                    break
                }
            }
        }

        println(
            pick(
                a = shoelace(path),
                b = path.size
            )
        )
    }

    // Shoelace formula
    private fun shoelace(path: List<Pair<Int, Int>>): Int {
        var result = 0

        (0..path.size - 2).forEach {
            result += path[it].first * path[it + 1].second
        }
        result += path.last().first * path.first().second

        (0..path.size - 2).forEach {
            result -= path[it + 1].first * path[it].second
        }
        result -= path.first().first * path.last().second

        return result.absoluteValue / 2
    }

    // Pick's theorem
    // A = i + b / 2 - 1
    private fun pick(
        a: Int, // area
        b: Int // boundary points
    ): Int {
        return a - (b / 2f).roundToInt() + 1
    }
}