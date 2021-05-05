fun main() {
	val n = readLine()!!.toInt()
	val switches = readLine()!!.split(" ").map(String::toInt).toIntArray()

	val numStudents = readLine()!!.toInt()
	repeat (numStudents) {
		val (gender, number) = readLine()!!.split(" ").map(String::toInt)

		when (gender) {
			1 -> {
				var idx = number - 1
				while (idx < switches.size) {
					switches[idx] = (switches[idx] + 1) % 2
					idx += number
				}
			}
			2 -> {
				val idx = number - 1
				var amount = 0
				while (idx - amount >= 0 && idx + amount < switches.size &&
					switches[idx - amount] == switches[idx + amount]) {
					if (amount > 0) {
						switches[idx - amount] = (switches[idx - amount] + 1) % 2
						switches[idx + amount] = (switches[idx + amount] + 1) % 2
					} else {
						switches[idx] = (switches[idx] + 1) % 2
					}
					amount++
				}
			}
		}
	}

	for (chunk in switches.toList().chunked(20)) {
		println(chunk.joinToString(" "))
	}
}