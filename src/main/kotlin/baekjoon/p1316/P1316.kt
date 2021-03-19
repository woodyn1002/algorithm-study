package baekjoon.p1316

fun main() {
    val n = readLine()!!.toInt()
    val words = Array(n) { readLine()!! }

    println(words.count { isGroupWord(it) })
}

fun isGroupWord(word: String): Boolean {
    val visited = BooleanArray(26)
    var prev: Char? = null
    for (cur in word) {
        val key = cur.toInt() - 'a'.toInt()
        if (visited[key] && cur != prev) {
            return false
        }
        visited[key] = true
        prev = cur
    }
    return true
}