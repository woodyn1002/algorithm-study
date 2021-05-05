package baekjoon.p1967

fun main() {
    val n = readLine()!!.toInt()

    val adj = Array(n) { mutableListOf<Pair<Int, Int>>() }
    repeat(n - 1) {
        val (a, b, weight) = readLine()!!.split(" ").map(String::toInt)
        adj[a - 1].add(Pair(b - 1, weight))
        adj[b - 1].add(Pair(a - 1, weight))
    }

    val end1 = farest(n, 0, adj).first
    println(farest(n, end1, adj).second)
}

fun farest(
    n: Int,
    node: Int,
    adj: Array<out List<Pair<Int, Int>>>,
    visited: BooleanArray = BooleanArray(n),
    sumWeights: Int = 0
): Pair<Int, Int> {
    val newVisited = visited.clone()
    newVisited[node] = true

    var max: Pair<Int, Int>? = null
    for ((neighbor, weight) in adj[node]) {
        if (newVisited[neighbor]) continue

        val result = farest(
            n = n,
            node = neighbor,
            adj = adj,
            visited = newVisited,
            sumWeights = sumWeights + weight
        )

        if (max == null || result.second > max.second) {
            max = result
        }
    }

    return max ?: Pair(node, sumWeights)
}