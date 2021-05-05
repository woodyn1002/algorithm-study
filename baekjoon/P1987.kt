package baekjoon.p1987

import kotlin.math.max

val dy = arrayOf(1, 0, -1, 0)
val dx = arrayOf(0, 1, 0, -1)

fun main() {
    val (r, c) = readLine()!!.split(" ").map(String::toInt)

    val map = Array(r) {
        readLine()!!.toCharArray()
    }

    val answer = dfs(map, r, c, 0, 0)
    println(answer)
}

fun dfs(
    map: Array<CharArray>,
    r: Int,
    c: Int,
    y: Int,
    x: Int,
    visited: BooleanArray = BooleanArray(26).apply { this[map[y][x].toInt() - 'A'.toInt()] = true },
    cost: Int = 1,
): Int {
    var maxCost: Int? = null
    for (i in 0 until 4) {
        val ny = y + dy[i]
        val nx = x + dx[i]
        if (ny !in 0 until r || nx !in 0 until c) continue

        val alphabet = map[ny][nx]
        if (visited[alphabet.toInt() - 'A'.toInt()]) continue
        val newVisited = visited.clone().apply { this[alphabet.toInt() - 'A'.toInt()] = true }

        maxCost = max(dfs(map, r, c, ny, nx, newVisited, cost + 1), maxCost ?: -1)
    }
    return maxCost ?: cost
}