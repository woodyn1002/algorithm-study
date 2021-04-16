package baekjoon.p1261

import java.util.*

val dr = intArrayOf(1, 0, -1, 0)
val dc = intArrayOf(0, 1, 0, -1)

fun main() {
    val (m, n) = readLine()!!.split(" ").map(String::toInt)
    val map = Array(n) {
        readLine()!!.toCharArray().map(Character::getNumericValue).toIntArray()
    }

    val costs = Array(n) { IntArray(m) { Int.MAX_VALUE } }

    val pq: Queue<Context> = PriorityQueue(compareBy { it.cost })
    pq.offer(Context(0, 0, 0))

    var answer = 0
    loop@while (pq.isNotEmpty()) {
        val cur = pq.poll()
        if (cur.cost > costs[cur.r][cur.c]) continue

        for (i in 0 until 4) {
            val nr = cur.r + dr[i]
            val nc = cur.c + dc[i]
            if (nr !in 0 until n || nc !in 0 until m) continue

            val newCost = cur.cost + if (map[nr][nc] == 1) 1 else 0
            if (newCost < costs[nr][nc]) {
                costs[nr][nc] = newCost

                if (nr == n - 1 && nc == m - 1) {
                    answer = newCost
                    break@loop
                } else {
                    pq.offer(Context(nr, nc, newCost))
                }
            }
        }
    }

    println(answer)
}

data class Context(val r: Int, val c: Int, val cost: Int)