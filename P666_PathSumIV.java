package Solutions;

import java.util.HashMap;
import java.util.Map;

public class P666_PathSumIV {
    public int pathSum(int[] nums) {
        // first 2 digits notes position level-pos-val
        Map<Integer, Integer> nodes = new HashMap<>();
        for (int num : nums) {
            int pos = num / 10;
            int val = num % 10;
            nodes.put(pos, val);
        }
        return getSum(nums[0] / 10, 0, nodes);
    }

    private int getSum(int num, int prevSum, Map<Integer, Integer> nodes) {
        if (!nodes.containsKey(num)) return 0;
        int level = num / 10, pos = num % 10;
        int val = nodes.get(num);
        int left = (level + 1) * 10 + pos * 2 - 1;
        int right = (level + 1) * 10 + pos * 2;

        int curSum = prevSum + val;

        if (!nodes.containsKey(left) && !nodes.containsKey(right)) return curSum;
        return getSum(left, curSum, nodes) + getSum(right, curSum, nodes);
    }
}
