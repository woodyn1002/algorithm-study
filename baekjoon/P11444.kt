package baekjoon.p11444

import java.math.BigDecimal
import java.math.RoundingMode

val TWO = BigDecimal(2)

fun main() {
    val n = BigDecimal(readLine()!!)

    val matrix = pow(Matrix(arrayOf(longArrayOf(1, 1), longArrayOf(1, 0))), n)
    println(matrix.arr[0][1])
}

fun pow(matrix: Matrix, num: BigDecimal): Matrix {
    if (num == BigDecimal.ONE) return matrix

    val half = pow(matrix, num.divide(TWO, RoundingMode.DOWN))
    return half * half * if (num.remainder(TWO) == BigDecimal.ONE) matrix else Matrix.ZERO
}

class Matrix(val arr: Array<LongArray>) {
    operator fun times(other: Matrix): Matrix = Matrix(
        arrayOf(
            longArrayOf(
                (this.arr[0][0] * other.arr[0][0] + this.arr[0][1] * other.arr[1][0]) % 1_000_000_007,
                (this.arr[0][0] * other.arr[0][1] + this.arr[0][1] * other.arr[1][1]) % 1_000_000_007
            ),
            longArrayOf(
                (this.arr[1][0] * other.arr[0][0] + this.arr[1][1] * other.arr[1][0]) % 1_000_000_007,
                (this.arr[1][0] * other.arr[0][1] + this.arr[1][1] * other.arr[1][1]) % 1_000_000_007
            ),
        )
    )

    companion object {
        val ZERO = Matrix(
            arrayOf(
                longArrayOf(1, 0),
                longArrayOf(0, 1)
            )
        )
    }
}