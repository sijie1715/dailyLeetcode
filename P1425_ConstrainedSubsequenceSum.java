package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class P1425_ConstrainedSubsequenceSum {
    public int constrainedSubsetSum(int[] nums, int k) {
        // dp with deque storing up to k values
        // deque tracks max in size k window
        // a[i] is highest sum ending with nums[i]
        // res records highest a[i]
        // only add to deque if a[i]>0
        Deque<Integer> d = new ArrayDeque<>();
        int res = nums[0];
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i] + (!d.isEmpty() ? dp[d.peek()] : 0);
            res = Math.max(res, dp[i]);
            while (!d.isEmpty() && dp[i] > dp[d.peekLast()]) d.pollLast();
            if (dp[i] > 0) d.add(i);
            if (i >= k && !d.isEmpty() && i - d.peek() >= k) d.poll();
        }
        return res;
    }
}
