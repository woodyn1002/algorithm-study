package baekjoon.p1918

import java.util.*

fun main() {
    val input = readLine()!!

    val operands: Deque<String> = ArrayDeque()
    val operators: Deque<Char> = ArrayDeque()

    for (c in input) {
        if (c.isLetter()) {
            operands.push(c.toString())
        } else {
            when (c) {
                '(' -> {
                    operators.push(c)
                }
                ')' -> {
                    while (operators.peek() != '(') {
                        eval(operands, operators)
                    }
                    operators.pop()
                }
                else -> {
                    while (operators.isNotEmpty() && priorityOf(operators.peek()) >= priorityOf(c)) {
                        eval(operands, operators)
                    }
                    operators.push(c)
                }
            }
        }
    }
    while (operators.isNotEmpty()) {
        eval(operands, operators)
    }

    val answer = operands.pop()
    println(answer)
}

private fun eval(
    operands: Deque<String>,
    operators: Deque<Char>
) {
    val v2 = operands.pop()
    val v1 = operands.pop()
    val op = operators.pop()
    operands.push(v1 + v2 + op)
}

fun priorityOf(op: Char) =
    when (op) {
        '+', '-' -> 0
        '*', '/' -> 1
        '(', ')' -> -1
        else -> throw IllegalArgumentException()
    }