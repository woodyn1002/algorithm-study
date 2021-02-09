package baekjoon.p1197

import java.util.*

fun main() {
    val v: Int
    val e: Int
    with(readLine()!!.split(' ')) {
        v = this[0].toInt()
        e = this[1].toInt()
    }

    val adj = Array(v + 1) { mutableListOf<Pair<Int, Int>>() }
    repeat(e) {
        val line = readLine()!!.split(' ')
        val a = line[0].toInt()
        val b = line[1].toInt()
        val w = line[2].toInt()

        adj[a].add(Pair(b, w))
        adj[b].add(Pair(a, w))
    }

    var answer = 0

    val visited = BooleanArray(v + 1)
    val edges = PriorityQueue<Edge>()
    edges.offer(Edge(0, 1))

    repeat(v) {
        val minEdge = edges.run {
            while (isNotEmpty()) {
                if (!visited[peek().dest]) break
                poll()
            }
            poll()
        }

        visited[minEdge.dest] = true

        answer += minEdge.weight

        for ((neighbor, weight) in adj[minEdge.dest]) {
            if (!visited[neighbor]) {
                edges.offer(Edge(weight, neighbor))
            }
        }
    }

    println(answer)
}

data class Edge(val weight: Int, val dest: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge) = weight.compareTo(other.weight)
}