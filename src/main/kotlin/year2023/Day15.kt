package year2023

import tools.readFile

fun main() {
    Day15().part1()
    Day15().part2()
}

class Day15 {

    private val text = readFile("year2023/day15Input")
    private val steps = text[0].split(',')
    
    fun part1() {
        println(
            steps.map { it.magicHash() }.sumOf { it }
        )
    }

    
    fun part2() {
        val boxes = MutableList<MutableList<Pair<String, String>>>(256) { mutableListOf() }
        steps.forEach { step ->
            val operation = step.first { it == '=' || it == '-' }
            val label = step.substring(0, step.indexOf(operation))
            val focus = step.substring(step.indexOf(operation) + 1, step.length)

            val box = boxes[label.magicHash()]
            val lensIndex = box.indexOfFirst { it.first == label }
            if (operation == '=') {
                if (lensIndex != -1) box[lensIndex] = label to focus else box.add(label to focus)
            } else {
                if (lensIndex != -1) box.removeAt(lensIndex)
            }
        }

        var sum = 0L
        boxes.forEachIndexed { index, box ->
            box.forEachIndexed { i, lens ->
                sum += (index + 1) * (i + 1) * lens.second.toInt()
            }
        }
        println(sum)
    }

    private fun String.magicHash(): Int {
        var hash = 0
        forEach {
            hash += it.code
            hash *= 17
            hash %= 256
        }
        return hash
    }
}