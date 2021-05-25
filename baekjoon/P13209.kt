import kotlin.math.*

fun main() {
	val t = readLine()!!.toInt()
	repeat(t) {
		val (n, k) = readLine()!!.split(" ").map(String::toInt)
		val people = readLine()!!.split(" ").map(String::toInt).toIntArray()

		val adj = Array(n) { mutableListOf<Int>() }
		repeat(n - 1) {
			val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
			adj[u].add(v)
			adj[v].add(u)
		}

		val visited = BooleanArray(n).apply { this[0] = true }
		val root = Node(0, people[0])
		makeTree(adj, people, visited, root)

		var maxPeople = 0
		var sumPeople = 0L
		for (p in people) {
			maxPeople = max(p, maxPeople)
			sumPeople += p.toLong()
		}

		var answer = 0L
		var bottom = maxPeople.toLong()
		var top = sumPeople
		while (top >= bottom) {
			val mid = (top + bottom) / 2L
			// println("mid=$mid..")

			root.solve(mid)
			if (root.cntCuts <= k) {
				answer = root.answer
				top = mid - 1
				// println("- promising (answer=$answer)")
			} else {
				bottom = mid + 1
			}
			// println("..")
		}

		println(answer)
	}
}

fun makeTree(adj: Array<out List<Int>>, people: IntArray, visited: BooleanArray, parent: Node) {
	for (nextNum in adj[parent.num]) {
		if (visited[nextNum]) continue

		visited[nextNum] = true
		val child = Node(nextNum, people[nextNum])
		parent.children.add(child)
		makeTree(adj, people, visited, child)
	}
}

class Node(val num: Int, val people: Int) {
	val children = mutableListOf<Node>()
	var answer = people.toLong()
	var groupPeople = people.toLong()
	var cntCuts = 0

	fun solve(maxAnswer: Long) {
		if (children.isEmpty()) {
			// println("- #${num + 1}: answer=$answer, groupPeople=$groupPeople, cntCuts=$cntCuts")
			return
		}

		this.answer = 0L
		this.groupPeople = this.people.toLong()
		this.cntCuts = 0

		for (child in children) {
			child.solve(maxAnswer)

			this.answer = max(child.answer, this.answer)
			this.groupPeople += child.groupPeople
			this.cntCuts += child.cntCuts
		}

		children.sortByDescending { it.groupPeople }

		var cur = 0
		while (this.groupPeople > maxAnswer) {
			this.groupPeople -= children[cur].groupPeople
			this.cntCuts++
			cur++
		}
		this.answer = max(this.groupPeople, this.answer)

		// println("- #${num + 1}: answer=$answer, groupPeople=$groupPeople, cntCuts=$cntCuts")
	}
}