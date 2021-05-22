package Solutions;

import java.util.Stack;

public class P581_ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray1(int[] nums) {
        // get the leftest and rightest misplaced element separately
        Stack<Integer> s = new Stack<>();
        int left = nums.length, right = 0;

        for (int i = 0; i < nums.length; i++) {
            while (!s.isEmpty() && nums[s.peek()] > nums[i]) {
                left = Math.min(left, s.pop());
            }
            s.push(i);
        }

        s.clear();
        for (int j = nums.length - 1; j >= 0; j--) {
            while (!s.isEmpty() && nums[s.peek()] < nums[j]) {
                right = Math.max(right, s.pop());
            }
            s.push(j);
        }

        return left - right == nums.length ? 0 : right - left + 1;
    }

    public int findUnsortedSubarray(int[] nums) {
        int left = -1, right = -2, max = nums[0], min = nums[nums.length - 1];
        // find rightest
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (max > nums[i]) {
                right = i;
            }
        }

        // find leftest
        for (int j = nums.length - 1; j >= 0; j--) {
            min = Math.min(min, nums[j]);
            if (min < nums[j]) {
                left = j;
            }
        }
        return right - left + 1;
    }
}
