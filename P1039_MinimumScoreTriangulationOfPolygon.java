package Solutions;

public class P1039_MinimumScoreTriangulationOfPolygon {
    public int minScoreTriangulation(int[] A) {
        // dp[i][j] is min score from i to j
        // dp[i][j] = min(dp[i][k]+dp[k][j]+A[i]*A[k]*A[j]) k=(i,j)
        int n = A.length;
        int[][] dp = new int[n][n];
        // d is diff between i,j; need to reach n-1 so i+d = n-1
        for (int d = 2; d < n; d++) {
            for (int i = 0; i + d < n; i++) {
                int j = i + d;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i][k] + dp[k][j] + A[i] * A[k] * A[j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
