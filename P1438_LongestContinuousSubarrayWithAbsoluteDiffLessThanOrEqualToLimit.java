package Solutions;

import java.util.ArrayDeque;
import java.util.TreeMap;

public class P1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public int longestSubarray1(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int i = 0, j;
        for (j = 0; j < nums.length; j++) {
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            if (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) - 1);
                if (map.get(nums[i]) == 0) map.remove(nums[i]);
                i++;
            }
        }
        return j - i;
    }

    public int longestSubarray2(int[] nums, int limit) {
        // 2 Deques to keep track of min and max in the window
        ArrayDeque<Integer> mind = new ArrayDeque<>();
        ArrayDeque<Integer> maxd = new ArrayDeque<>();
        int i = 0, j;
        for (j = 0; j < nums.length; j++) {
            // if nums[j]>last element, maxd.pollLast because last won't be the biggest in window
            // pollLast until nums[j]<=last; once last is polled then nums[j] will be remaining biggest
            // newly added will always be the next biggest in line
            while (!maxd.isEmpty() && nums[j] > maxd.peekLast()) maxd.pollLast();
            while (!mind.isEmpty() && nums[j] < mind.peekLast()) mind.pollLast();
            maxd.add(nums[j]);
            mind.add(nums[j]);
            // if over limit, move i
            // update deques if element out
            if (maxd.peek() - mind.peek() > limit) {
                if (maxd.peek() == nums[i]) maxd.poll();
                if (mind.peek() == nums[i]) mind.poll();
                i++;
            }
        }
        return j - i;
    }

}
