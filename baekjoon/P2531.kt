import kotlin.math.*

fun main() {
	val (n, d, k, c) = readLine()!!.split(" ").map(String::toInt)
	val belt = IntArray(n) { readLine()!!.toInt() }

	var best = 0

	val curCount = IntArray(d + 1)
	var curTypes = 0
	var coupon = true

	var start = 0
	var end = -1
	while (start < belt.size) {
		if (end - start + 1 < k) {
			end++
			val sushi = belt[end % belt.size]
			if (curCount[sushi] == 0) {
				curTypes++
				if (sushi == c) {
					coupon = false
				}
			}
			curCount[sushi]++

			val totalTypes = curTypes + if (coupon) 1 else 0
			best = max(totalTypes, best)
		} else {
			val sushi = belt[start]
			if (curCount[sushi] == 1) {
				curTypes--
				if (sushi == c) {
					coupon = true
				}
			}
			curCount[sushi]--

			start++
		}
	}

	println(best)
}