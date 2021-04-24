package programmers.p12985

import kotlin.math.*

class Solution {
    fun solution(n: Int, a: Int, b: Int): Int {
        var curA = a - 1
        var curB = b - 1

        var answer = 1
        while (!((curA % 2 == 0 && curB - curA == 1) || (curB % 2 == 0 && curA - curB == 1))) {
            curA /= 2
            curB /= 2
            // println("round $answer: A is $curA, B is $curB")
            answer++
        }
        return answer
    }
}