package year2015

import tools.findAllIndexed
import tools.readFile

fun main() {
    Day19().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day19 {

    private val input = readFile("year2015/day19input")
    private val theMolecule = input.last()
    private val map = input.filter { it.contains("=>") }.map {
        it.substringBefore(" =>") to it.substringAfter("=> ")
    }

    fun part1() {
        val molecules = mutableSetOf<String>()
        map.forEach {
            theMolecule.findAllIndexed(it.first).forEach { index ->
                molecules.add(theMolecule.replaceRange(index, index + it.first.length, it.second))
            }
        }
        println(molecules.size)
    }

    fun part2() {
        val structure = theMolecule.replace("Rn", "(")
            .replace("Y", ",")
            .replace("Ar", ")")
        val rnar = structure.count { it == '(' || it == ')' }
        val comma = structure.count { it == ',' }
        println(structure.length - rnar - 2 * comma - 1)
        // :(
    }
}