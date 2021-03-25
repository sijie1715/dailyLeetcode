package Solutions;

import java.util.HashMap;

public class P1577_NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers {
    public int numTriplets(int[] nums1, int[] nums2) {
        return count(nums1, nums2) + count(nums2, nums1);
    }

    private int count(int[] a, int[] b) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            HashMap<Long, Integer> map = new HashMap<>();
            // need long because a[i]<=10^5, a[i]^2<=10^10, >2^31
            long sq = (long) a[i] * a[i];
            for (long n : b) {
                if (sq % n == 0) res += map.getOrDefault(sq / n, 0);
                map.put(n, map.getOrDefault(n, 0) + 1);
            }
        }
        return res;
    }
}
