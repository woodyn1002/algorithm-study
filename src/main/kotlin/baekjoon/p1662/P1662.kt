package baekjoon.p1662

import java.util.*

fun main() {
    val s = readLine()!!

    val stack: Deque<String> = ArrayDeque()
    for (c in s) {
        if (c == ')') {
            var len = 0
            while (stack.peek() != "(") {
                val popped = stack.pop()
                len +=
                    if (popped.startsWith('+'))
                        popped.substring(1).toInt()
                    else
                        1
            }
            stack.pop()
            val k = stack.pop().toInt()

            stack.push("+${k * len}")
        } else {
            stack.push(c.toString())
        }
    }

    val answer = stack.sumOf {
        if (it.startsWith('+'))
            it.substring(1).toInt()
        else
            1
    }
    println(answer)
}