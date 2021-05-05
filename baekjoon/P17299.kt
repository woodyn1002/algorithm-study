package baekjoon.p17299

import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map(String::toInt)

    val f = mutableMapOf<Int, Int>()
    for (element in a) {
        f[element] = f.getOrDefault(element, 0) + 1
    }

    val ngf = IntArray(n) { -1 }

    val stack: Deque<Pair<Int, Int>> = ArrayDeque()
    for ((index, element) in a.withIndex()) {
        while (stack.isNotEmpty() && f[stack.peek().second]!! < f[element]!!) {
            ngf[stack.pop().first] = element
        }
        stack.push(Pair(index, element))
    }

    println(ngf.joinToString(separator = " "))
}