package programmers.p70129

class Solution {
    fun solution(s: String): IntArray {
        var answer: IntArray = intArrayOf(0, 0)

        var cur = s
        while (cur != "1") {
            var tmp = StringBuilder()
            var numZero = 0
            for (c in cur) {
                if (c == '1')
                    tmp.append(c)
                else
                    numZero++
            }

            cur = transform(tmp.length)
            answer[0]++
            answer[1] += numZero
        }

        return answer
    }

    fun transform(num: Int): String {
        val str = StringBuilder()

        var cur = num
        while (cur != 1) {
            str.append(cur % 2)
            cur /= 2
        }
        str.append(1)
        return str.toString().reversed()
    }
}