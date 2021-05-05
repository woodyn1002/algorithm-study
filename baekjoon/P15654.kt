package baekjoon.p15654

fun main() {
    val (_, m) = readLine()!!.split(" ").map(String::toInt)
    val numbers = readLine()!!.split(" ").map(String::toInt).sorted()

    for (seq in sequences(m, numbers)) {
        println(seq.joinToString(" "))
    }
}

fun sequences(m: Int, numbers: List<Int>, visited: List<Int> = emptyList()): List<List<Int>> =
    if (visited.size == m) {
        listOf(visited)
    } else {
        val list = mutableListOf<List<Int>>()
        for (num in numbers) {
            if (num in visited) continue
            list += sequences(m, numbers, visited + num)
        }
        list
    }