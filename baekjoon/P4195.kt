fun main() {
	val t = readLine()!!.toInt()
	repeat(t) {
		val friend = mutableMapOf<String, String>()
		val counts = mutableMapOf<String, Int>()

		val f = readLine()!!.toInt()
		repeat(f) {
			val (a, b) = readLine()!!.split(" ")

			if (a !in friend) {
				friend[a] = a
				counts[a] = 1
			}
			if (b !in friend) {
				friend[b] = b
				counts[b] = 1
			}
			union(friend, counts, a, b)

			val group = find(friend, b)
			println(counts[group])
		}
	}
}

fun find(friend: MutableMap<String, String>, person: String): String {
	if (friend[person] == person)
		return person

	friend[person] = find(friend, friend[person]!!)
	return friend[person]!!
}

fun union(friend: MutableMap<String, String>, counts: MutableMap<String, Int>, a: String, b: String) {
	val groupA = find(friend, a)
	val groupB = find(friend, b)

	if (groupA != groupB) {
		val cnt = counts[groupA]!! + counts[groupB]!!
		friend[groupA] = groupB
		counts[groupB] = cnt
	}
}