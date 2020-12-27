package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class P239_SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> maxd = new ArrayDeque<>();
        // List<Integer> res = new ArrayList<>();
        int[] res = new int[nums.length - k + 1];
        int l = 0;
        for (int j = 0; j < nums.length; j++) {
            while (!maxd.isEmpty() && nums[j] > maxd.peekLast()) {
                maxd.pollLast();
            }
            maxd.addLast(nums[j]);
            k--;
            if (k < 0) {
                if (maxd.peek() == nums[l]) maxd.poll();
                l++;
                k++;
            }
            // if (k == 0) res.add(maxd.peek());
            if (k == 0) res[l] = maxd.peek();
        }
        // int[] resArray = res.stream().mapToInt(Integer::intValue).toArray();
        return res;
    }
}
