fun main() {
	val n = readLine()!!.toInt()
	val m = readLine()!!.toInt()

	val adjList = Array(n) { mutableListOf<Int>() }
	for (i in 0 until n) {
		for ((j, way) in readLine()!!.split(" ").withIndex()) {
			if (way == "1") {
				adjList[i].add(j)
			}
		}
	}

	val plan = readLine()!!.split(" ").map { it.toInt() - 1 }

	val visited = BooleanArray(n)
	visited[plan.first()] = true
	dfs(adjList, visited, plan.first())

	var answer = "YES"
	for (city in plan) {
		if (!visited[city]) {
			answer = "NO"
			break
		}
	}

	println(answer)
}

fun dfs(
	adjList: Array<out List<Int>>,
	visited: BooleanArray,
	node: Int
) {
	for (next in adjList[node]) {
		if (visited[next]) continue
		visited[next] = true
		dfs(adjList, visited, next)
	}
}