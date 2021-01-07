package Solutions;

public class P518_CoinChange2 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        // 1+1+2 and 1+2+1 count as 1 combination
        // so need to loop coin outside
        // can use same coin multiple times in 1 combination so start from start
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
}
