package Solutions;

public class P375_GuessNumberHigherOrLowerII {
    public int getMoneyAmount(int n) {
        // dp[i][j] is min cost to guess [i..j]
        int[][] dp = new int[n + 1][n + 1];
        return helper(dp, 1, n);
    }

    private int helper(int[][] dp, int s, int e) {
        // base case
        if (s >= e) return 0;
        // skip already calculated
        if (dp[s][e] > 0) return dp[s][e];
        int res = Integer.MAX_VALUE;
        for (int i = s; i <= e; i++) {
            int temp = i + Math.max(helper(dp, s, i - 1), helper(dp, i + 1, e));
            res = Math.min(res, temp);
        }
        dp[s][e] = res;
        return res;
    }
}
