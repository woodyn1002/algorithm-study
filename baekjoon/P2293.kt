fun main() {
	val (n, k) = readLine()!!.split(" ").map(String::toInt)
	val coins = IntArray(n) { readLine()!!.toInt() }

	val dp = IntArray(k + 1)
	dp[0] = 1

	for (coin in coins) {
		for (num in 1..k) {
			if (num >= coin) {
				dp[num] += dp[num - coin]
			}
		}
	}

	println(dp[k])
}