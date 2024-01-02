package year2023

import tools.readFile
import kotlin.math.pow

fun main() {
    Day12().part1()
    Day12().part2()
}

class Day12 {

    private val lines = readFile("year2023/day12Input")
    private val regexStart = "^\\.*"
    private val regexEnd = "\\.*$"

    fun part1() {
        var count = 0

        lines.forEach { input ->
            val line = input.substring(0, input.indexOf(' '))
            val damaged = input.substring(input.indexOf(' ') + 1, input.length).split(',')
            var regex = regexStart
            damaged.forEachIndexed { index, s ->
                regex += if (index != damaged.size - 1) "#{$s}\\.+"
                else "#{$s}"
            }
            regex += regexEnd
            val reg = regex.toRegex()
            val unknowns = line.count { it == '?' }
            val steps = 2.0.pow(unknowns.toDouble()).toInt()
            val missingDamaged = damaged.sumOf { it.toInt() } - line.count { it == '#' }
            (0 until steps).forEach {
                val step = it.toString(2).padStart(unknowns, '0')
                if (step.count { it == '1' } == missingDamaged) {
                    var scheme = line
                    step.forEach {
                        scheme = scheme.replaceFirst('?', if (it == '0') '.' else '#')
                    }
                    if (reg.matches(scheme)) count++
                }
            }
        }

        println(count)
    }

    
    fun part2() {
        var count = 0L
        lines.forEach { input ->
            val lineInit = input.substring(0, input.indexOf(' '))
            var line = ""
            (0..4).forEach {
                line += if (it != 4) "$lineInit?" else lineInit
            }
            val damagedInit = input.substring(input.indexOf(' ') + 1, input.length).split(',')
            val damaged = mutableListOf<String>()
            repeat((0..4).count()) {
                damaged.addAll(damagedInit)
            }
            //TODO
            var regex = regexStart
            damaged.forEachIndexed { index, s ->
                regex += if (index != damaged.size - 1) "#{$s}\\.+"
                else "#{$s}"
            }
            regex += regexEnd
            val reg = regex.toRegex()
            val unknowns = line.count { it == '?' }
            val steps = 2.0.pow(unknowns.toDouble()).toInt()
            val missingDamaged = damaged.sumOf { it.toInt() } - line.count { it == '#' }
//            (0 until steps).forEach {
//                val step = it.toString(2).padStart(unknowns, '0')
//                if(step.count { it == '1' } == missingDamaged) {
//                    var scheme = line
//                    step.forEach {
//                        scheme = scheme.replaceFirst('?', if (it == '0') '.' else '#')
//                    }
//                    if (reg.matches(scheme)) count++
//                }
//            }
        }

        println(count)
    }
}