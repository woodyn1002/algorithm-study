package baekjoon.p3190

import java.util.*


fun main() {
    val n = readLine()!!.toInt()
    val map = Array(n) { IntArray(n) }

    val k = readLine()!!.toInt()
    repeat(k) {
        val (i, j) = readLine()!!.split(" ").map(String::toInt)
        map[i - 1][j - 1] = 1
    }

    val l = readLine()!!.toInt()
    val turn = mutableMapOf<Int, Char>()
    repeat(l) {
        val (i, j) = readLine()!!.split(" ")
        turn[i.toInt()] = j.first()
    }

    var dir = 1
    var sec = 1
    val deque: Deque<Pair<Int, Int>> = ArrayDeque()
    deque.offer(Pair(0, 0))
    map[0][0] = 2
    while (true) {
        val head = deque.first

        val next = nextOf(head, dir)
        if (next.first !in 0 until n || next.second !in 0 until n || map[next.first][next.second] == 2)
            break

        if (map[next.first][next.second] != 1) {
            val tail = deque.removeLast()
            map[tail.first][tail.second] = 0
        }
        deque.offerFirst(next)
        map[next.first][next.second] = 2

        if (turn.containsKey(sec)) {
            dir += 4 + when (turn[sec]) {
                'L' -> -1
                'D' -> 1
                else -> 0
            }
            dir %= 4
        }

//        println("sec=$sec, queue=$deque:")
//        for (i in 0 until n) {
//            for (j in 0 until n) {
//                print("${map[i][j]} ")
//            }
//            println()
//        }

        sec++
    }

    println(sec)
}

fun nextOf(pos: Pair<Int, Int>, dir: Int): Pair<Int, Int> =
    when (dir) {
        0 -> pos.copy(first = pos.first - 1)
        1 -> pos.copy(second = pos.second + 1)
        2 -> pos.copy(first = pos.first + 1)
        3 -> pos.copy(second = pos.second - 1)
        else -> throw IllegalArgumentException()
    }
