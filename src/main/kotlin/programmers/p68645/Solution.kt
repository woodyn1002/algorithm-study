package programmers.p68645

class Solution {
    fun solution(n: Int): IntArray {
        val triangle = Array(n) { i -> IntArray(i + 1) }
        val maxNum = n * (n + 1) / 2

        triangle[0][0] = 1
        if (n > 1) {
            var cur = 2
            var prev = Pair(0, 0)
            var dir = 0
            while (true) {
                val next = when (dir) {
                    0 -> Pair(prev.first + 1, prev.second)
                    1 -> Pair(prev.first, prev.second + 1)
                    2 -> Pair(prev.first - 1, prev.second - 1)
                    else -> error("invalid dir")
                }

                if (next.first !in 0 until n
                    || next.second !in 0 until triangle[next.first].size
                    || triangle[next.first][next.second] != 0) {
                    dir = (dir + 1) % 3
                } else {
                    triangle[next.first][next.second] = cur
                    if (cur == maxNum)
                        break
                    cur++
                    prev = next
                }
            }
        }

        val answer = mutableListOf<Int>()
        for (row in triangle) {
            for (num in row) {
                answer.add(num)
            }
        }
        return answer.toIntArray()
    }
}