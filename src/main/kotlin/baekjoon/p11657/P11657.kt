package baekjoon.p11657

const val INF = Long.MAX_VALUE

fun main() {
    val n: Int
    val m: Int
    with(readLine()!!.split(' ')) {
        n = this[0].toInt()
        m = this[1].toInt()
    }

    val adj = Array<MutableList<Pair<Int, Int>>>(n + 1) { mutableListOf() }
    repeat(m) {
        val line = readLine()!!.split(' ').map(String::toInt)
        adj[line[0]].add(Pair(line[1], line[2]))
    }

    val dist = LongArray(n + 1) { INF }
    for ((neighbor, weight) in adj[1])
        dist[neighbor] = weight.toLong()
    dist[1] = 0

    var negCycle = false
    for (i in 1..n) {
        for (j in 1..n) {
            if (dist[j] >= INF) continue
            for ((neighbor, weight) in adj[j]) {
                if (dist[j] + weight < dist[neighbor]) {
                    dist[neighbor] = dist[j] + weight

                    if (i == n)
                        negCycle = true
                }
            }
        }
    }

    if (negCycle) {
        println(-1)
    } else {
        for (v in 2..n) {
            val time = if (dist[v] < INF) dist[v] else -1
            println(time)
        }
    }
}