package baekjoon.p15650

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)

    for (seq in dfs(n, m)) {
        println(seq.joinToString(" "))
    }
}

fun dfs(n: Int, m: Int, visited: List<Int> = emptyList(), begin: Int = 1): List<List<Int>> =
    if (visited.size == m) {
        listOf(visited)
    } else {
        if (n - begin + 1 < m - visited.size) {
            emptyList()
        } else {
            val list = mutableListOf<List<Int>>()
            for (i in begin..n) {
                list += dfs(n, m, visited + i, i + 1)
            }
            list
        }
    }