fun main() {
	val (n, s) = readLine()!!.split(" ").map(String::toInt)
	val arr = readLine()!!.split(" ").map(String::toInt).toIntArray()

	var minLength: Int? = null

	var curSum = arr[0].toLong()
	var start = 0
	var end = 0
	while (start < arr.size) {
		if (curSum >= s && (end - start + 1) < minLength ?: Int.MAX_VALUE) {
			minLength = (end - start + 1)
		}

		if (curSum >= s || end == arr.lastIndex) {
			curSum -= arr[start]
			start++
		} else {
			end++
			curSum += arr[end]
		}
	}

	println(minLength ?: 0)
}