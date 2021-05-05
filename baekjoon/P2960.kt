package baekjoon.p2960

fun main() {
    val (n, k) = readLine()!!.split(" ").map(String::toInt)

    var answer = 0
    var cur = 1

    val visited = BooleanArray(n + 1)
    loop@for (i in 2..n) {
        if (visited[i]) continue

        var j = 1
        while (i * j <= n) {
            val num = i * j
            if (!visited[num]) {
                visited[num] = true

                if (cur == k) {
                    answer = num
                    break@loop
                }
                cur++
            }
            j++
        }
    }
    println(answer)
}