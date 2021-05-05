package baekjoon.p2636

val dr = intArrayOf(1, 0, -1, 0)
val dc = intArrayOf(0, 1, 0, -1)

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    val pane = Array(n) {
        readLine()!!.split(" ").map(String::toInt).toIntArray()
    }

    var elapsed = 0
    var prevNumMelted = 0
    while (true) {
        val melting = mutableListOf<Pos>()
        val visited = Array(n) { BooleanArray(m) }
        visited[0][0] = true
        dfs(melting, n, m, pane, visited, Pos(0, 0))

//        println("elapsed=$elapsed: melting=${melting.size}..")
//        for (r in 0 until n) {
//            for (c in 0 until m) {
//                print("${pane[r][c]} ")
//            }
//            println()
//        }
//        println("..")

        if (melting.isEmpty()) {
            break
        }

        prevNumMelted = melting.size
        for (pos in melting) {
            pane[pos.r][pos.c] = 0
        }

        elapsed++
    }

    println(elapsed)
    println(prevNumMelted)
}

fun dfs(
    dest: MutableList<Pos>,
    n: Int,
    m: Int,
    pane: Array<IntArray>,
    visited: Array<BooleanArray>,
    cur: Pos,
) {
    for (dir in 0 until 4) {
        val nr = cur.r + dr[dir]
        val nc = cur.c + dc[dir]
        if (nr !in 0 until n || nc !in 0 until m || visited[nr][nc]) continue

        visited[nr][nc] = true
        if (pane[nr][nc] == 1) {
            dest += Pos(nr, nc)
        } else {
            dfs(dest, n, m, pane, visited, Pos(nr, nc))
        }
    }
}

data class Pos(val r: Int, val c: Int)