fun main() {
	val (n, h) = readLine()!!.split(" ").map(String::toInt)
	val obstaclesDown = mutableListOf<Int>()
	val obstaclesUp = mutableListOf<Int>()
	repeat(n) { it ->
		val obstacle = readLine()!!.toInt()
		if (it % 2 == 0) {
			obstaclesDown += obstacle
		} else {
			obstaclesUp += obstacle
		}
	}
	obstaclesDown.sort()
	obstaclesUp.sort()

	var minObstacles = Int.MAX_VALUE
	var cntMinObstacles = 0
	for (height in 1..h) {
		val numObstaclesDown = obstaclesDown.size - lowerBound(obstaclesDown, height)
		val numObstaclesUp = obstaclesUp.size - lowerBound(obstaclesUp, h - height + 1)

		val numObstacles = numObstaclesDown + numObstaclesUp
		// println("height=$height: down=$numObstaclesDown, up=$numObstaclesUp")

		if (numObstacles < minObstacles) {
			minObstacles = numObstacles
			cntMinObstacles = 1
		} else if (numObstacles == minObstacles) {
			cntMinObstacles++
		}
	}

	println("$minObstacles $cntMinObstacles")
}

fun lowerBound(list: List<Int>, target: Int): Int {
	var start = 0
	var end = list.size

	while (end > start) {
		val mid = (start + end) / 2
		if (list[mid] >= target)
			end = mid
		else
			start = mid + 1
	}
	return end
}