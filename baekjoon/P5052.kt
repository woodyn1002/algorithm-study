fun main() {
	val t = readLine()!!.toInt()
	repeat(t) {
		val n = readLine()!!.toInt()
		val numbers = Array<String>(n) { readLine()!! }

		val root = Node()

		var answer = "YES"
		for (number in numbers) {
			if (!root.isValid(number, 0)) {
				// println("because of $number..")
				answer = "NO"
				break
			}
			root.insert(number, 0)
		}

		println(answer)
	}
}

class Node {
	val children = mutableMapOf<Char, Node>()

	fun insert(number: String, depth: Int) {
		if (depth < number.length) {
			val digit = number[depth]

			if (digit !in children) {
				children[digit] = Node()
			}
			children[digit]!!.insert(number, depth + 1)
		}
	}

	fun isValid(number: String, depth: Int): Boolean {
		if (depth == number.length) {
			// 더 짧은 중복 번호임
			return false
		} else {
			// 리프 노드 --> 이미 중복 번호가 있음
			if (depth > 0 && children.isEmpty()) {
				return false
			}

			val digit = number[depth]

			// 리프 노드가 아니지만 브랜치가 없음 --> 중복 번호가 아직 없음
			if (digit !in children) {
				return true
			}
			return children[digit]!!.isValid(number, depth + 1)
		}
	}
}