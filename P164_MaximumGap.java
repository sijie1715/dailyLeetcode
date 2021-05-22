package Solutions;

import java.util.Arrays;

public class P164_MaximumGap {
    public int maximumGap(int[] nums) {
        int min, max, maxGap;
        int n = nums.length;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        maxGap = (int) Math.ceil((double) (max - min) / (n - 1));

        // buckets with interval = maxGap;
        int[] bucketMin = new int[n];
        int[] bucketMax = new int[n];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        for (int num : nums) {
            if (num == min || num == max) continue;
            // bucket index
            int idx = (num - min) / maxGap;
            bucketMin[idx] = Math.min(bucketMin[idx], num);
            bucketMax[idx] = Math.max(bucketMax[idx], num);
        }

        // maxGap will only happen between bucketMin[i] - bucketMax[i-1]
        int prev = min;
        for (int i = 0; i < n; i++) {
            // empty bucket
            if (bucketMin[i] == Integer.MAX_VALUE) continue;
            maxGap = Math.max(maxGap, bucketMin[i] - prev);
            prev = bucketMax[i];
        }
        // check last gap
        maxGap = Math.max(maxGap, max - prev);
        return maxGap;
    }
}
