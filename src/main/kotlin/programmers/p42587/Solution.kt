package programmers.p42587

import java.util.*

class Solution {
    fun solution(priorities: IntArray, location: Int): Int {
        val jobs: Queue<Pair<Int, Int>> = LinkedList()
        for ((idx, prior) in priorities.withIndex()) {
            jobs.offer(Pair(idx, prior))
        }

        var cur = 1
        while (jobs.isNotEmpty()) {
            val job = jobs.poll()
            if (jobs.any { it.second > job.second }) {
                jobs.offer(job)
            } else {
                if (job.first == location) {
                    return cur
                }
                cur++
            }
        }
        return -1
    }
}