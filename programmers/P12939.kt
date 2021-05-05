package programmers.p12939

class Solution {
    fun solution(s: String): String {
        val elements = s.split(" ").map(String::toInt).sorted()
        return "${elements.first()} ${elements.last()}"
    }
}