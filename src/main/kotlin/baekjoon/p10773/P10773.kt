package baekjoon.p10773

fun main() {
    val k = readLine()!!.toInt()

    val numbers = mutableListOf<Int>()
    repeat(k) {
        val num = readLine()!!.toInt()
        if (num == 0)
            numbers.removeLast()
        else
            numbers.add(num)
    }

    println(numbers.sum())
}