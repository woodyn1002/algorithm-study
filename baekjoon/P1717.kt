fun main() {
	val (n, m) = readLine()!!.split(" ").map(String::toInt)

	val root = IntArray(n + 1) { i -> i }

	for (i in 0 until m) {
		val (cmd, a, b) = readLine()!!.split(" ").map(String::toInt)
		when (cmd) {
			0 -> union(root, a, b)
			1 -> println(if (find(root, a) == find(root, b)) "YES" else "NO")
		}
	}
}

fun find(root: IntArray, x: Int): Int {
	if (root[x] == x) {
		return x
	} else {
		root[x] = find(root, root[x])
		return root[x]
	}
}

fun union(root: IntArray, a: Int, b: Int) {
	val x = find(root, a)
	val y = find(root, b)
	root[y] = x
}