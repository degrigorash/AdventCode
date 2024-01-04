package year2015

import tools.readFileText

fun main() {
    Day3().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day3 {

    private val input = readFileText("year2015/day3input")

    fun part1() {
        val visited = mutableSetOf<Pair<Int, Int>>()
        var location = Pair(0, 0)
        visited.add(location)
        input.forEach {
            when (it) {
                '^' -> location = location.first to location.second - 1
                'v' -> location = location.first to location.second + 1
                '>' -> location = location.first + 1 to location.second
                '<' -> location = location.first - 1 to location.second
            }
            visited.add(location)
        }

        println(visited.size)
    }

    fun part2() {
        val visited = mutableSetOf<Pair<Int, Int>>()
        var santaLocation = Pair(0, 0)
        var roboLocation = Pair(0, 0)
        visited.add(santaLocation)
        visited.add(roboLocation)
        input.forEachIndexed { i, c ->
            when (c) {
                '^' -> {
                    if(i % 2 == 0) {
                        santaLocation = santaLocation.first to santaLocation.second - 1
                    } else {
                        roboLocation = roboLocation.first to roboLocation.second - 1
                    }
                }
                'v' -> {
                    if(i % 2 == 0) {
                        santaLocation = santaLocation.first to santaLocation.second + 1
                    } else {
                        roboLocation = roboLocation.first to roboLocation.second + 1
                    }
                }
                '>' -> {
                    if(i % 2 == 0) {
                        santaLocation = santaLocation.first + 1 to santaLocation.second
                    } else {
                        roboLocation = roboLocation.first + 1 to roboLocation.second
                    }
                }
                '<' -> {
                    if(i % 2 == 0) {
                        santaLocation = santaLocation.first - 1 to santaLocation.second
                    } else {
                        roboLocation = roboLocation.first - 1 to roboLocation.second
                    }
                }
            }
            visited.add(santaLocation)
            visited.add(roboLocation)
        }

        println(visited.size)
    }
}