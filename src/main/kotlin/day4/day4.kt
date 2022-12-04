package day4

fun getPairs(pair: String): List<List<Int>> {
    return pair.split(",")
        .map { elf ->
            val values = elf.split("-").map { value -> value.toInt() }
            (values.first()..values.last()).toList()
        }
}

fun isFullOverlap(first: List<Int>, second: List<Int>): Int {
    val result = first.fold(Pair(first, second), operation = { acc, i ->
        when {
            acc.first.contains(i) && acc.second.contains(i) -> Pair(acc.first.minus(i), acc.second.minus(i))
            else -> acc
        }
    })

    return when {
        result.first.isEmpty() || result.second.isEmpty() -> 1
        else -> 0
    }
}

fun isPartialOverlap(first: List<Int>, second: List<Int>): Int {
    return first.fold(0, operation = { acc, i ->
        when {
            second.contains(i) -> 1
            else -> acc
        }
    })
}

fun solve1() {
    val pairs = input
        .split("\n").sumOf { pair ->
            val pairs: List<List<Int>> = getPairs(pair)
            isFullOverlap(pairs.first(), pairs.last())
        }
    println(pairs)
}

fun solve2() {
    val pairs = input.split("\n").sumOf {pair ->
        val pairs: List<List<Int>> = getPairs(pair)
        isPartialOverlap(pairs.first(), pairs.last())
    }
    println(pairs)
}

fun main() {
    solve1()
    solve2()
}