package baekjoon.p2263

fun main() {
    val n = readLine()!!.toInt()

    preOrder(
        inOrder = readLine()!!.split(" ").map(String::toInt),
        postOrder = readLine()!!.split(" ").map(String::toInt),
        inOrderStart = 0,
        inOrderLast = n - 1,
        postOrderStart = 0,
        postOrderLast = n - 1
    )
}

fun preOrder(
    inOrder: List<Int>,
    postOrder: List<Int>,
    inOrderStart: Int,
    inOrderLast: Int,
    postOrderStart: Int,
    postOrderLast: Int
) {
    if (inOrderStart > inOrderLast || postOrderStart > postOrderLast) return

    val root = postOrder[postOrderLast]
    var indexOfRoot = -1
    for (i in inOrderStart..inOrderLast) {
        if (inOrder[i] == root) {
            indexOfRoot = i
            break
        }
    }

    print("$root ")
    preOrder(
        inOrder = inOrder,
        postOrder = postOrder,
        inOrderStart = inOrderStart,
        inOrderLast = indexOfRoot - 1,
        postOrderStart = postOrderStart,
        postOrderLast = postOrderStart + (indexOfRoot - inOrderStart) - 1
    )
    preOrder(
        inOrder = inOrder,
        postOrder = postOrder,
        inOrderStart = indexOfRoot + 1,
        inOrderLast = inOrderLast,
        postOrderStart = postOrderStart + (indexOfRoot - inOrderStart),
        postOrderLast = postOrderLast - 1
    )
}