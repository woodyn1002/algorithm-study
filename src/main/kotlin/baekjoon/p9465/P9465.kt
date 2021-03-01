package baekjoon.p9465

import kotlin.math.max

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val n = readLine()!!.toInt()

        val stickers = Array(n) { IntArray(2) }

        for (j in 0 until 2) {
            val line = readLine()!!.split(" ").map(String::toInt)
            for ((index, sticker) in line.withIndex())
                stickers[index][j] = sticker
        }

        val dp = Array(n) { IntArray(2) }

        for (i in 0 until n) {
            when (i) {
                0 -> {
                    dp[i][0] = stickers[i][0]
                    dp[i][1] = stickers[i][1]
                }
                1 -> {
                    dp[i][0] = stickers[i][0] + dp[i - 1][1]
                    dp[i][1] = stickers[i][1] + dp[i - 1][0]
                }
                else -> {
                    dp[i][0] = stickers[i][0] + max(dp[i - 1][1], max(dp[i - 2][0], dp[i - 2][1]))
                    dp[i][1] = stickers[i][1] + max(dp[i - 1][0], max(dp[i - 2][1], dp[i - 2][0]))
                }
            }
        }

        println(dp.maxOf { max(it[0], it[1]) })
    }
}