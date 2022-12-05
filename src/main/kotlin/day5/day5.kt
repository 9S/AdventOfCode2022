package day5

import java.util.*

class Command(string: String) {
    val amount: Int
    val from: Int
    val to: Int

    init {
        val result = string.split(" ")
        amount = result[1].toInt()
        from = result[3].toInt()
        to = result[5].toInt()
    }

    override fun toString(): String {
        return "Command(amount=$amount, from=$from, to=$to)"
    }
}

fun parseTrees(lines: List<String>, seperatorLine: Int): List<Stack<Char>> {
    val numberOfTrees = lines[seperatorLine].split("   ").maxOf { it.trim().toInt() }
    val trees = arrayOfNulls<Array<Char>>(numberOfTrees)
        .map { ArrayList<Char>() }

    return List(trees.size) { index ->
        (0 until seperatorLine)
            .fold<Int, List<Char>>(listOf()) { acc, ix ->
                when {
                    lines[ix][1 + index * 4] != ' ' -> acc.plus(lines[ix][1 + index * 4])
                    else -> acc
                }
            }.asReversed()
            .fold(Stack()) { acc, c ->
                acc.push(c)
                acc
            }
    }
}

fun parseCommands(lines: List<String>, line: Int): List<Command> {
    return lines
        .subList(line + 2, lines.size)
        .map { Command(it) }
}

fun applyCommand(tree: List<Stack<Char>>, command: Command): List<Stack<Char>> {
    repeat(command.amount) {
        tree[command.to - 1].push(tree[command.from - 1].pop())
    }
    return tree
}

fun applyCommand2(tree: List<Stack<Char>>, command: Command): List<Stack<Char>> {
    val buffer = Stack<Char>()
    repeat(command.amount) {
        buffer.push(tree[command.from - 1].pop())
    }
    repeat(command.amount) {
        tree[command.to - 1].push(buffer.pop())
    }
    return tree
}

fun getResult(tree: List<Stack<Char>>): String {
    val res = tree.map { it.pop() }
    return res.fold("") { acc, c ->
        "$acc$c"
    }
}

fun solve1() {
    val lines = input.split("\n")
    val line = lines.indexOfFirst {
        it.matches(Regex("[ 1-9]+"))
    }
    var tree = parseTrees(lines, line)
    val commands = parseCommands(lines, line)
    commands.forEach {
        tree = applyCommand(tree, it)
    }
    println(getResult(tree))
}


fun solve2() {
    val lines = input.split("\n")
    val line = lines.indexOfFirst {
        it.matches(Regex("[ 1-9]+"))
    }
    var tree = parseTrees(lines, line)
    val commands = parseCommands(lines, line)
    commands.forEach {
        tree = applyCommand2(tree, it)
    }
    println(getResult(tree))
}

fun main() {
    solve1()
    solve2()
}