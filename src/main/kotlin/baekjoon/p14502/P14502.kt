package baekjoon.p14502

import java.util.*

fun main() {
    val (n, m) = readLine()!!.split(' ').map(String::toInt)
    val map = Array(n) { readLine()!!.split(' ').map(String::toInt).toIntArray() }

    var answer = 0
    for (case in everyCase(n, m, map)) {
        val result = simulated(n, m, map, case)

        var cnt = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (result[i][j] == 0)
                    cnt++
            }
        }
        if (cnt > answer)
            answer = cnt
    }

    println(answer)
}

fun everyCase(n: Int, m: Int, map: Array<IntArray>): List<List<Pair<Int, Int>>> {
    val list = mutableListOf<List<Pair<Int, Int>>>()

    val pairs = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (map[i][j] == 0) {
                pairs += Pair(i, j)
            }
        }
    }

    combination(pairs, list, emptyList(), 3, 0)
    return list
}

fun <T> combination(
    src: List<T>,
    dest: MutableList<List<T>>,
    visited: List<T>,
    cnt: Int,
    begin: Int
) {
    if (cnt == 0) {
        dest += visited
    } else {
        for (i in begin until src.size) {
            if (src[i] !in visited) {
                combination(src, dest, visited + src[i], cnt - 1, i + 1)
            }
        }
    }
}

fun simulated(n: Int, m: Int, map: Array<IntArray>, walls: List<Pair<Int, Int>>): Array<IntArray> {
    val queue: Queue<Pair<Int, Int>> = LinkedList()

    val result = Array(n) { i ->
        IntArray(m) { j ->
            if (map[i][j] == 2)
                queue.offer(Pair(i, j))
            map[i][j]
        }
    }
    for (wall in walls) {
        result[wall.first][wall.second] = 1
    }

    val offerIfValid = { pos: Pair<Int, Int> ->
        if (pos.first in 0 until n && pos.second in 0 until m &&
                result[pos.first][pos.second] == 0) {
            queue.offer(pos)
        }
    }
    while (queue.isNotEmpty()) {
        val pos = queue.poll()
        result[pos.first][pos.second] = 2

        offerIfValid(pos.copy(first = pos.first + 1))
        offerIfValid(pos.copy(first = pos.first - 1))
        offerIfValid(pos.copy(second = pos.second + 1))
        offerIfValid(pos.copy(second = pos.second - 1))
    }
    return result
}