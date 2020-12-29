package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class P1696_JumpGameVI {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        // use deque to keep only the best result
        // change dp from O(NK) to O(N)
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> d = new ArrayDeque<>();
        d.add(0);
        for (int i = 1; i < n; i++) {
            // maintain deque size
            while (!d.isEmpty() && i - d.peekFirst() > k) {
                d.poll();
            }
            dp[i] = dp[d.peek()] + nums[i];
            // keep only max result in deque
            while (!d.isEmpty() && dp[i] >= dp[d.peekLast()]) {
                d.pollLast();
            }
            d.add(i);
        }
        return dp[n - 1];
    }
}
