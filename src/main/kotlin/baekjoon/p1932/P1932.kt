package baekjoon.p1932

import kotlin.math.max

fun main() {
    val n = readLine()!!.toInt()

    val pyramid = Array(n) {
        readLine()!!.split(' ')
            .map { it.toInt() }
            .toIntArray()
    }

    val dp = Array(n) { i -> IntArray(i + 1) }
    dp[0][0] = pyramid[0][0]

    for (i in 1 until n) {
        for (j in 0..i) {
            dp[i][j] = pyramid[i][j] + when(j) {
                0 -> dp[i - 1][j]
                i -> dp[i - 1][j - 1]
                else -> max(dp[i - 1][j], dp[i - 1][j - 1])
            }
        }
    }
    println(dp[n - 1].maxOrNull())
}