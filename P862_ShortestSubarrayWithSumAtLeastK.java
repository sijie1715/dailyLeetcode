package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class P862_ShortestSubarrayWithSumAtLeastK {
    public int shortestSubarray(int[] A, int K) {
        // presum[j]-presum[i]=sum of A[i+1..j]
        // deque to track index of presum (keep smallest for max sum)
        int n = A.length;
        int[] presum = new int[n + 1];
        Deque<Integer> d = new ArrayDeque<>();
        int res = n + 1; // if res doesn't change return -1

        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + A[i];
        }
        for (int i = 0; i < n + 1; i++) {
            while (!d.isEmpty() && presum[i] - presum[d.peekFirst()] >= K) {
                res = Math.min(res, i - d.pollFirst());
            }
            // if presum[i] smaller than d.peekLast than replace
            // presum[i] will get larger sum with shorter length
            while (!d.isEmpty() && presum[i] < presum[d.peekLast()]) {
                d.pollLast();
            }
            d.add(i);
        }
        return res == n + 1 ? -1 : res;
    }
}
