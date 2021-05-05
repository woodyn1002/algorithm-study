package baekjoon.p1865

fun main() {
    val tc = readLine()!!.toInt()
    repeat(tc) {
        testcase()
    }
}

fun testcase() {
    val (n, m, w) = readLine()!!.split(" ").map(String::toInt)

    val adj = Array(n) { mutableListOf<Neighbor>() }

    repeat(m) {
        val (s, e, t) = readLine()!!.split(" ").map(String::toInt)
        adj[s - 1].add(Neighbor(e - 1, t))
        adj[e - 1].add(Neighbor(s - 1, t))
    }
    repeat(w) {
        val (s, e, t) = readLine()!!.split(" ").map(String::toInt)
        adj[s - 1].add(Neighbor(e - 1, -t))
    }

    println(if (hasNegativeCycle(n, adj)) "YES" else "NO")
}

fun hasNegativeCycle(n: Int, adj: Array<out List<Neighbor>>): Boolean {
    val dist = IntArray(n) { 10001 }

    for (i in 0..n) {
        for (u in 0 until n) {
            for (next in adj[u]) {
                if (dist[u] + next.weight < dist[next.node]) {
                    if (i == n) return true
                    dist[next.node] = dist[u] + next.weight
                }
            }
        }
    }
    return false
}

data class Neighbor(val node: Int, val weight: Int)