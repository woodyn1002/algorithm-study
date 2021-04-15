package baekjoon.p17144

val dr = intArrayOf(1, 0, -1, 0)
val dc = intArrayOf(0, 1, 0, -1)

fun log(msg: String) {
    if (false) println(msg)
}

fun main() {
    val (R, C, T) = readLine()!!.split(" ").map(String::toInt)
    val map = Array(R) {
        readLine()!!.split(" ").map(String::toInt).toIntArray()
    }

    val machines = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until R) {
        for (j in 0 until C) {
            if (map[i][j] == -1) {
                machines += Pair(i, j)
            }
        }
    }
    machines.sortBy { it.first }

    repeat(T) {
        log("--- time=${it + 1}")

        // 확산
        for (dust in getDusts(R, C, map)) {
            val nextAmount = dust.size / 5
            for (i in 0 until 4) {
                val nr = dust.r + dr[i]
                val nc = dust.c + dc[i]
                if (nr in 0 until R && nc in 0 until C && map[nr][nc] != -1) {
                    map[nr][nc] += nextAmount
                    map[dust.r][dust.c] -= nextAmount
                }
            }
        }
        log("- 확산 후:")
        for (i in 0 until R) {
            log(map[i].joinToString(" "))
        }
        log("---")

        // 바람
        for (dust in getDusts(R, C, map)) {
            var next: Pair<Int, Int>? = null

            if (dust.r == 0 || dust.r == R - 1) {
                with(Pair(dust.r, dust.c - 1)) {
                    if (this.first in 0 until R && this.second in 0 until C) {
                        next = this
                    }
                }
            }

            if (dust.r == machines[0].first || dust.r == machines[1].first) {
                with(Pair(dust.r, dust.c + 1)) {
                    if (this.first in 0 until R && this.second in 0 until C) {
                        next = this
                    }
                }
            }

            if ((dust.c == 0 && dust.r >= machines[1].first) || (dust.c == C - 1 && dust.r <= machines[0].first)) {
                with(Pair(dust.r - 1, dust.c)) {
                    if (this.first in 0 until R && this.second in 0 until C) {
                        next = this
                    }
                }
            }

            if ((dust.c == 0 && dust.r <= machines[0].first) || (dust.c == C - 1 && dust.r >= machines[1].first)) {
                with(Pair(dust.r + 1, dust.c)) {
                    if (this.first in 0 until R && this.second in 0 until C) {
                        next = this
                    }
                }
            }

            if (next != null) {
                if (next !in machines) {
                    map[next!!.first][next!!.second] += dust.size
                }
                map[dust.r][dust.c] -= dust.size
            }
        }
        log("- 바람 후:")
        for (i in 0 until R) {
            log(map[i].joinToString(" "))
        }
        log("------")
    }

    var answer = 0
    for (i in 0 until R) {
        for (j in 0 until C) {
            if (map[i][j] != -1) {
                answer += map[i][j]
            }
        }
    }
    println(answer)
}

fun getDusts(R: Int, C: Int, map: Array<IntArray>): List<Dust> {
    val dusts = mutableListOf<Dust>()
    for (i in 0 until R) {
        for (j in 0 until C) {
            if (map[i][j] > 0) {
                dusts += Dust(i, j, map[i][j])
            }
        }
    }
    return dusts
}

data class Dust(val r: Int, val c: Int, val size: Int)