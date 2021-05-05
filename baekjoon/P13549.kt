package baekjoon.p13549

import java.util.*

const val MAX_NODE = 100_000

fun main() {
    val (n, k) = readLine()!!.split(" ").map(String::toInt)

    val dist = IntArray(MAX_NODE + 1) { MAX_NODE + 1 }
    dist[n] = 0

    val pq = PriorityQueue<Node>(compareBy { it.dist })
    pq.offer(Node(n, 0))

    val visited = BooleanArray(MAX_NODE + 1)

    while (pq.isNotEmpty()) {
        val node = pq.poll()
        if (visited[node.num]) continue

        visited[node.num] = true

        for (neighbor in listOf(
            Node(node.num - 1, 1),
            Node(node.num + 1, 1),
            Node(node.num * 2, 0)
        )) {
            if (neighbor.num !in 0..MAX_NODE) continue

            val newDist = dist[node.num] + neighbor.dist
            if (newDist < dist[neighbor.num]) {
                dist[neighbor.num] = newDist
                pq.offer(Node(neighbor.num, newDist))
            }
        }
    }

    println(dist[k])
}

data class Node(val num: Int, val dist: Int)