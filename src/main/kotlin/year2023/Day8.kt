package year2023

import tools.readFile

fun main() {
    Day8().part1()
    Day8().part2()
}

class Day8 {

    private val lines = readFile("year2023/day8input")
    private val steps = lines[0].map {
        it
    }
    private val nodes = lines.subList(2, lines.size).map {
        it.substring(0, 3) to (it.substring(7, 10) to it.substring(12, 15))
    }

    
    fun part1() {
        var currentNode = nodes.find { it.first == "AAA" }!!
        var currentStep = 0
        var result = 0
        while (currentNode.first != "ZZZ") {
            if (currentStep == steps.size) {
                currentStep = 0
            }
            val nextNode = if (steps[currentStep] == 'L') {
                currentNode.second.first
            } else {
                currentNode.second.second
            }
            currentNode = nodes.find { it.first == nextNode }!!
            currentStep++
            result++
        }
        println(result)
    }

    
    fun part2() {
        var startNodes = nodes.filter {
            it.first.endsWith("A")
        }

        nodes.first()

        var currentStep = 0
        var result = 1L
        var end = false
        val zs = mutableListOf(*startNodes.map { 0L }.toTypedArray())

        while (!end) {
            if (currentStep == steps.size) {
                currentStep = 0
            }

            val newNodes = mutableListOf<Pair<String, Pair<String, String>>>()
            startNodes.forEach {
                val nextNode = if (steps[currentStep] == 'L') {
                    it.second.first
                } else {
                    it.second.second
                }
                newNodes.add(nodes.find { it.first == nextNode }!!)
            }
            startNodes = newNodes
            startNodes.forEachIndexed { index, pair ->
                if (pair.first.endsWith("Z") && zs[index] == 0L) {
                    zs[index] = result
                }
            }
            if (zs.all { it != 0L }) {
                end = true
            }

            currentStep++
            result++
        }

        println(zs)
        println(zs.reduce { acc, i -> lcm(acc, i) })
    }

    private fun lcm(a: Long, b: Long): Long {
        var ma = a
        var mb = b
        var remainder: Long

        while (mb != 0L) {
            remainder = ma % mb
            ma = mb
            mb = remainder
        }

        return a * b / ma
    }
}