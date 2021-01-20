package Solutions;

public class P312_BurstBalloons {
    public int maxCoins(int[] nums) {
        // add boundaries of 1 on both sides and skip 0s
        int[] newNums = new int[nums.length + 2];
        int n = 1;
        for (int num : nums) if (num > 0) newNums[n++] = num;
        newNums[0] = newNums[n] = 1;

        int[][] memo = new int[n + 1][n + 1];
        return burst(newNums, memo, 0, n);
    }

    private int burst(int[] nums, int[][] memo, int left, int right) {
        if (left + 1 == right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        // nums[i] is the last balloon popped
        int ans = 0;
        for (int i = left + 1; i < right; i++) {
            ans = Math.max(ans, nums[i] * nums[left] * nums[right]
                    + burst(nums, memo, left, i)
                    + burst(nums, memo, i, right));
        }
        memo[left][right] = ans;
        return ans;
    }
}
