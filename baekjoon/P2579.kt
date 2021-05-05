package baekjoon.p2579

import kotlin.math.max

fun main() {
    val n = readLine()!!.toInt()

    val scores = IntArray(n + 1)
    for (i in 1..n) {
        scores[i] = readLine()!!.toInt()
    }

    val dp = IntArray(n + 1)

    for (i in 1..n) {
        dp[i] = when(i) {
            1 -> scores[1]
            2 -> dp[1] + scores[2]
            else -> max(dp[i - 3] + scores[i - 1], dp[i - 2]) + scores[i]
        }
    }

    println(dp[n])
}