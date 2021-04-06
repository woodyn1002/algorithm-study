package baekjoon.p5639

fun main() {
    val numbers = mutableListOf<Int>()
    while (true) {
        val line = readLine() ?: break
        if (line.isEmpty()) break
        numbers.add(line.toInt())
    }

    postOrder(numbers)
}

fun postOrder(numbers: List<Int>, begin: Int = 0, end: Int = numbers.size) {
    if (begin >= end) return

    val root = numbers[begin]
    var mid = end
    for (i in (begin + 1) until end) {
        if (numbers[i] > root) {
            mid = i
            break
        }
    }

    postOrder(numbers, begin + 1, mid)
    postOrder(numbers, mid, end)
    println(root)
}