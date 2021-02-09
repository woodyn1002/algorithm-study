package baekjoon.p11052

fun main() {
    val n = readLine()!!.toInt()
    val p = readLine()!!.split(' ').let {
        IntArray(n + 1) { i ->
            if (i > 0) it[i - 1].toInt()
            else 0
        }
    }

    val dp = IntArray(n + 1)
    for (i in 1..n) {
        dp[i] = IntArray(i) { j ->
            if (j == 0) p[i]
            else dp[j] + dp[i - j]
        }.maxOrNull()!!
    }
    println(dp[n])
}