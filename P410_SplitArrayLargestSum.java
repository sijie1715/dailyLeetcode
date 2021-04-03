package Solutions;

public class P410_SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        long sum = 0;
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) return (int) sum;
        long l = max, r = sum;
        // need to check last position (l==r)
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (validM(mid, nums, m)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    private boolean validM(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;
        for (int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m) return false;
            }
        }
        return true;
    }
}
