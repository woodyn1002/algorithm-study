package baekjoon.p2206

import java.util.*

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val map = Array(n) { readLine()!!.map { Character.getNumericValue(it) }.toIntArray() }

    val costs = Array(n) {
        Array(m) {
            IntArray(2) { -1 }
        }
    }

    val queue: Queue<Context> = LinkedList()
    queue.offer(Context(0, 0, true, 1))

    while (queue.isNotEmpty()) {
        val context = queue.poll()
        if (costs[context.i][context.j][context.hasChance.toInt()] != -1)
            continue

        costs[context.i][context.j][context.hasChance.toInt()] = context.cost

        for (offset in arrayOf(
            Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1)
        )) {
            val nextPos = Pair(context.i + offset.first, context.j + offset.second)
            if (nextPos.first !in 0 until n || nextPos.second !in 0 until m)
                continue

            if (map[nextPos.first][nextPos.second] == 1) {
                if (context.hasChance) {
                    val next = Context(
                        nextPos.first, nextPos.second,
                        false, context.cost + 1
                    )
                    if (costs[next.i][next.j][next.hasChance.toInt()] == -1) {
                        queue.offer(next)
                    }
                }
            } else {
                val next = context.copy(
                    i = nextPos.first, j = nextPos.second,
                    cost = context.cost + 1
                )
                if (costs[next.i][next.j][next.hasChance.toInt()] == -1) {
                    queue.offer(next)
                }
            }
        }
    }

    val answer = costs[n - 1][m - 1]
        .filterNot { it == -1 }
        .minOrNull() ?: -1
    println(answer)
}

fun Boolean.toInt(): Int = if (this) 1 else 0

data class Context(
    val i: Int,
    val j: Int,
    val hasChance: Boolean,
    val cost: Int
)