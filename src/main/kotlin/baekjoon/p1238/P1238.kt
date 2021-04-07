package baekjoon.p1238

import java.util.*
import kotlin.math.max

fun main() {
    val (n, m, inputX) = readLine()!!.split(" ").map(String::toInt)
    val x = inputX - 1

    val adj = Array(n) { mutableListOf<Pair<Int, Int>>() }
    repeat(m) {
        val (v1, v2, t) = readLine()!!.split(" ").map(String::toInt)
        adj[v1 - 1].add(Pair(v2 - 1, t))
    }

    val visited = BooleanArray(n)

    val costsBack = dijkstra(n, adj, visited, x)

    var maxCost = -1
    for (i in 0 until n) {
        val costs = dijkstra(n, adj, visited, i)
        val cost = costs[x] + costsBack[i]
        maxCost = max(cost, maxCost)
    }

    println(maxCost)
}

fun dijkstra(
    n: Int,
    adj: Array<out List<Pair<Int, Int>>>,
    visited: BooleanArray,
    begin: Int
): IntArray {
    // Clear visited
    for (i in visited.indices) {
        visited[i] = false
    }

    val costs = IntArray(n) { Int.MAX_VALUE }

    val queue: Queue<Pair<Int, Int>> = PriorityQueue(compareBy { it.second })
    queue.add(Pair(begin, 0))
    visited[begin] = true

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        if (cur.second > costs[cur.first]) continue

        for (next in adj[cur.first]) {
            if (visited[next.first]) continue

            val newCost = cur.second + next.second
            if (newCost < costs[next.first]) {
                costs[next.first] = newCost
                queue.offer(Pair(next.first, newCost))
            }
        }
    }

    return costs
}