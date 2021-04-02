package programmers.p72416

import kotlin.math.*

class Solution {
    fun solution(sales: IntArray, links: Array<IntArray>): Int {
        val adj = Array<MutableList<Int>>(sales.size) { mutableListOf() }
        for (link in links) {
            adj[link[0] - 1].add(link[1] - 1)
        }

        val answer = dfs(sales, adj, 0)
        // println("member=1: $answer")
        return min(answer.costWithLeader, answer.costWithoutLeader)
    }

    fun dfs(sales: IntArray, adj: Array<out List<Int>>, leader: Int): Node {
        val children = mutableListOf<Node>()
        for (member in adj[leader]) {
            val result = dfs(sales, adj, member)
            // println("member=${member+1}: $result")
            children.add(result)
        }

        return if (children.isEmpty()) {
            Node(sales[leader], 0, true)
        } else {
            val sumChildCosts = children.map { min(it.costWithLeader, it.costWithoutLeader) }.sum()

            val costWithLeader = sales[leader] + sumChildCosts
            val costWithoutLeader =
                if (children.any { it.shouldGoWorkshop() }) {
                    sumChildCosts
                } else {
                    val minDiff = children.map { it.costWithLeader - it.costWithoutLeader }.min()!!
                    sumChildCosts + minDiff
                }

            Node(costWithLeader, costWithoutLeader, false)
        }
    }

    data class Node(val costWithLeader: Int, val costWithoutLeader: Int, val isLeaf: Boolean) {
        fun shouldGoWorkshop(): Boolean = !isLeaf && costWithLeader < costWithoutLeader
    }
}