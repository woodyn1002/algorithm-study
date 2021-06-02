import java.util.*

val dr = intArrayOf(1, 0, -1 ,0)
val dc = intArrayOf(0, 1, 0, -1)

fun main() {
	val (n, m) = readLine()!!.split(" ").map(String::toInt)
	val map = Array(n) { readLine()!!.toCharArray() }

	val queue: Queue<Context> = LinkedList()
	queue.offer(Context(0, 0, 1))

	val visited = Array(n) { BooleanArray(m) }

	var answer = 0
	bfs@while (queue.isNotEmpty()) {
		val cur = queue.poll()

		for (dir in 0 until 4) {
			val nr = cur.r + dr[dir]
			val nc = cur.c + dc[dir]

			if (nr !in 0 until n || nc !in 0 until m) continue
			if (map[nr][nc] == '0') continue
			if (visited[nr][nc]) continue

			if (nr == n - 1 && nc == m - 1) {
				answer = cur.cost + 1
				break@bfs
			} else {
				visited[nr][nc] = true
				queue.offer(Context(nr, nc, cur.cost + 1))
			}
		}
	}

	println(answer)
}

data class Context(val r: Int, val c: Int, val cost: Int)