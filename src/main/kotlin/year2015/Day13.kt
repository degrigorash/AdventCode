package year2015

import tools.readFile
import kotlin.math.max

fun main() {
    Day13().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day13 {

    private val input = readFile("year2015/day13input")
    private val names = mutableSetOf<String>()
    private val data = input.associate {
        val name = it.substring(0, it.indexOf(" "))
        val value = it.filter { it.isDigit() }.toInt()
        val happy = it.contains("gain")
        val partner = it.substring(it.lastIndexOf(" ") + 1, it.length - 1)
        names.add(name)
        (name to partner) to (happy to value)
    }

    fun part1() {
        var optimal = 0
        permutations(names.toList()).forEach { permutation ->
            var sum = 0
            permutation.forEachIndexed { i, name ->
                val partner1 = if (i == 0) permutation.last() else permutation[i - 1]
                val partner2 = if (i == permutation.lastIndex) permutation.first() else permutation[i + 1]
                data[name to partner1]!!.let {
                    if (it.first) sum += it.second else sum -= it.second
                }
                data[name to partner2]!!.let {
                    if (it.first) sum += it.second else sum -= it.second
                }
            }
            optimal = max(optimal, sum)
        }
        println(optimal)
    }

    fun part2() {
        var optimal = 0
        permutations(names.toList() + "me").forEach { permutation ->
            var sum = 0
            permutation.forEachIndexed { i, name ->
                val partner1 = if (i == 0) permutation.last() else permutation[i - 1]
                val partner2 = if (i == permutation.lastIndex) permutation.first() else permutation[i + 1]
                data[name to partner1]?.let {
                    if (it.first) sum += it.second else sum -= it.second
                }
                data[name to partner2]?.let {
                    if (it.first) sum += it.second else sum -= it.second
                }
            }
            optimal = max(optimal, sum)
        }
        println(optimal)
    }

    private fun permutations(list: List<String>): List<List<String>> {
        if (list.size == 1) return listOf(list)
        val perms = mutableListOf<List<String>>()
        val sub = list[0]
        for (perm in permutations(list.drop(1))) {
            for (i in 0..perm.size) {
                val newPerm = perm.toMutableList()
                newPerm.add(i, sub)
                perms.add(newPerm)
            }
        }
        return perms
    }
}