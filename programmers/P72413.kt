package programmers.p72413

import kotlin.math.*

class Solution {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val costs = Array(n) { i ->
            LongArray(n) { j ->
                if (i == j) 0L else MAX_COST
            }
        }

        for (fare in fares) {
            costs[fare[0] - 1][fare[1] - 1] = fare[2].toLong()
            costs[fare[1] - 1][fare[0] - 1] = fare[2].toLong()
        }

        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (costs[i][k] == MAX_COST || costs[k][j] == MAX_COST) continue
                    if (costs[i][k] + costs[k][j] < costs[i][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j]
                    }
                }
            }
        }

        var minCost = MAX_COST
        for (i in 0 until n) {
            if (costs[s - 1][i] == MAX_COST) continue
            if (costs[i][a - 1] == MAX_COST) continue
            if (costs[i][b - 1] == MAX_COST) continue
            val cost = costs[s - 1][i] + costs[i][a - 1] + costs[i][b - 1]
            minCost = min(minCost, cost)
        }

        return minCost.toInt()
    }

    companion object {
        const val MAX_COST = Long.MAX_VALUE
    }
}