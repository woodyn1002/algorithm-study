package baekjoon.p14500

import kotlin.math.max

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val paper = Array(n) { readLine()!!.split(" ").map(String::toInt).toIntArray() }

    var maxScore = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            maxScore = maxOf(
                maxScore,
                bestDfsScore(paper, n, m, Pair(i, j)),
                bestConvexScore(paper, n, m, Pair(i, j))
            )
        }
    }

    println(maxScore)
}

fun bestDfsScore(
    paper: Array<IntArray>,
    n: Int, m: Int,
    pos: Pair<Int, Int>,
    prevVisited: Set<Pair<Int, Int>> = emptySet()
): Int {
    val visited = prevVisited + pos

    if (visited.size == 4) {
        return visited.sumOf { paper[it.first][it.second] }
    } else {
        var maxScore = -1
        for (next in listOf(
            pos.copy(first = pos.first + 1),
            pos.copy(first = pos.first - 1),
            pos.copy(second = pos.second + 1),
            pos.copy(second = pos.second - 1)
        )) {
            if (isValid(n, m, next) && next !in visited) {
                maxScore = max(maxScore, bestDfsScore(paper, n, m, next, visited))
            }
        }
        return maxScore
    }
}

fun bestConvexScore(
    paper: Array<IntArray>,
    n: Int, m: Int,
    pos: Pair<Int, Int>
): Int {
    var maxScore = -1
    for (case in listOf(
        listOf(
            pos,
            pos.copy(second = pos.second + 1),
            pos.copy(second = pos.second + 2),
            pos.copy(first = pos.first + 1, second = pos.second + 1)
        ),
        listOf(
            pos,
            pos.copy(second = pos.second + 1),
            pos.copy(second = pos.second + 2),
            pos.copy(first = pos.first - 1, second = pos.second + 1)
        ),
        listOf(
            pos,
            pos.copy(first = pos.first + 1),
            pos.copy(first = pos.first + 2),
            pos.copy(first = pos.first + 1, second = pos.second + 1)
        ),
        listOf(
            pos,
            pos.copy(first = pos.first + 1),
            pos.copy(first = pos.first + 2),
            pos.copy(first = pos.first + 1, second = pos.second - 1)
        )
    )) {
        if (case.any { !isValid(n, m, it) }) continue
        maxScore = max(maxScore, case.sumOf { paper[it.first][it.second] })
    }
    return maxScore
}

fun isValid(n: Int, m: Int, pos: Pair<Int, Int>): Boolean =
    (pos.first in 0 until n && pos.second in 0 until m)