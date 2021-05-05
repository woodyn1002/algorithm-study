package baekjoon.p1463

import kotlin.math.min

fun main() {
    val n = readLine()!!.toInt()

    val dp = IntArray(n + 1) { 0 }
    for (i in 1..n) {
        var min: Int
        if (i == 1) {
            min = 0
        } else {
            min = dp[i - 1] + 1
            if (i % 3 == 0)
                min = min(min, dp[i / 3] + 1)
            if (i % 2 == 0)
                min = min(min, dp[i / 2] + 1)
        }
        dp[i] = min
    }
    println(dp[n])
}