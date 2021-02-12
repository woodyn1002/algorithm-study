package baekjoon.p2812

import java.util.*

fun main() {
    val (n, k) = readLine()!!.split(' ').map(String::toInt)
    val number = readLine()!!

    val stack: Deque<Char> = ArrayDeque()
    var removalCnt = k
    for (digit in number) {
        while (stack.isNotEmpty() && stack.peekLast() < digit && removalCnt > 0) {
            stack.removeLast()
            removalCnt--
        }
        stack.addLast(digit)
    }
    repeat(removalCnt) { stack.removeLast() }

    println(stack.joinToString(""))
}