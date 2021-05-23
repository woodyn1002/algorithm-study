fun main() {
	val t = readLine()!!.toInt()
	repeat(t) {
		val input = readLine()!!

		var cur = Node()
		for (c in input) {
			when (c) {
				'<' -> {
					if (cur.left != null) {
						cur = cur.left!!
					}
				}
				'>' -> {
					if (cur.right != null) {
						cur = cur.right!!
					}
				}
				'-' -> {
					if (cur.left != null) {
						val left = cur.left!!
						val right = cur.right

						left.right = right
						if (right != null) right.left = left

						cur = left
					}
				}
				else -> {
					val node = Node(c)
					node.left = cur
					node.right = cur.right

					val right = cur.right

					cur.right = node
					if (right != null) right.left = node

					cur = node
				}
			}
		}

		val answer = StringBuilder()
		var node: Node? = cur.back()
		while (node != null) {
			if (node.char != null) {
				answer.append(node.char)
			}
			node = node.right
		}
		println(answer.toString())
	}
}

class Node(val char: Char? = null) {
	var left: Node? = null
	var right: Node? = null

	fun back(): Node = if (this.left != null) this.left!!.back() else this
}