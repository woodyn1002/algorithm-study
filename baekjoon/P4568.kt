fun main() {
	var simNum = 1
	while (true) {
		val input = readLine()!!.split(" ")

		val n = input[0].toInt()
		if (n == 0) {
			break
		}

		println("Simulation $simNum")

		val cache = Cache(n)

		for (cmd in input[1]) {
			when (cmd) {
				'!' -> {
					println(cache.contents)
				}
				else -> {
					cache.add(cmd)
				}
			}
		}

		simNum++
	}
}

class Cache(val capacity: Int) {
	var head = Node(null)
	var tail = head
	var size = 0

	val contents: String
		get() {
			val builder = StringBuilder()

			var cur: Node? = head
			while (cur != null) {
				if (cur.character != null) {
					builder.append(cur.character)	
				}
				cur = cur.next
			}

			return builder.toString()
		}

	fun add(character: Char) {
		var cur = this.head
		while (cur.next != null) {
			if (cur.next!!.character == character) {
				cur.next = cur.next!!.next
				this.size--

				if (cur.next == null) {
					this.tail = cur
				}
				break
			}
			cur = cur.next!!
		}

		if (this.size == this.capacity) {
			this.head.next = this.head.next!!.next

			if (this.head.next == null) {
				this.tail = this.head
			}
			this.size--
		}

		val node = Node(character)
		this.tail.next = node
		this.tail = node
		this.size++
		// println("..${this.contents}")
	}
}

class Node(val character: Char? = null) {
	var next: Node? = null
}