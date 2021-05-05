package baekjoon.p1931

fun main() {
    val n = readLine()!!.toInt()

    val meetings = mutableListOf<IntRange>()
    repeat(n) {
        val (begin, end) = readLine()!!.split(" ").map(String::toInt)
        meetings += begin..end
    }
    meetings.sortBy { it.first }
    meetings.sortBy { it.last }

    var cnt = 0
    var end = 0
    for (meeting in meetings) {
        if (meeting.first >= end) {
            end = meeting.last
            cnt++
        }
    }

    println(cnt)
}