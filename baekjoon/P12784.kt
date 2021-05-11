import kotlin.math.*

fun main() {
	val t = readLine()!!.toInt()
	repeat(t) {
		val (n, m) = readLine()!!.split(" ").map(String::toInt)
		val adj = Array(n) { mutableListOf<Pair<Int, Int>>() }

		repeat(m) {
			val (u, v, d) = readLine()!!.split(" ").map(String::toInt)
			adj[u - 1].add(Pair(v - 1, d))
			adj[v - 1].add(Pair(u - 1, d))
		}

		val root = Node(0)
		val visited = BooleanArray(n)
		visited[0] = true
		makeTree(adj, visited, root)

		val answer = root.minDynamites() ?: 0
		println(answer)
	}
}

fun makeTree(adj: Array<out List<Pair<Int, Int>>>, visited: BooleanArray, parent: Node) {
	for ((nextNum, d) in adj[parent.num]) {
		if (visited[nextNum]) continue

		visited[nextNum] = true
		val node = Node(nextNum)
		parent.children[node] = d
		makeTree(adj, visited, node)
	}
}

class Node(val num: Int) {
	val children = mutableMapOf<Node, Int>()

	fun minDynamites(): Int? {
		return if (children.isEmpty()) {
			null
		} else {
			var sum = 0
			for ((node, d) in children) {
				val recursive = node.minDynamites()
				sum += min(d, recursive ?: Int.MAX_VALUE)
			}
			sum
		}
	}
}