import java.util.*

fun main() {
	val n = readLine()!!.toInt()

	val pq: Queue<Int> = PriorityQueue()
	repeat(n) {
		val cards = readLine()!!.toInt()
		pq.offer(cards)
	}

	var answer = 0
	while (pq.size >= 2) {
		val a = pq.poll()
		val b = pq.poll()
		val combined = a + b

		answer += combined
		pq.offer(combined)
	}

	println(answer)
}