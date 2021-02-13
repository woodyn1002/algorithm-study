package baekjoon.p6198

import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val h = IntArray(n) { readLine()!!.toInt() }

    var answer = 0L
    val stack: Deque<Pair<Int, Int>> = ArrayDeque()
    for (i in 0..n) {
        while (stack.isNotEmpty() && (i == n || h[i] >= stack.peek().first)) {
            val popped = stack.pop()
            val cnt = i - popped.second - 1
            answer += cnt
        }
        if (i < n - 1) {
            stack.push(Pair(h[i], i))
        }
    }

    println(answer)
}