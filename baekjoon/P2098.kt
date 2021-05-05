package baekjoon.p2098

import kotlin.math.min

const val INF = 16 * 1_000_000 + 1

fun main() {
    val n = readLine()!!.toInt()

    val w = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        val input = readLine()!!.split(' ')
        for ((j, cost) in input.withIndex()) {
            w[i][j] = cost.toInt()
        }
    }

    val dp = Array(n) { IntArray(1 shl n) { INF } }
    val answer = tsp(n, w, dp, 0x1, 0)
    println(answer)
}

fun tsp(n: Int, arr: Array<IntArray>, dp: Array<IntArray>, visited: Int, node: Int): Int {
    if (visited == fullMask(n)) {
        return if (arr[node][0] != 0) arr[node][0] else INF
    }

    if (dp[node][visited] != INF) {
        return dp[node][visited]
    }

    for (i in 0 until n) {
        if (arr[node][i] > 0 && !contains(visited, i)) {
            val tsp = tsp(n, arr, dp, applied(visited, i), i)
            dp[node][visited] = min(dp[node][visited], arr[node][i] + tsp)
        }
    }
    return dp[node][visited]
}

fun applied(bitmask: Int, num: Int) = bitmask or (1 shl num)
fun contains(bitmask: Int, num: Int) = (bitmask and (1 shl num)) > 0
fun fullMask(size: Int) = (1 shl size) - 1