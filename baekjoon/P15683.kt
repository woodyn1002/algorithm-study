package baekjoon.p15683

import kotlin.math.max
import kotlin.math.min

val dr = arrayOf(1, 0, -1, 0)
val dc = arrayOf(0, 1, 0, -1)

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val room = Array(n) {
        readLine()!!.split(" ").map(String::toInt).toIntArray()
    }

    val cctvs = mutableListOf<Pair<Int, Pos>>()
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (room[i][j] in 1..5) {
                cctvs.add(Pair(room[i][j], Pos(i, j)))
            }
        }
    }

    val answer = dfs(n, m, room, cctvs, 0, Array(n) { BooleanArray(m) })
    println(answer)
}

fun dfs(n: Int, m: Int, room: Array<IntArray>, cctvs: List<Pair<Int, Pos>>, curIdx: Int, visited: Array<BooleanArray>): Int {
    if (curIdx >= cctvs.size) {
        var cnt = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (room[i][j] == 0 && !visited[i][j]) {
                    cnt++
//                    print("_ ")
                } else {
//                    print("${room[i][j]} ")
                }
            }
//            println()
        }
        return cnt
    } else {
        val (cctvNum, cctvPos) = cctvs[curIdx]
//        println("cctv #$curIdx: cctvNum=$cctvNum..")
        var minCount = Int.MAX_VALUE

        val numRotate = when (cctvNum) {
            2 -> 2
            5 -> 1
            else -> 4
        }
        for (dir in 0 until numRotate) {
            val checkDirections = when (cctvNum) {
                1 -> listOf(dir)
                2 -> listOf(dir, (dir + 2) % 4)
                3 -> listOf(dir, (dir + 1) % 4)
                4 -> listOf(dir, (dir + 1) % 4, (dir + 2) % 4)
                5 -> listOf(dir, (dir + 1) % 4, (dir + 2) % 4, (dir + 3) % 4)
                else -> error("wrong dir")
            }

            val newVisited = Array(n) { i -> BooleanArray(m) { j -> visited[i][j] } }
            for (checkDir in checkDirections) {
                var cur = cctvPos
                while (true) {
                    val next = Pos(cur.r + dr[checkDir], cur.c + dc[checkDir])
                    if (next.r !in 0 until n) break
                    if (next.c !in 0 until m) break
                    if (room[next.r][next.c] == 6) break

                    if (room[next.r][next.c] == 0) {
                        newVisited[next.r][next.c] = true
                    }
                    cur = next
                }
//                println("..cur[cctv=$cctvNum, dir=$dir, checkDir=$checkDir]=$cur")
            }
            val result = dfs(n, m, room, cctvs, curIdx + 1, newVisited)
//            println("..result[cctv=$cctvNum, dir=$dir]=$result")
            minCount = min(result, minCount)
        }
//        println("..min[cctv=$cctvNum]=$minCount")
        return minCount
    }
}

data class Pos(val r: Int, val c: Int)