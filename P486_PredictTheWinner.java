package Solutions;

public class P486_PredictTheWinner {
    public boolean PredictTheWinner1(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        // dp[i][j] is the best score picking player can get
        // *** score is player - oppnent; picking at that round
        // base case dp[x][x]=nums[x]
        // increment window length

        // base case populate
        for (int x = 0; x < n; x++) {
            dp[x][x] = nums[x];
        }
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                dp[i][i + l] = Math.max(nums[i] - dp[i + 1][i + l], nums[i + l] - dp[i][i + l - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }

    public boolean PredictTheWinner2(int[] nums) {
        int n = nums.length;
        int[] dp = nums.clone();
        // only topright half of dp[][] are updated
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                dp[i] = Math.max(nums[i] - dp[i + 1], nums[i + l] - dp[i]);
            }
        }
        return dp[0] >= 0;
    }

}
