package programmers.p72414

import kotlin.math.*

class Solution {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val playTimeSec = asSeconds(play_time)
        val advTimeSec = asSeconds(adv_time)

        val views = LongArray(playTimeSec + 1)
        for (log in logs) {
            val (beginTimeSec, endTimeSec) = log.split("-").map(this::asSeconds)
            views[beginTimeSec]++
            views[endTimeSec]--
        }

        for (i in 1 until views.size) {
            views[i] += views[i - 1]
        }

        for (i in 1 until views.size) {
            views[i] += views[i - 1]
        }

        var maxView = 0L
        var bestPoint = 0
        for (i in 0..(views.size - advTimeSec)) {
            val view = views[i + advTimeSec - 1] - views.getOrElse(i - 1) { 0L }
            if (view > maxView) {
                maxView = view
                bestPoint = i
            }
        }

        return asExpression(bestPoint)
    }

    fun asSeconds(time: String): Int {
        val segs = time.split(":").map(String::toInt)
        return (segs[0] * 60 * 60) + (segs[1] * 60) + segs[2]
    }

    fun asExpression(sec: Int): String {
        var hours = (sec / (60 * 60)).toString().padStart(2, '0')
        var minutes = ((sec / 60) % 60).toString().padStart(2, '0')
        var seconds = (sec % 60).toString().padStart(2, '0')

        return "$hours:$minutes:$seconds"
    }
}