package Solutions;

public class P1458_MaxDotProductOfTwoSubsequences {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        // dp[i][j] is max dot product from first i nums1 and j nums2
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = nums1[i] * nums2[j];     // initialize
                if (i > 0 && j > 0) {
                    dp[i][j] += Math.max(0, dp[i - 1][j - 1]);      // previous could be <0
                }
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);    // check if ditch i
                }
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);    // check if ditch j
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
