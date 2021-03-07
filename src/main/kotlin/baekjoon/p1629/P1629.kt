package baekjoon.p1629

fun main() {
    val (a, b, c) = readLine()!!.split(" ").map(String::toInt)

    println(divideAndConquer(a, b, c))
}

fun divideAndConquer(a: Int, b: Int, c: Int): Long {
    return if (b == 1) {
        a.toLong() % c
    } else {
        val divided = divideAndConquer(a, b / 2, c)
        ((divided * divided % c) * (if (b % 2 == 1) a else 1) % c)
    }
}