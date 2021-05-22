import kotlin.math.*

fun main() {
	val (n, k) = readLine()!!.split(" ").map(String::toInt)

	val adj = Array(n) { mutableListOf<Int>() }
	repeat(n - 1) {
		val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
		adj[u].add(v)
		adj[v].add(u)
	}

	val root = Node(0)
	val visited = BooleanArray(n).apply { this[0] = true }
	makeTree(adj, visited, root)

	root.solve(k)
	println(root.answer)
}

fun makeTree(adj: Array<out List<Int>>, visited: BooleanArray, parent: Node) {
	for (nextNum in adj[parent.num]) {
		if (visited[nextNum]) continue

		visited[nextNum] = true
		val child = Node(nextNum)
		parent.children.add(child)
		makeTree(adj, visited, child)
	}
}

class Node(val num: Int) {
	val children = mutableListOf<Node>()
	var answer = 1
	var ways = 0

	fun solve(k: Int) {
		if (children.isEmpty()) return

		var sumAnswers = 0
		for (child in children) {
			child.solve(k)
			sumAnswers += child.answer
		}
		children.sortBy { it.ways }

		// 모든 자식과 이어지지 않음
		this.answer = sumAnswers + 1
		this.ways = 0

		val minWays = children.first().ways
		if (minWays + 1 <= k) {
			// 하나의 자식과 이어짐
			this.answer = sumAnswers
			this.ways = minWays + 1
		}

		if (children.size >= 2 && children[0].ways + children[1].ways + 2 <= k) {
			// 두 자식과 이어지며, 상위 부모와 이어지지 않을 것임
			this.answer = sumAnswers - 1
			this.ways = k
		}

		// println("num=${num+1}: answer=$answer, ways=$ways")
	}
}