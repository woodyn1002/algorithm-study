package programmers.p67259

import java.util.*

val dr = arrayOf(1, 0, -1, 0)
val dc = arrayOf(0, 1, 0, -1)

class Solution {
    fun solution(board: Array<IntArray>): Int {
        val n = board.size

        val answer = dijkstra(n, board)
        return answer
    }

    fun dijkstra(n: Int, board: Array<IntArray>): Int {
        val costs = Array(n) {
            Array(n) {
                IntArray(2) { Int.MAX_VALUE }
            }
        }
        costs[0][0][0] = 0
        costs[0][0][1] = 0

        val pq: Queue<Context> = PriorityQueue(compareBy { it.cost })
        pq.offer(Context(0, 0, 0, 0))
        pq.offer(Context(0, 0, 0, 1))
        while (pq.isNotEmpty()) {
            val cur = pq.poll()
            // println("cur=$cur..")
            if (cur.cost > costs[cur.r][cur.c][cur.dir])
                continue

            for (i in 0 until 4) {
                val nr = cur.r + dr[i]
                val nc = cur.c + dc[i]
                if (nr !in 0 until n || nc !in 0 until n || board[nr][nc] == 1)
                    continue

                val moveDir = i % 2 // 0: veritcal, 1: horizontal
                val nextCost = cur.cost + 100 + if (cur.dir == moveDir || cur.dir == -1) 0 else 500
                if (nextCost < costs[nr][nc][moveDir]) {
                    costs[nr][nc][moveDir] = nextCost
                    if (nr != n - 1 || nc != n - 1) {
                        pq.offer(Context(nr, nc, nextCost, moveDir))
                    }
                }
            }
        }
        return costs[n - 1][n - 1].min()!!
    }

    data class Context(val r: Int, val c: Int, val cost: Int, val dir: Int)
}