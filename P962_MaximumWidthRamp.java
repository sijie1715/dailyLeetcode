package Solutions;

import java.util.Stack;

public class P962_MaximumWidthRamp {
    public int maxWidthRamp(int[] A) {
        // decreasing stack
        // scan 2nd time from the end to look for farthest smaller
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            if (stack.isEmpty() || A[i] <= A[stack.peek()]) stack.push(i);
        }
        int res = -1;
        for (int i = A.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[i] >= A[stack.peek()]) {
                res = Math.max(res, i - stack.pop());
            }
        }
        return res;
    }
}
