package baekjoon.p1167

fun main() {
    val v = readLine()!!.toInt()

    val adj = Array(v) { mutableListOf<Pair<Int, Int>>() }
    repeat(v) {
        val line = readLine()!!.split(" ").map(String::toInt)

        var i = 1
        while (line[i] != -1) {
            adj[line[0] - 1].add(Pair(line[i] - 1, line[i + 1]))
            adj[line[i] - 1].add(Pair(line[0] - 1, line[i + 1]))
            i += 2
        }
    }

    val farestA = farest(adj, BooleanArray(v), 0)
    val farestB = farest(adj, BooleanArray(v), farestA.first)
    println(farestB.second)
}

fun farest(
    adj: Array<out List<Pair<Int, Int>>>,
    visited: BooleanArray,
    node: Int,
    sumOfWeight: Int = 0
): Pair<Int, Int> {
    visited[node] = true

    var max: Pair<Int, Int>? = null
    for (next in adj[node]) {
        if (visited[next.first]) continue

        val result = farest(adj, visited, next.first, sumOfWeight + next.second)
        if (result.second > max?.second ?: -1) {
            max = result
        }
    }

    return max ?: Pair(node, sumOfWeight)
}