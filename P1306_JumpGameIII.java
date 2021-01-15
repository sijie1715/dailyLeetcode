package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class P1306_JumpGameIII {
    // DFS
    public boolean canReach(int[] arr, int start) {
        if (start >= 0 && start < arr.length && arr[start] >= 0) {
            arr[start] = -arr[start];
            return arr[start] == 0 || canReach(arr, start + arr[start])
                    || canReach(arr, start - arr[start]);
        }
        return false;
    }

    //BFS
    public boolean canReach1(int[] arr, int start) {
        boolean[] seen = new boolean[arr.length];
        Deque<Integer> d = new ArrayDeque<>();
        d.add(start);

        while (!d.isEmpty()) {
            int curr = d.poll();
            if (arr[curr] == 0) return true;
            if (seen[curr]) continue;
            if (arr[curr] + curr < arr.length) d.add(curr + arr[curr]);
            if (curr - arr[curr] >= 0) d.add(curr - arr[curr]);
            seen[curr] = true;
        }
        return false;
    }
}
