package baekjoon.p1753

import kotlin.math.min

const val INF = Int.MAX_VALUE

fun main() {
    val v: Int
    val e: Int
    with(readLine()!!.split(' ')) {
        v = this[0].toInt()
        e = this[1].toInt()
    }

    val k = readLine()!!.toInt()

    val adj = Array<MutableList<Neighbor>>(v + 1) { mutableListOf() }
    repeat(e) {
        val line = readLine()!!.split(' ')
        adj[line[0].toInt()].add(
            Neighbor(line[1].toInt(), line[2].toInt())
        )
    }

    val dist = IntArray(v + 1) { INF }
    dist[k] = 0
    for (neighbor in adj[k]) {
        dist[neighbor.node] = neighbor.weight
    }

    val visited = BooleanArray(v + 1)
    var cur = k
    while (cur != 0) {
        visited[cur] = true

        for (neighbor in adj[cur]) {
            val dest = neighbor.node
            dist[dest] = min(dist[dest], dist[cur] + neighbor.weight)
        }

        var next = 0
        for (i in 1..v) {
            if (!visited[i] && dist[i] < dist[next]) {
                next = i
            }
        }
        cur = next
    }

    for (i in 1..v) {
        val w = if (dist[i] < INF) dist[i].toString() else "INF"
        println(w)
    }
}

data class Neighbor(val node: Int, val weight: Int)