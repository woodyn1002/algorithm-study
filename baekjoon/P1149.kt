package baekjoon.p1149

import kotlin.math.min

fun main() {
    val n = readLine()!!.toInt()

    val answers = Array(n) { IntArray(3) { 0 } }
    val costs = Array(n) { IntArray(3) { 0 } }
    repeat(n) { i ->
        readLine()!!.split(" ").forEachIndexed { j, input ->
            costs[i][j] = input.toInt()
        }
    }

    answers[0][0] = costs[0][0]
    answers[0][1] = costs[0][1]
    answers[0][2] = costs[0][2]
    for (i in 1 until n) {
        answers[i][0] = costs[i][0] + min(answers[i - 1][1], answers[i - 1][2])
        answers[i][1] = costs[i][1] + min(answers[i - 1][0], answers[i - 1][2])
        answers[i][2] = costs[i][2] + min(answers[i - 1][0], answers[i - 1][1])
    }

    println(answers[n - 1].minOrNull())
}