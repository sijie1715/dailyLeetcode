package Solutions;

public class P1510_StoneGameIV {
    public boolean winnerSquareGame(int n) {
        // dp[i] is if can win with i stones
        // if dp[i-k*k]==false then can take k*k stones and make opponent lose
        // dp[i]=true
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int k = 1; i - k * k >= 0; k++) {
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
