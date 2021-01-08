package Solutions;

public class P1049_LastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        // divide stones into 2 groups
        // the closest diff between the 2 sums will be the answer
        // use dp[boolean] to record if sum is reachable
        boolean[] dp = new boolean[1501];
        dp[0] = true;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
            for (int i = sum; i >= stone; i--) {
                dp[i] |= dp[i - stone];
            }
        }
        for (int i = sum / 2; i >= 0; i--) {
            if (dp[i]) return sum - i - i;
        }
        return 9999;
    }
}
