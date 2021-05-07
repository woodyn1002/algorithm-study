fun main() {
	val (n, c) = readLine()!!.split(" ").map(String::toInt)
	val arr = IntArray(n) { readLine()!!.toInt() }.sorted()

	var answer = 0

	var top = arr.last() - arr.first()
	var bottom = 1
	while (top >= bottom) {
		val mid = (top + bottom) / 2
		val cnt = count(mid, arr)
		// print("[$bottom, $top]: mid=$mid, cnt=$cnt ")
		if (cnt >= c) {
			answer = mid
			bottom = mid + 1
		} else {
			top = mid - 1
		}
	}
	println(answer)
}

fun count(minGap: Int, arr: List<Int>): Int {
	var cnt = 1

	var prev = arr[0]
	for (i in 1 until arr.size) {
		val gap = arr[i] - prev
		if (gap >= minGap) {
			cnt++
			prev = arr[i]
		}
	}
	return cnt
}