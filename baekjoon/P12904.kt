fun main() {
	val s = readLine()!!
	val t = readLine()!!

	var answer = 0

	var tmp = t
	while (tmp.length >= s.length) {
		if (tmp == s) {
			answer = 1
			break
		}

		if (tmp.last() == 'A') {
			tmp = tmp.substring(0, tmp.length - 1)
		} else {
			tmp = tmp.substring(0, tmp.length - 1).reversed()
		}
	}
	println(answer)
}