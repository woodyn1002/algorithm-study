package programmers.p72410

class Solution {
    val alphabets = 'a'.toInt()..'z'.toInt()
    val numbers = '0'.toInt()..'9'.toInt()
    val special = arrayOf('-', '_', '.')

    fun solution(new_id: String): String {
        var answer = new_id.toLowerCase()
        // println("1: $answer")

        answer = answer.filter {
            it.toInt() in alphabets || it.toInt() in numbers || it in special
        }
        // println("2: $answer")

        while (answer.contains(".."))
            answer = answer.replace("..", ".")
        // println("3: $answer")

        if (answer.isNotEmpty() && answer.first() == '.')
            answer = answer.substring(1)
        if (answer.isNotEmpty() && answer.last() == '.')
            answer = answer.substring(0, answer.lastIndex)
        // println("4: $answer")

        if (answer.isEmpty())
            answer = "a"
        // println("5: $answer")

        if (answer.length >= 16) {
            answer = answer.substring(0, 15)
            if (answer.isNotEmpty() && answer.last() == '.')
                answer = answer.substring(0, answer.lastIndex)
        }
        // println("6: $answer")

        if (answer.length <= 2) {
            while (answer.length < 3)
                answer = answer + answer.last()
        }
        // println("7: $answer")

        return answer
    }
}