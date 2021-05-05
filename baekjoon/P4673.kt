package baekjoon.p4673

fun main() {
    val counts = IntArray(10000)

    for (num in 1 until 10000) {
        val d = num + num.toString().toCharArray()
            .map { Character.getNumericValue(it) }
            .sum()
        if (d < 10000) {
            counts[d]++
        }
    }

    for ((number, cnt) in counts.withIndex()) {
        if (number > 0 && cnt == 0) {
            println(number)
        }
    }
}