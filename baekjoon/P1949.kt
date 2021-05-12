fun main() {
	val n = readLine()!!.toInt()
	val residents = readLine()!!.split(" ").map(String::toInt)
	val adj = Array(n) { mutableListOf<Int>() }
	repeat(n - 1) {
		val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
		adj[u].add(v)
		adj[v].add(u)
	}

	val root = Node(0, residents[0])
	val visited = BooleanArray(n)
	visited[0] = true
	makeTree(adj, residents, visited, root)

	root.traverse()
	val answer = root.dp.max()!!
	println(answer)
}

fun makeTree(adj: Array<out List<Int>>, residents: List<Int>, visited: BooleanArray, parent: Node) {
	for (nextNum in adj[parent.num]) {
		if (visited[nextNum]) continue

		visited[nextNum] = true
		val child = Node(nextNum, residents[nextNum]);
		parent.children.add(child)
		makeTree(adj, residents, visited, child)
	}
}

class Node(val num: Int, val resident: Int) {
	val children = mutableListOf<Node>()
	var dp = intArrayOf(resident, 0)

	fun traverse() {
		if (children.isEmpty()) return

		var included = resident
		var excluded = 0
		
		for (child in children) {
			child.traverse()

			included += child.dp[1]
			excluded += child.dp.max()!!
		}

		this.dp[0] = included
		this.dp[1] = excluded
	}
}