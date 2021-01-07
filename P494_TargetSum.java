package Solutions;

public class P494_TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        // positive and negative parts sumP-sumN=S
        // sumP+sumN=sum,sumP-(sum-sumP)=S,sumP=(S+sum)/2
        // dp[i] is number of ways to get sum of i
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || (S + sum) % 2 > 0) return 0;

        int target = (S + sum) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}
