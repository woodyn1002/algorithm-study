package programmers.p42891

import java.util.*

class Solution {
    fun solution(food_times: IntArray, k: Long): Int {
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        for ((index, foodTime) in food_times.withIndex()) {
            pq.offer(Pair(index, foodTime))
        }

        val isEmpty = BooleanArray(food_times.size)

        var cur = k
        var lastTime = 0
        while (pq.isNotEmpty()) {
            val (minFood, minTime) = pq.peek()
            // println("cur=$cur, food=$minFood, time=$minTime..")
            val diff = (minTime - lastTime).toLong() * pq.size
            if (diff > cur) {
                // println("..eat the remaining")
                cur %= pq.size
                // println("..after cycle, cur=$cur")

                val nonEmptyFoods = (0 until food_times.size)
                    .filterNot { isEmpty[it] }
                // println("..nonEmpty: $nonEmptyFoods")
                return nonEmptyFoods[cur.toInt()] + 1
            } else {
                // println("..eat away")
                cur -= diff
                lastTime = minTime
                isEmpty[minFood] = true
                pq.poll()
            }
        }
        return -1
    }
}