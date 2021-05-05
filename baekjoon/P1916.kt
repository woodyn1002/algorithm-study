package baekjoon.p1916

import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()

    val adj = Array<MutableList<Pair<Int, Int>>>(n + 1) { mutableListOf() }
    repeat(m) {
        val line = readLine()!!.split(' ').map(String::toInt)
        adj[line[0]].add(Pair(line[1], line[2]))
    }

    val depart: Int
    val dest: Int
    with(readLine()!!.split(' ')) {
        depart = this[0].toInt()
        dest = this[1].toInt()
    }

    val dist = IntArray(n + 1) { Int.MAX_VALUE }
    for ((neighbor, weight) in adj[depart])
        dist[neighbor] = weight
    dist[depart] = 0

    val pq: Queue<Pair<Int, Int>> = PriorityQueue(compareBy { it.second })
    for ((neighbor, weight) in adj[depart])
        pq.offer(Pair(neighbor, weight))
    pq.offer(Pair(depart, 0))

    val visited = BooleanArray(n + 1)
    while (pq.isNotEmpty()) {
        val (v, _) = pq.poll()
        if (visited[v]) continue
        visited[v] = true

        for ((neighbor, weight) in adj[v]) {
            if (dist[v] + weight < dist[neighbor]) {
                dist[neighbor] = dist[v] + weight
                pq.offer(Pair(neighbor, dist[neighbor]))
            }
        }
    }

    println(dist[dest])
}