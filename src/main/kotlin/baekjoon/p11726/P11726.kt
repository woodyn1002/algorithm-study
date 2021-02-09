package baekjoon.p11726

fun main() {
    val n = readLine()!!.toInt()

    val dp = IntArray(n + 1) { 0 }

    for (i in 1..n) {
        dp[i] = when (i) {
            1 -> 1
            2 -> 2
            else -> (dp[i - 1] + dp[i - 2]) % 10007
        }
    }

    println(dp[n])
}