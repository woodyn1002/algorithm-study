package baekjoon.p11725

import java.util.*

fun main() {
    val n = readLine()!!.toInt()

    val adj = Array(n) { mutableListOf<Int>() }
    repeat(n - 1) {
        val (a, b) = readLine()!!.split(" ").map(String::toInt).map { it - 1 }
        adj[a].add(b)
        adj[b].add(a)
    }

    val parents = IntArray(n)

    val visited = BooleanArray(n)
    val queue: Queue<Int> = LinkedList()
    queue.offer(0)

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        visited[node] = true

        for (neighbor in adj[node]) {
            if (visited[neighbor]) continue

            parents[neighbor] = node
            queue.offer(neighbor)
        }
    }

    for ((node, parent) in parents.withIndex()) {
        if (node == 0) continue
        println(parent + 1)
    }
}