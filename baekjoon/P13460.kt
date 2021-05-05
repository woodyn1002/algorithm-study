package baekjoon.p13460

import java.util.*

val dr = arrayOf(1, 0, -1, 0)
val dc = arrayOf(0, 1, 0, -1)

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val map = Array(n) { IntArray(m) }
    var redPos = Pair(0, 0)
    var bluePos = Pair(0, 0)

    for (i in 0 until n) {
        for ((j, c) in readLine()!!.withIndex()) {
            map[i][j] = when (c) {
                '#' -> 1
                'O' -> 2
                else -> 0
            }

            if (c == 'R') redPos = Pair(i, j)
            if (c == 'B') bluePos = Pair(i, j)
        }
    }

    val queue: Queue<Context> = LinkedList()
    queue.offer(Context(redPos, bluePos, 0))
    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        val newCost = cur.cost + 1
        for (i in 0 until 4) {
            val dir = Pair(dr[i], dc[i])

            val beads = mutableListOf(Pair('R', cur.red), Pair('B', cur.blue))
            when (i) {
                0 -> beads.sortByDescending { it.second.first }
                1 -> beads.sortByDescending { it.second.second }
                2 -> beads.sortBy { it.second.first }
                3 -> beads.sortBy { it.second.second }
            }

            val result = mutableMapOf<Char, Pair<Int, Int>>()

            var otherBead: Pair<Int, Int>? = null
            for ((color, bead) in beads) {
                var curPos = bead
                while (true) {
                    if (map[curPos.first][curPos.second] == 2) break

                    val next = Pair(curPos.first + dir.first, curPos.second + dir.second)
                    if (next.first !in 0 until n || next.second !in 0 until m) break
                    if (map[next.first][next.second] == 1) break
                    if (otherBead != null && next == otherBead) break
                    curPos = next
                }

                if (map[curPos.first][curPos.second] != 2) {
                    result[color] = curPos
                    otherBead = curPos
                }
            }

//            if (newCost == 1 && i == 3) {
//                println("newCost=$newCost, dir=$i..")
//                println("red: ${result['R']}, blue: ${result['B']}")
//                for (r in 0 until n) {
//                    for (c in 0 until m) {
//                        if (Pair(r, c) == result['R']) print("R")
//                        else if (Pair(r, c) == result['B']) print("B")
//                        else print(map[r][c])
//                    }
//                    println()
//                }
//                println("..")
//            }
            if (result['B'] == null) continue
            if (result['R'] == null) {
                println(newCost)
                return
            }
            if (newCost < 10) {
                val newRed = result['R']!!
                val newBlue = result['B']!!
                if (cur.red == newRed && cur.blue == newBlue) continue
                queue.offer(Context(newRed, newBlue, newCost))
            }
        }
    }
    println("-1")
}

data class Context(val red: Pair<Int, Int>, val blue: Pair<Int, Int>, val cost: Int)