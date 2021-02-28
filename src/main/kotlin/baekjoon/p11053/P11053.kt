package baekjoon.p11053

import kotlin.math.min

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map(String::toInt)

    // dp[i][j]: i번째 원소까지 고려했을 때, 부분 수열의 크기가 j인 경우 마지막 원소의 최솟값
    val dp = Array(n) { IntArray(n + 1) }

    for (i in a.indices) {
        for (j in 0..n) {
            dp[i][j] = if (j == 0) {
                Int.MAX_VALUE // 아무 원소도 고를 수 없음
            } else if (j <= (i + 1)) {
                if (i == 0) {
                    a[i]
                } else {
                    // i번째 원소를 고르지 않으면 dp[i - 1][j]
                    var minLast = dp[i - 1][j]

                    // i번째 원소를 고르려면 하나만 고르는 경우거나 해당 원소가 dp[i-1][j-1]보다 커야 함, 고른다면 a[i]
                    if (j == 1 || a[i] > dp[i - 1][j - 1])
                        minLast = min(minLast, a[i])

                    minLast
                }
            } else {
                Int.MAX_VALUE // 고를 수 없는 원소 개수
            }
        }
    }

    println(dp.last().withIndex().filter { it.value in 1..1000 }.maxOf { it.index })
}