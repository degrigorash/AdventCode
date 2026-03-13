package year2016

import tools.printEach
import tools.readFile
import java.util.Collections
import kotlin.collections.forEach

fun main() {
    Day10().part1()
    Day10().part2()
}

class Day10 {

    private val input = readFile("year2016/day10input")

    //        private val input = readFile("test")
    private var result = -1
    private val instructions = input.filter { it.startsWith("bot") }
    private val bots = instructions.indices.associateWith { mutableListOf<Int>() }.toMutableMap()
    private val outputs = instructions.indices.associateWith { mutableListOf<Int>() }.toMutableMap()

    fun part1() {
        val test = input.filter { it.startsWith("value") }
        test.forEach {
            it.split(" ").let { split ->
                val number = split.last().toInt()
                bots[number]!!.add(split[1].toInt())
            }
        }
        run loop@{
            while (true) {
                bots.filter { it.value.size == 2 }.forEach { bot ->
                    if (bot.value.joinToString("") == "6117" || bot.value.joinToString("") == "1761") {
                        result = bot.key
                        return@loop
                    }
                    instructions.firstOrNull { it.split(" ")[1].toInt() == bot.key }?.let { inst ->
                        val split = inst.split(" ")
                        if (split[5] == "bot") {
                            bots[split[6].toInt()]!!.add(bot.value.min())
                        }
                        bot.value.remove(bot.value.min())
                        if (split[10] == "bot") {
                            bots[split[11].toInt()]!!.add(bot.value[0])
                        }
                        bot.value.clear()
                    }
                }
            }
        }
        println("part1: $result")
    }

    fun part2() {
        val test = input.filter { it.startsWith("value") }
        test.forEach {
            it.split(" ").let { split ->
                val number = split.last().toInt()
                bots[number]!!.add(split[1].toInt())
            }
        }
        run loop@{
            while (true) {
                if (bots.filter { it.value.size == 2 }.size == 0) {
                    return@loop
                }
                bots.filter { it.value.size == 2 }.forEach { bot ->
                    instructions.firstOrNull { it.split(" ")[1].toInt() == bot.key }?.let { inst ->
                        val split = inst.split(" ")
                        if (split[5] == "bot") {
                            bots[split[6].toInt()]!!.add(bot.value.min())
                        } else {
                            outputs[split[6].toInt()]!!.add(bot.value.min())
                        }
                        bot.value.remove(bot.value.min())
                        if (split[10] == "bot") {
                            bots[split[11].toInt()]!!.add(bot.value[0])
                        } else {
                            outputs[split[11].toInt()]!!.add(bot.value.min())
                        }
                        bot.value.clear()
                    }
                }
            }
        }
        result = outputs[0]!![0] * outputs[1]!![0] * outputs[2]!![0]
        println("part2: $result")
    }
}