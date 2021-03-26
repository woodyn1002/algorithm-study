package baekjoon.p14891

fun main() {
    val gears = Array(4) {
        readLine()!!.toCharArray().map(Character::getNumericValue).toIntArray()
    }

    val k = readLine()!!.toInt()
    repeat(k) {
        val (num, dir) = readLine()!!.split(" ").map(String::toInt)
        rotate(gears, num - 1, dir)

//        println("-- #$it:")
//        gears.forEach { println(it.joinToString("")) }
//        println("---")
    }

    var answer = 0
    if (gears[0][0] == 1) answer += 1
    if (gears[1][0] == 1) answer += 2
    if (gears[2][0] == 1) answer += 4
    if (gears[3][0] == 1) answer += 8
    println(answer)
}

fun rotate(
    gears: Array<IntArray>,
    index: Int,
    dir: Int,
    visited: BooleanArray = BooleanArray(4)
) {
    visited[index] = true

    if (index > 0 && !visited[index - 1] && gears[index - 1][2] != gears[index][6]) {
        rotate(gears, index - 1, -dir, visited)
    }
    if (index < 3 && !visited[index + 1] && gears[index + 1][6] != gears[index][2]) {
        rotate(gears, index + 1, -dir, visited)
    }

    val tmp = gears[index].clone()
    for (i in 0 until 8) {
        gears[index][i] = tmp[(i - dir + 8) % 8]
    }
}