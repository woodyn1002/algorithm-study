val dx = arrayOf(0, 1, 0, -1)
val dy = arrayOf(1, 0, -1, 0)

fun main() {
	val (a, b) = readLine()!!.split(" ").map(String::toInt)
	val map = Array(b) { IntArray(a) }
	val robots = mutableMapOf<Int, Robot>()

	val (n, m) = readLine()!!.split(" ").map(String::toInt)

	for (num in 1..n) {
		val input = readLine()!!.split(" ")
		val x = input[0].toInt() - 1
		val y = input[1].toInt() - 1
		val dir = when(input[2]) {
			"N" -> 0
			"E" -> 1
			"S" -> 2
			"W" -> 3
			else -> error("wrong dir")
		}

		map[y][x] = num
		robots[num] = Robot(Pos(x, y), dir)
	}

	var answer = "OK"
	sim@for (i in 0 until m) {
		val input = readLine()!!.split(" ")
		val num = input[0].toInt()
		val cmd = input[1]
		val count = input[2].toInt()

		val robot = robots[num]!!

		when (cmd) {
			"L" -> {
				robots[num] = Robot(robot.pos, (104 + robot.dir - count) % 4)
			}
			"R" -> {
				robots[num] = Robot(robot.pos, (robot.dir + count) % 4)
			}
			"F" -> {
				var moved = robot.pos
				for (j in 0 until count) {
					val newPos = Pos(moved.x + dx[robot.dir], moved.y + dy[robot.dir])
					if (newPos.x !in 0 until a || newPos.y !in 0 until b) {
						answer = "Robot $num crashes into the wall"
						break@sim
					} else if (map[newPos.y][newPos.x] > 0) {
						answer = "Robot $num crashes into robot ${map[newPos.y][newPos.x]}"
						break@sim
					}
					moved = newPos
				}
				map[robot.pos.y][robot.pos.x] = 0
				map[moved.y][moved.x] = num
				robots[num] = Robot(moved, robot.dir)
			}
		}

		// for (y in (b - 1) downTo 0) {
		// 	for (x in 0 until a) {
		// 		if (map[y][x] > 0) {
		// 			print("${robots[map[y][x]]!!.dir} ")
		// 		} else {
		// 			print("_ ")
		// 		}
		// 	}
		// 	println()
		// }
	}

	println(answer)
}

data class Robot(val pos: Pos, val dir: Int)
data class Pos(val x: Int, val y: Int)