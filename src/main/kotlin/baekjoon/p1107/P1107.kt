package baekjoon.p1107

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val broken = if (m > 0)
        readLine()!!.split(" ").map(String::toInt)
    else
        emptyList()

    val dp = Array<Int?>(1000000) { null }
    for (i in dp.indices) {
        val cases = mutableListOf<Int>()

        if (i > 0 && dp[i - 1] != null) {
            cases += dp[i - 1]!! + 1
        }

        val digits = i.toString().map(Character::getNumericValue)
        if (digits.none { it in broken }) {
            cases += digits.size
        }

        if (i == 100) {
            cases += 0
        }

        dp[i] = cases.minOrNull()
    }

    for (i in dp.indices.reversed()) {
        val cases = mutableListOf<Int>()

        if (dp[i] != null) {
            cases += dp[i]!!
        }

        if (i < dp.lastIndex && dp[i + 1] != null) {
            cases += dp[i + 1]!! + 1
        }

        dp[i] = cases.minOrNull()
    }

    println(dp[n])
}