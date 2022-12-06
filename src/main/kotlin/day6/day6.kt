package day6

import java.util.*

fun evaluateInput(input: String, size: Int): Int {
    val buffer = LinkedList<Char>()
    input.forEachIndexed { ix, it ->
        buffer.addLast(it)
        if (buffer.size > size) buffer.removeFirst()
        if (buffer.size == size && buffer.distinct().size == buffer.size) {
            return ix + 1
        }
    }
    return -1
}

fun solve1() {
    println(evaluateInput(input, 4))
}

fun solve2() {
    println(evaluateInput(input, 14))
}

fun main() {
    solve1()
    solve2()
}