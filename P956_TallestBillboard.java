package Solutions;

public class P956_TallestBillboard {
    public int tallestBillboard(int[] rods) {
        // dp[d] = x, d is the diff between 2 poles, x is the height of lower pole
        // for every rod:
        // [higher side] dp[d+rod]=dp[d]
        // [lower side] (rod>=d) dp[rod-d]=max(d[rod-d],dp[d]+d)
        // [lower side] (rod<d) dp[d-rod]=max(dp[d-rod],dp[d]+rod)
        // *** above terms might have same reference; use dp.clone() every iteration
        // *** need to initialize all dp[1..] to be negative
        // because dp[i..]=0 means a rod of length i was once reached
        // otherwise dp[0] will be sum because started with dp[sum]=0
        int[] dp = new int[5001];
        for (int d = 1; d <= 5000; d++) dp[d] = -5001;
        for (int rod : rods) {
            int[] curr = dp.clone();
            for (int d = 0; d + rod <= 5000; d++) {
                dp[d + rod] = Math.max(dp[d + rod], curr[d]);
                dp[Math.abs(d - rod)] = Math.max(dp[Math.abs(d - rod)], curr[d] + Math.min(rod, d));
            }
        }
        return dp[0];
    }
}
