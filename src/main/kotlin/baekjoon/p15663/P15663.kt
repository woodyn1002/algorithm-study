package baekjoon.p15663

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val numbers = readLine()!!.split(" ").map(String::toInt)

    val answer = linkedSetOf<String>()
    dfs(answer, m, numbers.sorted())

    answer.forEach { println(it) }
}

fun dfs(dest: MutableCollection<String>, m: Int, numbers: List<Int>, visited: List<Int> = emptyList()) {
    if (visited.size == m) {
        dest.add(visited.map { numbers[it] }.joinToString(" "))
    } else {
        for (i in numbers.indices) {
            if (i in visited) continue
            dfs(dest, m, numbers, visited + i)
        }
    }
}