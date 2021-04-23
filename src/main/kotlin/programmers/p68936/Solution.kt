package programmers.p68936

class Solution {
    fun solution(arr: Array<IntArray>): IntArray {
        val (numZero, numOne) = divide(arr, arr.size, 0, 0)
        return intArrayOf(numZero, numOne)
    }

    fun divide(arr: Array<IntArray>, n: Int, r: Int, c: Int): Pair<Int, Int> {
        val num = arr[r][c]

        var match = true
        check@for (i in r until (r + n)) {
            for (j in c until (c + n)) {
                if (arr[i][j] != num) {
                    match = false
                    break@check
                }
            }
        }

        return if (match) {
            if (num == 0) Pair(1, 0) else Pair(0, 1)
        } else {
            var numZero = 0
            var numOne = 0
            for (divided in arrayOf(
                divide(arr, n / 2, r, c),
                divide(arr, n / 2, r, c + (n / 2)),
                divide(arr, n / 2, r + (n / 2), c),
                divide(arr, n / 2, r + (n / 2), c + (n / 2))
            )) {
                numZero += divided.first
                numOne += divided.second
            }
            return Pair(numZero, numOne)
        }
    }
}