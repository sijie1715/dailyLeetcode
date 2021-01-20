package Solutions;

public class P198_HouseRobber {
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + (i > 1 ? dp[i - 2] : 0));
        }
        return dp[nums.length - 1];
    }
}
