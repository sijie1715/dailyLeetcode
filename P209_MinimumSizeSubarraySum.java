package Solutions;

public class P209_MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int iLo = 0, iHi = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        for (; iHi < nums.length; iHi++) {
            sum += nums[iHi];
            while (sum >= s) {
                ans = Math.min(ans, iHi - iLo + 1);
                sum -= nums[iLo++];
            }
        }
        return (ans == Integer.MAX_VALUE) ? 0 : ans;
    }
}
