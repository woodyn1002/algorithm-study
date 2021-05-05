package programmers.p12951

class Solution {
    fun solution(s: String): String {
        val answer = StringBuilder()

        var prev: Char? = null
        for (c in s) {
            answer.append(
                if (prev == null || prev == ' ') c.toUpperCase() else c.toLowerCase()
            )
            prev = c
        }
        return answer.toString()
    }
}