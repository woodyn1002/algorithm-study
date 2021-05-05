package baekjoon.p17298

import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(' ').map(String::toInt)

    val nge = IntArray(n)
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

    for ((index, element) in a.withIndex()) {
        while (pq.isNotEmpty() && element > pq.peek().first) {
            val popped = pq.remove()
            nge[popped.second] = element
        }

        pq.add(Pair(element, index))
    }

    while (pq.isNotEmpty()) {
        val popped = pq.remove()
        nge[popped.second] = -1
    }

    println(nge.joinToString(separator = " "))
}