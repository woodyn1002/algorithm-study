package baekjoon.p15652

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)

    for (case in dfs(n, m)) {
        println(case.joinToString(" "))
    }
}

fun dfs(n: Int, m: Int, visited: List<Int> = emptyList(), begin: Int = 1): List<List<Int>> =
    if (visited.size == m) {
        listOf(visited)
    } else {
        val list = mutableListOf<List<Int>>()
        for (i in begin..n) {
            list += dfs(n, m, visited + i, i)
        }
        list
    }