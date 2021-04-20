package programmers.p42889

class Solution {
    fun solution(N: Int, stages: IntArray): IntArray {
        val numChallenging = IntArray(N + 1)
        for (stage in stages) {
            numChallenging[stage - 1]++
        }

        val numVisited = IntArray(N + 1)
        numVisited[N] = numChallenging[N]
        for (i in N - 1 downTo 0) {
            numVisited[i] = numVisited[i + 1] + numChallenging[i]
        }

        return (0 until N)
            .sortedByDescending {
                if (numVisited[it] > 0) numChallenging[it].toDouble() / numVisited[it] else 0.0
            }
            .map { it + 1}
            .toIntArray()
    }
}