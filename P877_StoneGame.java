package Solutions;

public class P877_StoneGame {
    public boolean stoneGame1(int[] piles) {
        // dp[i][j] is the best score of (Alex - Lee) from piles[i]~piles[j]
        // if you pick p[i] then score is p[i]-dp[i+1][j]
        // if you pick p[j] then score is p[j]-dp[i][j-1]
        // base case is dp[i][i]=p[i]
        // extend piles length l until we get dp[0][n-1] (l=[1,n-1])
        int n = piles.length;
        int[][] dp = new int[n][n];
        // base case
        for (int x = 0; x < n; x++) dp[x][x] = piles[x];
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                dp[i][i + l] = Math.max(piles[i] - dp[i + 1][i + l], piles[i + l] - dp[i][i + l - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }

    public boolean stoneGame2(int[] piles) {
        // iterate l=[0,n-1]
        // dp[i] is dp[i][i+l] at that iteration of l
        // so: dp[i][i+l] = Math.max(piles[i]-dp[i+1][i+l], piles[i+l]-dp[i][i+l-1]);
        // can be:  dp[i] = Math.max(piles[i]-dp[i+1], piles[i+l]-dp[i]);
        // dp[] in the comparison is from last round where l was l-1
        int n = piles.length;
        int[] dp = piles.clone();
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                dp[i] = Math.max(piles[i] - dp[i + 1], piles[i + l] - dp[i]);
            }
        }
        return dp[0] > 0;
    }
}
