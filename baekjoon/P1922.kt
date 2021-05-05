import java.util.*

fun main() {
	val n = readLine()!!.toInt()
	val m = readLine()!!.toInt()

	val adj = Array(n) { mutableListOf<Pair<Int, Int>>() }

	repeat(m) {
		val (a, b, c) = readLine()!!.split(" ").map(String::toInt)
		adj[a - 1].add(Pair(b - 1, c))
		adj[b - 1].add(Pair(a - 1, c))
	}

	val visited = BooleanArray(n)
	var answer = 0

	val pq: Queue<Pair<Int, Int>> = PriorityQueue(compareBy { it.second })
	pq.offer(Pair(0, 0))
	while (pq.isNotEmpty()) {
		val cur = pq.poll()
		if (visited[cur.first]) continue

		answer += cur.second
		visited[cur.first] = true
		for (next in adj[cur.first]) {
			if (visited[next.first]) continue
			pq.offer(next)
		}
	}

	println(answer)
}