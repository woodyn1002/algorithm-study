package baekjoon.p9012

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val input = readLine()!!

        val answer = when(isVps(input)) {
            true -> "YES"
            false -> "NO"
        }
        println(answer)
    }
}

fun isVps(input: String): Boolean {
    var leftPar = 0

    for (c in input) {
        if (c == '(') {
            leftPar++
        } else {
            if (leftPar == 0)
                return false

            leftPar--
        }
    }
    return (leftPar == 0)
}