package baekjoon.p2156

import kotlin.math.max

fun main() {
    val n = readLine()!!.toInt()
    val amount = IntArray(n) {
        readLine()!!.toInt()
    }

    val dp = IntArray(n)
    for (i in 0 until n) {
        dp[i] = when(i) {
            0 -> amount[0]
            1 -> amount[0] + amount[1]
            2 -> max(amount[0], amount[1]) + amount[2]
            else -> max(dp[i - 3] + amount[i - 1], dp[i - 2]) + amount[i]
        }
        if (i > 0) {
            dp[i] = max(dp[i], dp[i - 1])
        }
    }
    println(dp[n - 1])
}