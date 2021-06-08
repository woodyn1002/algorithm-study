import java.util.*

fun main() {
	val t = readLine()!!.toInt()
	repeat(t) {
		val (n, m) = readLine()!!.split(" ").map(String::toInt)
		val arr = readLine()!!.split(" ").map(String::toInt).toIntArray()

		val queue = LinkedList<IndexedValue<Int>>()
		for (pair in arr.withIndex()) {
			queue.offer(pair)
		}

		var answer = 0
		while (queue.isNotEmpty()) {
			val cur = queue.poll()

			if (queue.isNotEmpty() && queue.maxOf { it.value } > cur.value) {
				queue.offer(cur)
			} else {
				answer++
				if (cur.index == m) {
					break
				}
			}
		}

		println(answer)
	}
}