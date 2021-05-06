import kotlin.math.*

fun main() {
	val (n, m, r) = readLine()!!.split(" ").map(String::toInt)
	val matrix = Array(n) {
		readLine()!!.split(" ").map(String::toInt).toIntArray()
	}

	repeat(r) { it ->
		val prev = Array(n) { i -> IntArray(m) { j -> matrix[i][j] } }

		for (a in 0 until min(n, m) / 2) {
			for (j in a until (m - 1 - a)) {
				val i = a
				matrix[i][j] = prev[i][j + 1]
			}
			for (i in a until (n - 1 - a)) {
				val j = m - 1 - a
				matrix[i][j] = prev[i + 1][j]
			}
			for (j in (m - 1 - a) downTo (a + 1)) {
				val i = n - 1 - a
				matrix[i][j] = prev[i][j - 1]
			}
			for (i in (n - 1 - a) downTo (a + 1)) {
				val j = a
				matrix[i][j] = prev[i - 1][j]
			}
		}

		// println("cur=$it:")
		// for (i in 0 until n) {
		// 	println(matrix[i].joinToString(" "))
		// }
		// println("..")
	}

	for (i in 0 until n) {
		println(matrix[i].joinToString(" "))
	}
}