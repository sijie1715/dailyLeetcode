package Solutions;

public class P72_EditDistance {
    public int minDistance(String word1, String word2) {
        // dp[i][j] to make the first i chars in word1 and first j in word2 the same
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // dp[i][0] and dp[0][j] should be i and j
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
