package year2015

import kotlin.math.sqrt

fun main() {
    Day20().apply {
        println("-=-=-=- part 1 -=-=-=-")
        part1()
        println("-=-=-=- part 2 -=-=-=-")
        part2()
    }
}

class Day20 {

    private val input = 34000000
    val houses = mutableMapOf<Int, Int>()

    fun part1() {
        var houseNumber = -1
        var currentHouse = 1
        do {
            var presents = 0
            getDivisors(currentHouse).forEach {
                presents += it * 10
            }
            if (presents >= input) houseNumber = currentHouse
            currentHouse++
        } while (houseNumber == -1)

        println(houseNumber)
    }

    fun part2() {
        var houseNumber = -1
        var currentHouse = 1
        do {
            var presents = 0
            getDivisors(currentHouse).filter { it > currentHouse / 50 }.forEach {
                presents += it * 11
            }
            if (presents >= input) houseNumber = currentHouse
            currentHouse++
        } while (houseNumber == -1)

        println(houseNumber)
    }

    fun getDivisors(n: Int): List<Int> {
        val divisors = mutableSetOf<Int>()
        val limit = sqrt(n.toDouble()).toInt()

        for (i in 1..limit) {
            if (n % i == 0) {
                divisors.add(i)
                divisors.add(n / i)
            }
        }
        return divisors.sorted()
    }
}