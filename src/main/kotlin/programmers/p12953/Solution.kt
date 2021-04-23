package programmers.p12953

class Solution {
    fun solution(arr: IntArray): Int {
        var answer = arr[0]
        for (i in 1 until arr.size) {
            answer = lcm(arr[i], answer)
        }
        return answer
    }

    fun lcm(a: Int, b: Int): Int {
        for (i in 2..(a * b)) {
            if (i % a == 0 && i % b == 0) {
                return i
            }
        }
        throw error("no lcm")
    }
}