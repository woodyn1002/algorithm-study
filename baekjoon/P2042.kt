import kotlin.math.*

fun main() {
	val (n, m, k) = readLine()!!.split(" ").map(String::toInt)

	val arr = LongArray(n) { readLine()!!.toLong() }

	val height = ceil(log2(n.toDouble())).toInt()
	val tree = LongArray(1 shl (height + 1))
	initTree(tree, arr, 1, 0, arr.lastIndex)

	// println("current tree: [${tree.joinToString(", ")}]")
	repeat(m + k) {
		val segs = readLine()!!.split(" ")

		when (segs[0]) {
			"1" -> {
				val b = segs[1].toInt() - 1
				val c = segs[2].toLong()

				update(tree, arr, 1, 0, arr.lastIndex, b, c - arr[b])
				arr[b] = c
			}
			"2" -> {
				val b = segs[1].toInt() - 1
				val c = segs[2].toInt() - 1

				val result = sum(tree, arr, 1, 0, arr.lastIndex, b, c)
				println(result)
			}
		}
		// println("current tree: [${tree.joinToString(", ")}]")
	}
}

fun initTree(tree: LongArray, arr: LongArray, node: Int, start: Int, end: Int): Long {
	if (start == end) {
		tree[node] = arr[start]
		return tree[node]
	}

	val mid = (start + end) / 2
	val sum = initTree(tree, arr, node * 2, start, mid) + initTree(tree, arr, node * 2 + 1, mid + 1, end)
	tree[node] = sum
	return sum
}

fun update(tree: LongArray, arr: LongArray, node: Int, start: Int, end: Int, index: Int, diff: Long) {
	if (index < start || index > end)
		return
	
	tree[node] += diff

	if (start != end) {
		val mid = (start + end) / 2
		update(tree, arr, node * 2, start, mid, index, diff)
		update(tree, arr, node * 2 + 1, mid + 1, end, index, diff)
	}
}

fun sum(tree: LongArray, arr: LongArray, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
	if (left > end || right < start)
		return 0L

	if (left <= start && end <= right) {
		return tree[node]
	}

	var sum = 0L

	val mid = (start + end) / 2
	sum += sum(tree, arr, node * 2, start, mid, left, right)
	sum += sum(tree, arr, node * 2 + 1, mid + 1, end, left, right)

	return sum
}