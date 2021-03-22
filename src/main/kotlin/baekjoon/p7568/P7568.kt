package baekjoon.p7568

fun main() {
    val n = readLine()!!.toInt()
    val body = Array(n) {
        val (x, y) = readLine()!!.split(" ").map(String::toInt)
        Pair(x, y)
    }

    val answer = IntArray(n)
    for (i in 0 until n) {
        var cntBigger = 0
        for (j in 0 until n) {
            if (body[j].first > body[i].first && body[j].second > body[i].second) {
                cntBigger++
            }
        }
        answer[i] = (cntBigger + 1)
    }

    println(answer.joinToString(" "))
}