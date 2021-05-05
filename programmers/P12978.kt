package programmers.p12978

import java.util.*

class Solution {
    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        val costs = IntArray(N) { Int.MAX_VALUE }
        costs[0] = 0

        val adjMatrix = Array(N) { IntArray(N) { Int.MAX_VALUE } }
        for ((a, b, c) in road) {
            if (c < adjMatrix[a - 1][b - 1]) {
                adjMatrix[a - 1][b - 1] = c
                adjMatrix[b - 1][a - 1] = c
            }
        }

        val adjList = Array(N) { mutableListOf<Pair<Int, Int>>() }
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (adjMatrix[i][j] < Int.MAX_VALUE) {
                    adjList[i].add(Pair(j, adjMatrix[i][j]))
                }
            }
        }

        val pq: Queue<Pair<Int, Int>> = PriorityQueue(compareBy { it.first })
        pq.offer(Pair(0, 0))

        while (pq.isNotEmpty()) {
            val cur = pq.poll()
            if (cur.first > costs[cur.second])
                continue

            for ((node, cost) in adjList[cur.second]) {
                val newCost = cur.first + cost
                if (newCost < costs[node]) {
                    costs[node] = newCost
                    pq.offer(Pair(newCost, node))
                }
            }
        }

        return costs.count { it <= k }
    }
}