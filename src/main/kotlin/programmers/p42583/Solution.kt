package programmers.p42583

import java.util.*

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var elapsedTime = 1
        var curWeight = 0
        val onBridge: Queue<Pair<Int, Int>> = LinkedList()
        val waiting: Queue<Pair<Int, Int>> = LinkedList(
            truck_weights.withIndex().map { (idx, w) -> Pair(idx, w) }
        )
        var numGone = 0

        while (numGone < truck_weights.size) {
            if (onBridge.isNotEmpty() && onBridge.peek().second == elapsedTime) {
                val truck = onBridge.poll()
                curWeight -= truck.first
                numGone++
            }

            if (waiting.isNotEmpty() && curWeight + waiting.peek().second <= weight) {
                val truck = waiting.poll()
                onBridge.offer(Pair(truck.second, elapsedTime + bridge_length))
                curWeight += truck.second

                elapsedTime++
            } else if (onBridge.isNotEmpty()) {
                elapsedTime = onBridge.peek().second
            }

            // println("elapsed=$elapsedTime, curWeight=$curWeight, onBridge=$onBridge, waiting=$waiting")
        }
        return elapsedTime
    }
}