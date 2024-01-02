package year2023

import tools.readFile
import kotlin.math.min

fun main() {
    Day13().part1()
    Day13().part2()
}

class Day13 {

//    private val lines = readFile("test")
    private val lines = readFile("year2023/day13Input")
    
    fun part1() {
        val patterns = mutableListOf<List<String>>()
        var partition = mutableListOf<String>()
        lines.forEachIndexed { index, s ->
            if (s.isNotEmpty()) {
                partition.add(s)
                if (index == lines.size - 1) {
                    patterns.add(partition)
                }
            } else {
                patterns.add(partition)
                partition = mutableListOf()
            }
        }

        var result = 0
        patterns.forEach { strings ->
            var sum = 0
            strings.indices.forEach {
                if (strings.areSameRows(it)) sum = (it + 1) * 100
            }

            if (sum == 0) {
                val turned = MutableList(strings[0].length) { "" }
                strings.forEach {
                    val str = it
                    str.forEachIndexed { i, c ->
                        turned[i] = turned[i] + c
                    }
                }
                turned.indices.forEach {
                    if (turned.areSameRows(it)) sum = it + 1
                }
            }

            result += sum
        }

        println(result)
    }

    fun part2() {
        val patterns = mutableListOf<List<String>>()
        var partition = mutableListOf<String>()
        lines.forEachIndexed { index, s ->
            if (s.isNotEmpty()) {
                partition.add(s)
                if (index == lines.size - 1) {
                    patterns.add(partition)
                }
            } else {
                patterns.add(partition)
                partition = mutableListOf()
            }
        }

        var result = 0
        patterns.forEachIndexed { _, strings ->
            strings.indices.forEach {
                if (strings.areSameRowsWithSmudge(it)) {
                    result += (it + 1) * 100
                    return@forEachIndexed
                }
            }

            val turned = MutableList(strings[0].length) { "" }
            strings.forEach {
                val str = it
                str.forEachIndexed { i, c ->
                    turned[i] = turned[i] + c
                }
            }
            turned.indices.forEach {
                if (turned.areSameRowsWithSmudge(it)) {
                    result += it + 1
                    return@forEachIndexed
                }
            }
        }

        println(result)
    }

    private fun List<String>.areSameRows(index: Int): Boolean {
        return if (this[index] == this.getOrNull(index + 1)) {
            val size = min(index + 1, this.size - index - 1)
            val top = this.subList(index + 1 - size, index + 1).reversed()
            val bottom = this.subList(index + 1, index + 1 + size)

            top == bottom
        } else {
            false
        }
    }

    private fun List<String>.areSameRowsWithSmudge(index: Int): Boolean {
        val size = min(index + 1, this.size - index - 1)
        val top = this.subList(index + 1 - size, index + 1).reversed().map { it.toList() }
        val bottom = this.subList(index + 1, index + 1 + size).map { it.toList() }

        var differences = 0
        top.indices.forEach { x ->
            var localDiff = 0
            top[x].indices.forEach { y ->
                if (top[x][y] != bottom[x][y]) localDiff++
            }
            differences += localDiff
        }
        return differences == 1
    }
}