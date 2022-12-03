package day3

private fun convertToPriority(dupe: Char) = when {
    dupe.code >= 97 -> dupe.code - 96
    else -> dupe.code - 38
}

fun part1() {
    val sum = input.split("\n").sumOf { line ->
        val center = line.length / 2
        val firstHalf = line.subSequence(0, center)
        val secondHalf = line.subSequence(center, line.length)
        println("$firstHalf $secondHalf")

        val dupe = firstHalf.fold("", operation = { acc, char ->
            when {
                secondHalf.contains(char, false) -> "$char"
                else -> acc
            }
        })[0]

        convertToPriority(dupe)
    }
    println(sum)
}

fun part2() {
    val rucksacks = input.split("\n")

    val groups = ArrayList<List<String>>()
    (rucksacks.indices step 3).mapTo(groups) { rucksacks.subList(it, it + 3) }

    val sum = groups.sumOf { group ->
        val res = group[0].fold("", operation = { acc, char ->
            when {
                group[1].contains(char) && group[2].contains(char) -> "$char"
                else -> acc
            }
        }).first()

        convertToPriority(res)
    }

    println(sum)
}

fun main() {
    part1()
    part2()
}