package Solutions;

public class P712_MinimumASCIIDeleteSumForTwoStrings {
    public int minimumDeleteSum(String s1, String s2) {
        // find LCS and sum them ascii
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int sum1 = 0, sum2 = 0;
        for (int i = 1; i <= m; i++) {
            sum1 += s1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            sum2 += s2.charAt(i);
        }
        return sum1 + sum2 - 2 * dp[m][n];
    }
}
