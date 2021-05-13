import kotlin.math.*

fun main() {
	val n = readLine()!!.toInt()
	val nodes = Array(n) { Node(it) }

	val master = readLine()!!.split(" ").map(String::toInt).toIntArray()
	for (i in 1 until n) {
		nodes[master[i]].children.add(nodes[i])
	}

	nodes[0].traverse()
	val answer = nodes[0].time
	println(answer)
}

class Node(val num: Int) {
	val children = mutableListOf<Node>()
	var time = 0

	fun traverse() {
		if (children.isEmpty()) return

		for (child in children) {
			child.traverse()
		}
		children.sortByDescending { it.time }

		var elapsed = 0
		for (child in children) {
			elapsed++
			time = max(elapsed + child.time, time)
		}
	}
}