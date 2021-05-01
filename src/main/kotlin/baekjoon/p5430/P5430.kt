package baekjoon.p5430

import java.lang.StringBuilder
import java.util.*

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val p = readLine()!!.toCharArray()
        val n = readLine()!!.toInt()

        var arr = readLine()!!
        arr = arr.substring(1, arr.length - 1)

        val deque: Deque<Int> = ArrayDeque()
        if (arr.isNotEmpty()) {
            deque.addAll(arr.split(",").map(String::toInt))
        }

        var reversed = false
        var error = false
        for (func in p) {
            when (func) {
                'R' -> reversed = !reversed
                'D' -> {
                    if (deque.isEmpty()) {
                        error = true
                        break
                    }
                    if (reversed) deque.removeLast() else deque.removeFirst()
                }
            }
        }

        val answer =
        if (error) {
            "error"
        } else {
            val builder = StringBuilder("[")
            while (deque.isNotEmpty()) {
                builder.append(if (reversed) deque.removeLast() else deque.removeFirst())
                if (deque.isNotEmpty())
                    builder.append(",")
            }
            builder.append("]")
            builder.toString()
        }

        println(answer)
    }
}