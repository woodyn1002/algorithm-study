package baekjoon.p12865

import kotlin.math.max

fun main() {
    val (n, k) = readLine()!!.split(" ").map(String::toInt)
    val weights = IntArray(n)
    val values = IntArray(n)

    for (i in 0 until n) {
        val (w, v) = readLine()!!.split(" ").map(String::toInt)
        weights[i] = w
        values[i] = v
    }

    val dp = Array(n) { IntArray(k + 1) }

    var maxValue = 0
    for (i in 0 until n) {
        val w = weights[i]
        val v = values[i]

        for (j in 0..k) {
            if (i == 0) {
                if (j >= w) {
                    dp[i][j] = v
                }
            } else {
                if (j >= w) {
                    dp[i][j] = max(dp[i - 1][j], v + dp[i - 1][j - w])
                } else {
                    dp[i][j] = dp[i - 1][j]
                }
            }
            maxValue = max(maxValue, dp[i][j])
        }
    }

    println(maxValue)
}