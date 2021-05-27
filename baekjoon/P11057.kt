fun main() {
	val n = readLine()!!.toInt()

	val dp = Array(n) { IntArray(10) }
	for (j in 0 until 10) {
		dp[0][j] = 1
	}

	// i: 자릿수
	for (i in 1 until n) {
		// j: i번째 자리 숫자
		for (j in 0 until 10) {
			for (k in j until 10) {
				dp[i][j] += dp[i - 1][k]
				dp[i][j] %= 10007
			}
			// println("dp[$i][$j]: ${dp[i][j]}")
		}
	}

	val answer = dp[n - 1].sum() % 10007
	println(answer)
}