val HR_LINE = "-".repeat(80)

fun main() {
	val tokens = mutableListOf<String>()

	while (true) {
		val input = readLine()
		if (input == null) {
			break
		}

		tokens.addAll(input.split(Regex(" |	")))
	}

	var builder = StringBuilder()
	for (token in tokens) {
		when (token) {
			"<br>" -> {
				println(builder.toString())
				builder = StringBuilder()
			}
			"<hr>" -> {
				if (builder.length > 0) {
					println(builder.toString())
					builder = StringBuilder()
				}
				println(HR_LINE)
			}
			else -> {
				if (token.isEmpty()) continue

				if (builder.length + 1 + token.length > 80) {
					println(builder.toString())
					builder = StringBuilder(token)
				} else {
					if (builder.length > 0) {
						builder.append(' ')
					}
					builder.append(token)
				}
			}
		}
	}
	if (builder.length > 0) {
		println(builder.toString())
	}
}