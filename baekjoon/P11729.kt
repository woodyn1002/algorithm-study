import java.util.*

fun main() {
	val n = readLine()!!.toInt()

	val platforms = Array(3) { ArrayDeque<Int>() }
	for (disk in 1..n) {
		platforms[0].addLast(disk)
	}

	val movements = StringBuilder()
	val answer = hanoi(platforms, movements, 0, 2, 1, n)

	println(answer)
	println(movements)
}

fun hanoi(platforms: Array<out Deque<Int>>, movements: StringBuilder, from: Int, to: Int, mid: Int, cnt: Int): Int {
	var totalCnt = 0

	if (cnt > 1) {
		totalCnt += hanoi(platforms, movements, from, mid, to, cnt - 1)
	}

	val disk = platforms[from].removeLast()
	platforms[to].addLast(disk)
	movements.append("${from + 1} ${to + 1}\n")
	totalCnt++

	if (cnt > 1) {
		totalCnt += hanoi(platforms, movements, mid, to, from, cnt - 1)
	}
	return totalCnt
}