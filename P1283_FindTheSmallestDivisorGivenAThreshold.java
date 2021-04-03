package Solutions;

public class P1283_FindTheSmallestDivisorGivenAThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1, r = 1000000;
        while (l < r) {
            int m = l + (r - l) / 2;
            int sum = 0;
            for (int num : nums) sum += (num + m - 1) / m;
            if (sum > threshold) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}
