package baekjoon.p1786

fun main() {
    val s = readLine()!!
    val w = readLine()!!

    val t = IntArray(w.length + 1)
    t[0] = -1

    var pos = 1
    var cnd = 0
    while (pos < w.length) {
        if (w[pos] == w[cnd]) {
            t[pos] = t[cnd]
        } else {
            t[pos] = cnd
            while (cnd >= 0 && w[pos] != w[cnd])
                cnd = t[cnd]
        }
        pos++
        cnd++
    }
    t[pos] = cnd
    t.forEachIndexed { index, i -> println("#${index}: $i") }

    val answer = mutableListOf<Int>()

    var j = 0
    var k = 0
    while (j < s.length) {
        if (w[k] == s[j]) {
            j++
            k++
            if (k == w.length) {
                answer += (j - k + 1)
                k = t[k]
            }
        } else {
            k = t[k]
            if (k < 0) {
                j++
                k++
            }
        }
    }

    println(answer.size)
    println(answer.joinToString(" "))
}