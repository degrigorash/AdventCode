package year2015

import tools.readFile
import java.lang.Integer.max
import java.lang.Integer.min

fun main() {
    Day14().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day14 {

    private val input = readFile("year2015/day14input")
    private val deers = input.map {
        val speed = it.substringAfter("fly ").substringBefore(" km/s").toInt()
        val time = it.substringAfter("for ").substringBefore(" seconds").toInt()
        val rest = it.substringAfter("rest for ").substringBefore(" seconds.").toInt()
        Triple(speed, time, rest)
    }
    private val duration = 2503

    fun part1() {
        var result = 0
        deers.forEach { data ->
            with(data) {
                val step = first * second
                val partDuration = duration % (second + third)
                val fullCount = duration / (second + third)
                val distance = fullCount * step + first * min(partDuration, second)
                result = max(result, distance)
            }
        }
        println(result)
    }

    fun part2() {
        val distances = deers.map { 0 }.toMutableList()
        val scores = deers.map { 0 }.toMutableList()
        for (time in 0.until(duration)) {
            deers.forEachIndexed { i, data ->
                with(data) {
                    val partDuration = time % (second + third) + 1
                    if (partDuration <= second) distances[i] += first
                }
            }
            val max = distances.max()
            distances.forEachIndexed { i, distance ->
                if (distance == max) scores[i]++
            }
        }
        println(scores.max())
    }
}