import kotlin.math.*

fun main() {
	val n = readLine()!!.toInt()
	val arr = readLine()!!.split(" ").map(String::toInt).sorted()

	var minSum = Int.MAX_VALUE
	var answer = intArrayOf(0, 0)
	for (a in arr) {
		val (sum, b) = findBest(arr, a)
		// println("$a, $b: $sum..")

		if (sum < minSum) {
			minSum = sum
			answer[0] = a
			answer[1] = b
		}
	}

	println(answer.sorted().joinToString(" "))
}

fun findBest(arr: List<Int>, a: Int): Pair<Int, Int> {
	var best = Int.MAX_VALUE
	var bestB = -1

	var top = arr.lastIndex
	var bottom = 0
	while (top >= bottom) {
		val mid = (top + bottom) / 2

		val result = a + arr[mid]
		// println("[$bottom, $top]: $a + ${arr[mid]} = $result")
		if (abs(result) < best && arr[mid] != a) {
			best = abs(result)
			bestB = arr[mid]
		}

		if (result > 0) {
			// println("top--")
			top = mid - 1
		} else {
			// println("bottom--")
			bottom = mid + 1
		}
	}
	return Pair(best, bestB)
}