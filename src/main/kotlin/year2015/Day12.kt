package year2015

import kotlinx.serialization.json.*
import tools.readFileText

fun main() {
    Day12().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day12 {

    private val input = readFileText("year2015/day12input")

    fun part1map() {
        val digits = input.map { if (it == '-') it else it.digitToIntOrNull() }
        var sum = 0
        var number = ""
        var plus = true
        digits.forEach {
            when (it) {
                '-' -> plus = false
                null -> {
                    if (number.isNotEmpty()) {
                        if (plus) sum += number.toInt() else sum -= number.toInt()
                        number = ""
                        plus = true
                    }
                }

                else -> number += it
            }
        }

        println(sum)
    }

    fun part1() {
        val regex = Regex("-?\\d+")
        val numbers = regex.findAll(input).map { it.value.toInt() }.toList()
        println(numbers.sum())
    }

    fun part2() {
        val json = Json.decodeFromString<JsonArray>(input)
        println(json.proceed())
    }

    private fun JsonElement.proceed(): Int {
        return when (this) {
            is JsonArray -> {
                this.sumOf { it.proceed() }
            }

            is JsonObject -> {
                if (this.entries.any { it.value is JsonPrimitive && it.value.jsonPrimitive.content == "red" }) {
                    0
                } else {
                    this.entries.sumOf {
                        when {
                            it.value is JsonPrimitive && it.value.jsonPrimitive.intOrNull != null -> {
                                it.value.jsonPrimitive.int
                            }

                            it.value is JsonArray -> it.value.jsonArray.sumOf { it.proceed() }

                            it.value is JsonObject -> it.value.proceed()
                            else -> 0
                        }
                    }
                }
            }

            is JsonPrimitive -> this.jsonPrimitive.intOrNull ?: 0

            else -> {
                0
            }
        }
    }
}