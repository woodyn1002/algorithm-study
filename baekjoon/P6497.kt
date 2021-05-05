package baekjoon.p6497

fun main() {
    while (true) {
        val m: Int
        val n: Int
        with(readLine()!!.split(' ')) {
            m = this[0].toInt()
            n = this[1].toInt()
        }

        if (m == 0 && n == 0)
            break

        val edges = mutableListOf<Edge>()
        repeat(n) {
            val line = readLine()!!.split(' ').map(String::toInt)
            edges += Edge(line[0], line[1], line[2])
        }
        edges.sortBy(Edge::weight)

        val set = IntArray(m) { i -> i }
        var sumWeight = 0
        for (edge in edges) {
            if (find(set, edge.u) != find(set, edge.v)) {
                union(set, edge.u, edge.v)
                sumWeight += edge.weight
            }
        }

        println(edges.sumOf(Edge::weight) - sumWeight)
    }
}

fun union(set: IntArray, x: Int, y: Int) {
    val uX = find(set, x)
    val uY = find(set, y)
    if (uX != uY) {
        set[uY] = uX
    }
}

fun find(set: IntArray, node: Int): Int =
    if (set[node] != node) {
        val parent = find(set, set[node])
        set[node] = parent
        parent
    } else {
        node
    }

data class Edge(val u: Int, val v: Int, val weight: Int)