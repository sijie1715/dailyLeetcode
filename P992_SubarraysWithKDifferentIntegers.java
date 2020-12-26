package Solutions;

import java.util.HashMap;

public class P992_SubarraysWithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMost(A, K) - atMost(A, K - 1);
    }

    private int atMost(int[] nums, int k) {
        int res = 0, l = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int r = 0; r < nums.length; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            if (map.get(nums[r]) == 1) k--;
            while (k < 0) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) k++;
                l++;
            }
            res += (r - l + 1);
        }
        return res;
    }
}
