fun main() {
	val n = readLine()!!.toInt()

	val adj = Array(n) { mutableListOf<Int>() }
	repeat(n - 1) {
		val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
		adj[u].add(v)
		adj[v].add(u)
	}

	val root = Node(0)
	val visited = BooleanArray(n)
	visited[0] = true
	makeTree(adj, visited, root)

	root.countEarlyAdapters()
	val answer = root.numEarlyAdapters.min()!!
	println(answer)
}

fun makeTree(adj: Array<out List<Int>>, visited: BooleanArray, parent: Node) {
	for (nextNum in adj[parent.num]) {
		if (visited[nextNum]) continue

		visited[nextNum] = true

		val next = Node(nextNum)
		parent.children.add(next)
		makeTree(adj, visited, next)
	}
}

class Node(val num: Int) {
	val children = mutableListOf<Node>()
	val numEarlyAdapters = intArrayOf(1, 0)

	fun countEarlyAdapters() {
		if (children.isNotEmpty()) {
			var numWhenIncluded = 1
			var numWhenExcluded = 0
			for (child in children) {
				child.countEarlyAdapters()
				numWhenIncluded += child.numEarlyAdapters.min()!!
				numWhenExcluded += child.numEarlyAdapters[0]
			}

			this.numEarlyAdapters[0] = numWhenIncluded
			this.numEarlyAdapters[1] = numWhenExcluded
		}
	}
}