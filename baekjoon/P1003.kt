package baekjoon.p1003

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()

        val dp0 = IntArray(n + 1) { 0 }
        val dp1 = IntArray(n + 1) { 0 }

        for (i in 0..n) {
            dp0[i] = when(i) {
                0 -> 1
                1 -> 0
                else -> dp0[i - 1] + dp0[i - 2]
            }
            dp1[i] = when(i) {
                0 -> 0
                1 -> 1
                else -> dp1[i - 1] + dp1[i - 2]
            }
        }

        println("${dp0[n]} ${dp1[n]}")
    }
}