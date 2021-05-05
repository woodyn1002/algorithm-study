package baekjoon.p1339

import java.util.*

fun main() {
    val n = readLine()!!.toInt()

    val words = mutableListOf<String>()
    repeat(n) {
        val word = readLine()!!
        words.add(word)
    }

    val pq: Queue<Pair<Int, Char>> = PriorityQueue(compareByDescending { it.first })
    for (c in words.flatMap(String::toList).distinct()) {
        pq.add(Pair(score(words, c), c))
    }

    val transform = mutableMapOf<Char, Int>()
    var curNum = 9
    while (pq.isNotEmpty()) {
        val cur = pq.poll()
        val alphabet = cur.second
        if (transform.containsKey(alphabet)) continue

        transform[alphabet] = curNum
        curNum--
    }

    var answer = 0
    for (word in words) {
        for ((index, c) in word.withIndex()) {
            var value = transform[c]!!
            repeat(word.length - index - 1) {
                value *= 10
            }
            answer += value
        }
    }
    println(answer)
}

fun score(words: List<String>, alphabet: Char): Int {
    var score = 0
    for (word in words) {
        for ((index, c) in word.withIndex()) {
            if (c != alphabet) continue
            var value = 1
            repeat(word.length - index - 1) {
                value *= 10
            }
            score += value
        }
    }
    return score
}