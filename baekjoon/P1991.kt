package baekjoon.p1991

fun main() {
    val n = readLine()!!.toInt()

    val nodes = Array<Node?>(n) { null }

    for (i in 0 until n) {
        val (self, left, right) = readLine()!!.split(" ").map(String::first)

        val selfNode = nodes[self.toInt() - 'A'.toInt()] ?: Node(self)

        val leftNode = if (left != '.')
            nodes[left.toInt() - 'A'.toInt()] ?: Node(left)
        else
            null

        val rightNode = if (right != '.')
            nodes[right.toInt() - 'A'.toInt()] ?: Node(right)
        else
            null

        selfNode.left = leftNode
        selfNode.right = rightNode

        nodes[self.toInt() - 'A'.toInt()] = selfNode
        if (left != '.') nodes[left.toInt() - 'A'.toInt()] = leftNode
        if (right != '.') nodes[right.toInt() - 'A'.toInt()] = rightNode
    }

    val root = nodes[0]!!
    root.traversePreorder { print(it.name) }
    println()
    root.traverseInorder { print(it.name) }
    println()
    root.traversePostorder { print(it.name) }
    println()
}

class Node(
    val name: Char,
    var left: Node? = null,
    var right: Node? = null
) {
    fun traversePreorder(lambda: (Node) -> Unit) {
        lambda(this)
        left?.traversePreorder(lambda)
        right?.traversePreorder(lambda)
    }

    fun traverseInorder(lambda: (Node) -> Unit) {
        left?.traverseInorder(lambda)
        lambda(this)
        right?.traverseInorder(lambda)
    }

    fun traversePostorder(lambda: (Node) -> Unit) {
        left?.traversePostorder(lambda)
        right?.traversePostorder(lambda)
        lambda(this)
    }
}