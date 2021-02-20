package baekjoon.p14503

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)
    var (r, c, d) = readLine()!!.split(" ").map(String::toInt)

    val map = Array(n) { readLine()!!.split(" ").map(String::toInt).toIntArray() }

    var cnt = 0
    loop@while (true) {
        if (map[r][c] == 0) {
            map[r][c] = -1
            cnt++
        }

        for (i in 1..4) {
            val d2 = (d - i + 4) % 4
            val (r2, c2) = towardOf(r, c, d2)
            if (map[r2][c2] == 0) {
                r = r2
                c = c2
                d = d2
                continue@loop
            }
        }

        val (backR, backC) = towardOf(r, c, (d + 2) % 4)
        if (map[backR][backC] == 1) {
            break
        }
        r = backR
        c = backC
    }

    println(cnt)
}

fun towardOf(r: Int, c: Int, d: Int): Pair<Int, Int> = when (d) {
    0 -> Pair(r - 1, c)
    1 -> Pair(r, c + 1)
    2 -> Pair(r + 1, c)
    3 -> Pair(r, c - 1)
    else -> throw IllegalArgumentException()
}