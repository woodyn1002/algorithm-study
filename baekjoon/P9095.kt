package baekjoon.p9095

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()

        val dp = IntArray(n + 1) { 0 }

        for (i in 1..n) {
            dp[i] = when(i) {
                1 -> 1
                2 -> 2
                3 -> 4
                else -> dp[i - 1] + dp[i - 2] + dp[i - 3]
            }
        }
        println(dp[n])
    }
}