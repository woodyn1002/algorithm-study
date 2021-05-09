fun main() {
	val (n, _r, q) = readLine()!!.split(" ").map(String::toInt)
	val r = _r - 1

	val adj = Array(n) { mutableListOf<Int>() }
	repeat(n - 1) {
		val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
		adj[u].add(v)
		adj[v].add(u)
	}

	val nodes = Array(n) { Node(it) }
	val root = nodes[r]
	val visited = BooleanArray(n)
	visited[r] =
	makeTree(adj, nodes, visited, r)

	root.count()

	repeat(q) {
		val u = readLine()!!.toInt() - 1
		println(nodes[u].numNodes)
	}
}

fun makeTree(adj: Array<out List<Int>>, nodes: Array<Node>, visited: BooleanArray, r: Int) {
	for (c in adj[r]) {
		if (visited[c]) continue

		visited[c] = true
		nodes[r].children += nodes[c]

		makeTree(adj, nodes, visited, c)
	}
}

class Node(num: Int) {
	val children = mutableListOf<Node>()
	var numNodes = 1

	fun count(): Int {
		var sum = 1
		for (child in children) {
			sum += child.count()
		}
		this.numNodes = sum
		return this.numNodes
	}
}