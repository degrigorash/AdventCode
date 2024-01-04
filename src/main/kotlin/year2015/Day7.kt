package year2015

import tools.readFile

fun main() {
    Day7().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day7 {

    internal sealed class Input {
        data class Value(val value: Int) : Input()
        data class Wire(
            val operation: String?,
            val left: String?,
            val right: String
        ) : Input()
    }

    private val input = readFile("year2015/day7input")
    private val scheme = input.associate {
        val (left, right) = it.split(" -> ")
        right to (left.toIntOrNull()?.let { Input.Value(it) } ?: when {
            left.contains("NOT") -> {
                Input.Wire("NOT", null, left.split(" ")[1])
            }

            left.contains("AND") -> {
                Input.Wire("AND", left.split(" AND ")[0], left.split(" AND ")[1])
            }

            left.contains("OR") -> {
                Input.Wire("OR", left.split(" OR ")[0], left.split(" OR ")[1])
            }

            left.contains("LSHIFT") -> {
                Input.Wire("LSHIFT", left.split(" LSHIFT ")[0], left.split(" LSHIFT ")[1])
            }

            left.contains("RSHIFT") -> {
                Input.Wire("RSHIFT", left.split(" RSHIFT ")[0], left.split(" RSHIFT ")[1])
            }

            else -> {
                Input.Wire(null, null, left)
            }
        })
    }
    private val cache = mutableMapOf<String, Int>()

    fun part1() {
        println(scheme.grab("a"))
    }

    fun part2() {
        val aInput = scheme.grab("a")
        cache.clear()
        cache["b"] = aInput
        println(scheme.grab("a"))
    }

    private fun Map<String, Input>.grab(wire: String): Int {
        if (cache.containsKey(wire)) {
            return cache[wire]!!
        }
        return wire.toIntOrNull() ?: when (val input = this[wire]!!) {
            is Input.Value -> input.value
            is Input.Wire -> when (input.operation) {
                "NOT" -> {
                    grab(input.right).inv()
                }

                "AND" -> {
                    grab(input.left!!) and grab(input.right)
                }

                "OR" -> {
                    grab(input.left!!) or grab(input.right)
                }

                "LSHIFT" -> {
                    grab(input.left!!) shl input.right.toInt()
                }

                "RSHIFT" -> {
                    grab(input.left!!) shr input.right.toInt()
                }

                else -> {
                    grab(input.right)
                }
            }
        }.apply {
            cache[wire] = this
        }
    }
}