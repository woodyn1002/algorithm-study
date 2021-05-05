fun main() {
	val input = readLine()!!

	val answer = StringBuilder()
	val buffer = StringBuilder()
	var isTag = false
	for (c in input) {
		when (c) {
			' ' -> {
				answer.append(buffer.toString())
				buffer.clear()
				
				answer.append(c)
			}
			'<' -> {
				answer.append(buffer.toString())
				buffer.clear()

				isTag = true
				answer.append(c)
			}
			'>' -> {
				isTag = false
				answer.append(c)
			}
			else -> {
				if (isTag) {
					answer.append(c)	
				} else {
					buffer.insert(0, c)
				}
			}
		}
	}

	if (!buffer.isEmpty()) {
		answer.append(buffer.toString())
	}

	println(answer.toString())
}