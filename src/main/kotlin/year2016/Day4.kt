package year2016

import tools.readFile
import kotlin.comparisons.compareBy
import kotlin.math.max
import kotlin.math.min
import kotlin.text.isNotBlank
import kotlin.text.split

fun main() {
    Day4().part1()
    Day4().part2()
}

class Day4 {

    private val input = readFile("year2016/day4input")

    //    private val input = readFile("test")
    private val rooms = input.map {
        val temp = it.split("-")
        val last = temp.last()
        Room(
            names = temp.dropLast(1),
            id = last.substring(0, last.indexOf("[")).toInt(),
            checksum = last.substring(last.indexOf("[") + 1, last.length - 1)
        )
    }
    private var result = 0

    fun part1() {
        rooms.forEach { room ->
            val letters = room.names.map { it.map { it } }.flatten()
            val sets = mutableSetOf<Pair<Char, Int>>()
            letters.toSet().forEach { letter ->
                sets.add(letter to letters.filter { it == letter }.size)
            }
            val res = sets.sortedWith(
                compareByDescending<Pair<Char, Int>> { it.second }.thenBy { it.first }
            ).take(5).map { it.first }.joinToString("")
            if (res == room.checksum) result += room.id
        }
        println("part1: $result")
    }

    fun part2() {
        val alph = ('a'.code..'z'.code).toList() // 97 to 122
        rooms.forEach { room ->
            val realNames = room.names.map { name ->
                name.map {
                    var real = it.code + (room.id % alph.size)
                    if (real > alph.last()) real -= alph.size
                    real.toChar()
                }.joinToString("")
            }.joinToString(" ")
            if (realNames == "northpole object storage") result = room.id
        }
        println("part2: $result")
    }

    private data class Room(
        val names: List<String>,
        val id: Int,
        val checksum: String
    )


}