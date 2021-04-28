package baekjoon.p5567

import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()

    val adj = Array(m) { mutableListOf<Int>() }
    repeat(m) {
        val segs = readLine()!!.split(" ").map(String::toInt)
        adj[segs[0] - 1].add(segs[1] - 1)
        adj[segs[1] - 1].add(segs[0] - 1)
    }

    val visited = BooleanArray(n)
    visited[0] = true

    var answer = 0

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(Pair(0, 0))
    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (next in adj[cur.second]) {
            if (visited[next]) continue

            visited[next] = true
            answer++
            if (cur.first + 1 < 2) {
                queue.offer(Pair(cur.first + 1, next))
            }
        }
    }
    println(answer)
}