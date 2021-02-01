package Solutions;

public class P1673_FindTheMostCompetitiveSubsequence {
    public int[] mostCompetitive(int[] nums, int k) {
        // increasing monostack until what's left is JUST enough to fill k
        int[] res = new int[k];
        // Stack<Integer> stack = new Stack<>();
        int ki = 0;
        for (int i = 0; i < nums.length; i++) {
            while (ki > 0 && nums[i] < res[ki - 1] && nums.length - i > k - ki) {
                ki--;
            }
            if (ki < k) res[ki++] = nums[i];
            // while (!stack.isEmpty() && nums[i] < stack.peek() && nums.length - i > k - stack.size()) {
            //     stack.pop();
            // }
            // stack.push(nums[i]);
        }
        // while (stack.size() > k) stack.pop();
        // for (int i = k - 1; i >= 0; i--) {
        //     res[i] = stack.pop();
        // }
        return res;
    }
}
