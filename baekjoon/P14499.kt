package baekjoon.p14499

fun main() {
    val (n, m, x, y, k) = readLine()!!.split(" ").map(String::toInt)

    val map = Array(n) {
        readLine()!!.split(" ").map(String::toInt).toIntArray()
    }

    val commands = readLine()!!.split(" ").map(String::toInt)
    val dice = Dice(y, x)

    for (cmd in commands) {
        var moved = false
        when (cmd) {
            1 -> if (dice.x + 1 < m) {
                dice.moveEast()
                moved = true
            }
            2 -> if (dice.x - 1 >= 0) {
                dice.moveWest()
                moved = true
            }
            3 -> if (dice.y - 1 >= 0) {
                dice.moveNorth()
                moved = true
            }
            4 -> if (dice.y + 1 < n) {
                dice.moveSouth()
                moved = true
            }
        }
        if (moved) {
            if (map[dice.y][dice.x] == 0) {
                map[dice.y][dice.x] = dice.bottom
            } else {
                dice.bottom = map[dice.y][dice.x]
                map[dice.y][dice.x] = 0
            }

            println(dice.top)
        }
    }
}

class Dice(
    var x: Int,
    var y: Int
) {
    private val horizontalNumbers: MutableList<Int> = mutableListOf(0, 0, 0, 0)
    private val verticalNumbers: MutableList<Int> = mutableListOf(0, 0, 0, 0)

    var bottom: Int
        get() = horizontalNumbers.last()
        set(value) {
            horizontalNumbers[3] = value
            verticalNumbers[3] = value
        }

    val top: Int
        get() = horizontalNumbers[1]

    fun moveEast() {
        x++

        val last = horizontalNumbers.removeLast()
        horizontalNumbers.add(0, last)

        verticalNumbers[1] = horizontalNumbers[1]
        verticalNumbers[3] = horizontalNumbers[3]
    }

    fun moveWest() {
        x--

        val first = horizontalNumbers.removeFirst()
        horizontalNumbers.add(first)

        verticalNumbers[1] = horizontalNumbers[1]
        verticalNumbers[3] = horizontalNumbers[3]
    }

    fun moveNorth() {
        y--

        val first = verticalNumbers.removeFirst()
        verticalNumbers.add(first)

        horizontalNumbers[1] = verticalNumbers[1]
        horizontalNumbers[3] = verticalNumbers[3]
    }

    fun moveSouth() {
        y++

        val last = verticalNumbers.removeLast()
        verticalNumbers.add(0, last)

        horizontalNumbers[1] = verticalNumbers[1]
        horizontalNumbers[3] = verticalNumbers[3]
    }
}
