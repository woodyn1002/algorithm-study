package programmers.p76502

import java.util.*

class Solution {
    fun solution(s: String): Int {
        var answer: Int = 0

        for (x in 0 until s.length) {
            val rotated = s.substring(x) + s.substring(0, x)
            // println("rotated=$rotated..")
            if (isValid(rotated)) {
                answer++
            }
        }

        return answer
    }

    fun isValid(str: String): Boolean {
        val stack: Deque<Char> = ArrayDeque()
        for (c in str) {
            if (c in arrayOf('(', '[', '{')) {
                stack.push(c)
            } else {
                val opening = when(c) {
                    ')' -> '('
                    ']' -> '['
                    '}' -> '{'
                    else -> error("wrong bracket")
                }

                if (stack.isEmpty() || stack.peek() != opening)
                    return false

                stack.pop()
            }
            // println("stack=$stack")
        }
        return stack.isEmpty()
    }
}