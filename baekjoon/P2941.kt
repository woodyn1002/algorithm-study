package baekjoon.p2941

val croatiaAlphabets: List<String> = listOf("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=")

fun main() {
    val word = readLine()!!

    var answer = 0
    var i = 0
    while (i < word.length) {
        var len = 1
        for (croatia in croatiaAlphabets) {
            if (croatia.length > word.length - i) continue

            var included = true
            for (j in croatia.indices) {
                if (word[i + j] != croatia[j]) {
                    included = false
                    break
                }
            }
            if (included) {
                len = croatia.length
                break
            }
        }
        i += len
        answer++
    }

    println(answer)
}