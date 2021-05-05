package programmers.p12949

class Solution {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val nr = arr1.size
        val nc = arr2[0].size
        val answer = Array(nr) { IntArray(nc) }
        for (r in 0 until nr) {
            for (c in 0 until nc) {
                for (i in 0 until arr1[0].size) {
                    answer[r][c] += arr1[r][i] * arr2[i][c]
                }
            }
        }
        return answer
    }
}