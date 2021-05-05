package baekjoon.p2407

import java.math.BigInteger

fun main() {
    val (n, m) = readLine()!!.split(" ").map(String::toInt)

    var answer = BigInteger.ONE
    for (i in (m + 1)..n)
        answer = answer.multiply(BigInteger.valueOf(i.toLong()))
    for (i in 1..(n - m))
        answer = answer.divide(BigInteger.valueOf(i.toLong()))

    println(answer)
}