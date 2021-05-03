package baekjoon.p11559

val dr = intArrayOf(1, 0, -1, 0)
val dc = intArrayOf(0, 1, 0, -1)

fun main() {
    val map = Array(12) {
        readLine()!!.toCharArray()
    }

    var answer = 0
    while (true) {
        var exploded = false

        for (r in 0 until 12) {
            for (c in 0 until 6) {
                if (map[r][c] != '.') {
                    val cur = Pos(r, c)
                    val color = map[r][c]
                    val puyos = mutableListOf(cur)
                    dfs(puyos, map, color, cur)

                    if (puyos.size >= 4) {
                        exploded = true
                        for (puyo in puyos) {
                            map[puyo.r][puyo.c] = '.'
                        }
                    }
                }
            }
        }

        if (!exploded)
            break

//        println("answer=$answer..")
//        println("after exploded:")
//        for (r in 0 until 12) {
//            println(map[r])
//        }
//        println("..")

        for (r in 11 downTo 0) {
            for (c in 0 until 6) {
                if (map[r][c] != '.') {
                    var nr = r
                    while (nr + 1 < 12 && map[nr + 1][c] == '.')
                        nr++

                    if (nr != r) {
                        map[nr][c] = map[r][c]
                        map[r][c] = '.'
                    }
                }
            }
        }

//        println("after down:")
//        for (r in 0 until 12) {
//            println(map[r])
//        }
//        println("..")

        answer++
    }

    println(answer)
}

fun dfs(dest: MutableList<Pos>, map: Array<CharArray>, color: Char, cur: Pos) {
    for (dir in 0 until 4) {
        val next = Pos(cur.r + dr[dir], cur.c + dc[dir])
        if (next.r !in 0 until 12 || next.c !in 0 until 6 || next in dest) continue
        if (map[next.r][next.c] != color) continue

        dest += next
        dfs(dest, map, color, next)
    }
}

data class Pos(val r: Int, val c: Int)