package baekjoon.p9251

import kotlin.math.max

fun main() {
    val a = readLine()!!
    val b = readLine()!!

    val dp = Array(a.length + 1) { IntArray(b.length + 1) }
    for (i in a.indices) {
        for (j in b.indices) {
            dp[1 + i][1 + j] = if (a[i] == b[j])
                dp[i][j] + 1
            else
                max(dp[i][1 + j], dp[1 + i][j])
        }
    }

    println(dp.maxOf { it.maxOrNull()!! })
}