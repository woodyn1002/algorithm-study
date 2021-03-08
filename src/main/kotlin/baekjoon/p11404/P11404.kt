package baekjoon.p11404

import kotlin.math.min

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()

    val costs = Array(n) { i ->
        Array<Long?>(n) { j ->
            if (i == j) 0 else null
        }
    }
    repeat(m) {
        val (a, b, c) = readLine()!!.split(" ").map(String::toInt)
        costs[a - 1][b - 1] = min(costs[a - 1][b - 1] ?: Long.MAX_VALUE, c.toLong())
    }

    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (costs[i][k] != null && costs[k][j] != null) {
                    costs[i][j] = min(costs[i][j] ?: Long.MAX_VALUE, costs[i][k]!! + costs[k][j]!!)
                }
            }
        }
    }

    for (i in 0 until n) {
        println(
            costs[i].map { it ?: 0 }
                .joinToString(" ")
        )
    }
}