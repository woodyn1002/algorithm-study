package programmers.p42890

class Solution {
    fun solution(relation: Array<Array<String>>): Int {
        val numColumns = relation[0].size

        val answers = mutableListOf<Set<Int>>()
        for (size in 1..numColumns) {
            val cases = mutableListOf<Set<Int>>()
            everyCase(cases, numColumns, size)

            caseLoop@for (case in cases) {
                // println("case=$case...")

                if (answers.any { case.containsAll(it) }) {
                    // println("..not minimal")
                    continue
                }

                val hashSet = mutableSetOf<String>()
                for (tuple in relation) {
                    val key = case.toList().map { tuple[it] }.joinToString(":")
                    if (hashSet.contains(key)) {
                        // println("..not unique")
                        continue@caseLoop
                    }
                    hashSet.add(key)
                }

                answers.add(case)
                // println("..good!")
            }
        }

        return answers.size
    }

    fun everyCase(
        dest: MutableList<Set<Int>>,
        n: Int,
        size: Int,
        visited: Set<Int> = emptySet(),
        begin: Int = 0
    ) {
        if (visited.size == size) {
            dest.add(visited)
        } else if (n - begin >= size - visited.size) {
            for (i in begin until n) {
                everyCase(dest, n, size, visited + i, i + 1)
            }
        }
    }
}