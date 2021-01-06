package Solutions;

public class P1406_StoneGameIII {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] is the best score of (current taker - opponent) if we start from stone[i]
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            int take = 0;
            for (int k = 0; k < 3 && i + k < n; k++) {
                take += stoneValue[i + k];
                dp[i] = Math.max(dp[i], take - dp[i + k + 1]);
            }
        }

        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}
