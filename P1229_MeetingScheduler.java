package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class P1229_MeetingScheduler {
    // 2 pointers
    public List<Integer> minAvailableDuration1(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int i1 = 0, i2 = 0;
        while (i1 < slots1.length && i2 < slots2.length) {
            int s = Math.max(slots1[i1][0], slots2[i2][0]);
            int e = Math.min(slots1[i1][1], slots2[i2][1]);
            if (s + duration <= e) {
                res = Arrays.asList(s, s + duration);
                return res;
            }
            if (slots1[i1][1] <= slots2[i2][1]) {
                i1++;
            } else {
                i2++;
            }
        }
        return res;
    }

    // PriorityQueue - put all slots in heap and compare each 2
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // need to avoid slots too short
        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) pq.offer(slot);
        }
        for (int[] slot : slots2) {
            if (slot[1] - slot[0] >= duration) pq.offer(slot);
        }
        while (pq.size() > 1) {
            int[] s1 = pq.poll();
            int[] s2 = pq.peek();
            if (s1[1] >= s2[0] + duration) {
                res = Arrays.asList(s2[0], s2[0] + duration);
                return res;
            }
        }
        return res;
    }
}
