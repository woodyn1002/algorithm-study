package baekjoon.p16236

import java.util.*

fun main() {
    val n = readLine()!!.toInt()

    val area = Array(n) {
        readLine()!!.split(" ").map(String::toInt).toIntArray()
    }

    var pos = Pair(0, 0)
    var numFish = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (area[i][j] == 9)
                pos = Pair(i, j)
            else if (area[i][j] != 0)
                numFish++
        }
    }
    area[pos.first][pos.second] = 0

    var answer = 0
    var size = 2
    var numEaten = 0
    while (numFish > 0) {
        val next = nextFish(n, area, pos, size) ?: break

        answer += next.cost
        pos = next.pos
        area[next.pos.first][next.pos.second] = 0

        numEaten++
        if (numEaten >= size) {
            size++
            numEaten = 0
        }

        numFish--
    }

    println(answer)
}

fun nextFish(n: Int, area: Array<IntArray>, pos: Pair<Int, Int>, size: Int): Context? {
    val visited = Array(n) { BooleanArray(n) }

    val contexts = mutableListOf<Context>()
    var minCost = Int.MAX_VALUE

    val queue: Queue<Context> = LinkedList()
    queue.add(Context(pos, 0))

    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        if (cur.cost > minCost) continue

        if (area[cur.pos.first][cur.pos.second] in 1 until size) {
            contexts += cur
            minCost = cur.cost
            continue
        }

        for (nextPos in arrayOf(
            Pair(cur.pos.first - 1, cur.pos.second),
            Pair(cur.pos.first, cur.pos.second - 1),
            Pair(cur.pos.first, cur.pos.second + 1),
            Pair(cur.pos.first + 1, cur.pos.second)
        )) {
            if (nextPos.first !in 0 until n || nextPos.second !in 0 until n) continue
            if (area[nextPos.first][nextPos.second] > size) continue
            if (visited[nextPos.first][nextPos.second]) continue

            visited[nextPos.first][nextPos.second] = true
            queue.offer(Context(nextPos, cur.cost + 1))
        }
    }

    contexts.sort()
    return contexts.firstOrNull()
}

data class Context(val pos: Pair<Int, Int>, val cost: Int) : Comparable<Context> {
    override fun compareTo(other: Context): Int {
        return if (this.pos.first == other.pos.first)
            this.pos.second.compareTo(other.pos.second)
        else
            this.pos.first.compareTo(other.pos.first)
    }
}