package programmers.p72415

import java.util.*
import kotlin.math.*

class Solution {
    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        var numCards = 0
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                if (board[i][j] > 0) {
                    numCards++
                }
            }
        }
        numCards /= 2

        var minCost = Int.MAX_VALUE
        for (nextCard in bfs(board, r, c) { it > 0 }) {
            val boardClone = Array(4) { i ->
                IntArray(4) { j ->
                    board[i][j]
                }
            }

            minCost = min(dfs(boardClone, nextCard, numCards), minCost)
        }
        return minCost
    }

    fun dfs(board: Array<IntArray>, firstCard: Context, numCards: Int): Int {
        var cost = 0

        val card = board[firstCard.r][firstCard.c]
        board[firstCard.r][firstCard.c] = 0
        cost += firstCard.cost + 1

        val second = bfs(board, firstCard.r, firstCard.c) { it == card }.first()

        board[second.r][second.c] = 0
        cost += second.cost + 1

        if (numCards == 1) {
            return cost
        } else {
            var minCost = Int.MAX_VALUE
            for (nextCard in bfs(board, second.r, second.c) { it > 0 }) {
                val boardClone = Array(4) { i ->
                    IntArray(4) { j ->
                        board[i][j]
                    }
                }

                minCost = min(dfs(boardClone, nextCard, numCards - 1), minCost)
            }
            return cost + minCost
        }
    }

    fun bfs(
        board: Array<IntArray>,
        r: Int, c: Int,
        predicate: (Int) -> Boolean
    ): List<Context> {
        val contexts = mutableListOf<Context>()

        val queue: Queue<Context> = LinkedList()
        queue.offer(Context(r, c, 0))

        val visited = Array(4) { BooleanArray(4) }

        while (queue.isNotEmpty()) {
            val cur = queue.remove()

            if (predicate(board[cur.r][cur.c])) {
                contexts += cur
            }

            for (i in 0 until 4) {
                val next = Context(cur.r + dr[i], cur.c + dc[i], cur.cost + 1)
                if (next.r in 0 until 4 && next.c in 0 until 4 && !visited[next.r][next.c]) {
                    visited[next.r][next.c] = true
                    queue.offer(next)
                }

                var ctrlR = cur.r
                var ctrlC = cur.c
                while (ctrlR + dr[i] in 0 until 4
                    && ctrlC + dc[i] in 0 until 4) {
                    ctrlR += dr[i]
                    ctrlC += dc[i]
                    if (board[ctrlR][ctrlC] > 0) break
                }
                if (!visited[ctrlR][ctrlC]) {
                    visited[ctrlR][ctrlC] = true
                    queue.offer(Context(ctrlR, ctrlC, cur.cost + 1))
                }
            }
        }
        return contexts
    }

    companion object {
        val dr = arrayOf(1, 0, -1, 0)
        val dc = arrayOf(0, 1, 0, -1)
    }
}

data class Context(val r: Int, val c: Int, val cost: Int)