package year2023

import tools.readFile

fun main() {
    Day7().part1()
    Day7().part2()
}

class Day7 {

    private val lines = readFile("year2023/day7input")
    private val handBids = lines.map { it.split(" ") }
    private val cards = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    private val cards2 = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')
    private val kind5 = mutableListOf<Pair<String, Long>>()
    private val kind4 = mutableListOf<Pair<String, Long>>()
    private val kindFull = mutableListOf<Pair<String, Long>>()
    private val kind3 = mutableListOf<Pair<String, Long>>()
    private val kind2pair = mutableListOf<Pair<String, Long>>()
    private val kind1pair = mutableListOf<Pair<String, Long>>()
    private val kindNone = mutableListOf<Pair<String, Long>>()

    inner class CardsComparator(private val cards: List<Char>) : Comparator<Pair<String, Long>> {
        override fun compare(o1: Pair<String, Long>?, o2: Pair<String, Long>?): Int {
            if (o1 == null || o2 == null) {
                return 0
            }

            val hand1 = o1.first
            val hand2 = o2.first
            return when {
                hand1[0] != hand2[0] -> cards.indexOf(hand2[0]) - cards.indexOf(hand1[0])
                hand1[1] != hand2[1] -> cards.indexOf(hand2[1]) - cards.indexOf(hand1[1])
                hand1[2] != hand2[2] -> cards.indexOf(hand2[2]) - cards.indexOf(hand1[2])
                hand1[3] != hand2[3] -> cards.indexOf(hand2[3]) - cards.indexOf(hand1[3])
                else -> cards.indexOf(hand2[4]) - cards.indexOf(hand1[4])
            }
        }
    }

    private val comparator = CardsComparator(cards)
    private val comparator2 = CardsComparator(cards2)

    fun part1() {
        handBids.forEach { handBid ->
            val kinds = mutableMapOf<Char, Int>()
            val hand = handBid[0]
            hand.forEach {
                kinds[it] = kinds.getOrDefault(it, 0) + 1
            }
            when {
                kinds.any { it.value == 5 } -> {
                    kind5.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.any { it.value == 4 } -> {
                    kind4.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.any { it.value == 3 } && kinds.any { it.value == 2 } -> {
                    kindFull.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.any { it.value == 3 } -> {
                    kind3.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.filter { it.value == 2 }.size == 2 -> {
                    kind2pair.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.any { it.value == 2 } -> {
                    kind1pair.add(Pair(handBid[0], handBid[1].toLong()))
                }

                else -> {
                    kindNone.add(Pair(handBid[0], handBid[1].toLong()))
                }
            }
        }

        kind5.sortWith(comparator)
        kind4.sortWith(comparator)
        kindFull.sortWith(comparator)
        kind3.sortWith(comparator)
        kind2pair.sortWith(comparator)
        kind1pair.sortWith(comparator)
        kindNone.sortWith(comparator)

        var sum = 0L
        kindNone.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1)
        }
        kind1pair.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size)
        }
        kind2pair.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size + kind1pair.size)
        }
        kind3.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size + kind1pair.size + kind2pair.size)
        }
        kindFull.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size + kind1pair.size + kind2pair.size + kind3.size)
        }
        kind4.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size + kind1pair.size + kind2pair.size + kind3.size + kindFull.size)
        }
        kind5.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size + kind1pair.size + kind2pair.size + kind3.size + kindFull.size + kind4.size)
        }

        println(sum)
    }

    fun part2() {
        handBids.forEach { handBid ->
            val kinds = mutableMapOf<Char, Int>()
            val hand = handBid[0]
            hand.forEach {
                kinds[it] = kinds.getOrDefault(it, 0) + 1
            }
            when {
                kinds.got('J') >= 4 -> {
                    kind5.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.got('J') == 3 -> {
                    if (kinds.any { it.value == 2 }) {
                        kind5.add(Pair(handBid[0], handBid[1].toLong()))
                    } else {
                        kind4.add(Pair(handBid[0], handBid[1].toLong()))
                    }
                }

                kinds.got('J') == 2 -> {
                    when {
                        kinds.any { it.value == 3 } -> {
                            kind5.add(Pair(handBid[0], handBid[1].toLong()))
                        }

                        kinds.filter { it.value == 2 }.size == 2 -> {
                            kind4.add(Pair(handBid[0], handBid[1].toLong()))
                        }

                        else -> {
                            kind3.add(Pair(handBid[0], handBid[1].toLong()))
                        }
                    }
                }

                kinds.got('J') == 1 -> {
                    when {
                        kinds.any { it.value == 4 } -> {
                            kind5.add(Pair(handBid[0], handBid[1].toLong()))
                        }

                        kinds.any { it.value == 3 } -> {
                            kind4.add(Pair(handBid[0], handBid[1].toLong()))
                        }

                        kinds.filter { it.value == 2 }.size == 2 -> {
                            kindFull.add(Pair(handBid[0], handBid[1].toLong()))
                        }

                        kinds.any { it.value == 2 } -> {
                            kind3.add(Pair(handBid[0], handBid[1].toLong()))
                        }

                        else -> {
                            kind1pair.add(Pair(handBid[0], handBid[1].toLong()))
                        }
                    }
                }

                kinds.any { it.value == 5 } -> {
                    kind5.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.any { it.value == 4 } -> {
                    kind4.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.any { it.value == 3 } && kinds.any { it.value == 2 } -> {
                    kindFull.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.any { it.value == 3 } -> {
                    kind3.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.filter { it.value == 2 }.size == 2 -> {
                    kind2pair.add(Pair(handBid[0], handBid[1].toLong()))
                }

                kinds.any { it.value == 2 } -> {
                    kind1pair.add(Pair(handBid[0], handBid[1].toLong()))
                }

                else -> {
                    kindNone.add(Pair(handBid[0], handBid[1].toLong()))
                }
            }
        }

        kind5.sortWith(comparator2)
        kind4.sortWith(comparator2)
        kindFull.sortWith(comparator2)
        kind3.sortWith(comparator2)
        kind2pair.sortWith(comparator2)
        kind1pair.sortWith(comparator2)
        kindNone.sortWith(comparator2)

        var sum = 0L
        kindNone.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1)
        }
        kind1pair.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size)
        }
        kind2pair.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size + kind1pair.size)
        }
        kind3.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size + kind1pair.size + kind2pair.size)
        }
        kindFull.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size + kind1pair.size + kind2pair.size + kind3.size)
        }
        kind4.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size + kind1pair.size + kind2pair.size + kind3.size + kindFull.size)
        }
        kind5.forEachIndexed { index, pair ->
            sum += pair.second * (index + 1 + kindNone.size + kind1pair.size + kind2pair.size + kind3.size + kindFull.size + kind4.size)
        }

        println(sum)
    }

    private fun Map<Char, Int>.got(key: Char) = this[key] ?: -1
}