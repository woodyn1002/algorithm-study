package programmers.p67258

class Solution {
    fun solution(gems: Array<String>): IntArray {
        val types = mutableSetOf<String>()
        for (gem in gems)
            types.add(gem)

        val pos = mutableMapOf<String, Int>()
        var begin = 0
        var end = gems.size - 1
        for ((idx, gem) in gems.withIndex()) {
            pos[gem] = idx
            if (pos.size == types.size) {
                pos[gem] = idx
                val minPos = pos.values.min()!!

                if (idx - minPos < end - begin) {
                    begin = minPos
                    end = idx
                }
            }
        }
        return intArrayOf(begin + 1, end + 1)
    }
}