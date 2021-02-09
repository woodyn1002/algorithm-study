package baekjoon.p9935

fun main() {
    val text = readLine()!!
    val exp = readLine()!!

    val answer = mutableListOf<Char>()
    for (c in text) {
        answer += c
        if (answer.size >= exp.length) {
            var flag = true
            for (i in 0..exp.lastIndex) {
                if (answer[answer.size - exp.length + i] != exp[i]) {
                    flag = false
                    break
                }
            }
            if (flag) {
                repeat(exp.length) {
                    answer.removeLast()
                }
            }
        }
    }

    if (answer.isEmpty())
        println("FRULA")
    else
        println(answer.joinToString(separator = ""))
}