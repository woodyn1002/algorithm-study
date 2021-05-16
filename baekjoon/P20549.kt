import java.util.*
import kotlin.math.*

val kdx = intArrayOf(1, 2, 2, 1, -1, -2, -2, -1)
val kdy = intArrayOf(2, 1, -1, -2, 2, 1, -1, -2)

val bdx = intArrayOf(1, 1, -1, -1)
val bdy = intArrayOf(1, -1, -1, 1)

val rdx = intArrayOf(0, 1, 0, -1)
val rdy = intArrayOf(1, 0, -1, 0)

fun main() {
	val n = readLine()!!.toInt()
	val (a, b, c) = readLine()!!.split(" ").map(String::toInt)
	val (x, y) = readLine()!!.split(" ").map(String::toInt)

	val map = Array(n) { IntArray(n) }

	val m = readLine()!!.toInt()
	for (i in 1..m) {
		val (xi, yi) = readLine()!!.split(" ").map(String::toInt)
		map[yi][xi] = i
	}

	val costs = Array(n) { Array(n) { IntArray(1 shl m) { Int.MAX_VALUE } } }
	costs[y][x][0] = 0

	val queue: Queue<Context> = PriorityQueue(compareBy { it.cost })
	queue.offer(Context(x, y, 0, 0))

	var answer = Int.MAX_VALUE
	bfs@while (queue.isNotEmpty()) {
		val cur = queue.poll()
		if (cur.cost > costs[cur.y][cur.x][cur.foods]) continue

		val nexts = mutableListOf<Context>()

		// knight
		for (i in 0 until 8) {
			val nx = cur.x + kdx[i]
			val ny = cur.y + kdy[i]
			if (nx !in 0 until n || ny !in 0 until n) continue

			val nextFoods = if (map[ny][nx] > 0 && !hasEaten(cur.foods, map[ny][nx]))
				eaten(cur.foods, map[ny][nx])
			else
				cur.foods

			nexts += Context(nx, ny, nextFoods, cur.cost + a)
		}

		// bishop
		for (i in 0 until 4) {
			for (j in 1 until n) {
				val nx = cur.x + bdx[i] * j
				val ny = cur.y + bdy[i] * j
				if (nx !in 0 until n || ny !in 0 until n) break

				val nextFoods = if (map[ny][nx] > 0 && !hasEaten(cur.foods, map[ny][nx]))
					eaten(cur.foods, map[ny][nx])
				else
					cur.foods

				nexts += Context(nx, ny, nextFoods, cur.cost + b)
			}
		}

		// rook
		for (i in 0 until 4) {
			for (j in 1 until n) {
				val nx = cur.x + rdx[i] * j
				val ny = cur.y + rdy[i] * j
				if (nx !in 0 until n || ny !in 0 until n) break

				val nextFoods = if (map[ny][nx] > 0 && !hasEaten(cur.foods, map[ny][nx]))
					eaten(cur.foods, map[ny][nx])
				else
					cur.foods

				nexts += Context(nx, ny, nextFoods, cur.cost + c)
			}
		}

		for (next in nexts) {
			if (next.cost < costs[next.y][next.x][next.foods]) {
				costs[next.y][next.x][next.foods] = next.cost

				if (next.foods == ((1 shl m) - 1)) {
					answer = min(next.cost, answer)
				} else {
					queue.offer(next)
				}
			}
		}
	}

	println(answer)
}

fun hasEaten(foods: Int, num: Int) = (foods and (1 shl num - 1)) > 0
fun eaten(foods: Int, num: Int) = (foods or (1 shl num - 1))

class Context(val x: Int, val y: Int, val foods: Int, val cost: Int)