package programmers.p42892

class Solution {
    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val maxX = nodeinfo.maxBy { it[0] }!![0]
        val maxY = nodeinfo.maxBy { it[1] }!![1]

        val nodes = Array<MutableList<Node>>(maxY + 1) { mutableListOf() }

        for ((idx, node) in nodeinfo.withIndex()) {
            nodes[node[1]].add(Node(node[0], node[1], idx + 1))
        }
        nodes.forEach { it.sortBy { it.x } }

        val prefix = mutableListOf<Int>()
        val postfix = mutableListOf<Int>()
        traverse(nodes, nodes[maxY].first(), 0, maxX, prefix, postfix)

        // println("prefix=$prefix")
        // println("postfix=$postfix")
        return arrayOf(prefix.toIntArray(), postfix.toIntArray())
    }

    fun traverse(
        nodes: Array<MutableList<Node>>,
        root: Node,
        minX: Int,
        maxX: Int,
        prefix: MutableList<Int>,
        postfix: MutableList<Int>
    ) {
        // println("root=${root.num}..")
        prefix.add(root.num)

        val nextLevel = ((root.y - 1) downTo 0).find { nodes[it].isNotEmpty() }
        if (nextLevel != null) {
            val left = nodes[nextLevel].find { it.x < root.x && it.x >= minX }
            if (left != null) {
                // println("..left=${left.num}")
                traverse(nodes, left, minX, root.x, prefix, postfix)
            }

            val right = nodes[nextLevel].find { it.x > root.x && it.x <= maxX }
            if (right != null) {
                // println("..right=${right.num}")
                traverse(nodes, right, root.x, maxX, prefix, postfix)
            }
        }

        postfix.add(root.num)
        // println("..${root.num} end")
    }
}

data class Node(val x: Int, val y: Int, val num: Int)