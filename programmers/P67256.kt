package programmers.p67256

import kotlin.math.*

class Solution {
    fun solution(numbers: IntArray, hand: String): String {
        var leftHand = Pos(3, 0)
        var rightHand = Pos(3, 2)

        val answer = mutableListOf<String>()
        for (number in numbers) {
            val btn = Pos.of(number)
            var useLeft = false
            if (number in arrayOf(1, 4, 7)) {
                useLeft = true
            } else if (number in arrayOf(3, 6, 9)) {
                useLeft = false
            } else {
                val distLH = leftHand.dist(btn)
                val distRH = rightHand.dist(btn)
                useLeft = (distLH < distRH || (distLH == distRH && hand == "left"))
            }

            if (useLeft) {
                answer.add("L")
                leftHand = btn
            } else {
                answer.add("R")
                rightHand = btn
            }
        }
        return answer.joinToString("")
    }
}

data class Pos(val r: Int, val c: Int) {
    fun dist(other: Pos): Int = abs(this.r - other.r) + abs(this.c - other.c)

    companion object {

        fun of(number: Int): Pos =
            if (number == 0) Pos(3, 1)
            else Pos((number - 1) / 3, (number - 1) % 3)

    }
}