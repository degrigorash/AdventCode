package year2016

import tools.readFileText
import kotlin.math.abs

fun main() {
    Day1().part1()
    Day1().part2()
}

class Day1 {

    private val input = readFileText("year2016/day1input").split(", ").map { it.trim() }
    private var result = 0
    private var angle = 90
    private var coordinates = MutablePoint(0, 0)
    private val locations = mutableSetOf<MutablePoint>(MutablePoint(0, 0))

    fun part1() {
        input.forEach {
            if (it[0] == 'R') angle -= 90 else angle += 90
            val value = it.substring(1).toInt()
            when {
                angle == 0 || angle % 360 == 0 -> coordinates.x += value
                angle % 180 == 0 -> coordinates.x -= value
                angle % 360 == -270 || angle % 360 == 90 -> coordinates.y += value
                angle % 360 == -90 || angle % 360 == 270 -> coordinates.y -= value
            }
        }
        result = abs(coordinates.x) + abs(coordinates.y)
        println("part1: $result")
    }

    fun part2() {
        run loop@{
            input.forEachIndexed { index, it ->
                if (it[0] == 'R') angle -= 90 else angle += 90
                val value = it.substring(1).toInt()
                when {
                    angle == 0 || angle % 360 == 0 -> {
                        (1..value).forEach { v ->
                            coordinates.x += 1
                            if (!locations.add(coordinates.copy())) {
                                result = abs(coordinates.x) + abs(coordinates.y)
                                return@loop
                            }
                        }
                    }

                    angle % 180 == 0 -> {
                        (1..value).forEach { v ->
                            coordinates.x -= 1
                            if (!locations.add(coordinates.copy())) {
                                result = abs(coordinates.x) + abs(coordinates.y)
                                return@loop
                            }
                        }
                    }

                    angle % 360 == -270 || angle % 360 == 90 -> {
                        (1..value).forEach { v ->
                            coordinates.y += 1
                            if (!locations.add(coordinates.copy())) {
                                result = abs(coordinates.x) + abs(coordinates.y)
                                return@loop
                            }
                        }
                    }

                    angle % 360 == -90 || angle % 360 == 270 -> {
                        (1..value).forEach { v ->
                            coordinates.y -= 1
                            if (!locations.add(coordinates.copy())) {
                                result = abs(coordinates.x) + abs(coordinates.y)
                                return@loop
                            }
                        }
                    }
                }
            }
        }
        println("part2: $result")
    }

    data class MutablePoint(var x: Int, var y: Int)
}