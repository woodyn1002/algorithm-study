package programmers.p67257

import java.util.*
import kotlin.math.*

class Solution {
    fun solution(expression: String): Long {
        var answer = 0L
        for (case in arrayOf(
            charArrayOf('+', '-', '*'),
            charArrayOf('+', '*', '-'),
            charArrayOf('-', '+', '*'),
            charArrayOf('-', '*', '+'),
            charArrayOf('*', '+', '-'),
            charArrayOf('*', '-', '+')
        )) {
            // println("case: ${case.joinToString(" < ")}..")
            val result = calc(expression, case)
            // println("..result=$result")
            answer = max(abs(result), answer)
        }
        return answer
    }

    fun calc(exp: String, prior: CharArray): Long {
        val operands: Deque<Long> = ArrayDeque()
        val operators: Deque<Char> = ArrayDeque()
        var curNum = 0L
        for (c in exp) {
            if (c in '0'..'9') {
                curNum = Character.getNumericValue(c).toLong() + (curNum * 10)
            } else {
                operands.push(curNum)
                curNum = 0

                while (operators.isNotEmpty() && prior.indexOf(operators.peek()) >= prior.indexOf(c)) {
                    val b = operands.pop()
                    val a = operands.pop()
                    val op = operators.pop()
                    val result = when (op) {
                        '+' -> a + b
                        '-' -> a - b
                        '*' -> a * b
                        else -> throw IllegalStateException()
                    }
                    // println("$a $op $b = $result")
                    operands.push(result)
                }
                operators.push(c)
            }
        }
        operands.push(curNum)
        while (operators.isNotEmpty()) {
            val b = operands.pop()
            val a = operands.pop()
            val op = operators.pop()
            val result = when (op) {
                '+' -> a + b
                '-' -> a - b
                '*' -> a * b
                else -> throw IllegalStateException()
            }
            // println("$a $op $b = $result")
            operands.push(result)
        }
        return operands.pop()
    }
}