package baekjoon.p15657

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val numbers = readLine()!!.split(" ").map(String::toInt)

    dfs(numbers.sorted(), m)
}

fun dfs(numbers: List<Int>, m: Int, visited: List<Int> = emptyList(), begin: Int = 0) {
    if (visited.size == m) {
        println(visited.joinToString(" "))
    } else {
        for (i in begin until numbers.size) {
            val next = numbers[i]
            dfs(numbers, m, visited + next, i)
        }
    }
}