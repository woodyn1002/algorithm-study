package baekjoon.p15686

import kotlin.math.abs
import kotlin.math.min

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val map = Array(n) { readLine()!!.split(" ").map(String::toInt) }

    val homes = mutableListOf<Pair<Int, Int>>()
    val prevChickens = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until n) {
        for (j in 0 until n) {
            when (map[i][j]) {
                1 -> homes += Pair(i, j)
                2 -> prevChickens += Pair(i, j)
            }
        }
    }

    var minCityDist = Int.MAX_VALUE
    for (case in everyCase(prevChickens.size, m)) {
        val liveChickens = case.map { prevChickens[it] }

        var cityDist = 0
        for (home in homes) {
            val dist = liveChickens.minOf { distanceBetween(home, it) }
            cityDist += dist
        }

        minCityDist = min(minCityDist, cityDist)
    }

    println(minCityDist)
}

fun distanceBetween(pos1: Pair<Int, Int>, pos2: Pair<Int, Int>) =
    abs(pos1.first - pos2.first) + abs(pos1.second - pos2.second)

fun everyCase(
    size: Int,
    m: Int,
    visitedIndices: List<Int> = emptyList(),
    begin: Int = 0
): List<List<Int>> {
//    println("node=$visitedIndices")
    if (visitedIndices.size == m) {
        return listOf(visitedIndices)
    } else if ((size - begin) >= (m - visitedIndices.size)) {
        val cases = mutableListOf<List<Int>>()
        for (i in begin until size) {
//            println("going to branch=$i")
            cases += everyCase(size, m, visitedIndices + i, i + 1)
//            println("backtracked to node=$visitedIndices")
        }
        return cases
    }
    return emptyList()
}