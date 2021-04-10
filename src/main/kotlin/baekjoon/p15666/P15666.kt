package baekjoon.p15666

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val numbers = readLine()!!.split(" ").map(String::toInt)

    val answer = linkedSetOf<String>()
    dfs(answer, numbers.sorted(), m)

    answer.forEach { println(it) }
}

fun dfs(
    dest: MutableCollection<String>,
    numbers: List<Int>,
    m: Int,
    visited: List<Int> = emptyList(),
    begin: Int = 0
) {
    if (visited.size == m) {
        dest.add(visited.map { numbers[it] }.joinToString(" "))
    } else {
        for (i in begin until numbers.size) {
            dfs(dest, numbers, m, visited + i, i)
        }
    }
}