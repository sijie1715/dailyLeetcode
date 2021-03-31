package Solutions;

public class P1537_GetTheMaximumScore {
    public int maxSum(int[] nums1, int[] nums2) {
        int i1 = 0, i2 = 0;
        long sum1 = 0, sum2 = 0, mod = (long) 1e9 + 7;
        while (i1 < nums1.length || i2 < nums2.length) {
            if (i1 < nums1.length && (i2 == nums2.length || nums1[i1] < nums2[i2])) {
                sum1 += nums1[i1++];
            } else if (i2 < nums2.length && (i1 == nums1.length || nums2[i2] < nums1[i1])) {
                sum2 += nums2[i2++];
            } else {
                sum1 = sum2 = Math.max(sum1, sum2) + nums1[i1];
                i1++;
                i2++;
            }
        }
        return (int) (Math.max(sum1, sum2) % mod);
    }
}
