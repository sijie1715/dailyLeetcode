package Solutions;

import java.util.Arrays;

public class P1498_NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1, mod = (int) 1e9 + 7;
        int[] pows = new int[nums.length];
        pows[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            pows[i] = (pows[i - 1] * 2) % mod;
        }
        int res = 0;
        // if nums[l]+nums[r]<=target, then there are 2^(r-l) subsequences
        // for each num after nums[l], we can pick or not pick
        while (l <= r) {
            if (nums[l] + nums[r] > target) r--;
            else {
                res = (res + pows[r - l]) % mod;
                l++;
                res %= mod;
            }
        }
        return res;
    }
}
