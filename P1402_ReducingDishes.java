package Solutions;

import java.util.Arrays;

public class P1402_ReducingDishes {
    public int maxSatisfaction(int[] satisfaction) {
        // sort the array first and start adding from the best dish, keep running total
        // every time a dish is added, whole total is moved to 1 more time unit
        // as long as A[i]+total>0, adding A[i] will have positive change
        Arrays.sort(satisfaction);
        int total = 0, res = 0, n = satisfaction.length;
        for (int i = n - 1; i >= 0 && satisfaction[i] + total > 0; i--) {
            total += satisfaction[i];
            res += total;
        }
        return res;
    }
}
