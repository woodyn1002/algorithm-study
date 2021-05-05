package baekjoon.p2504

import java.util.*

fun main() {
    val input = readLine()!!
    println(calculate(input))
}

fun calculate(input: String): Int {
    val stack: Deque<String> = ArrayDeque()
    for (c in input) {
        if (c == '(' || c == '[') {
            stack.push(c.toString())
        } else if (c == ')' || c == ']') {
            if (stack.isEmpty()) return 0

            val partner = if (c == ')') "(" else "["
            if (stack.peek() == partner) {
                stack.pop()
                stack.push(if (c == ')') "2" else "3")
            } else {
                var sum = 0
                while (stack.peek() != partner) {
                    sum += stack.pop().toIntOrNull() ?: return 0
                    if (stack.isEmpty()) return 0
                }
                stack.pop()

                sum *= (if (c == ')') 2 else 3)
                stack.push(sum.toString())
            }
        }
    }
    var sum = 0
    while (stack.isNotEmpty()) {
        val popped = stack.pop()
        if (popped == "(" || popped == "[") return 0
        sum += popped.toInt()
    }
    return sum
}