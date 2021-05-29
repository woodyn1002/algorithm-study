import kotlin.math.*

fun main() {
	val (n, m) = readLine()!!.split(" ").map(String::toInt)

	val arr = IntArray(n) { readLine()!!.toInt() }

	val height = ceil(log2(n.toDouble())).toInt()
	val minTree = IntArray(1 shl (height + 1))
	val maxTree = IntArray(1 shl (height + 1))

	initTrees(minTree, maxTree, arr, 1, 0, arr.lastIndex)

	repeat(m) {
		val (a, b) = readLine()!!.split(" ").map { it.toInt() - 1 }

		val (min, max) = find(minTree, maxTree, 1, 0, arr.lastIndex, a, b)
		println("$min $max")
	}
}

fun initTrees(minTree: IntArray, maxTree: IntArray, arr: IntArray, node: Int, start: Int, end: Int): Pair<Int, Int> {
	if (start == end) {
		minTree[node] = arr[start]
		maxTree[node] = arr[start]
		return Pair(minTree[node], maxTree[node])
	}

	val mid = (start + end) / 2
	val (leftMin, leftMax) = initTrees(minTree, maxTree, arr, node * 2, start, mid)
	val (rightMin, rightMax) = initTrees(minTree, maxTree, arr, node * 2 + 1, mid + 1, end)
	minTree[node] = min(leftMin, rightMin)
	maxTree[node] = max(leftMax, rightMax)
	return Pair(minTree[node], maxTree[node])
}

fun find(minTree: IntArray, maxTree: IntArray, node: Int, start: Int, end: Int, left: Int, right: Int): Pair<Int, Int> {
	if (left > end || right < start) {
		return Pair(Int.MAX_VALUE, Int.MIN_VALUE)
	}

	if (left <= start && end <= right) {
		return Pair(minTree[node], maxTree[node])
	}

	val mid = (start + end) / 2
	val (leftMin, leftMax) = find(minTree, maxTree, node * 2, start, mid, left, right)
	val (rightMin, rightMax) = find(minTree, maxTree, node * 2 + 1, mid + 1, end, left, right)
	return Pair(min(leftMin, rightMin), max(leftMax, rightMax))
}