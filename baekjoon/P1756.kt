fun main() {
	val (d, n) = readLine()!!.split(" ").map(String::toInt)
	val oven = readLine()!!.split(" ").map(String::toInt).toIntArray()
	val pizzas = readLine()!!.split(" ").map(String::toInt).toIntArray()

	for (i in 1 until d) {
		if (oven[i] > oven[i - 1]) {
			oven[i] = oven[i - 1]
		}
	}

	var top = d

	for (i in 0 until n) {
		val pizza = pizzas[i]

		var cur = top - 1
		while (cur >= 0 && oven[cur] < pizza) {
			cur--
		}
		if (cur < 0) {
			top = -1
			break
		}
		// println("$pizza: $cur")

		top = cur
	}
	println(top + 1)
}