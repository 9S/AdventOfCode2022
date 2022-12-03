package day1

fun part1() {
    val elves = input.split("\n\n")
    println(elves.maxOfOrNull { s ->
        s.split("\n")
            .map(Integer::parseInt)
            .sum()
    })
}

fun part2() {
    println(
        input.split("\n\n")
            .map { s ->
                s.split("\n")
                    .map(Integer::parseInt)
                    .sum()
            }
            .sortedDescending()
            .subList(0, 3)
            .sum()
    )
}

fun main() {
    part1();
    part2()
}