package day2

const val rock = "A"
const val paper = "B"
const val scissor = "C"
const val rockReply = "X"
const val paperReply = "Y"
const val scissorReply = "Z"
const val loseOutcome = "X"
const val drawOutcome = "Y"
const val winOutcome = "Z"

enum class Outcome {
    WIN, LOSE, DRAW
}

fun calculateOutcome(round: List<String>): Outcome {
    return when {
        // Rock defeats scissors
        round.last() == rockReply && round.first() == scissor -> Outcome.WIN
        // Scissors defeats paper
        round.last() == scissorReply && round.first() == paper -> Outcome.WIN
        // Paper defeats rock
        round.last() == paperReply && round.first() == rock -> Outcome.WIN

        // Draws
        round.last() == rockReply && round.first() == rock -> Outcome.DRAW
        round.last() == scissorReply && round.first() == scissor -> Outcome.DRAW
        round.last() == paperReply && round.first() == paper -> Outcome.DRAW

        else -> Outcome.LOSE
    }
}

fun calculateScore(round: List<String>, outcome: Outcome): Int {
    val shapeScore = when (round.last()) {
        rockReply -> 1
        paperReply -> 2
        scissorReply -> 3
        else -> throw Exception("cannot handle self draw ${round.last()}")
    }
    val outcomeScore = when (outcome) {
        Outcome.WIN -> 6
        Outcome.DRAW -> 3
        Outcome.LOSE -> 0
    }
    return shapeScore + outcomeScore
}

fun calculateChosen(round: List<String>): String {
    return when {
        round.last() == drawOutcome && round.first() == scissor -> scissorReply
        round.last() == drawOutcome && round.first() == paper -> paperReply
        round.last() == drawOutcome && round.first() == rock -> rockReply

        round.last() == winOutcome && round.first() == scissor -> rockReply
        round.last() == winOutcome && round.first() == paper -> scissorReply
        round.last() == winOutcome && round.first() == rock -> paperReply

        round.last() == loseOutcome && round.first() == scissor -> paperReply
        round.last() == loseOutcome && round.first() == paper -> rockReply
        round.last() == loseOutcome && round.first() == rock -> scissorReply
        else -> throw Exception("Invalid combo")
    }
}

fun part1() {
    val sum = input
        .split("\n")
        .map { it.split(" ") }
        .sumOf {
            val outcome = calculateOutcome(it)
            val score = calculateScore(it, outcome)
            println("outcome: $outcome score: $score")
            score
        }

    println("totalScore: $sum")
}

fun part2() {
    val sum = input
        .split("\n")
        .map { s -> s.split(" ") }
        .sumOf {
            val chosen = calculateChosen(it)
            val entry = listOf(it.first(), chosen)
            val outcome = calculateOutcome(entry)
            val score = calculateScore(entry, outcome)
            println("outcome: $outcome score: $score")
            score
        }

    print("totalScore: $sum")
}

fun main() {
    part1()
    part2()
}
