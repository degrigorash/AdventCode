package year2016

import tools.readFile
import java.util.Collections
import kotlin.collections.forEach

fun main() {
    Day8().part1()
    Day8().part2()
}

class Day8 {

    private val input = readFile("year2016/day8input").map { it.split(" ") }
//    private val input = readFile("test").map { it.split(" ") }
    private var result = 0

        private val display = List(6) { MutableList(50) { false } }
//    private val display = List(6) { MutableList(6) { false } }

    fun part1() {
        input.forEach { inst ->
            when {
                inst[0] == "rect" -> {
                    val x = inst[1].split("x")[0].toInt()
                    val y = inst[1].split("x")[1].toInt()
                    (0 until y).forEach { yy ->
                        (0 until x).forEach { xx ->
                            display[yy][xx] = true
                        }
                    }
                }

                inst[0] == "rotate" && inst[1] == "row" -> {
                    val y = inst[2].split("=")[1].toInt()
                    Collections.rotate(display[y], inst.last().toInt())
                }

                inst[0] == "rotate" && inst[1] == "column" -> {
                    val x = inst[2].split("=")[1].toInt()
                    val temp = mutableListOf<Boolean>()
                    display.indices.forEach {
                        temp.add(display[it][x])
                    }
                    Collections.rotate(temp, inst.last().toInt())
                    display.indices.forEach {
                        display[it][x] = temp[it]
                    }
                }
            }
        }
        display.forEach {
            result += it.filter { it }.size
        }
        println("part1: $result")
    }

    fun part2() {
        part1()
        val new = display.map {
            it.map { if (it) "#" else "." }
        }
        new.forEach { println(it) }
    }
}