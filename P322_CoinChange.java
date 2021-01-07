package Solutions;

public class P322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        // dp[i] is the min number of coins to make up i
        int[] dp = new int[amount + 1];
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                // if coin is i, then 1 coin
                if (i == coin) {
                    dp[i] = 1;
                }
                // i has not been visited before; make sure i-coin is reachable
                else if (dp[i] == 0 && dp[i - coin] != 0) {
                    dp[i] = dp[i - coin] + 1;
                } else if (dp[i - coin] != 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }
}
