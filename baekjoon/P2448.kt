package baekjoon.p2448

fun main() {
    val n = readLine()!!.toInt()

    val lines = Array(n) { CharArray(n * 2 - 1) { ' ' } }
    triangle(lines, n)

    for (line in lines)
        println(line)
}

fun triangle(lines: Array<CharArray>, h: Int, beginI: Int = 0, beginJ: Int = 0) {
    if (h == 3) {
        lines[beginI][beginJ + 2] = '*'
        lines[beginI + 1][beginJ + 1] = '*'
        lines[beginI + 1][beginJ + 3] = '*'
        for (j in 0 until 5) {
            lines[beginI + 2][beginJ + j] = '*'
        }
    } else {
        triangle(lines, h / 2, beginI, beginJ + (h / 2))
        triangle(lines, h / 2, beginI + (h / 2), beginJ)
        triangle(lines, h / 2, beginI + (h / 2), beginJ + h)
    }
}