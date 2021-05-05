package baekjoon.p16234

import kotlin.math.abs

val di = arrayOf(1, 0, -1, 0)
val dj = arrayOf(0, 1, 0, -1)

fun main() {
    val (n, l, r) = readLine()!!.split(" ").map(String::toInt)
    val map = Array(n) {
        readLine()!!.split(" ").map(String::toInt).toIntArray()
    }

    var answer = 0
    while (true) {
        val unions = mutableListOf<List<Pair<Int, Int>>>()

        val visited = Array(n) { BooleanArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (visited[i][j]) continue

                visited[i][j] = true
                val union = dfs(n, map, l, r, visited, i, j)
                if (union.size > 1) {
                    unions += union
                }
            }
        }
//        println("answer=$answer, numUnions=${unions.size}..")

        if (unions.isEmpty()) break

        for (union in unions) {
            val avg = union.sumOf { map[it.first][it.second] } / union.size
            for (country in union) {
                map[country.first][country.second] = avg
            }
        }

//        for (i in 0 until n) {
//            for (j in 0 until n) {
//                print(map[i][j].toString() + " ")
//            }
//            println()
//        }
//        println("..answer++")

        answer++
    }
    println(answer)
}

fun dfs(n: Int, map: Array<IntArray>, l: Int, r: Int, visited: Array<BooleanArray>, i: Int, j: Int): List<Pair<Int, Int>> {
    val list = mutableListOf(Pair(i, j))
    for (dir in 0 until 4) {
        val ni = i + di[dir]
        val nj = j + dj[dir]
        if (ni !in 0 until n || nj !in 0 until n || visited[ni][nj]) continue

        if (abs(map[i][j] - map[ni][nj]) in l..r) {
            visited[ni][nj] = true
            list += dfs(n, map, l, r, visited, ni, nj)
        }
    }
    return list
}