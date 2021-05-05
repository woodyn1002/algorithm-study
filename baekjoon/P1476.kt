package baekjoon.p1476

fun main() {
    // 15*a+e == 28*b+s == 19*c+m == answer
    val (e, s, m) = readLine()!!.split(" ").map(String::toInt)

    var answer = 1
    while (true) {
        if ((answer - e) % 15 == 0 && (answer - s) % 28 == 0 && (answer - m) % 19 == 0) break
        answer++
    }
    println(answer)
}