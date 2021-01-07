package Solutions;

import java.util.Arrays;

public class P473_MatchsticksToSquare {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) return false;
        // sort nums from big to small so that we can add to over-target faster
        // can't sort int[] with Comparator; need Integer
        Arrays.sort(nums);
        reverse(nums);
        return dfs(nums, new int[4], 0, sum / 4);
    }

    // dfs backtracking
    private boolean dfs(int[] nums, int[] sums, int idx, int target) {
        // int[4]sums to track 4 sides
        if (idx == nums.length) {
            if (sums[0] == target && sums[1] == target && sums[2] == target) {
                return true;
            } else {
                return false;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (sums[i] + nums[idx] > target) continue;
            sums[i] += nums[idx];
            if (dfs(nums, sums, idx + 1, target)) return true;
            sums[i] -= nums[idx];
        }
        return false;
    }

    private void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
