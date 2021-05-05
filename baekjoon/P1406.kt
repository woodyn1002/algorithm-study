package baekjoon.p1406

import java.util.*

fun main() {
    val left: Deque<Char> = ArrayDeque()
    val right: Deque<Char> = ArrayDeque()

    val text = readLine()!!
    for (c in text)
        left.addLast(c)

    val m = readLine()!!.toInt()
    repeat(m) {
        val input = readLine()!!.split(' ')
        when (input[0]) {
            "L" -> if (left.isNotEmpty()) right.addFirst(left.removeLast())
            "D" -> if (right.isNotEmpty()) left.addLast(right.removeFirst())
            "B" -> if (left.isNotEmpty()) left.removeLast()
            "P" -> left.addLast(input[1].first())
        }
    }

    println(left.joinToString(separator = "") + right.joinToString(separator = ""))
}