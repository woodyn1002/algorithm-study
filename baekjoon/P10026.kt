package baekjoon.p10026

import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val grid = Array(n) { readLine()!!.toCharArray() }

    val answer1 = countAreas(n, grid)

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (grid[i][j] == 'G')
                grid[i][j] = 'R'
        }
    }
    val answer2 = countAreas(n, grid)

    println("$answer1 $answer2")
}

fun countAreas(n: Int, grid: Array<CharArray>): Int {
    val queue: Queue<Pair<Int, Int>> = LinkedList()

    val visited = Array(n) { BooleanArray(n) }
    var cnt = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (visited[i][j]) continue

            cnt++
            queue.offer(Pair(i, j))

            while (queue.isNotEmpty()) {
                val pos = queue.remove()
                if (visited[pos.first][pos.second]) continue

                visited[pos.first][pos.second] = true

                for (next in listOf(
                    pos.copy(first = pos.first - 1),
                    pos.copy(first = pos.first + 1),
                    pos.copy(second = pos.second - 1),
                    pos.copy(second = pos.second + 1),
                )) {
                    if (next.first in 0 until n && next.second in 0 until n
                        && grid[next.first][next.second] == grid[i][j]
                        && !visited[next.first][next.second]) {
                        queue.offer(next)
                    }
                }
            }
        }
    }
    return cnt
}