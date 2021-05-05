package baekjoon.p3015

import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val heights = IntArray(n) { readLine()!!.toInt() }

    var answer = 0L

    val stack: Deque<Pair<Int, Int>> = ArrayDeque()
    for (i in 0 until n) {
        val height = heights[i]
        while (stack.isNotEmpty() && stack.peek().first < height) {
            answer += stack.pop().second
        }

        if (stack.isEmpty()) {
            stack.push(Pair(height, 1))
        } else {
            if (stack.peek().first == height) {
                val popped = stack.pop()

                answer += popped.second
                if (stack.isNotEmpty()) answer++

                stack.push(popped.let { Pair(it.first, it.second + 1) })
            } else {
                stack.push(Pair(height, 1))
                answer++
            }
        }
    }

    println(answer)
}