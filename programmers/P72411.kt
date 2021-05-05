package programmers.p72411

class Solution {
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val answers = mutableListOf<String>()

        for (numMenu in course) {
            val pop = mutableMapOf<String, Int>()

            for (order in orders) {
                val sortedOrder = order.toList().sorted().joinToString("")
                putAllCombinations(pop, numMenu, sortedOrder)
            }
            // pop.forEach { println(it) }

            val max = pop.maxBy { it.value }?.value
            if (max != null && max >= 2) {
                answers += pop.filter { it.value == max }.keys
            }
        }

        answers.sort()
        return answers.toTypedArray()
    }

    fun putAllCombinations(
        dest: MutableMap<String, Int>,
        numMenu: Int,
        order: String,
        visited: String = "",
        begin: Int = 0
    ) {
        // println("visited=$visited, begin=$begin:")
        if (visited.length == numMenu) {
            // println("leaf!")
            dest[visited] = (dest[visited] ?: 0) + 1
        } else if (numMenu - visited.length <= order.length - begin) {
            // println("branching")
            for (i in begin until order.length) {
                putAllCombinations(dest, numMenu, order, visited + order[i], i + 1)
            }
        }
    }
}