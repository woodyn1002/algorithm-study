package baekjoon.p2493

import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val heights = readLine()!!
        .split(' ')
        .map(String::toInt)
        .toIntArray()

    val answer = IntArray(n)
    val stack: Deque<Pair<Int, Int>> = ArrayDeque()

    for (i in (n - 1) downTo 0) {
        while (stack.isNotEmpty() && heights[i] >= stack.peek().first) {
            val element = stack.pop();
            answer[element.second] = (i + 1)
        }
        stack.push(Pair(heights[i], i))
    }
    while (stack.isNotEmpty()) {
        val element = stack.pop();
        answer[element.second] = 0
    }

    println(answer.joinToString(separator = " "))
}