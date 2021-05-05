package baekjoon.p16120

import java.util.*

fun main() {
    val input = readLine()!!
    var answer = true

    val stack: Deque<Char> = ArrayDeque()
    main@for (c in input) {
        if (c == 'P' && stack.isNotEmpty() && stack.peek() == 'A') {
            stack.pop()
            for (i in 0 until 2) {
                if (stack.isEmpty() || stack.pop() != 'P') {
                    answer = false
                    break@main
                }
            }
        }
        stack.push(c)
    }

    println(
        if (answer && stack.size == 1 && stack.peek() == 'P')
            "PPAP"
        else
            "NP"
    )
}