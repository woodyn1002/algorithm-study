package baekjoon.p11721

fun main() {
    val text = readLine()!!

    for (window in text.windowed(10, step = 10, partialWindows = true))
        println(window)
}