package programmers.p42888

class Solution {
    fun solution(records: Array<String>): Array<String> {
        val logs = mutableListOf<Pair<String, String>>()
        val nicknames = mutableMapOf<String, String>()

        for (record in records) {
            val seg = record.split(" ")

            if (seg[0] == "Enter" || seg[0] == "Change") {
                nicknames[seg[1]] = seg[2]
            }
            if (seg[0] == "Enter" || seg[0] == "Leave") {
                logs += Pair(seg[0], seg[1])
            }
        }

        return logs.map { (action, id) ->
            nicknames[id]!! + if (action == "Enter") "님이 들어왔습니다." else "님이 나갔습니다."
        }.toTypedArray()
    }
}