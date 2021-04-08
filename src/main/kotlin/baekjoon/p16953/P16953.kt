package baekjoon.p16953

import java.util.*

fun main() {
    val (a, b) = readLine()!!.split(" ").map(String::toInt)

    val dist = mutableMapOf<Long, Int>()

    val queue: Queue<Pair<Long, Int>> = PriorityQueue(compareBy { it.second })
    queue.offer(Pair(a.toLong(), 0))

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        if (cur.second > dist[cur.first] ?: Int.MAX_VALUE) continue

        for (next in arrayOf(cur.first * 2, cur.first * 10 + 1)) {
            if (next > b) continue

            val newDist = cur.second + 1
            if (newDist < dist[next] ?: Int.MAX_VALUE) {
                dist[next] = newDist
                queue.offer(Pair(next, newDist))
            }
        }
    }

    println(dist[b.toLong()]?.let { it + 1 } ?: -1)
}