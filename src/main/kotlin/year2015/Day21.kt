package year2015

import kotlin.math.max

fun main() {
    Day21().part1()
    Day21().part2()
}

class Day21 {

    private val player = Player(100, 0, 0)
    private val boss = Player(100, 8, 2)
    private val weapons = listOf(
        Item(8, 4, 0),
        Item(10, 5, 0),
        Item(25, 6, 0),
        Item(40, 7, 0),
        Item(74, 8, 0),
    )
    private val armor = listOf(
        null,
        Item(13, 0, 1),
        Item(31, 0, 2),
        Item(53, 0, 3),
        Item(75, 0, 4),
        Item(102, 0, 5)
    )
    private val rings = listOf(
        null,
        null,
        Item(25, 1, 0),
        Item(50, 2, 0),
        Item(100, 3, 0),
        Item(20, 0, 1),
        Item(40, 0, 2),
        Item(80, 0, 3)
    )

    fun part1() {
        var money = Int.MAX_VALUE
        weapons.forEach { w ->
            armor.forEach { a ->
                rings.forEachIndexed { i, r1 ->
                    ((i + 1) until rings.size).forEach { i2 ->
                        val r2 = rings[i2]
                        val player = Player(
                            hp = player.hp,
                            damage = player.damage + w.damage + (r1?.damage ?: 0) + (r2?.damage ?: 0),
                            armor = player.armor + (a?.armor ?: 0) + (r1?.armor ?: 0) + (r2?.armor ?: 0)
                        )
                        val cost = w.cost + (a?.cost ?: 0) + (r1?.cost ?: 0) + (r2?.cost ?: 0)
                        if (duelEqualHp(player) && cost < money) {
                            money = cost
                        }
                    }
                }
            }
        }
        println(money)
    }

    fun part2() {
        var money = 0
        weapons.forEach { w ->
            armor.forEach { a ->
                rings.forEachIndexed { i, r1 ->
                    ((i + 1) until rings.size).forEach { i2 ->
                        val r2 = rings[i2]
                        val player = Player(
                            hp = player.hp,
                            damage = player.damage + w.damage + (r1?.damage ?: 0) + (r2?.damage ?: 0),
                            armor = player.armor + (a?.armor ?: 0) + (r1?.armor ?: 0) + (r2?.armor ?: 0)
                        )
                        val cost = w.cost + (a?.cost ?: 0) + (r1?.cost ?: 0) + (r2?.cost ?: 0)
                        if (duelEqualHpLose(player) && cost > money) {
                            money = cost
                        }
                    }
                }
            }
        }
        println(money)
    }

    private fun duelEqualHp(player: Player): Boolean {
        return player.armor + player.damage >= boss.armor + boss.damage
    }

    private fun duelEqualHpLose(player: Player): Boolean {
        return player.armor + player.damage < boss.armor + boss.damage
    }
}

private class Player(
    var hp: Int,
    var damage: Int,
    var armor: Int
)

private data class Item(
    val cost: Int,
    val damage: Int,
    val armor: Int
)