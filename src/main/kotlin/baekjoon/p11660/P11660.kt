package baekjoon.p11660

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)

    val table = Array(n) { readLine()!!.split(" ").map(String::toInt).toIntArray() }

    val dp = Array(n + 1) { IntArray(n + 1) }
    for (i in 1..n) {
        for (j in 1..n) {
            dp[i][j] = table[i - 1][j - 1] + when {
                i == 0 -> dp[i][j - 1]
                j == 0 -> dp[i - 1][j]
                else -> dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1]
            }
        }
    }

    repeat(m) {
        val (x1, y1, x2, y2) = readLine()!!.split(" ").map(String::toInt)

        val answer = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]
        println(answer)
    }
}